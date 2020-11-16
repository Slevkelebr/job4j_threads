package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.print("\r load: " + "\\");
                Thread.sleep(500);
                System.out.print("\r load: " + "|");
                Thread.sleep(500);
                System.out.print("\r load: " + "/");
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread threadProgress = new Thread(new ConsoleProgress());
        threadProgress.start();
        Thread.sleep(4000);
        threadProgress.interrupt();
    }
}
