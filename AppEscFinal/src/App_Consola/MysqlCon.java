package App_Consola;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlCon {

    public static void main(String[] args) {

        connection();

    }

    // Assignació de variables per accedir a la bd
    private static String db_ = "provaff";
    private static String login_ = "root";
    private static String password_ = "";
    private static String url_ = "jdbc:mysql://localhost:3306/" + db_;

    private static Connection connection_;
    private static Statement st_ = null;

    public static void connection() {

        // Conexió a la bd
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            connection_ = DriverManager.getConnection(url_, login_, password_);

            // Comprobació
            if (connection_ != null) {
                st_ = connection_.createStatement();
                System.out.println("Conexion a base dedatos " + db_ + " correcta.");

            } else
                System.out.println("Conexion  fallida.");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Statement createStatement() {
        return null;
    }

    public static void close() {
    }

}