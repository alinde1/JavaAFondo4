package chap3;

import java.sql.Connection;

public class ShutdownHookImple extends Thread {

    public void run() {

        try {

            Connection con = JdbcUtil.getConnection();
            con.close();
            System.out.println("bye bye...");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

}
