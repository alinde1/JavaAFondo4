package chap10;

public class Main {

    public static void main(String[] args) {

        String host = "localhost";
        int port = 5432;
        MySpringBootClient client = new MySpringBootClient(host,port);

        // Invocamos al servicio holaMundo, pasándole: Pablo
        Request request = new Request("/demo/holamundo", "Pablo");

        // Enviamos el request y obtenemos el response
        Response response = client.send(request);

        // Mostramos el resultado: un HolaMundo personalizado
        System.out.println(response.get(0)); // HolaMundo, Pablo

        // Invocamos al servicio sumar, le pasamos 2 y 3
        request = new Request("/calc/sumar", 2, 3);
        response = client.send(request);

        // Mostramos el resultado obtenido
        System.out.println(response.get(0)); // SALIDA: 5

        // Invocamos al servicio dividir y contemplamos el error
        request = new Request("/calc/dividir", 5, 0);
        response = client.send(request);

        if (response.getErrorCode() != 0) {
            System.out.println("ERROR: " + response.getErrorMessage());
        } else {
            // Mostramos el resultado obtenido
            System.out.println(response.get(0));
        }

    }

}
