import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Boolean active = true;
        Scanner sc = new Scanner(System.in);
        Jeu jeu = new Jeu();
        while (active) {
            System.out.println("=================================================\n==================== MORPION ====================\n=================================================");
            System.out.println("\n --- Quelle taille de jeu voulez-vous ? (3 - 9) ---\n0 : Quitter\n");
            String rep = sc.nextLine();

            if (rep.equals("0")) {
                System.out.println(" --- Au revoir ! ---");
                break;
            }

            int taille;

            //check Format
            try {
                taille = Integer.valueOf(rep);
            } catch (NumberFormatException e) {
                System.err.println("=== Veuilliez entrer un nombre ===");
                continue;
            }

            //check limit
            if (taille < 3 || taille >9) {
                System.err.println("=== Veuilliez entrer un nombre entre 3 et 9 ===");
                continue;
            }
            jeu.createGrille(taille);
            jeu.partie();



        }
    }
}