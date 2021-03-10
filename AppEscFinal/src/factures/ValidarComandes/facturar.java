package factures.ValidarComandes;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class facturar {
    public static void factures() {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        try {
            Scanner teclat = new Scanner(System.in);
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/flyingfree", "root", "");
            Statement st = con.createStatement();

            System.out.println("Introdueix el ID de la comanda: ");

            int comanda = teclat.nextInt();
            String verificar = "SELECT * FROM comanda WHERE ID_comanda = " + comanda + ";";
            ResultSet rs = st.executeQuery(verificar);

            if (rs.next()) {

                Statement st1 = con.createStatement();
                Statement st2 = con.createStatement();
                Statement st3 = con.createStatement();

                File factura = new File("factura" + comanda + ".txt");
                factura.createNewFile();

                String facturaSelect = "SELECT clients.DNI,Nom,Cognom,Correu FROM clients INNER JOIN "
                        + "comanda ON comanda.DNI = clients.DNI AND comanda.ID_comanda = " + comanda + ";";
                ResultSet facturaRs = st1.executeQuery(facturaSelect);

                String facturaSelect2 = "SELECT * FROM detalls_factura INNER JOIN"
                        + " productes ON detalls_factura.IDProducte = productes.IDProducte AND detalls_factura.ID_comanda = "
                        + comanda + ";";
                ResultSet facturaRs2 = st2.executeQuery(facturaSelect2);

                String facturaSelect3 = "SELECT sum(Preu) FROM productes, comanda WHERE comanda.ID_comanda = " + comanda
                        + " ;";
                ResultSet facturaRs3 = st3.executeQuery(facturaSelect3);

                String preuTotal = facturaRs3.getString("sum(Preu)");

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

                    if (facturaRs3.next()) {
                        pw.println("║   Preu total: " + facturaRs3.getString("sum(Preu)") + "€        ║");

                    }
                    try {
                        Double preu = Double.parseDouble(preuTotal.trim());

                        if (preu > 0) {
                            preu = preu + (preu * 0.21);
                        }

                    } catch (NumberFormatException nfe) {
                        System.out.println("NumberFormatException: " + nfe.getMessage());
                    }
                    pw.println("║ " + preu + " ║");
                    pw.println("║                              ║");
                    pw.println("║   " + dtf.format(now) + "        ║");
                    pw.println("║                             ©║");
                    pw.println("╚══════════════════════════════╝");
                    pw.close();
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
