package ru.saviorium;

import java.util.Arrays;

public class Table {
    private final int PHI_NUM;
    private final Philosopher[] philosophers;
    private final Fork[] forks;
    private final Bowl[] bowls;

    public Table(int PHI_NUM) {
        this.PHI_NUM = PHI_NUM;
        forks = new Fork[PHI_NUM];
        bowls = new Bowl[PHI_NUM];
        philosophers = new Philosopher[PHI_NUM];
        for(int i = 0; i < PHI_NUM; i++) {
            forks[i] = new Fork();
            bowls[i] = new Bowl();
        }
        for(int i = 0; i < PHI_NUM; i++) {
            philosophers[i] = new Philosopher(forks[i], forks[(i+1)%PHI_NUM], bowls[i], String.valueOf(i));
            Thread t = new Thread(philosophers[i]);
            t.start();
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < PHI_NUM; i++) {
            str.append("Philosopher ").append(i).append(": ").append(philosophers[i]).append("\n");
            str.append("Fork ").append(i).append(": Usage ").append(String.format("%.2f",forks[i].getUsagePercent())).append("%\n");
        }
        return str.toString();
    }
}
