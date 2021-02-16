package productes;

import java.util.Scanner;

public class excursions {
    private static void excursions() {
        Scanner teclat = new Scanner(System.in);
        int opcio;
        boolean sortir = false;

        while (!sortir) {
            System.out.println("_____________________________");
            System.out.println("|********FlyingFree*********|");
            System.out.println("_____________________________");
            System.out.println("|********EXCURSIONS*********|");
            System.out.println("| 1. Afegeix excursions     |");
            System.out.println("| 2. Gestiona els excursions|");
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
                        System.out.println("Afegeix nous excursions.");
                        break;

                    case 2:
                        System.out.println("Gestiona els excursions.");
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
        excursions();
    }
}
