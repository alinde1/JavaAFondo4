package chap10;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Response implements Serializable {

    private List<Object> args;
    private int errorCode;
    private String errorMessage;

    public Response() {

    }
    public Response(Object... args) {
        this.args = Arrays.asList(args);
    }

    public void add(Object obj) {
        this.args.add(obj);
    }

    public void setErrorCode(int i) {
        this.errorCode = i;
    }

    public void setErrorMessage(String s) {
        this.errorMessage = s;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Object get(int i) {
        return this.args.get(i);
    }
}
