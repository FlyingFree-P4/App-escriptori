package factures;

import java.util.Scanner;
import factures.ValidarComandes.*;

public class factures {
    private static void factures() {
        Scanner teclat = new Scanner(System.in);
        int opcio;
        boolean sortir = false;

        while (!sortir) {
            System.out.println("_____________________________");
            System.out.println("|********FlyingFree*********|");
            System.out.println("_____________________________");
            System.out.println("|*********FACTURES**********|");
            System.out.println("| 1. Validar comandes       |");
            System.out.println("| 2. Comandes               |");
            System.out.println("| 3. Crear factura          |");
            System.out.println("|                           |");
            System.out.println("| 4. Tornar enrere          |");
            System.out.println("|                           |");
            System.out.println("| Gestiona els productes    |");
            System.out.println("-----------------------------");

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
                        System.out.println("Gestiona les comandes.");
                        break;

                    case 3:
                        System.out.println("Crea factura.");
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

    public static void getValue() {
        factures();
    }
}
