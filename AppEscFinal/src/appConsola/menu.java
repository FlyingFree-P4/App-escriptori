package appConsola;

import java.util.Scanner;
import ofertes.*;
import productes.*;
import factures.*;

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
            System.out.println("| 3. Ofertes                |");
            System.out.println("|                           |");
            System.out.println("| 4. Surt                   |");
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
                        menuProd.getValue();
                        break;

                    case 2:
                        factures.getValue();
                        break;

                    case 3:
                        ofertes.getValue();
                        break;

                    case 4:
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
