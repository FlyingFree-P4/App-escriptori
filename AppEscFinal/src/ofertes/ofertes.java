// Codi per a la pantalla d'ofertes.
package ofertes;

import java.util.Scanner;
import ofertes.gestionarOfertes.*;

public class ofertes {
    public static boolean ofertes() {
        Scanner teclat = new Scanner(System.in);
        int opcio;
        boolean sortir = false;

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
            System.out.println("   |********************OFERTES********************|");
            System.out.println("   |						   |");
            System.out.println("   |            1. Afegeix nova oferta             |");
            System.out.println("   |            2. Borra una oferta                |");
            System.out.println("   |                                               |");
            System.out.println("   |            3. Tornar enrere                   |");
            System.out.println("   |                                               |");
            System.out.println("   |_______________________________________________|");
            System.out.println("   |						   |");
            System.out.println("   |           Tria una de les 3 opcions:          |");
            System.out.println("   |_______________________________________________|");

            if (!teclat.hasNextInt()) {
                System.out.println("Has d'introduir un nº com a opció.");
                teclat.next();
            } else {
                opcio = teclat.nextInt();
                switch (opcio) {
                case 1:
                    if (inseriroferta.inseriroferta()) {
                        System.out.println("Procedim a inserir ofertes");
                    } else {
                        System.out.println("Torna a intentar");
                    }
                    break;

                case 2:
                    if (eliminarofertes.eliminarofertes()) {
                        System.out.println("Procedim a borrar ofertes");
                    } else {
                        System.out.println("Torna a intentar");
                    }
                    break;

                case 3:
                    System.out.println("Has tornat enrere.");
                    sortir = true;
                    break;

                default:
                    System.out.println("Opció incorrecta");
                }
            }
        }
        return true;
    }
}