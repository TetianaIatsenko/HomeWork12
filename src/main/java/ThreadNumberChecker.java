public abstract class ThreadNumberChecker implements Runnable{
    private volatile NumberRow numberRow;
    public ThreadNumberChecker(NumberRow numberRow){
        this.numberRow = numberRow;
    }
    public NumberRow getNumberRow() {
        return numberRow;
    }

}
