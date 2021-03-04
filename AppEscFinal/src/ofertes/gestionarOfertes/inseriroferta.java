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

        Connection con = null;
        Scanner sc = null;
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;

        try {
            sc = new Scanner(System.in);
            int n = 0;
            if (sc != null) {
                System.out.println("Quantes ofertes vols inserir?");
                n = sc.nextInt();
            }
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/flyingfree", "root", "");

            if (con != null) {
                ps = con.prepareStatement(
                        "INSERT INTO ofertes (ID_ofertes, Nom_oferta, Quant_Descompte) VALUES (?,?,?)");
                ps1 = con.prepareStatement("INSERT INTO data (Data_inici) VALUES (?)");
                ps2 = con.prepareStatement("INSERT INTO te (ID_Ofertes, Data_inici, Data_fi) VALUES (?,?,?)");
                if (ps != null && ps1 != null && ps2 != null && sc != null) {
                    for (int i = 1; i <= n; i++) {
                        System.out.println("Insereix la oferta nº" + i);
                        System.out.println("Insereix el ID: ");
                        String idOf = sc.next();

                        System.out.println("Insereix el nom de l'oferta: ");
                        String nomOf = sc.next();

                        System.out.println("Insereix la quantitat de descompte (sense el %): ");
                        int descompte = sc.nextInt();

                        System.out.println("Insereix la data d'inici de l'oferta: ");
                        String dataInici = sc.next();

                        System.out.println("Insereix la data de fi de l'oferta: ");
                        String dataFi = sc.next();

                        ps.setString(1, idOf);
                        ps.setString(2, nomOf);
                        ps.setInt(3, descompte);
                        ps1.setString(1, dataInici);
                        ps2.setString(1, idOf);
                        ps2.setString(2, dataInici);
                        ps2.setString(3, dataFi);

                        int res = ps.executeUpdate();
                        int res1 = ps1.executeUpdate();
                        int res2 = ps2.executeUpdate();
                        if (res == 0 && res1 == 0 && res2 == 0) {
                            System.out.println("La oferta " + i + " no s'ha inserit.");
                        } else {
                            System.out.println("La oferta " + i + " s'ha inserit correctament.");
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
