package chap5;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

public class Introspectando {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // El usuario ingresa el nombre de una clase
        System.out.print("Ingrese un nombre de clase: ");
        String sClazz = scanner.nextLine();

        // Obtenemos una instancia de Class a partir del nombre
        Class<?> clazz;
        try {
            clazz = Class.forName(sClazz);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Mostramos los métodos de la clase
        for (Method method : clazz.getDeclaredMethods()) {
            String s = _motrarMetodo(method);
            System.out.println(s);
        }

        // El usuario ingresa el nombre de un campo de la clase
        System.out.println("Ingrese el nombre de un campo");
        String sField = scanner.next();

        // Obtenemos referencias a la clase y al campo
        try {

            clazz = Class.forName(sClazz);
            Field field = clazz.getField(sField);
            boolean esAtt = BeanUtil.isAttribute(clazz, field);
            System.out.println(sClazz + "es atributo? " + esAtt);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

        scanner.close();

    }

    public static String _motrarMetodo(Method mtd) {
        String s = "";

        // modificadores
        int modif = mtd.getModifiers();
        s += _modificadores(modif) + " ";

        // tipo del valor de retorno
        Class<?> returnType = mtd.getReturnType();
        s += returnType.getSimpleName() + " ";

        // nombre del método
        s += mtd.getName();

        // parámetros
        s += _parametros(mtd);

        return s;
    }

    public static String _modificadores(int modif)  {
        String s = "";

        if ( (modif & Modifier.PUBLIC) != 0) s += "public ";
        if ( (modif & Modifier.PRIVATE) != 0 ) s += "private ";
        if ( (modif & Modifier.PROTECTED) != 0 ) s += "protected ";
        if ( (modif & Modifier.STATIC) != 0 ) s += "static";
        if ( (modif & Modifier.ABSTRACT) !=0 ) s += "abstract ";

        return s.trim();
    }

    public static String _parametros(Method mtd) {
        String s = "(";
        Class<?>[] paramTypes = mtd.getParameterTypes();
        for (int i=0; i<paramTypes.length; i++) {
            s += paramTypes[i].getSimpleName();
            s += (i<paramTypes.length-1) ? ";" : "";
        }
        s += ")";

        return s;
    }

}
