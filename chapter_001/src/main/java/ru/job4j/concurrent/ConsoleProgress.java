package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {

    @Override
    public void run() {
        try {
            String[] progress = {"\\", "|", "/"};
            int index = 0;
            while (!Thread.currentThread().isInterrupted()) {
                System.out.print("\r load: " + progress[index]);
                Thread.sleep(500);
                index++;
                if (index > 2) {
                    index = 0;
                }
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
