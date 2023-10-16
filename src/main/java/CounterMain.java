public class CounterMain {
    public static void main(String[] args) {
        NumberRow numberRow = new NumberRow(30);
        Thread threadA = new Thread(new ThreadNumberChecker(numberRow) {
            @Override
            public void run() {
                super.getNumberRow().fizz();
            }
        });
        Thread threadB = new Thread(new ThreadNumberChecker(numberRow) {
            @Override
            public void run() {
                super.getNumberRow().buzz();
            }
        });
        Thread threadC = new Thread(new ThreadNumberChecker(numberRow) {
            @Override
            public void run() {
                super.getNumberRow().fizzbuzz();
            }
        });
        Thread threadD = new Thread(new ThreadNumberChecker(numberRow) {
            @Override
            public void run() {
                super.getNumberRow().printNumbers();
            }
        });
        threadC.start();
        threadA.start();
        threadB.start();
        threadD.start();

    }
}
