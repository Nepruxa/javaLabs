package db;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import java.sql.*;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Test {

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:derby:DB");
    }

    @org.junit.Test
    public void test1Insert() {
        try {
            Connection conn = getConnection();
            String q = "INSERT INTO CHILD (NAME, AGE, SCHOOL_ID) VALUES ('Проверка', 42, 1)";
            Statement stmt = conn.createStatement();
            int c = stmt.executeUpdate(q);
            assertEquals(1, c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void test2Select() {
        try {
            Connection conn = getConnection();
            String q = "SELECT * FROM CHILD WHERE ID = 1";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(q);
            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("NAME");
                System.out.println(Integer.toString(id) + " " + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void test3Delete() {
        try {
            Connection conn = getConnection();
            String q = "DELETE FROM CHILD WHERE NAME LIKE 'Проверка'";
            Statement stmt = conn.createStatement();
            int c = stmt.executeUpdate(q);
            assertEquals(1, c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}