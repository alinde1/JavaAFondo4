package chap9;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {

        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        Socket socket = null;

        try {

            // Instanciamos el server con la IP y el PORT
            socket = new Socket("127.0.0.1", 5432);
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());

            // Enviamos un nombre
            oos.writeObject("Pepito");

            // Recibimos la respouesta (el saludo personalizado)
            String ret = (String) ois.readObject();

            // Mostramos la respuesta que nos envió el server
            System.out.println(ret);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ( ois != null ) ois.close();
            if ( oos != null ) oos.close();
            if ( socket != null ) socket.close();
        }

    }

}
