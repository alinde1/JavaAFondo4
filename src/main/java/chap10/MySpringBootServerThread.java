package chap10;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.HashMap;

public class MySpringBootServerThread extends Thread{
    private Socket socket = null;
    private HashMap<String, TargetMethod> methods = null;

    public MySpringBootServerThread(
            Socket s
            ,HashMap<String, TargetMethod> methods
    ) {
        this.socket = s;
        this.methods = methods;
    }

    @Override
    public void run() {

        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;

        String ip = socket.getInetAddress().toString();
        System.out.println("Se conectaron desde la IP: " + ip);

        try {

            ois = new ObjectInputStream(socket.getInputStream());
            oos = new ObjectOutputStream(socket.getOutputStream());

            // Obtenemos el request y el path
            Request request = (Request) ois.readObject();
            String path = request.getPath();

            // Con el path obtenemos el método y el objeto target
            Method mtd = methods.get(path).getMethod();
            Object tgt = methods.get(path).getTarget();

            // Invocamos al método sobre el objeto target
            Response response = (Response) mtd.invoke(tgt, request);

            // Enviamos el response
            oos.writeObject(response);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {
                if (oos!=null) oos.close();
                if (ois!=null) ois.close();
                if (socket!=null) socket.close();
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RuntimeException(e2);
            }
        }
    }

}
