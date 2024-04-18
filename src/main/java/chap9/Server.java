package chap9;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {

        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;

        // Instanciamos ServerSocket indicando en que puerto atenderá
        ServerSocket serverSocket = new ServerSocket(5432);
        Socket socket = null;

        while (true) {
            try {

                // Bloqueante: Sólo pasamos cuando llega un cliente
                socket = serverSocket.accept();

                // Información en la consola
                String ip = socket.getInetAddress().toString();
                System.out.println("Se conectaron desde la IP: " + ip);

                // Enmascaramos la entrada y salida de bytes
                ois = new ObjectInputStream(socket.getInputStream());
                oos = new ObjectOutputStream(socket.getOutputStream());

                // Leemos el nombre que envía el cliente
                String nom = (String) ois.readObject();

                // Confeccionamos la salida que le vamos a enviar
                long ts = System.currentTimeMillis();
                String saludo = "Hola Mundo, " + nom + " (" + ts + ")";

                // Se lo enviamos al cliente
                oos.writeObject(saludo);
                System.out.println("Saludo enviado...");

            } catch (Exception ex) {
                ex.printStackTrace();
                throw new RuntimeException(ex);
            } finally {
                if ( oos != null ) oos.close();
                if ( ois != null ) ois.close();
                if ( socket != null ) socket.close();
                System.out.println("Conexión cerrada!");
            }
        }

    }

}
