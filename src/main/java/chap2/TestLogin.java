package chap2;

public class TestLogin {

    public static void main(String[] args) {

        try {
            Aplicacion app = new Aplicacion();

            Usuario u = app.login("juan", "juan123");

            System.out.println(u);

        } catch(ErrorFisicoException ex) {
            System.out.print("Fuera de servicio, ");
            System.out.println("Intente más tarde.");
            System.out.println(ex.getCause());
        }

        Aplicacion app2 = new Aplicacion();
        try {
            Usuario u2 = app2.login("pepito", "secret");
        } catch (ErrorFisicoException e) {
            throw new RuntimeException(e);
        }
    }

}
