package ofertes.gestionarOfertes;

import java.util.Date;
import java.util.Scanner;
import java.lang.Thread.State;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class inseriroferta {

    static Scanner teclat = new Scanner(System.in);

    public static void inseriroferta() {

        // try {
        // Connection con =
        // DriverManager.getConnection("jdbc:mysql://localhost:3306/flyingfree", "root",
        // "");
        // Statement st = con.createStatement(inserirOfertes);

        // String idOf;
        // String nomOf;
        // String descompte;
        // String datainici;
        // String datafi;

        // System.out.println("Procedim a incerir una oferta!");

        // System.out.println("Quin serà l'ID?");
        // idOf = teclat.next();

        // System.out.println("Quin nom tindrà l'oferta?");
        // nomOf = teclat.next();

        // System.out.println("Quina quantitat de descompte farà? (sense el caracter
        // %)");
        // descompte = teclat.next();

        // System.out.println("Quina és la seva data d'inici?");
        // datainici = teclat.next();

        // System.out.println("I la de fi?");
        // datafi = teclat.next();

        // String inserirOfertes = "INSERT INTO ofertes VALUES (" + idOf + ", " + nomOf
        // + ", " + descompte + "); ";
        // String inserirDataInici = "INSERT INTO data VALUES (" + datainici + ");";
        // String inserirDataFi = "INSERT INTO te VALUES (" + idOf + ", " + datainici +
        // ", " + datafi + ");";
        // ResultSet rs = st.executeQuery(inserirOfertes);
        // ResultSet rs2 = st.executeQuery(inserirDataInici);
        // ResultSet rs3 = st.executeQuery(inserirDataFi);

        // } catch (Exception e) {
        // // TODO: handle exception
        // }

        Connection con = null;
        Scanner sc = null;
        PreparedStatement ps = null;

        try {
            sc = new Scanner(System.in);
            int n = 0;
            if (sc != null) {
                System.out.println("Quantes ofertes vols inserir?");
                n = sc.nextInt();
            }
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/flyingfree", "root", "");

            if (con != null) {
                ps = con.prepareStatement("INSERT INTO ofertes VALUES (?,?,?)");

                if (ps != null && sc != null) {
                    for (int i = 1; i <= n; i++) {
                        System.out.println("Insereix la oferta nº" + i);
                        System.out.println("Insereix el ID: ");
                        String idOf = sc.next();

                        System.out.println("Insereix el nom de l'oferta: ");
                        String nomOf = sc.next();

                        System.out.println("Insereix la quantitat de descompte (sense el %): ");
                        int descompte = sc.nextInt();

                        ps.setString(1, idOf);
                        ps.setString(2, nomOf);
                        ps.setInt(3, descompte);

                        int res = ps.executeUpdate();
                        if (res == 0) {
                            System.out.println("La comanda " + i + "no s'ha inserit.");
                        } else {
                            System.out.println("La comanda " + i + "s'ha inserit correctament.");
                        }
                    }
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null)
                    ps.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException se1) {
                se1.printStackTrace();
            }
        }

    }
}
