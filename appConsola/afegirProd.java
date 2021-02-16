package appConsola;
import java.util.Scanner;
import productes.*;
public class afegirProd {
    
    private static void afegirProd() {
        
        Scanner teclat = new Scanner(System.in);
        int opcio;
        boolean sortir = false;

        while (!sortir) {
            
        
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
                        vols.getValue();
                        break;
                
                    case 2:
                        hotels.getValue();
                        break;

                    case 3:
                        restaurants.getValue();
                        break;
                    
                    case 4:
                        excursions.getValue(); 
                        break;
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
    public static void getValue() {
        afegirProd();
    }
}
