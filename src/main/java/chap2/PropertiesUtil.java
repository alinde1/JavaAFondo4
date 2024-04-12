package chap2;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

    private static Properties _getProperties(InputStream is) throws Exception {

        Properties prop = new Properties();
        prop.load(is);
        return prop;

    }

    public static Properties getProperties(String filename) {

        try (FileInputStream fis = new FileInputStream(filename)) {
            return _getProperties(fis);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public static Properties getProperties(Class<?> clazz) {

        String fname = clazz.getName().replace('.','/');
        fname += ".properties";

        ClassLoader loader = Thread.currentThread()
                .getContextClassLoader();

        try (InputStream is = loader.getResourceAsStream(fname)) {
            return _getProperties(is);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        Properties props = PropertiesUtil.getProperties("src/main/java/chap2/persona.properties");

        System.out.println(props);

        Properties props2 = PropertiesUtil.getProperties(PropertiesUtil.class);

        System.out.println(props2);
    }
}
