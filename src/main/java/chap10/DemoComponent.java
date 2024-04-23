package chap10;

import chap6.Component;

@Component
public class DemoComponent {
    @RequestMapping("/demo/holamundo")
    public Response holaMundo(Request request) {
        // En el request nos envían un nombre
        String nombre = (String) request.get(0);

        // En el response enviamos un HolaMundo personalizado
        Response response = new Response("HolaMundo, " + nombre);

        return response;
    }
}
