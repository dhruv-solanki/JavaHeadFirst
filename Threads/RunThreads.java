public class RunThreads implements Runnable {
    public void run() {
        for (int i=0; i<10; i++) {
            String threadName = Thread.currentThread().getName();

            System.out.println(threadName + " is running.");
        }
    }
    
    public static void main(String[] args) {
        RunThreads runner = new RunThreads();

        Thread alpha = new Thread(runner);
        Thread beta = new Thread(runner);

        alpha.setName("Alpha Thread");
        beta.setName("Beta Thread");

        alpha.start();
        beta.start();
    }
}