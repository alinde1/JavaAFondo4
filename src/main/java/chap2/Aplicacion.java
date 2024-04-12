package chap2;

import java.io.FileInputStream;
import java.util.Properties;

public class Aplicacion {

    public Usuario login(String u, String p) throws ErrorFisicoException {

        FileInputStream fis = null;

        try {

            Properties prop = new Properties();
            fis = new FileInputStream("src/main/java/chap2/usuario.properties");
            prop.load(fis);

            String usr = prop.getProperty("username");
            String pwd = prop.getProperty("password");

            Usuario usuario = null;

            if (usr.equals(u) && pwd.equals(p)) {

                usuario = new Usuario();
                usuario.setUsername(usr);
                usuario.setPassword(pwd);
                usuario.setNombre(prop.getProperty("nombre"));
                usuario.setEmail(prop.getProperty("email"));

            }

            return usuario;

        } catch (Exception ex) {

            String mssg = "Error verificando los datos";
            throw new ErrorFisicoException(ex);

        } finally {
            try {
                if (fis != null) fis.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new RuntimeException(ex);
            }
        }

    }

}
