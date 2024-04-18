package chap8;

import chap6.TheBeatles;

import java.nio.channels.GatheringByteChannel;

public class TestHolaMundoThread {

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(new HolaMundoThread("Pedro"));
        Thread t2 = new Thread(new HolaMundoThread("Pablo"));
        Thread t3 = new Thread(new HolaMundoThread("Juan"));

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println("Finalizo el programa");

    }

}
