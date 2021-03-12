package factures.ValidarComandes;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.Result;

public class facturar {
    public static void factures() {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        try {
            Scanner teclat = new Scanner(System.in);
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/flyingfree", "root", "");
            Statement st = con.createStatement();
            Statement st1 = con.createStatement();

            System.out.println("Introdueix el ID de la comanda: ");

            int comanda = teclat.nextInt();
            String verificar = "SELECT * FROM comanda WHERE ID_comanda = " + comanda + ";";
            ResultSet rs = st.executeQuery(verificar);

            System.out.println("Insereix l'ID de la oferta");
            int ofertaSel = teclat.nextInt();
            String consultar = "SELECT * FROM ofertes WHERE ID_ofertes = " + ofertaSel + ";";
            ResultSet res = st1.executeQuery(consultar);

            if (rs.next() && res.next()) {

                Statement st2 = con.createStatement();
                Statement st3 = con.createStatement();
                Statement st4 = con.createStatement();
                Statement st5 = con.createStatement();
                // PreparedStatement ps = null;

                File factura = new File("factura" + comanda + ".txt");
                factura.createNewFile();

                String facturaSelect = "SELECT clients.DNI,Nom,Cognom,Correu FROM clients INNER JOIN "
                        + "comanda ON comanda.DNI = clients.DNI AND comanda.ID_comanda = " + comanda + ";";
                ResultSet facturaRs = st2.executeQuery(facturaSelect);

                String facturaSelect2 = "SELECT * FROM detalls_factura INNER JOIN"
                        + " productes ON detalls_factura.IDProducte = productes.IDProducte AND detalls_factura.ID_comanda = "
                        + comanda + ";";
                ResultSet facturaRs2 = st3.executeQuery(facturaSelect2);

                String facturaSelect3 = "SELECT sum(Preu) FROM productes, comanda, detalls_factura WHERE (comanda.ID_comanda = detalls_factura.ID_comanda AND detalls_factura.IDProducte = productes.IDProducte AND comanda.ID_comanda = "
                        + comanda + ");";
                ResultSet facturaRs3 = st4.executeQuery(facturaSelect3);

                String ofertes = "SELECT Quant_Descompte FROM ofertes WHERE ID_ofertes = " + ofertaSel + ";";
                ResultSet ofertaRs = st5.executeQuery(ofertes);

                if (factura.exists()) {
                    FileWriter filewriter = new FileWriter(factura, true);
                    PrintWriter pw = new PrintWriter(filewriter);
                    pw.println("╔══════════════════════════════╗");
                    pw.println("║                              ║");
                    pw.println("║        FLYING FREE ®         ║");
                    pw.println("║                              ║");
                    pw.println("╠══════════════════════════════╣");
                    pw.println("║                              ║");
                    pw.println("║                              ║");

                    while (facturaRs.next()) {
                        pw.println("║       DNI: " + facturaRs.getString("clients.DNI") + "         ║");
                        pw.println("║       Nom: " + facturaRs.getString("Nom") + "           ║");
                        pw.println("║       Cognom: " + facturaRs.getString("Cognom") + "     ║");
                        pw.println("║       Correu: " + facturaRs.getString("Correu") + "    ║");
                        pw.println("║                              ║");
                    }

                    while (facturaRs2.next()) {
                        pw.println("║       ID producte: " + facturaRs2.getString("IDProducte") + "         ║");
                        pw.println("║       Nom producte: " + facturaRs2.getString("NomProd") + "    ║");
                        pw.println("║       Desti: " + facturaRs2.getString("Desti") + "          ║");
                        pw.println("║       Preu: " + facturaRs2.getString("Preu") + "            ║");
                        pw.println("║                              ║");
                    }

                    pw.println("║                              ║");

                    if (facturaRs3.next() && ofertaRs.next()) {
                        Double preu = Double.parseDouble(facturaRs3.getString("sum(Preu)"));
                        Double preu2 = Double.parseDouble(facturaRs3.getString("sum(Preu)"));
                        Double ofertaAplicable = Double.parseDouble(ofertaRs.getString("Quant_Descompte"));
                        if (preu > 0) {
                            preu = preu + (preu * 0.21);
                        }
                        if (preu2 > 150) {
                            preu2 = preu2 + (preu2 * 0.21) - (preu * ofertaAplicable / 100);
                        }

                        pw.println("║   Preu sense IVA: " + facturaRs3.getString("sum(Preu)") + "€    ║");
                        pw.println("║   Preu sense oferta inclosa: " + preu + "€        ║");
                        pw.println("║                              ║");
                        pw.println("║   Preu total: " + preu2 + "€        ║");
                    }

                    pw.println("║                              ║");
                    pw.println("║   " + dtf.format(now) + "        ║");
                    pw.println("║                             ©║");
                    pw.println("╚══════════════════════════════╝");
                    pw.close();

                    // ps = con.prepareStatement("INSERT INTO factura (ID_comanda, ID_ofertes, DNI)
                    // VALUES (?, ?, ?)");
                    // if (ps != null) {
                    // ps.setString(1, facturaRs.getString("ID_comanda"));
                    // ps.setString(2, facturaRs.getString(""));
                    // ps.setString(3, facturaRs.getString("clients.DNI"));
                    // }
                } else {
                    System.out.println("error");
                }
            } else {
                System.out.println("!!!La comanda no existeix!!!");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
