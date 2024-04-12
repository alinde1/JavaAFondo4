package chap3;

import chap2.PropertiesUtil;

import java.util.Properties;

public class DaoUtil {
    public static Properties prop = null;

    static {
        prop = PropertiesUtil.getProperties(DaoUtil.class);
    }

    public static <T> T getObject(String objName) {

        try {

            String classname = prop.getProperty(objName);
            Class<?> clazz = Class.forName(classname);
            return (T)clazz.newInstance();

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }

    }

}
