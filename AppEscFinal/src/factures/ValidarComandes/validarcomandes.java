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

            System.out.println("Procedim a verificar la comanda.");

            // Check si l'ID comanda coincideix amb alguna de la base de dades, si és aixi,
            // validem dni
            try {

                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/flyingfree", "root", "");
                Statement st = con.createStatement();

                System.out.println("Introdueix el ID de la comanda: ");
                int comanda = teclat.nextInt();

                String verificar = "SELECT * FROM comanda WHERE ID_comanda = " + comanda + ";";

                ResultSet rs = st.executeQuery(verificar);

                if (rs.next()) {
                    System.out.println("Perfecte, la comanda existeix!");
                    System.out.println("Procedim a validar el DNI.");
                    System.out.println("Insereix el DNI:");

                    String dni = teclat.next();

                    if (validarcomandes.validaDNI(dni)) {
                        System.out.println("Dni correcte");
                    } else {
                        System.out.println("Dni incorrecte");
                    }

                } else {
                    System.out.println("!!!La comanda no existeix!!!");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            sortir = true;
        }

    }

    public static boolean validaDNI(String dni) {

        char[] lletraDni = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V',
                'H', 'L', 'C', 'K', 'E' };
        String dniNum = "";

        boolean dniCorrecte = true;

        if (dni.length() != 9) {
            return false;
        } else {
            char ult = dni.charAt(dni.length() - 1);

            if (ult >= 'a' && ult <= 'z') {
                ult -= 32;
            }

            if (ult >= 'A' && ult <= 'Z') {
                for (int i = 0; i < 8; i++) {
                    if (dni.charAt(i) < '0' || dni.charAt(i) > '9') {
                        dniCorrecte = false;
                        return false;
                    }
                }

                char lletra = 0;

                if (dniCorrecte) {

                    dni = dni.substring(0, dni.length() - 1);

                    int dniInte = Integer.parseInt(dni);
                    int reste = dniInte % 23;

                    if (ult == lletraDni[reste]) {
                        System.out.println("El DNI s'ha validat correctament");

                        try {
                            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/flyingfree",
                                    "root", "");
                            Statement stmt = conn.createStatement();

                            String verificarDNI = "SELECT * FROM comanda WHERE ID_comanda = " + comanda + " AND DNI = "
                                    + dni + ";";

                            ResultSet rs2 = stmt.executeQuery(verificarDNI);

                            if (rs2.next()) {
                                return true;
                            } else {
                                return false;
                            }
                        } catch (Exception e) {
                            // TODO: handle exception
                        }

                    } else {
                        return false;
                    }
                }
            } else {
                return false;
            }
        }
        return true;
    }

}