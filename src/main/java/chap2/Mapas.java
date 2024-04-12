package chap2;

import java.util.HashMap;
import java.util.Map;

public class Mapas {

    public static void main(String[] args) {

        HashMap<Integer,String> m = new HashMap<>();

        m.put(1, "uno");
        m.put(2, "dos");
        m.put(3, "tres");

        String x = m.get(2);
        System.out.println("Elemento 2: " + x);
        System.out.println("Mapa: " + m);

        System.out.println("Valores: " + m.values());
        System.out.println("Claves: "+ m.keySet());

        for (Map.Entry<Integer, String> entry : m.entrySet()){
            System.out.println(entry);
        }

    }

}
