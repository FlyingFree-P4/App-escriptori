// Codi per a la eliminació d'ofertes existents de la bd.
package ofertes.gestionarOfertes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class eliminarofertes {
    static Scanner teclat = new Scanner(System.in);

    public static boolean eliminarofertes() {
        // A diferencia d'altres codis, aquesta vegada declarem aquestes variables coma
        // null per no deixar-les "buides"
        Connection con = null;
        Scanner sc = null;
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;

        try {
            sc = new Scanner(System.in);

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/flyingfree", "root", "");

            if (con != null) {
                // Amb aquests prepared statements, apliquem la línia de borrar
                // Al ser lligades les FK cascade, al borrar per exemple un ID_ofertes, també és
                // borra de la taula te
                ps = con.prepareStatement("DELETE FROM ofertes WHERE ID_ofertes = ?");
                ps1 = con.prepareStatement("DELETE FROM data WHERE Data_inici = ?");
                if (ps != null && ps1 != null && sc != null) {
                    // Per la "seguretat" demanem dues dades per a realitzar el delete, l'id i la
                    // data d'inici d'aquesta
                    System.out.println("Insereix el ID de la oferta que vols borrar: ");
                    String idOf = sc.next();

                    System.out.println("Per confirmar, insereix la data d'inici de l'oferta: ");
                    String dataInici = sc.next();
                    // Fixan't-nos amb les linies de delete dels ps i ps1, veiem que a values
                    // tenim posats '?', llavors aqui diem quins valors volem inserir des del teclat
                    // a cada '?'
                    // Sent el ps tenimm que al primer '?' és idOf (ID ofertes)
                    ps.setString(1, idOf);
                    ps1.setString(1, dataInici);

                    // Tenint que hem insertat el id i la data d'inici, confirmem amb un s(si) o
                    // n(no)
                    if (idOf != null && dataInici != null) {
                        System.out.println("Estàs segur que vols borrar aquesta oferta s/n?");
                        String si = sc.next();
                        // Si el que inserim per teclat és igual a s, executem l'actualització de la bd
                        // borrant aquesta oferta amb un executeUpdate(igual que a l'insert)
                        if (si.equals("s")) {
                            int res = ps.executeUpdate();
                            int res1 = ps.executeUpdate();

                            if (res == 0 && res1 == 0) {
                                System.out.println("La oferta no s'ha pogut borrar.");
                            } else {
                                System.out.println("La oferta s'ha borrat correctament");
                            }
                        } else {
                            System.out.println("Has decidit no borrar la oferta");

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
