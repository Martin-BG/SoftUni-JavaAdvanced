package Lesson09AsynchronousProgramming.Lab;

public class Pr01SingleThread {

    public static void main(String[] args) {
        Runnable task = () -> {
            for (int i = 1; i <= 10; i++) {
                System.out.print(i + " ");
            }
        };

        Thread thread = new Thread(task);
        thread.start();
        //System.exit(1);
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}