package chap3;

import java.sql.Connection;
import java.sql.SQLException;

public class Transaction {
    private Connection con = null;

    public Transaction(Connection con) {
        this.con = con;
        try {
            this.con.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void commit() {
        try {
            con.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    public void rollback() {
        try {
            con.rollback();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }
}
