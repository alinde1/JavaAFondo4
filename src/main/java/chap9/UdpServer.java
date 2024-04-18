package chap9;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpServer {

    public static void main(String[] args) throws IOException {

        // Creo el socket
        DatagramSocket socket = new DatagramSocket(5432);

        while( true ) {

            System.out.println("Esperando conexión...");

            // buffer para recibir el nombre del cliente
            byte[] buffer = new byte[256];

            // recibo el nombre del cliente
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);

            System.out.println("Conexión recibida !");

            // preparo el saludo para enviar
            String nombre = new String(packet.getData());

            String saludo = "Hola Mundo (" + nombre + ") _ "
                    + System.currentTimeMillis();
            System.out.println("Voy a enviar: [" + saludo + "]...");

            // buffer para enviar saludo
            byte[] respuesta = saludo.getBytes();

            // envio el saludo
            InetAddress address = packet.getAddress();
            packet = new DatagramPacket(respuesta,respuesta.length,address,packet.getPort());

            socket.send(packet);
            System.out.println("Saludo enviado !!");

        }

    }

}
