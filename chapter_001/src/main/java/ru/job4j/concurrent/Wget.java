package ru.job4j.concurrent;

public class Wget {
    public static void main(String[] args) {
        Thread threadLoading = new Thread(
                () -> {
                    try {
                        System.out.println("Start loading ... ");
                        for (int index = 0; index <= 100; index++) {
                            System.out.print("\rLoading : " + index  + "%");
                            Thread.sleep(1000);
                        }
                        System.out.println("\nLoading completed");
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
        );
        threadLoading.start();
    }
}
