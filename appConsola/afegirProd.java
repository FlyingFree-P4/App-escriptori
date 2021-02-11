package appConsola;
import java.util.Scanner;

public class afegirProd {
    
    private static void afegirProd() {
        
        Scanner teclat = new Scanner(System.in);
        int opcio;
        boolean sortir = false;

        System.out.println("_____________________________");
        System.out.println("|********FlyingFree*********|");
        System.out.println("_____________________________");
        System.out.println("|*****Afegir productes******|"); 
        System.out.println("| 1. Vols                   |");
        System.out.println("| 2. Hotels                 |");
        System.out.println("| 3. Restaurants            |");
        System.out.println("| 4. Excursions             |");
        System.out.println("|                           |");
        System.out.println("| 5. Tornar al menu         |");
        System.out.println("|                           |");
        System.out.println("| Gestiona els productes    |");
        System.out.println("-----------------------------");

        if (!teclat.hasNextInt()) {
            System.out.println("Has d'introduÃ¯r un nÂº com a opciÃ³");
            teclat.next();
        } else {
            opcio = teclat.nextInt();

            switch (opcio) {
                case 1:
                    System.out.println("Gestiona els vols");
                    break;
            
                case 2:
                    System.out.println("Gestiona els hotels");    
                    break;

                case 3:
                    System.out.println("Gestiona els restaurants");
                    break;
                   
                case 4:
                    System.out.println("Gestiona les excursions");    

                case 5:
                    System.out.println("Has tornat al menu");
                    sortir = true;
                    break;
                default:
                    System.out.println("OpciÃ³ incorrecta.");
                    break;
            }

        }

    }   

}
