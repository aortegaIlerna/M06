package bd;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        BDJava bd = new BDJava();
        bd.connectToBD();
    }
}
