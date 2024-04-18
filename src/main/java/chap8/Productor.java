package chap8;

public class Productor extends Thread {
    private Monitor buff;
    private int n;
    private int sleep;

    public Productor(Monitor b, int n, int s) {
        // el monitor
        this.buff = b;

        // cuantos caracteres debe producir
        this.n = n;

        // cuanto tiempo dormir entre carácter y carácter
        this.sleep = s;
    }

    @Override
    public void run() {
        try {
            char c;
            for (int i=0; i<n; i++ ) {
                c = (char) ('A' + i);
                buff.poner(c);
                System.out.println("Produje: " + c);
                sleep( (int) (Math.random()*sleep));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }
}
