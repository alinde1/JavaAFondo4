package chap3;

import java.sql.Connection;

public class ShutdownHook extends Thread {

    public void run() {

        try {

            Connection con = DataAccess.getConnection();
            con.close();
            System.out.println("bye bye...");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

}
