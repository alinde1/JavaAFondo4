package chap9;

import java.io.IOException;
import java.net.*;

public class UdpClient {

    public static void main(String[] args) throws IOException {

        // instancio un DatagramSocket
        DatagramSocket datagramSocket = new DatagramSocket();

        // buffer con info a enviar
        byte[] mensaje = "Pepito".getBytes();

        // ip del server
        byte[] ip = { 127,0,0,1 };
        InetAddress address = InetAddress.getByAddress(ip);

        int port = 5432;

        // paquete de información a enviar, ip + port (5432)
        DatagramPacket packet = new DatagramPacket(mensaje
                ,mensaje.length
                ,address
                ,port);

        // envio el paquete
        datagramSocket.send(packet);

        // buffer para recibir la respuesta
        byte[] respuesta = new byte[256];
        packet = new DatagramPacket(respuesta, respuesta.length, address, 5432);

        // Recibo el saludo
        datagramSocket.receive(packet);

        // Muestro el saludo
        String saludo = new String(packet.getData());

        System.out.println(saludo);
        datagramSocket.close();

    }

}
