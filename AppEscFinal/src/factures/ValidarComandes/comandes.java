// Codi per a veure els continguts de les comandes, els seus productes
package factures.ValidarComandes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class comandes {

    public static boolean comandes() {
        Scanner teclat = new Scanner(System.in);

        try {

            // creem les variables de conexió a base de dades i el statement per cridar-lo a
            // continuació

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/flyingfree", "root", "");
            Statement st = con.createStatement();

            System.out.println("Introdueix el ID de la comanda: ");
            int comanda = teclat.nextInt();
            // Igual que al codi de validarcomandes, verifiquem si aquesta comanda existeix
            // a la nostra bd
            String verificar = "SELECT * FROM comanda WHERE ID_comanda = " + comanda + ";";
            ResultSet rs = st.executeQuery(verificar);
            // Si el resultat que hem inserit és correcte, procedim a mostrar els productes
            // de la comanda
            if (rs.next()) {
                // Consulta per a ensenyar les dades que ens inporten (Id producte, nom,
                // preu...) i hem de tenir en compte que l'ID de la comanda coincideixi amb el
                // que busquem a la bd. Més entendible, ens busqui els productes de la comanda
                // que inserim per teclat amb una concatenació de la variable comanda
                // (detalls_factura.ID_comanda = +comanda+)
                String gestioProductes = "SELECT * FROM detalls_factura INNER JOIN"
                        + " productes ON detalls_factura.IDProducte = productes.IDProducte AND detalls_factura.ID_comanda = "
                        + comanda + ";";
                ResultSet gestioRs = st.executeQuery(gestioProductes);
                // Fem un bucle while per que ens ensenyi tot el contingut d'aquesta comanda (de
                // cada fila de la taula detalls_factura)
                while (gestioRs.next()) {
                    System.out.println(" IDComanda:" + gestioRs.getString("ID_comanda"));
                    System.out.println(" IDProducte: " + gestioRs.getString("IDProducte"));
                    System.out.println(" NomProducte: " + gestioRs.getString("NomProd"));
                    System.out.println(" Destí: " + gestioRs.getString("Desti"));
                    System.out.println(" Dia: " + gestioRs.getString("Dia"));
                    System.out.println(" Hora: " + gestioRs.getString("Hora"));
                    System.out.println(" Preu: " + gestioRs.getString("Preu") + "€");
                    System.out.println("");
                }
            } else {
                return false;
            }
            // Totes les excepcions les agafem aqui i llavors ens dirà quin error d'excepció
            // tenim
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return true;
    }

}
