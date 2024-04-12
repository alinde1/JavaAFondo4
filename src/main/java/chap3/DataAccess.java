package chap3;

import chap2.PropertiesUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DataAccess {
    public static Transaction beginTransaction() {
        return new Transaction( getConnection() );
    }

    private static Connection con = null;
    private static Properties prop = null;

    static {
        prop = PropertiesUtil.getProperties(DataAccess.class);

        try {

            Thread t = new ShutdownHook();
            Runtime.getRuntime().addShutdownHook(t);

            String propName = "jdbc.connection.drv";
            String drv = prop.getProperty(propName);
            Class.forName(drv);

        } catch (Exception e ) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {

        try {

            if ( con == null || con.isClosed() ) {

                String url = prop.getProperty("jdbc.connection.url");
                String usr = prop.getProperty("jdbc.connection.usr");
                String pwd = prop.getProperty("jdbc.connection.pwd");

                con = DriverManager.getConnection(url, usr, pwd);
            }

            return con;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

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
