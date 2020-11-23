package ru.job4j.net;

import java.nio.file.Paths;

public class Args {

    private final String[] args;

    public Args(String[] args) {
        this.args = args;
    }

    public static boolean isNumeric(String strNum) {
        return strNum.matches("\\d+(\\.\\d+)?");
    }

    /**
     * В методе проверяем валидность данных.
     * @return true если все данные валидны иначе false.
     */
    public boolean valid() throws NotValidDataException {
        boolean result = true;
        if (args.length != 3) {
            throw new  NotValidDataException();
        }
        if (!link().contains("https://") | link().contains("http://")) {
           result = false;
           System.out.println("Invalid url, without https or http " + link());
        }
        if (!Paths.get(directory()).isAbsolute()) {
            result = false;
            System.out.println("Directory " + directory() + " is not correct");
        }
        if (!isNumeric(speedLimit())) {
            result = false;
            System.out.println(speedLimit() + " is not a number");
        }
        if (!result) {
            throw  new NotValidDataException();
        }
        return result;
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
