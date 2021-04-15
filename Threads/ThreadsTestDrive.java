class MyRunnable implements Runnable {
    public void run() {
        go();
    }

    public void go() {
        try {
            Thread.sleep(2000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        doMore();
    }
    
    public void doMore() {
        System.out.println("Top of the Stack!");
    }
}

class ThreadsTestDrive {
    public static void main(String[] args) {
        Runnable theJob = new MyRunnable();
        Thread t = new Thread(theJob);

        t.start();

        System.out.println("Back in Main!");
    }
}