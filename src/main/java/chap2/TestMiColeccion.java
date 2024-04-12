package chap2;

import java.util.Arrays;
import java.util.List;

public class TestMiColeccion {

    public static void main(String[] args) {
        MiColeccion<String> mc = new MiColeccion<>();
        mc.insertar("Pedro", 0);
        mc.insertar("Pablo", 0);
        mc.insertar("Juan", 0);

        for (int i=0; i<mc.cantidad(); i++) {
            String s = mc.obtener(i);
            System.out.println(s);
        }

        for (int i=0; i<10; i++) {
            mc.agregar("Prueba");
        }

        System.out.println("Número de elementos: " + mc.cantidad());

        MiColeccion<Integer> mi = new MiColeccion<>();
        mi.insertar(1,0);
        mi.insertar(2,0);
        mi.insertar(3,0);

        for (int i=0; i<mi.cantidad(); i++) {
            int x = mi.obtener(i);
            System.out.println(x);
        }

        List<Integer> lst = Arrays.asList(1,2,3,4);
        System.out.println(lst);
    }

}
