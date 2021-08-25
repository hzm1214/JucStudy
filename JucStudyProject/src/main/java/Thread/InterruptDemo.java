package Thread;

public class InterruptDemo {
    static class MyThread implements Runnable{
        @Override
        public void run() {
            if ( 1 == 0 ) {
                while (!Thread.currentThread().isInterrupted()) {//判断线程中断标志位
                    System.out.println("MyThread running- interrupt flag :" + Thread.currentThread().isInterrupted());
                }
                System.out.println("MyThread - interrupt flag :" + Thread.currentThread().isInterrupted());//返回true
            }
            if (1 == 0) {
                while (!Thread.interrupted()) {//判断线程中断标志位，若为true,重设为false
                    System.out.println("MyThread running- interrupt flag :" + Thread.currentThread().isInterrupted());
                }
                System.out.println("MyThread - interrupt flag :" + Thread.currentThread().isInterrupted());//返回false
            }
            if ( 1 == 1) {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        Thread.sleep(200);//线程阻塞过程中中断线程，当检测到标志位为true时将抛出InterruptedException异常
                    } catch (InterruptedException e) {
                        System.out.println("MyThread - interrupt flag :" + Thread.currentThread().isInterrupted());//catch中会重新将标志位设置成false，因此返回false
                        Thread.currentThread().interrupt();//再去中断线程，此时while循环中检测到标志位为false,跳出循环
                    }
                }
                System.out.println("MyThread - end interrupt flag :" + Thread.currentThread().isInterrupted());
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new MyThread());
        thread.start();
        Thread.sleep(500);
        thread.interrupt();//中断线程，并非抢占式的中断，而是协作式，何时中断根据线程运行状态决定
    }
}
