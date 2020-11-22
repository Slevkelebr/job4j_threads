package ru.job4j.net;

public class Start {
    public static void main(String[] args) {
        Args arg = new Args(args);
        FileDownload download = new FileDownload(arg.link(), arg.directory(), Integer.parseInt(arg.speedLimit()));
        Thread threadDownload = new Thread(download);
        threadDownload.start();
    }
}
