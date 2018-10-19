package com.agoda.downloader;

import java.net.URL;

public interface Downloader {

    void download(URL url, String location, String fileName, String tempDirectory) throws Exception;
}
