package appConsola;

import java.util.Scanner;
import ofertes.*;
import factures.*;

public class menu {
    public static void main(String[] args) {

        Scanner teclat = new Scanner(System.in);
        boolean sortir = false;
        int opcio;

        while (!sortir) {
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
            if (!teclat.hasNextInt()) {
                System.out.println("Has d'introduir un nº com a opció");
                teclat.next();
            } else {
                opcio = teclat.nextInt();

                switch (opcio) {

                    case 1:
                        factures.getValue();
                        break;

                    case 2:
                        ofertes.getValue();
                        break;

                    case 3:
                        System.out.println("Has triat l'opció 4, adeu!");
                        sortir = true;
                        break;
                    default:
                        System.out.println("Opció incorrecta.");

                }
            }
        }
    }
}