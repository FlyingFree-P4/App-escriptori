// Codi principal de l'app d'escriptori.
package appConsola;
// Els imports que necessitem en aquest codi (Scanner per inserir per teclat, ofertes.ofertes i factures.factures per obtenir els valors d'aquell arxiu i poder cridar-lo)  

import java.util.Scanner;
import ofertes.ofertes;
import factures.factures;

public class menu {

    // Declarem que sigui la pantalla principal i d'on arranquem amb main
    public static void main(String[] args) {

        // Variables pel scanner com a teclat, les opcions a triar i el poder sortir del
        // programa
        Scanner teclat = new Scanner(System.in);
        boolean sortir = false;
        int opcio;

        // Al declarar sortir com a boolea, diem que mentres que no hi sigui el sortir
        // (que continui sent false), fagi el codi a continuació
        while (!sortir) {
            // Menú entendible per navegar entre les pantalles
            System.out.println("   _________________________________________________");
            System.out.println("   |						   |");
            System.out.println("   |    ________________ * * * * ________________  |");
            System.out.println("   |    \\	       *        *               /  |");
            System.out.println("   | 	 \\________    *          *     ________/   |");
            System.out.println("   |	  \\	     * FlyingFree *           /    |");
            System.out.println("   |	   \\	     *            *          /     |");
            System.out.println("   |        \\____     *	         *      ____/      |");
            System.out.println("   |	     \\         *        *          /       |");
            System.out.println("   |          \\__________* * * *__________/        |");
            System.out.println("   |						   |");
            System.out.println("   |						   |");
            System.out.println("   |_______________________________________________|");
            System.out.println("   |*****************Menu principal****************|");
            System.out.println("   |						   |");
            System.out.println("   |            1. Factures                        |");
            System.out.println("   |            2. Ofertes                         |");
            System.out.println("   |                                               |");
            System.out.println("   |            3. Surt                            |");
            System.out.println("   |                                               |");
            System.out.println("   |_______________________________________________|");
            System.out.println("   |						   |");
            System.out.println("   |           Tria una de les 3 opcions:          |");
            System.out.println("   |_______________________________________________|");

            // Decidim que per teclat només accepti nº i no lletres (agafant l'excepció i
            // que no falli el programa al inserir una lletra)
            if (!teclat.hasNextInt()) {
                System.out.println("Has d'introduir un nº com a opció");
                teclat.next();
            } else {
                // agafem les opcions que ens donen per a navegar (1, 2 i 3)
                opcio = teclat.nextInt();
                switch (opcio) {

                case 1:
                    if (factures.factures()) { 
                    } else{
                        System.out.println("Torna a intentar");
                    }
                    break;

                case 2:
                    if (ofertes.ofertes()) {
                    }else{
                        System.out.println("Torna a intentar");
                    }
                    break;

                case 3:
                    System.out.println("Has triat l'opció 3, adeu!");
                    sortir = true;
                    break;
                // Donem el valor true a sortir per a que tanqui el programa. Com hem esmentat
                // abans, fins que sortir continui sent fals, segueixi amb el codi fins que
                // sigui true
                default:
                    System.out.println("Opció incorrecta.");
                    // Ens dona un missatge d'opció incorrecta per que si seleccionem un nº més gran
                    // que 3 o que no oferim, no doni cap resultat exepte aquest missatge

                }
            }
        }
    }
}