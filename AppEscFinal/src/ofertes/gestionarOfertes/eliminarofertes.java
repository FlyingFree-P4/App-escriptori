package ofertes.gestionarOfertes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class eliminarofertes {
    static Scanner teclat = new Scanner(System.in);

    public static void eliminarofertes() {

        Connection con = null;
        Scanner sc = null;
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;

        try {
            sc = new Scanner(System.in);

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/flyingfree", "root", "");

            if (con != null) {
                ps = con.prepareStatement("DELETE FROM ofertes WHERE ID_ofertes = ?");
                ps1 = con.prepareStatement("DELETE FROM data WHERE Data_inici = ?");
                if (ps != null && ps1 != null && sc != null) {

                    System.out.println("Insereix el ID de la oferta que vols borrar: ");
                    String idOf = sc.next();

                    System.out.println("Per confirmar, insereix la data d'inici de l'oferta: ");
                    String dataInici = sc.next();

                    ps.setString(1, idOf);
                    ps1.setString(1, dataInici);

                    if (idOf != null && dataInici != null) {
                        System.out.println("Estàs segur que vols borrar aquesta oferta s/n?");
                        String si = sc.next();

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
    }
}
