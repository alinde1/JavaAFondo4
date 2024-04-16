package chap6;

import org.reflections.ReflectionUtils;
import org.reflections.Reflections;
import java.lang.reflect.Field;
import java.util.Set;

import static org.reflections.scanners.Scanners.*;

public class MySpring {
    
    public static <T> T getObject(Class<T> clazz) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        T t;
        
        if (clazz.isInterface()) {
            clazz = _buscarImplementacion(clazz);
        }

        t = clazz.newInstance();

        for (Field field : clazz.getDeclaredFields()) {
            Autowired autowired = field.getAnnotation(Autowired.class);
            if (autowired != null) {
                boolean access = field.isAccessible();
                field.setAccessible(true);
                if (autowired.implementation() == Class.forName("java.lang.Object")) {
                    field.set(t, MySpring.getObject(field.getType()));
                } else {
                    field.set(t, autowired.implementation().newInstance());
                }
                field.setAccessible(access);
            }
        }

        return t;
    }

    private static <T> Class<T> _buscarImplementacion(Class<T> clazz) {

        Reflections reflections = new Reflections("chap6");

        Set<Class<?>> annotated =
                reflections.get(SubTypes.of(TypesAnnotated.with(Component.class)).asClass());

        for (Class<?> cl : annotated) {
            if (reflections.get(ReflectionUtils.Interfaces.of(cl)).contains(clazz)) {
                return (Class<T>) cl;
            }
        }

        return null;
    }

}
