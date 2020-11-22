package ru.job4j.net;

import java.io.*;
import java.net.URL;

public class FileDownloadTest {

    private static final long CONST_TIME = 1000;
    private static final int SIZE_BUFFER = 1024;

    public static void main(String[] args) {
        int speedLimit = 2500;
        String file = "https://github.com/ik87/TheatreSquare/archive/master.zip";
        try (BufferedInputStream in = new BufferedInputStream(new URL(file).openStream());
             FileOutputStream out = new FileOutputStream("pom_s.xml")) {
            byte[] dataBuffer = new byte[SIZE_BUFFER];
            int bytesRead;
            int sumBytes = 0;
            long startTime = System.currentTimeMillis();
            long finishTime;
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
