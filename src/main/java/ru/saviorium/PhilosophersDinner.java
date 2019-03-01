package ru.saviorium;

public class PhilosophersDinner {
    public static void main(String[] args) {
        Table dinnerTable = new Table(5);
        while (true) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(dinnerTable);
        }
    }
}
