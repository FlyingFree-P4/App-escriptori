package factures.ValidarComandes;

import java.util.Scanner;
import java.lang.Thread.State;
import java.sql.*;
import App_Consola.MysqlCon;

public class validarcomandes {

    private static MysqlCon conexio;

    public static void validarcomandes() {

        Scanner teclat = new Scanner(System.in);
        int opcio;
        boolean sortir = false;

        while (!sortir) {

            System.out.println("Procedim a verificar la comanda");

            // Check si l'ID comanda coincideix amb alguna de la base de dades, si és aixi,
            // validem dni
            try {

                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/provaff", "root", "");
                Statement st = con.createStatement();

                System.out.println("Introdueix el ID de la comanda: ");
                int comanda = teclat.nextInt();

                String verificar = "SELECT * FROM comandes WHERE ID_comandes = " + comanda + ";";

                ResultSet rs = st.executeQuery(verificar);

                if (rs.next()) {
                    System.out.println("Perfecte!");
                } else {
                    System.out.println("Error");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            sortir = true;
        }

    }

    public boolean checkIdCom(int comanda) {

        return true;
    }

}
