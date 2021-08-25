package Thread;

/**
 * 创建线程的两种方式：继承Thread类，实现Runnable接口
 * 两者的区别：Thread是java对线程的抽象，Runnable是任务与业务逻辑的抽象
 */
public class ThreadCreateMethodDemo {
    static class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println("run - MyThread");
        }
    }

    static class MyRunnable implements Runnable{
        @Override
        public void run() {
            System.out.println("run - MyRunnable");
        }
    }

    public static void main(String[] args) {
        new MyThread().start();
        new Thread(new MyRunnable()).start();
    }
}
