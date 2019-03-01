package ru.saviorium;

public class Bowl {
    public SpaghettiPiece getSpaghettiPiece(Fork leftFork, Fork rightFork, Philosopher toUser) {
        if(leftFork.checkUser(toUser) && rightFork.checkUser(toUser)) {
            return new SpaghettiPiece(leftFork, rightFork);
        } else {
            return null;
        }
    }
}
