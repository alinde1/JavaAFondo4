package chap10;

public class MyServer {

    public static void main(String[] args) throws Exception {

        MySpringBootServer server = new MySpringBootServer();

        // Registramos los componentes
        server.registerComponent(DemoComponent.class);
        server.registerComponent(CalcComponent.class);

        // Ejecutamos el server indicando el puerto donde
        // queremos que atienda
        int port = 5432;
        server.runServer(port);

    }

}
