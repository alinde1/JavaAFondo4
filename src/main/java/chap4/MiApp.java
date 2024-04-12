package chap4;

import chap3.Categoria;
import chap3.DataAccess;
import chap3.Producto;

import java.util.List;
import java.util.Scanner;

public class MiApp {

    private Scanner scanner = new Scanner(System.in);

    public int pantalla1() {

        // Mensaje para el usuario
        System.out.println("Seleccione una categoria: ");

        // Obtenemos la lista de todas las categorías
        Facade f = DataAccess.getObject("facade");
        List<Categoria> categorias = f.obtenerCategorias();

        for (Categoria cat: categorias) {
            System.out.print(cat.getIdCategoria() + " - ");
            System.out.println(cat.getDescripcion());
        }

        return scanner.nextInt();

    }

    public int pantalla2(int idCat) {

        // Mensaje para el usuario
        System.out.println("-Productos de la categoria-");

        // Obtenemos la lista de productos
        Facade f = DataAccess.getObject("facade");

        List<Producto> productos = f.obtenerProductos(idCat);

        for (Producto prod: productos) {
            System.out.print(prod.getIdProducto() + " - ");
            System.out.println(prod.getDescripcion());
        }

        return scanner.nextInt();

    }

    public static void main(String[] args) {

        MiApp app = DataAccess.getObject("miApp");

        // Pantalla 1
        int idCat = app.pantalla1();

        // Pantalla 2
        app.pantalla2(idCat);

    }

}
