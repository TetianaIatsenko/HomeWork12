import java.util.SortedMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class NumberRow {
    private int counter;
    private int maxNumber;
    private int flagFizz;
    private int flagBuzz;
    private int flagFizzBuzz;

    private ConcurrentLinkedQueue<String> queuePrinter;

    public NumberRow(int maxNumber){
        this.counter = 1;
        this.flagBuzz = 0;
        this.flagFizz = 0;
        this.flagFizzBuzz = 0;
        if(maxNumber > 0)
            this.maxNumber = maxNumber;
        else
            throw new IllegalArgumentException("Number must be > 0");
        this.queuePrinter = new ConcurrentLinkedQueue<>();
    }

    public void fizz(){
        while (counter <= maxNumber) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (this){
                if(this.counter % 3 == 0){
                    counter++;
                    this.queuePrinter.add("fizz");
                    notifyAll();
                }else {
                    flagFizz = counter;
                    checkNumber();
                }
            }
        }
    }

    public void buzz(){
        while (counter <= maxNumber){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (this){
                if(this.counter % 5 == 0){
                    counter++;
                    this.queuePrinter.add("buzz");
                    notifyAll();
                }
                else {
                    flagBuzz = counter;
                    checkNumber();
                }
            }
        }
    }

    public void fizzbuzz(){
        while (counter <= maxNumber) {
            synchronized (this) {
                if (this.counter % 3 == 0 && this.counter % 5 == 0) {
                    counter++;
                    this.queuePrinter.add("fizzbuzz");
                notifyAll();
                }
                else {
                    flagFizzBuzz = counter;
                    checkNumber();
                }
            }
        }
    }

    public void printNumbers(){
        synchronized (this) {
            do{
                if (this.queuePrinter.isEmpty() && counter <= maxNumber) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else
                    while (!this.queuePrinter.isEmpty()) {
                        System.out.print(queuePrinter.poll() + ", ");
                    }
            }while (counter<=maxNumber || !queuePrinter.isEmpty());
        }
    }
    private synchronized void checkNumber(){
        if (flagFizzBuzz == flagBuzz && flagFizzBuzz == flagFizz){
            this.queuePrinter.add(String.valueOf(flagBuzz));
            flagFizzBuzz = 0;
            flagBuzz = 0;
            flagFizz = 0;
            counter++;
            notifyAll();
        }
    }
}
