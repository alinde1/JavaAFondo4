package chap8;

public class Monitor {
    private char[] buff = null;
    private int tope = 0;
    private boolean lleno = false;
    private boolean vacio = true;

    public Monitor(int capacidad) {
        buff = new char[capacidad];
    }

    public synchronized void poner(char c) throws Exception {
        // Mientras el buffer esté lleno nos bloqueamos
        // para que el consumidor pueda consumir algún carácter
        while (lleno) {
            wait();
        }

        // sección crítica
        buff[tope++] = c;
        vacio = false;
        lleno = tope >= buff.length;
        notifyAll();
    }

    public synchronized char sacar() throws Exception {
        // Mientras el buffer esté vacío nos bloqueamos para
        // que el productor pueda producir un carácter
        while (vacio) {
            wait();
        }

        // sección crítica
        char c = buff[--tope];
        lleno = false;
        vacio = tope == 0;
        notifyAll();
        return c;
    }
}
