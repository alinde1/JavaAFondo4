package chap10;

import java.io.Serializable;
import java.util.*;

public class Request implements Serializable {


    private String path;
    private List<Object> args;


    public Request(String path) {
        this.path = path;
    }

    public Request(String path, Object... args) {
        this.path = path;
        this.args = Arrays.asList(args);
    }

    public void add(Object obj) {
        this.args.add(obj);
    }

    public Object get(int index) {
        return this.args.get(index);
    }

    public String getPath() {
        return path;
    }
}
