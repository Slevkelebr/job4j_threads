package ru.job4j.net;

import java.io.*;
import java.net.URL;

public class FileDownload implements Runnable {

    private static final long CONST_TIME = 1000;
    private static final int SIZE_BUFFER = 1024;

    private final String url;
    private final String directory;
    /**
     * speed limit KB.
     */
    private final int speedLimit;


    public FileDownload(String url, String directory, int speedLimit) {
        this.url = url;
        this.directory = directory;
        this.speedLimit = speedLimit;
    }

    @Override
    public void run() {
        download();
    }

    private void download() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream out = new FileOutputStream(directory)) {
            byte[] dataBuffer = new byte[SIZE_BUFFER];
            int bytesRead;
            int sumBytes = 0;
            long finishTime;
            long startTime = System.currentTimeMillis();
            while ((bytesRead = in.read(dataBuffer)) != -1) {
                sumBytes += bytesRead;
                out.write(dataBuffer, 0, bytesRead);
                if (sumBytes >= speedLimit) {
                    finishTime = System.currentTimeMillis() - startTime;
                    if (finishTime < CONST_TIME) {
                        try {
                            Thread.sleep(finishTime);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    sumBytes = 0;
                    startTime = System.currentTimeMillis();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
