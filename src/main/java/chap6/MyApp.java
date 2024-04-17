package chap6;

import java.util.List;
import java.util.Scanner;

@ComponentScan("chap6")
@Component
public class MyApp {

    private Scanner scanner = new Scanner(System.in);

    @Autowired
    private Facade facade;

    public int pantalla1() {

        // Mensaje para el usuario
        System.out.println("Seleccione una categoria: ");

        // facade ya esta "inyectado" y disponible para usar
        List<Categoria> categorias = facade.obtenerCategorias();

        for (Categoria categoria : categorias) {
            System.out.print(categoria.getIdCategoria() + " - ");
            System.out.println(categoria.getDescripcion());
        }

        return scanner.nextInt();
    }

    public int pantalla2(int idCat) {

        // Mensaje para el usuario
        System.out.println("-Productos de la categoria-");

        List<Producto> productos = facade.obtenerProductos(idCat);

        for (Producto producto : productos) {
            System.out.print(producto.getIdProducto() + " - ");
            System.out.println(producto.getDescripcion());
        }

        return scanner.nextInt();
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        MyApp app = MySpring.getObject(MyApp.class);

        // Pantalla 1
        int idCat = app.pantalla1();

        // Pantalla 2
        app.pantalla2(idCat);

    }
}
