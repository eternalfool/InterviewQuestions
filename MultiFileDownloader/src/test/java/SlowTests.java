import com.agoda.downloader.MultiFileDownloader;
import com.agoda.utils.DownloadUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.fail;

class SlowTests extends BaseTest{


    @Test
    void testBigFileDownload() throws Exception {
        String folderName = getTestDir() + "downloads/";
        URL url = new URL("http://releases.ubuntu.com/18.04/ubuntu-18.04-desktop-amd64.iso?" +
                "_ga=2.179276324.1862745394.1530355504-229121651.1530355504");
        String fileName = DownloadUtils.getFileNameFromURI(url);
        File file = new File(folderName + fileName);
        MultiFileDownloader fileDownloader = new MultiFileDownloader();
        fileDownloader.download(url, folderName, fileName);
        assert file.exists();
    }

    @Test
    void testMultiDownload() throws Exception {
        String folderName = getTestDir() + "downloads/";
        String[] urls = new String[]{"https://www.sample-videos.com/video/mp4/240/big_buck_bunny_240p_30mb.mp4",
                                     " https://www.sample-videos.com/video/mp4/720/big_buck_bunny_720p_5mb.mp4"};
        MultiFileDownloader multiFileDownloader = new MultiFileDownloader();
        multiFileDownloader.setRetryCount(1);
        multiFileDownloader.processMultipleUrls(urls, folderName);
        boolean downloaded = multiFileDownloader.getCountDownLatch().await(10000, TimeUnit.SECONDS);
        if (!downloaded){
            fail("File not downloaded");
        }
        String fileName1 = DownloadUtils.getFileNameFromURI(new URL(urls[0]));
        String fileName2 = DownloadUtils.getFileNameFromURI(new URL(urls[1]));
        File file1 = new File(folderName + fileName1);
        File file2 = new File(folderName + fileName2);
        assert file1.exists();
        assert file1.length() == 31539436;
        assert file2.exists();
        assert file2.length() == 5253880;
    }

    @Test
    void testMultiProtocolDownload() throws Exception {
        String folderName = getTestDir() + "downloads/";
        // ftp://speedtest.tele2.net/2MB.zip
        String[] urls = new String[]{"https://www.sample-videos.com/video/mp4/240/big_buck_bunny_240p_30mb.mp4",
                                     "ftp://speedtest.tele2.net/2MB.zip"};
        MultiFileDownloader multiFileDownloader = new MultiFileDownloader();
        multiFileDownloader.setRetryCount(1);
        multiFileDownloader.processMultipleUrls(urls, folderName);
        boolean downloaded = multiFileDownloader.getCountDownLatch().await(1000, TimeUnit.SECONDS);
        if (!downloaded){
            fail("File not downloaded");
        }
        String fileName1 = DownloadUtils.getFileNameFromURI(new URL(urls[0]));
        String fileName2 = DownloadUtils.getFileNameFromURI(new URL(urls[1]));
        File file1 = new File(folderName + fileName1);
        File file2 = new File(folderName + fileName2);
        assert file1.exists();
        assert file1.length() == 31539436;
        assert file2.exists();
        System.out.println(file1.length());
        System.out.println(file2.length());
        assert file2.length() == 2097152;
    }

}
