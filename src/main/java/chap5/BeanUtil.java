package chap5;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class BeanUtil {

    public static boolean isAttribute(Class<?> clazz, Field field) {
        try {
            // Obtenemos el nombre del método get
            String getterName = attNameToAccessName(field.getName(), "get");

            // Obtenemos el nombre del método set
            String setterName = attNameToAccessName(field.getName(), "set");

            // Si no existe alguno de estos métodos
            // arrojará una excepción: NoSuchMethodException

            // Accedemos al getter sólo para ver si existe
            clazz.getMethod(getterName);

            // Accedemos al setter sólo para vers si existe
            clazz.getMethod(setterName);

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public static String attNameToAccessName(String attname, String accessor) {
        String inicial = attname.substring(0,1).toUpperCase();
        String siguiente = attname.substring(1);
        return accessor + inicial + siguiente;
    }

    public static void invokeSetter(Object target, Field field, Object value) {
        try {
            // nombre del setter
            String setterName = attNameToAccessName(field.getName(), "set");

            // referencia al setter
            Method setter = target.getClass().getDeclaredMethod(
                    setterName,
                    field.getType()
            );

            // invocamos al setter
            setter.invoke(target, value);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static Object invokeGetter(Object target, Field f) {

        try {
            // nombre del getter
            String getterName = attNameToAccessName(f.getName(), "get");

            // referencia al getter
            Method getter = target
                    .getClass()
                    .getDeclaredMethod(getterName);

            // invocamos al getter y retornamos lo que devuelve
            return getter.invoke(target);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
