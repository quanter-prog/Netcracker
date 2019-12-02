package com.netcracker.lab;

/**
 * Класс - точка входа в программу.
 */
public class Main {

    private static final String PATH =
            "/home/quanter/Documents/Projects/Java/FirstLab/src/main/resources/persons.csv";

    /**
     * Точка входа в программу.
     * @param args возможные (но необязательные входные значения)
     */
    public static void main(final String[] args) {
        CSVLoader loader = new CSVLoader(PATH);
        System.out.println(loader.getRepository().toList().toString());
    }


}
