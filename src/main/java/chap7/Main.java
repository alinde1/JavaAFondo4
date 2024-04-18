package chap7;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        // Scanner para leer bytes a través de la consola
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hola Mundo !!!");

        // Cambiamos la estandar output
        FileOutputStream fos = new FileOutputStream("salida.txt");
        PrintStream stdout = System.out;
        System.setOut(new PrintStream(fos));

        // Imprimimos en la salida estándar
        System.out.println("Hola Mundo !!!");

        fos.close();
        System.setOut(stdout);

        // Cambiamos la entrada estándard
        // Contenido de entrada.txt
        // 1 Pablo
        // 2 Juan
        // 3 Pedro
        FileInputStream fis = new FileInputStream("entrada.txt");
        InputStream stdin = System.in;
        System.setIn(fis);

        Scanner scanner1 = new Scanner(System.in);

        while( scanner1.hasNext()) {
            int i = scanner1.nextInt();
            String s = scanner1.next();

            System.out.println(i + ", " + s);
        }

        fis.close();
        scanner1.close();
        System.setIn(stdin);

        // Escribir un archivo

        // El usuario ingresa una cadena
        Scanner scanner2 = new Scanner(System.in);
        System.out.print("Ingrese una cadena: ");
        String s = scanner2.nextLine();

        // Abrimos el archivo
        FileOutputStream fos2 = new FileOutputStream("archivo.txt");
        byte[] bytes = s.getBytes();

//        for (int i=0; i<bytes.length; i++) {
//            // Grabamos byte por byte
//            fos2.write(bytes[i]);
//        }

        // Grabamos todos los bytes que contiene el array
        fos2.write(bytes);

        fos2.close();

        // Leer un archivo.

        // Abrimos el archivo
        FileInputStream fis2 = new FileInputStream("archivo.txt");

        // Recorremos leyendo byte por byte
        int b;
        while ( (b=fis2.read() ) > 0 ) {
            char c = (char)b;
            System.out.print(c);
        }
        fis2.close();

        // Archivos de acceso aleatorio

        RandomAccessFile raf = new RandomAccessFile("archivo.txt", "r");

        // Longitud del archivo
        long n = raf.length();

        for (long i=n-1; i>=0; i--) {
            // Nos posicionamos sobre el i-ésimo byte
            raf.seek(i);

            // Leemos y mostramos
            int c = raf.read();
            System.out.println((char) c);
        }
        raf.close();

        // Las clases java.io.File y java.nio.Files

        File dir = new File(args[0]);

        // Mostramos el directorio procesando a todos sus hijos
        _listFiles(dir);

        scanner.close();
    }

    private static void _listFiles(File dir) {
        // Mostramos el directorio
        System.out.println(dir.getAbsolutePath());

        // Recorremos los hijos
        String[] files = dir.list();
        for (String file : files) {
            // Por cada hijo, si es directorio, invocamos recursivo
            File f = new File(dir, file);
            if( f.isDirectory() ) {
                _listFiles(f);
            }
        }
    }

}
