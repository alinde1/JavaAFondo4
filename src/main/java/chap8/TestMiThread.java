package chap8;

public class TestMiThread {

    public static void main(String[] args) {

        MiThread t1 = new MiThread("Pablo");
        MiThread t2 = new MiThread("Pedro");

        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.MIN_PRIORITY);

        t1.start();
        t2.start();

    }

}
