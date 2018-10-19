package com.agoda.downloader;

import com.agoda.utils.DownloadUtils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.agoda.utils.DownloadUtils.cleanup;
import static com.agoda.utils.DownloadUtils.getFileNameFromURI;


/**
 * This class takes in a list of URLs of files and downloads then separate threads.
 * The downloads initially go to a temp location before being copied to a final location.
 */
public class MultiFileDownloader {

    private ExecutorService executor;
    private final int PARALLEL_DOWNLOAD_THREAD_COUNT = 10;
    static final int CHUNK_SIZE = 4096;
    private Set<String> fileNames = new HashSet<>();
    private int RETRY_COUNT = 3;
    /**
     * This counter keeps track of downloads remaining.
     * Initial count is number of urls and final count is 0.
     */
    private CountDownLatch countDownLatch = new CountDownLatch(1);

    public MultiFileDownloader() {
        executor = Executors.newFixedThreadPool(PARALLEL_DOWNLOAD_THREAD_COUNT);
    }


    public void processMultipleUrls(String[] urls, String location) {
        countDownLatch = new CountDownLatch(urls.length);
        for (String urlString : urls) {
            URL url;
            try {
                url = new URL(urlString);
            } catch (MalformedURLException e) {
                System.out.println(String.format("Invalid URL: %s skipping", urlString));
                continue;
            }
            String fileName = getFileNameFromURI(url);
            // This is to handle duplicate file names.
            while (fileNames.contains(fileName)) {
                fileName += "_";
            }
            fileNames.add(fileName);
            System.out.println(String.format("URL: %s File Name: %s Downloading to location: %s",
                    urlString, fileName, location));
            URL finalUrl = url;
            String finalFileName = fileName;
            executor.submit(() -> {
                try {
                    runWithRetries(RETRY_COUNT, new DownloadTask(finalUrl, location, finalFileName));
                } catch (Exception e) {
                    System.out.println(String.format("Could not download file: %s", e.getMessage()));
                }
            });
        }
        executor.shutdown();
    }


    public void download(URL url, String location, String fileName) throws Exception {
        String tempDirectory = DownloadUtils.getTempDirByOS() + url.getPath().hashCode();
        try {
            Downloader downloader = getDownloader(url);
            downloader.download(url, location, fileName, tempDirectory);
        } catch (Exception e) {
            System.out.println(String.format("Failed to download: %s and filename: %s", url.getPath(), fileName));
            throw e;
        } finally {
            cleanup(new File(tempDirectory + "/" + fileName), tempDirectory);
            countDownLatch.countDown();
        }
        System.out.println(String.format("Finished processing url: %s and filename: %s", url.getHost() + url.getPath(), fileName));

    }

    private Downloader getDownloader(URL url) {
        switch (url.getProtocol()) {
            case "http":
                return new HttpDownloader();
            case "https":
                return new HttpDownloader();
            case "ftp":
                return new FTPDownloader();
            default:
                throw new UnsupportedOperationException("Unsupported protocol" + url.getProtocol());
        }
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    class DownloadTask implements ThrowingTask {
        URL url;
        String location;
        String fileName;

        DownloadTask(URL url, String location, String fileName) {
            this.url = url;
            this.location = location;
            this.fileName = fileName;
        }


        @Override
        public void run() throws Exception {
            download(url, location, fileName);
        }
    }

    private static void runWithRetries(int maxRetries, ThrowingTask t) {
        int count = 0;
        while (count < maxRetries) {
            try {
                t.run();
                return;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Download failed, retrying..");
                if (++count >= maxRetries)
                    return;
            }
        }
    }

    public void setRetryCount(int retryCount) {
        this.RETRY_COUNT = retryCount;
    }
}
