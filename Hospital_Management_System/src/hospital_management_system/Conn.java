//package hospital_management_system;
//
//
//import java.sql.*;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.Statement;
//
//public class Conn {
//    Connection c;
//    Statement s;
//    public Conn() {
//        try {
//            c = DriverManager.getConnection("jdbc:mysql:///hospital_db", "root", "Ganesh@1120KM");
//            s = c.createStatement();
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }
//}


package hospital_management_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conn implements AutoCloseable {
    Connection c;
    Statement s;

    public Conn() {
        try {
            c = DriverManager.getConnection("jdbc:mysql:///hospital_db", "root", "Ganesh@1120KM");
            s = c.createStatement();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void close() {
        try {
            if (s != null) s.close();
            if (c != null) c.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
