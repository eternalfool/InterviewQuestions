import com.agoda.downloader.MultiFileDownloader;
import com.agoda.utils.DownloadUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;


class Tests extends BaseTest {


    @Test
    void testCleanup() {
        String folderName = getTestDir() + "cleanup/";
        String fileName = folderName + "file";
        File folder = new File(folderName);
        new File(folderName).mkdir();
        File file = new File(folderName + "file");
        DownloadUtils.cleanup(folder, fileName);
        assert !file.exists();
    }

    @Test
    void testHTTPSDownload() throws Exception {
        String folderName = getTestDir() + "downloads/";
        URL url = new URL("https://www.sample-videos.com/video/mp4/240/big_buck_bunny_240p_30mb.mp4");
        String fileName = DownloadUtils.getFileNameFromURI(url);
        File file = new File(folderName + fileName);
        MultiFileDownloader fileDownloader = new MultiFileDownloader();
        fileDownloader.download(url, folderName, fileName);
        assert file.exists();
        assert file.length() == 31539436;
    }

    @Test
    void testFTPFileDownload() throws Exception {
        String folderName = getTestDir() + "downloads/";
        URL url = new URL("ftp://speedtest.tele2.net/2MB.zip");
        String fileName = DownloadUtils.getFileNameFromURI(url);
        File file = new File(folderName + fileName);
        MultiFileDownloader fileDownloader = new MultiFileDownloader();
        fileDownloader.download(url, folderName, fileName);
        assert file.exists();
        assert file.length() == 2097152;
    }

    @Test
    void testGetFilenameFromURL() throws MalformedURLException {
        assert DownloadUtils.getFileNameFromURI(new URL("ftp://speedtest.tele2.net/2MB.zip")).equals("2MB.zip");
        assert DownloadUtils.getFileNameFromURI(new URL(
                "https://www.sample-videos.com/video/mp4/240/big_buck_bunny_240p_30mb.mp4")).equals("big_buck_bunny_240p_30mb.mp4");
    }

}
