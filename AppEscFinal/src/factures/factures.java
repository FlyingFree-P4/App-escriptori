// Codi per a la pantalla de factures
package factures;

import java.util.Scanner;
import factures.ValidarComandes.*;

public class factures {
    public static boolean factures() {
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
            System.out.println("   |********************FACTURES*******************|");
            System.out.println("   |						   |");
            System.out.println("   |            1. Validar comandes                |");
            System.out.println("   |            2. Comandes                        |");
            System.out.println("   |            3. Crear factura                   |");
            System.out.println("   |                                               |");
            System.out.println("   |            4. Tornar enrere                   |");
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
                    // validarcomandes.validarcomandes();
                    if (validarcomandes.validarcomandes()) {
                        System.out.println("La comanda existeix i el DNI és correcte");
                    } else {
                        System.out.println("La comanda no existeix o el DNI és incorrecte");
                    }
                    break;

                case 2:
                    if (comandes.comandes()) {
                        System.out.println("Aquests són els productes de la comanda");
                    } else{
                        System.out.println("Hi ha hagut un error.");
                    }
                    break;

                case 3:
                    facturar.factures();
                    if (facturar.factures()){
                        System.out.println("Ja s'ha facturat la comanda");
                    } else{
                        System.out.println("La comanda no s'ha pogut crear");
                    }
                    break;
                case 4:
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