package chap10;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MySpringBootClient {
    private String ip;
    private int port;

    public MySpringBootClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public Response send(Request request) {
        Socket socket = null;

        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;

        try {

            socket = new Socket(ip,port);
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());

            // Enviamos el request
            oos.writeObject(request);

            // Recibimos el response y lo retornamos
            return (Response) ois.readObject();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {

                if (ois!=null) ois.close();
                if(oos!=null) oos.close();
                if(socket!=null) socket.close();

            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RuntimeException(e2);
            }
        }
    }
}
