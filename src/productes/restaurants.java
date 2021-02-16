package productes;

import java.util.Scanner;

public class restaurants {
    private static void restaurants() {
        Scanner teclat = new Scanner(System.in);
        int opcio;
        boolean sortir = false;

        while (!sortir) {
            System.out.println("_____________________________");
            System.out.println("|********FlyingFree*********|");
            System.out.println("_____________________________");
            System.out.println("|********RESTAURANTS********|");
            System.out.println("| 1. Afegeix restaurants    |");
            System.out.println("| 2. Gestiona els restaurants|");
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
                        System.out.println("Afegeix nous restaurants.");
                        break;

                    case 2:
                        System.out.println("Gestiona els restaurants.");
                        break;

                    case 3:
                        System.out.println("Has tornat enrere.");
                        sortir = true;
                        break;
                    default:
                        System.out.println("Opció incorrecta");
                        break;
                }
            }

        }
    }

    public static void getValue() {
        restaurants();
    }
}
