package ru.job4j.net;

public class Args {

    private final String[] args;

    public Args(String[] args) {
        this.args = args;
    }

    public String link() {
        return args[0];
    }

    public String directory() {
        return args[1];
    }

    public String speedLimit() {
        return args[2];
    }

}
