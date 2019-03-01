package ru.saviorium;

import java.util.LinkedList;
import java.util.List;

public class Philosopher implements Runnable{
    private final String name;
    private final Fork leftFork;
    private final Fork rightFork;
    private final Bowl spaghetti;
    private List<SpaghettiPiece> eatenPieces = new LinkedList<>();
    private long thinkingTime;

    public Philosopher(Fork leftFork, Fork rightFork, Bowl spaghetti, String name) {
        this.name = name;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.spaghetti = spaghetti;
    }

    public void run() {
        while (true) {
            if (takeFork(leftFork)) {
                setState("holding left fork");
                if (takeFork(rightFork)) {
                    setState("holding both forks");
                    eat();
                    putFork(rightFork);
                    setState("holding left fork");
                }
                putFork(leftFork);
                setState("holding no forks");
            }
            think(100);
        }
    }

    private boolean takeFork(Fork fork) {
        return fork.take(this, 100);
    }

    private void putFork(Fork fork) {
        try {
            fork.put(this);
        } catch (ForkAccessViolation e) {
            e.printStackTrace();
        }
    }

    void think(long timeMs) {
        try {
            setState("thinking for " + timeMs + "ms");
            Thread.sleep(timeMs);
            thinkingTime += timeMs;
            setState("done thinking");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void eat() {
        setState("eating");
        int piecesToPick = 50;
        for(int i = 0; i < piecesToPick; i++) {
            eatenPieces.add(spaghetti.getSpaghettiPiece(leftFork, rightFork, this));
        }
        setState("done eating");
    }

    private void setState(String message) {
        System.out.println("Philosopher-" + name + "-" + message);
    }

    @Override
    public String toString() {
        return "eaten " + eatenPieces.size() + " spaghetti pieces, was thinking for " + thinkingTime + " ms";
    }
}
