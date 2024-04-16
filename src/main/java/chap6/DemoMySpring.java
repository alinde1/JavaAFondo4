package chap6;

@ComponentScan("chap6")
public class DemoMySpring {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        // Obtenemos una instancia de Banda
        Banda banda = MySpring.getObject(Banda.class);
        System.out.println(banda);

//        Guitarrista guitarrista = MySpring.getObject(Guitarrista.class);
//        System.out.println(guitarrista);

//        Banda beatles = new TheBeatles();
//        System.out.println(beatles);
//
//        Guitarrista guitarrista = MySpring.getObject(JohnLennon.class);
//        System.out.println(guitarrista);

//        Class<?> clazz = Class.forName("chap6.TheBeatles");
//        System.out.println(clazz.getName());
    }

}
