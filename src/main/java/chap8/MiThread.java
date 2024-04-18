package chap8;

import java.io.OutputStream;

public class MiThread extends Thread {
    private String name;
    public MiThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i=0; i<5; i++) {
            System.out.println( name + " - " + i);
            //yield();
        }
    }
}
