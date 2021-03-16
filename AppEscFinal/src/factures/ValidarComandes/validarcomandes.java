// Codi per a fer la validació de les comandes i que coincidi amb el dni del client.
package factures.ValidarComandes;

// Impots necessaris per aquest codi (SQL per fer totes les funcions per a la conexió, selects... de la bd)
import java.util.Scanner;
import java.lang.Thread.State;
import java.sql.*;
import App_Consola.MysqlCon;

public class validarcomandes {

    // En aquest cas declarem el teclat com a scanner I STATIC per que sigui clobal,
    // que no faci falta tornar a declarar-lo a altres métodes d'aquest codi
    static Scanner teclat = new Scanner(System.in);
    private static MysqlCon conexio;

    public static boolean validarcomandes() {

        boolean sortir = false;

        while (!sortir) {

            System.out.println("Procedim a verificar la comanda.");

            // Check si l'ID comanda coincideix amb alguna de la base de dades, si és aixi,
            // validem dni
            try {

                // creem les variables de conexió a base de dades i el statement per cridar-lo a
                // continuació
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/flyingfree", "root", "");
                Statement st = con.createStatement();

                System.out.println("Introdueix el ID de la comanda: ");
                int comanda = teclat.nextInt();

                // Variable de la consulta a la bd de la id comanda
                String verificar = "SELECT * FROM comanda WHERE ID_comanda = " + comanda + ";";

                // Execució del statement preparat(conexió) amb la consulta
                ResultSet rs = st.executeQuery(verificar);

                if (rs.next()) {
                    // Verifiquem si el resultat que ens dona és correcte, seguim am el codi de la
                    // validació del dni
                    System.out.println("Perfecte, la comanda existeix!");
                    System.out.println("Procedim a validar el DNI.");
                    System.out.println("Insereix el DNI:");

                    String dni = teclat.next();

                    if (validarcomandes.validaDNI(dni)) {
                        // System.out.println("El DNI coincideix amb la comanda");
                        return true;
                    } else {
                        // System.out.println("El DNI no coincideix amb la comanda");
                        return false;
                    }

                } else {
                    // System.out.println("!!!La comanda no existeix!!!");
                    return false;
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            sortir = true;
        }
        return true;

    }

    public static boolean validaDNI(String dni) {

        char[] lletraDni = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V',
                'H', 'L', 'C', 'K', 'E' };
        String dniNum = "";
        // System.out.println("Insereix la comanda: ");
        // int comanda = teclat.nextInt();

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
                        System.out
                                .println("Torna a inserir el ID de la comanda per analitzar si coincideix amb el DNI.");
                        int comanda = teclat.nextInt();
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