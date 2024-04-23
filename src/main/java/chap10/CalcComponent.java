package chap10;

import chap6.Component;

@Component
public class CalcComponent {
    @RequestMapping("/calc/sumar")
    public Response sumar(Request request){
        // En el request recibimos dos valores
        int a = (Integer) request.get(0);
        int b = (Integer) request.get(1);

        // Sumamos ambos valores
        int c = a + b;

        // En el response retornamos la suma de los valores
        return new Response(c);
    }

    @RequestMapping("/calc/restar")
    public Response restar(Request request) {
        int a = (Integer) request.get(0);
        int b = (Integer) request.get(1);
        int c = a - b;
        return new Response(c);
    }

    @RequestMapping("/calc/multiplicar")
    public Response mulplicar(Request request) {
        int a = (Integer) request.get(0);
        int b = (Integer) request.get(1);
        int c = a*b;
        return new Response(c);
    }

    @RequestMapping("/calc/dividir")
    public Response dividir(Request request) {
        int a = (Integer) request.get(0);
        int b = (Integer) request.get(1);

        Response response = new Response();

        if ( b != 0 ) {
            double c = (double) a/b;
            response.add(c);
        } else {
            // código y mensaje de error
            response.setErrorCode(1);
            response.setErrorMessage("No puede dividir por 0");
        }

        return response;
    }

}
