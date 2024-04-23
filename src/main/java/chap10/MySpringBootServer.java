package chap10;

import chap6.MySpring;

import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class MySpringBootServer {
    // Estructura de datos que vincula paths con métodos
    private static HashMap<String, TargetMethod> methods = null;

    static {
        methods = new HashMap<String, TargetMethod>();
    }

    public void registerComponent(Class<?> clazz) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        try {
            //Creamos la instancia de clazz
            Object target = MySpring.getObject(clazz);

            // Obtenemos los métodos de clazz
            Method[] mtds = clazz.getDeclaredMethods();

            // Iteramos los métodos para ver cuales son servicios
            for (Method m : mtds) {
                // Obtenemos la annotation @RequestMapping
                RequestMapping annPath = m.getAnnotation(RequestMapping.class);
                // Si tiene la annotation entonces es un servicio
                if ( annPath != null ) {
                    // Obtenemos el path (la key)
                    String key = annPath.value();

                    // Agregamos la entrada al hashMap
                    methods.put(key, new TargetMethod(target, m));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void runServer(int port) throws Exception{
        ServerSocket ss = new ServerSocket(port);
        while (true) {
            try {
                // Se conecta un cliente
                Socket s = ss.accept();

                // Instanciamos un thread para atender al cliente
                MySpringBootServerThread thread;
                thread = new MySpringBootServerThread(s, methods);

                // Ponemos a correr el thread
                thread.start();

            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }

        }
    }

    public void getMap() {
        System.out.println(methods);
    }

}
