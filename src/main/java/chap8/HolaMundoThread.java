package chap8;

public class HolaMundoThread implements Runnable {

    private String nombre;

    public HolaMundoThread(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {

        try {
            int x = (int) (Math.random()*5000);
            Thread.sleep(x);
            System.out.println("Soy: " + nombre + " (" + x + ")");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
