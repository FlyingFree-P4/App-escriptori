// Codi per a la pantalla de factures
package factures;

import java.util.Scanner;
import factures.ValidarComandes.*;

public class factures {
    private static void factures() {
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
                    validarcomandes.validarcomandes();
                    break;

                case 2:
                    comandes.comandes();
                    break;

                case 3:
                    facturar.factures();
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
    }

    // Creem un métode per a que el poguem cridar des del menú
    public static void getValue() {
        factures();
    }
}