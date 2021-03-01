package factures.ValidarComandes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class comandes {
    public static void comandes (){
        Scanner teclat = new Scanner(System.in);
        
              try {

                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/flyingfree", "root", "");
                Statement st = con.createStatement();

                System.out.println("Introdueix el ID de la comanda: ");
                int comanda = teclat.nextInt();

                String verificar = "SELECT * FROM comanda WHERE ID_comanda = " + comanda + ";";

                ResultSet rs = st.executeQuery(verificar);

                if (rs.next()) {
               //     System.out.println("Perfecte, la comanda existeix!");
                    
               String gestioProductes = "SELECT * from detalls_factura INNER JOIN"
                       + " productes ON detalls_factura.IDProducte = productes.IDProducte;" ;
               ResultSet gestioRs = st.executeQuery(gestioProductes);
               
                while(gestioRs.next()){
                     System.out.println(" IDComanda:"+gestioRs.getString("ID_comanda") );
                     System.out.println(" IDProducte: " + gestioRs.getString("IDProducte"));
                     System.out.println(" NomProducte: " + gestioRs.getString("NomProd"));
                     System.out.println(" Destí: " + gestioRs.getString("Desti"));
                     System.out.println(" Preu: " + gestioRs.getString("Preu")+"€");
                    
                     
                }
               

                } else {
                    System.out.println("!!!La comanda no existeix!!!");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
           
        
    } 
}
