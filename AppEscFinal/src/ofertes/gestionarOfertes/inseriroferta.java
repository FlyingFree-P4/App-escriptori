// Codi per a la inserció d'ofertes a la bd
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

    public static boolean inseriroferta() {

        // A diferencia d'altres codis, aquesta vegada declarem aquestes variables coma
        // null per no deixar-les "buides"
        Connection con = null;
        Scanner sc = null;
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;

        try {
            // Opció de donar quantes ofertes és vol inserir en aquell moment 1-infinit
            sc = new Scanner(System.in);
            int n = 0;
            if (sc != null) {
                System.out.println("Quantes ofertes vols inserir?");
                n = sc.nextInt();
            }
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/flyingfree", "root", "");

            // Al treballar amb més d'una taula, hem creat 3 prepared statements per a les
            // diferentes insersions a les taules: Ofertes (ID, nom i descompte)
            // Data (data d'inici)
            // te (ID oferta, data inici i data fi)

            if (con != null) {
                ps = con.prepareStatement(
                        "INSERT INTO ofertes (ID_ofertes, Nom_oferta, Quant_Descompte) VALUES (?,?,?)");
                ps1 = con.prepareStatement("INSERT INTO data (Data_inici) VALUES (?)");
                ps2 = con.prepareStatement("INSERT INTO te (ID_Ofertes, Data_inici, Data_fi) VALUES (?,?,?)");
                if (ps != null && ps1 != null && ps2 != null && sc != null) {
                    // Al demanar abans quantes ofertes volem inserir, és crearà el següent codi
                    // tantes vegades com hem seleccionat abans

                    // També aqui agafem totes les dades per teclat on desprès declarem els
                    // paràmetres als prepared statements
                    for (int i = 1; i <= n; i++) {
                        System.out.println("Insereix la oferta nº" + i);
                        System.out.println("Insereix el ID: ");
                        int idOf = sc.nextInt();

                        System.out.println("Insereix el nom de l'oferta: ");
                        String nomOf = sc.next();

                        System.out.println("Insereix la quantitat de descompte (sense el %): ");
                        int descompte = sc.nextInt();

                        System.out.println("Insereix la data d'inici de l'oferta: ");
                        String dataInici = sc.next();

                        System.out.println("Insereix la data de fi de l'oferta: ");
                        String dataFi = sc.next();

                        // Fixan't-nos amb les linies d'insersió dels ps, ps1 i ps2, veiem que a values
                        // tenim posats '?', llavors aqui diem quins valors volem inserir des del teclat
                        // a cada '?'
                        // Sent el ps tenimm que al primer '?' és idOf (ID ofertes), al segon '?'
                        // nomOf(nomOfertes) i aixì amb cada
                        ps.setInt(1, idOf);
                        ps.setString(2, nomOf);
                        ps.setInt(3, descompte);
                        ps1.setString(1, dataInici);
                        ps2.setInt(1, idOf);
                        ps2.setString(2, dataInici);
                        ps2.setString(3, dataFi);

                        // Amb execute update fem que la funció d'insert és realitzi i que "actualitzi"
                        // la taula amb les dades que hem inserit
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
        return true;
    }
}
