package com.agoda.downloader;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.net.URL;

import static com.agoda.downloader.MultiFileDownloader.CHUNK_SIZE;
import static com.agoda.utils.DownloadUtils.copyFromInputToOutputStream;
import static com.agoda.utils.DownloadUtils.openOutputStream;

public class FTPDownloader implements Downloader {

    private FTPClient ftp = null;

    FTPDownloader() {
    }

    @Override
    public void download(URL url, String location, String fileName, String tempDirectory) throws IOException {
        String destination = location + "/" + fileName;
        String tempLocation = tempDirectory + "/" + fileName;
        File destinationFile = new File(destination);
        File tempFile = new File(tempLocation);
        ftp = new FTPClient();
        ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
        int reply;
        ftp.connect(url.getHost());
        reply = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
            throw new RuntimeException("Exception in connecting to FTP Server");
        }
        // This is for the specific ftp server ftp://speedtest.tele2.net/
        String user = "anonymous";
        String pwd = "";
        ftp.login(user, pwd);
        ftp.setFileType(FTP.BINARY_FILE_TYPE);
        try (FileOutputStream fos = openOutputStream(tempFile)) {
            this.ftp.retrieveFile(url.getPath(), fos);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        FileOutputStream destOut = openOutputStream(destinationFile);
        copyFromInputToOutputStream(new FileInputStream(tempFile), destOut, new byte[CHUNK_SIZE]);
    }
}
