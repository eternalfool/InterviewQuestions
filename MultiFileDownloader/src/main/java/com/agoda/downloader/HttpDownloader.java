package com.agoda.downloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

import static com.agoda.downloader.MultiFileDownloader.CHUNK_SIZE;
import static com.agoda.utils.DownloadUtils.copyFromInputToOutputStream;
import static com.agoda.utils.DownloadUtils.openOutputStream;

public class HttpDownloader implements Downloader {

    @Override
    public void download(URL url, String location, String fileName, String tempDirectory) throws Exception {
        String destination = location + "/" + fileName;
        String tempLocation = tempDirectory + "/" + fileName;
        File destinationFile = new File(destination);
        File tempFile = new File(tempLocation);

        InputStream in = url.openStream();
        FileOutputStream out = openOutputStream(tempFile);
        FileOutputStream destOut = openOutputStream(destinationFile);

        copyFromInputToOutputStream(in, out, new byte[CHUNK_SIZE]);
        copyFromInputToOutputStream(new FileInputStream(tempFile), destOut, new byte[CHUNK_SIZE]);

        System.out.println("Finished downloading " + fileName);
    }
}
