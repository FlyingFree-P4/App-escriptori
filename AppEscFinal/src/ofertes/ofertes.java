package ofertes;

import java.util.Scanner;

public class ofertes {
    private static void ofertes() {
        Scanner teclat = new Scanner(System.in);
        int opcio;
        boolean sortir = false;

        while (!sortir) {
            System.out.println("_____________________________");
            System.out.println("|********FlyingFree*********|");
            System.out.println("_____________________________");
            System.out.println("|*********OFERTES***********|");
            System.out.println("| 1. Afegeix nova oferta    |");
            System.out.println("| 2. Borra una oferta       |");
            System.out.println("|                           |");
            System.out.println("| 3. Tornar enrere          |");
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
                        System.out.println("Afegeix nova oferta.");
                        break;

                    case 2:
                        System.out.println("Borra una oferta.");
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
    }

    public static void getValue() {
        ofertes();
    }
}
