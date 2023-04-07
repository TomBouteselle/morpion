import java.util.ArrayList;
import java.util.Scanner;

public class Jeu {
    //private String[][] grille = new String[3][3];
    ArrayList<ArrayList<String>> grille = new ArrayList<ArrayList<String>>();
    private Scanner sc = new Scanner(System.in);

    public void createGrille(int taille) {
        grille.clear();
        for (int i=1; i <= taille; i++) {
            ArrayList<String> row = new ArrayList<String>();
            for (int j =1; j <= taille; j++) {
                row.add("-");
            }
            grille.add(row);
        }
    }
    public void partie() {
        // Grille reset
        /** for(int row = 0; row < this.grille.length; row++){
            for(int col = 0; col < this.grille[row].length; col++){
                this.grille[row][col] =  "-";
            }
        }*/

        boolean active = true;

        while (true) {
            if (!tour("J1", "X") || !tour("J2", "O"))
                break;
        }
        System.out.println(" === Au revoir ===");
    }

    public boolean tour(String joueur, String symbol) {

        int row, col;
        String err = "";
        while (true) {
            System.out.print("\n\n\n\n");
            System.out.println("\n --- TOUR DE "+joueur+" ---");
            System.err.println(err);
            err = "";
            draw();
            System.out.println("Quelle case jouer (ex : 21) : ");
            String rep = sc.nextLine();

            //check format
            try {
                int check = Integer.valueOf(rep);
                row =  rep.charAt(0) - '0';
                col = rep.charAt(1) - '0';
            } catch (Exception e) {
                err = " === Entrez deux chiffres ! ===";
                continue;
            }
            //check limit
            if (row < 1 || row > grille.size() || col < 1 || col > grille.size()) {
                System.err.println(" === Entrez des valeurs dans la grille ! ===");
                continue;
            }
            if (!remplir(row, col, symbol)) {
                System.err.println("=== La case est déjà prise ! ===");
                continue;
            }
            break;
        }
        remplir(row, col, symbol);

        return checkFin(joueur, symbol);
    }

    public boolean checkFin(String joueur, String symbol) {
        int taille = grille.size();
        boolean active = true;
        boolean aGagne = true;
        String msg = "=====================\n= "+joueur+" A GAGNE !!!!!! =\n"+"=====================";

        //ligne
        for (int i = 0; i < taille; i++) {
            aGagne = true;
            for (int j = 0; j < taille; j++) {
                if (grille.get(i).get(j) != symbol) {
                    aGagne = false;
                    break;
                }
            }
            if (aGagne) {
                System.out.println(msg);
                active = false;
            }
        }
        //colone
        for (int i = 0; i < taille; i++) {
            aGagne = true;
            for (int j = 0; j < taille; j++) {
                if (grille.get(j).get(i) != symbol) {
                    aGagne = false;
                    break;
                }
            }
            if (aGagne) {
                System.out.println(msg);
                active = false;
            }
        }


        aGagne = true;
        //diagonale 1
        for (int i = 0; i < taille; i++) {
            if (grille.get(i).get(i) != symbol) {
                aGagne = false;
                break;
            }
        }
        if (aGagne) {
            System.out.println(msg);
            active = false;
        }

        aGagne = true;
        //diagonale 2
        for (int i = taille-1; i > -1; i--) {

            if (grille.get(i).get(taille-i-1) != symbol) {
                aGagne = false;
                break;
            }
        }
        if (aGagne) {
            System.out.println(msg);
            active = false;
        }


        return active;
    }


    public boolean remplir(int x, int y, String symbol){
        x--;
        y--;
        if (!grille.get(x).get(y).equals("-")){
            return false;
        } else {
            grille.get(x).set(y, symbol);
        }
        return true;
    }



    public void draw() {
        int taille = grille.size();
        String line = "  ";
        for (int i=1; i<=taille; i++) {
            line += (" "+i);
        }
        System.out.println(line);
        line = "";
        for (int i = 1; i <= taille; i++) {
            line += " "+i;
            for (int j = 1; j <= taille; j++) {
                line += " "+grille.get(i-1).get(j-1);
            }
            line += "\n";
        }
        System.out.println(line);
    }
}
