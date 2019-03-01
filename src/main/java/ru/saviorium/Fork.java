package ru.saviorium;

public class Fork {
    private volatile Philosopher user;
    private volatile SpaghettiPiece piece;
    private long timeInUse;
    private long timeLastChange;
    private long timeStart;

    public Fork() {
        timerReset();
        timeStart = System.nanoTime();
    }

    public synchronized boolean take(Philosopher user, long maxWaitTimeMs) {
        long time = System.nanoTime();
        while(this.user != null) {
            if(System.nanoTime() - time > maxWaitTimeMs * 1000000) {
                return false;
            }
        }
        this.user = user;
        return true;
    }

    public synchronized void put(Philosopher user) throws ForkAccessViolation {
        if (this.user != user) throw new ForkAccessViolation();
        this.user = null;
    }

    public synchronized void use(SpaghettiPiece pieceToPick) {
        if(piece == null) {
            piece = pieceToPick;
            timerReset();
        } else {
            throw new IllegalStateException();
        }
    }

    public void stopUse(SpaghettiPiece pieceToPick) {
        if(piece == pieceToPick) {
            piece = null;
            timerAdd();
        } else {
            throw new IllegalArgumentException();
        }
    }

    public boolean checkUser(Philosopher user) {
        if(this.user == user) return true;
        else return false;
    }

    private void timerAdd() {
        timeInUse += System.nanoTime() - timeLastChange;
    }

    private void timerReset() {
        timeLastChange = System.nanoTime();
    }

    public float getUsagePercent() {
        long currentTime = System.nanoTime();
        return (float) 100 * timeInUse / (currentTime - timeStart);
    }
}
