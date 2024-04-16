package chap6;

@ComponentScan("chap6")
public class DemoMySpring {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        // Obtenemos una instancia de Banda
        Banda banda = MySpring.getObject(Banda.class);
        System.out.println(banda);
    }

}
