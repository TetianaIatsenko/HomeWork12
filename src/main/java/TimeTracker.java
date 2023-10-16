import java.time.Duration;

public class TimeTracker {
    public void timeTrackerPrint(long millis){
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(millis);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("5 seconds have passed");
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
    public void workingTimePrinter(){
        Long startProgramMillis = System.currentTimeMillis();
        Thread thread = new Thread(() -> {
            while (true){
                Duration duration = Duration.ofMillis(System.currentTimeMillis() - startProgramMillis);
                System.out.println("Program is running " + duration.toHoursPart()
                        + " hours " + duration.toMinutesPart() + " minutes " + duration.toSecondsPart() + " seconds");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
}
