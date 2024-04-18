package chap7;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.GregorianCalendar;

public class TestPersona {

    public static void main(String[] args) throws IOException {

        // Escribir objetos en un archivo

        FileOutputStream fos = new FileOutputStream("objetos.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        // Grabamos un Integer, un String y un Double
        oos.writeObject(123);
        oos.writeObject("Hola");
        oos.writeObject(3.14);

        // Grabamos un GregorianCalendar y una Persona !!!
        oos.writeObject(new GregorianCalendar(2019, 5, 28));
        oos.writeObject(new Persona("Pedro", 25));

        oos.close();
        fos.close();

        // Leer objetos desde un archivo

        FileInputStream fis = new FileInputStream("objetos.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);

        try {
            Object x;
            while ( (x=ois.readObject() ) != null ) {
                System.out.println(x);
            }
        } catch (EOFException | ClassNotFoundException e) {
            System.out.println("-- Fin del archivo --");
        }

        ois.close();
        fis.close();

        // Readers y Writers

        FileInputStream fis2 = new FileInputStream("chino.txt");
        Reader r = new InputStreamReader(fis2, StandardCharsets.UTF_8);

        int c;
        while( (c=r.read()) >= 0) {
            System.out.print((char)c);
        }

        r.close();

    }

}
