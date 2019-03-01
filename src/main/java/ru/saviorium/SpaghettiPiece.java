package ru.saviorium;

import java.util.concurrent.ThreadLocalRandom;

public class SpaghettiPiece {

    public SpaghettiPiece(Fork leftFork, Fork rightFork) {
        leftFork.use(this);
        rightFork.use(this);
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        leftFork.stopUse(this);
        rightFork.stopUse(this);
    }
}
