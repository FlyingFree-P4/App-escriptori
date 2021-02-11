package appConsola;

import java.util.Scanner;
import appConsola.afegirProd;

public class menu {
    public static void main(String[] args) {

        Scanner teclat = new Scanner(System.in);
        boolean sortir = false;
        int opcio;
        String dniNum = "";

        while (!sortir) {

            System.out.println("_____________________________");
            System.out.println("|********FlyingFree*********|");
            System.out.println("_____________________________");
            System.out.println("|*******MenÃº principal******|");
            System.out.println("| 1. Afegir productes       |");
            System.out.println("| 2. Validar E-mail         |");
            System.out.println("| 3. Sortir                 |");
            System.out.println("|                           |");
            System.out.println("|Tria una de les 3 opcions: |");
            System.out.println("-----------------------------");
            if (!teclat.hasNextInt()) {
                System.out.println("Has d'introduÃ¯r un nÂº com a opciÃ³");
                teclat.next();
            } else {
                opcio = teclat.nextInt();

                switch (opcio) {

                    case 1:
                        afegirProd();
                    break;

                    case 2:
                        System.out.println("Has triat l'opciÃ³ 2.");
                        break;

                    case 3:
                        System.out.println("Has triat l'opciÃ³ 3, adeu!");
                        sortir = true;
                        break;

                    default:
                        System.out.println("OpciÃ³ incorrecta.");

                }
            }
        }
    } 
}
