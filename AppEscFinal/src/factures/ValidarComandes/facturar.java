// Codi per a la facturació de les comandes.
package factures.ValidarComandes;

// Imports obligatoris per aquest codi (io. tot el que va relacionat en arxius i creació d'aquests, sql. base de dades i time. per inserir les dates)
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
    public static boolean factures(int comanda) {
        // Declaració de les variables de les dates i la manera que la volem ensenyar
        // (any, més i dia)

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        try {
            // Connexió a la bd i creació de statements

            Scanner teclat = new Scanner(System.in);
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/flyingfree", "root", "");
            Statement st = con.createStatement();
            Statement st1 = con.createStatement();

            // Verificació si la comanda inserida per teclat existeix

            String verificar = "SELECT ID_comanda FROM comanda WHERE ID_comanda = " + comanda + ";";
            ResultSet rs = st.executeQuery(verificar);

            // Verificació si l'oferte inserida per teclat existeix(comanda 0 és refereix a
            // cap comanda, 0% descompte)

            System.out.println("Insereix l'ID de la oferta (0 si no és vol cap)");
            int ofertaSel = teclat.nextInt();
            String consultar = "SELECT * FROM ofertes WHERE ID_ofertes = " + ofertaSel + ";";
            ResultSet res = st1.executeQuery(consultar);

            // Verificació si la comanda ha estat facturada o no (amb 0 i 1)

            Statement st2 = con.createStatement();
            String facturat = "SELECT facturat FROM comanda WHERE ID_comanda = " + comanda + ";";
            ResultSet rs2 = st2.executeQuery(facturat);

            if (rs.next() && res.next() && rs2.next()) {

                // Transformem el string "facturat" en un int per fer la operació de si és 0,
                // faci la factura i si es 1 que no

                int facturacio = Integer.parseInt(rs2.getString("facturat"));

                if (facturacio <= 0) {
                    System.out.println("La comanda no ha estat facturada, procedim a fer-ho");
                } else {
                    return false;
                }

                // Declaració de nous statements i prepared statements per a noves consults
                Statement st3 = con.createStatement();
                Statement st4 = con.createStatement();
                Statement st5 = con.createStatement();
                Statement st6 = con.createStatement();
                PreparedStatement ps = null;
                PreparedStatement ps1 = null;

                // Path d'on guardarem tots els fitxers i ja creem el nou arxiu txt on hi hauran
                // les següentes dades
                File factura = new File("C:/Users/Dam12021/factures/factura" + comanda + ".txt");
                factura.createNewFile();

                // Select de les dades del client que coincidi amb la seva comanda (ID comanda)
                String facturaSelect = "SELECT clients.DNI,Nom,Cognom,Correu FROM clients INNER JOIN "
                        + "comanda ON comanda.DNI = clients.DNI AND comanda.ID_comanda = " + comanda + ";";
                ResultSet facturaRs = st3.executeQuery(facturaSelect);

                // Select de tots els productes de la comanda del client
                String facturaSelect2 = "SELECT * FROM detalls_factura INNER JOIN"
                        + " productes ON detalls_factura.IDProducte = productes.IDProducte AND detalls_factura.ID_comanda = "
                        + comanda + ";";
                ResultSet facturaRs2 = st4.executeQuery(facturaSelect2);

                // Select del preu total de tots els productes de la comanda demanada
                String facturaSelect3 = "SELECT sum(Preu) FROM productes, comanda, detalls_factura WHERE (comanda.ID_comanda = detalls_factura.ID_comanda AND detalls_factura.IDProducte = productes.IDProducte AND comanda.ID_comanda = "
                        + comanda + ");";
                ResultSet facturaRs3 = st5.executeQuery(facturaSelect3);

                // Select de la quantitat de descompte de l'oferta aplicada
                String ofertes = "SELECT Quant_Descompte FROM ofertes WHERE ID_ofertes = " + ofertaSel + ";";
                ResultSet ofertaRs = st6.executeQuery(ofertes);

                // Procedim amb la creació del contingut de la factura
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

                    // While per que passi per totes les columnes i files del client i importem les
                    // dades de cada amb un facturaRs.getString("value")
                    // facturaRS > és el result set, l'execució del query, select
                    // getString > agafar el valor string, ja que declarem el query com a string, ho
                    // fem aixi
                    while (facturaRs.next()) {
                        pw.println("║       DNI: " + facturaRs.getString("clients.DNI") + "         ║");
                        pw.println("║       Nom: " + facturaRs.getString("Nom") + "           ║");
                        pw.println("║       Cognom: " + facturaRs.getString("Cognom") + "     ║");
                        pw.println("║       Correu: " + facturaRs.getString("Correu") + "    ║");
                        pw.println("║                              ║");
                    }

                    // Aquest while recorre totes les files de la taula productes on coincideix amb
                    // la comanda, si no hi han més files d'aquesta, no continui
                    while (facturaRs2.next()) {
                        pw.println("║       ID producte: " + facturaRs2.getString("IDProducte") + "         ║");
                        pw.println("║       Nom producte: " + facturaRs2.getString("NomProd") + "    ║");
                        pw.println("║       Desti: " + facturaRs2.getString("Desti") + "          ║");
                        pw.println("║       Preu: " + facturaRs2.getString("Preu") + "            ║");
                        pw.println("║                              ║");
                    }

                    pw.println("║                              ║");

                    // Si hi han resultat de facturaRs3(suma de tots els preus) i ofertaRs(quantitat
                    // de descompte), transformar-los a double ja que tots són decimals
                    if (facturaRs3.next() && ofertaRs.next()) {

                        Double preu = Double.parseDouble(facturaRs3.getString("sum(Preu)"));
                        Double preu2 = Double.parseDouble(facturaRs3.getString("sum(Preu)"));
                        Double ofertaAplicable = Double.parseDouble(ofertaRs.getString("Quant_Descompte"));

                        // Apliquem l'iva a la suma de tot els preus
                        if (preu > 0) {
                            preu = preu + (preu * 0.21);
                        }

                        // Apliquem el descompte de l'oferta
                        if (preu2 > 0) {
                            preu2 = preu2 + (preu2 * 0.21) - (preu * ofertaAplicable / 100);
                        }

                        pw.println("║   Preu sense IVA: " + facturaRs3.getString("sum(Preu)") + "€    ║");
                        pw.println("║   Preu sense amb IVA: " + preu + "€        ║");
                        pw.println("║                              ║");

                        // Si és selecciona l'oferta 0, s'aplica el preu total amb l'iva, sino el preu
                        // total amb iva + el descompte
                        if (ofertaAplicable == 0) {
                            pw.println("║   Preu total: " + preu + "€        ║");

                        } else {
                            pw.println("║   Preu total (+oferta): " + preu2 + "€        ║");

                        }
                    }

                    pw.println("║                              ║");
                    pw.println("║   " + dtf.format(now) + "        ║");
                    pw.println("║                             ©║");
                    pw.println("╚══════════════════════════════╝");
                    pw.close();

                    // Execució de dos consultes

                    // Uptade a la taula de comandes posant 1 a la columna facturat referint-se a
                    // que ja s'ha facturat aquella comanda
                    ps1 = con.prepareStatement(
                            "UPDATE comanda, clients SET facturat = 1 WHERE comanda.DNI = clients.DNI AND comanda.ID_comanda ="
                                    + comanda + " ");
                    ps1.executeUpdate();

                    // Insersió a la taula factura amb les dades de la factura (ID factura, id
                    // oferta i id comanda)
                    ps = con.prepareStatement(
                            "INSERT INTO factura (ID_factura, ID_ofertes, ID_comanda) VALUES (null, ?, ?)");
                    if (ps != null) {
                        ps.setInt(1, ofertaSel);
                        ps.setLong(2, comanda);

                        int res1 = ps.executeUpdate();
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return true;
    }

}