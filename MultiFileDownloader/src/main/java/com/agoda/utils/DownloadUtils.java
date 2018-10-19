package com.agoda.utils;

import java.io.*;
import java.net.URL;

/**
 * Created by mdnmcg on 6/25/18.
 */
public class DownloadUtils {
    public static void copyFromInputToOutputStream(InputStream input, OutputStream output, byte[] buffer)
            throws IOException {
        long count;
        int n;
        for (count = 0L; -1 != (n = input.read(buffer)); count += (long) n) {
            output.write(buffer, 0, n);
        }
    }

    public static String getTempDirByOS() throws IOException {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("mac") || os.contains("nix")) {
            return "/tmp/MultiDownloader/";
        }
        throw new IOException("Unsupported OS");
    }

    public static void cleanup(File tempFile, String tempDirectory) {
        System.out.println("Cleaning up " + tempFile.getPath() + " in folder " + tempDirectory);
        if (tempFile.exists()) {
            tempFile.delete();
        }
        new File(tempDirectory).delete();
    }

    public static FileOutputStream openOutputStream(File file) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new IOException("File \'" + file + "\' exists but is a directory");
            }

            if (!file.canWrite()) {
                throw new IOException("File \'" + file + "\' cannot be written to");
            }
        } else {
            File parent = file.getParentFile();
            if (parent != null && !parent.mkdirs() && !parent.isDirectory()) {
                throw new IOException("Directory \'" + parent + "\' could not be created");
            }
        }

        return new FileOutputStream(file);
    }


    public static String getFileNameFromURI(URL uri) {
        String[] segments = uri.getPath().split("/");
        return segments[segments.length - 1];
    }

}
