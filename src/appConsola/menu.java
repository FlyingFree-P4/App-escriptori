package appConsola;

import java.util.Scanner;

public class menu {
    public static void main(String[] args) {

        Scanner teclat = new Scanner(System.in);
        boolean sortir = false;
        int opcio;

        while (!sortir) {

            System.out.println("_____________________________");
            System.out.println("|********FlyingFree*********|");
            System.out.println("_____________________________");
            System.out.println("|*******Menu principal******|");
            System.out.println("| 1. Productes              |");
            System.out.println("| 2. Factures               |");
            System.out.println("| 3. Sortir                 |");
            System.out.println("|                           |");
            System.out.println("|Tria una de les 3 opcions: |");
            System.out.println("-----------------------------");
            if (!teclat.hasNextInt()) {
                System.out.println("Has d'introduir un nº com a opció");
                teclat.next();
            } else {
                opcio = teclat.nextInt();

                switch (opcio) {

                    case 1:
                        afegirProd.getValue();
                        break;

                    case 2:
                        System.out.println("Has triat l'opció 2.");
                        break;

                    case 3:
                        System.out.println("Has triat l'opció 3, adeu!");
                        sortir = true;
                        break;

                    default:
                        System.out.println("Opció incorrecta.");

                }
            }
        }
    }
}
