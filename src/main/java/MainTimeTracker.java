public class MainTimeTracker {
    public static void main(String[] args) {
        TimeTracker timeTracker = new TimeTracker();
        timeTracker.timeTrackerPrint(5000);
        timeTracker.workingTimePrinter();
        try {
            Thread.sleep(10000);
        }catch (InterruptedException ex){
            //
        }
    }
}
