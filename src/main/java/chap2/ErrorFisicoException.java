package chap2;

public class ErrorFisicoException extends Exception {

    public ErrorFisicoException(Exception ex) {
        super("Ocurrió un error físico", ex);
    }

}
