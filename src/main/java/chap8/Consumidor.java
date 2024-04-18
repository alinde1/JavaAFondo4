package chap8;

import chap6.ManyToOne;

import javax.management.relation.RelationNotFoundException;

public class Consumidor extends Thread {
    private Monitor buff;
    private int n;
    private int sleep;

    public Consumidor(Monitor b, int n, int s) {
        this.buff = b;
        this.n = n;
        this.sleep = s;
    }

    @Override
    public void run() {
        try {
            char c;
            for (int i=0; i<n; i++) {
                c = buff.sacar();
                System.out.println("Consumi: " + c);
                sleep( (int) (Math.random()*sleep));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
