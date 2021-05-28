import java.awt.*;

/**
 * Classe permettant de simuler la vue console du tetris pour les tests.
 * La plupart des méthodes vont être réutilisées dans la class modele en utilisant des variables.
 */

public class Jeu {
    private int[][] grille;
    private int[][] piece;
    private ControlJeu cj;
    private boolean perdu;

    public Jeu() throws InterruptedException {
        this.initGrille();
        this.piece = new int[][]{
                {1, 4},
                {2, 4},
                {3, 4},
                {3, 5}
        };
        this.perdu = false;
        cj = new ControlJeu(this);
        this.play();

    }

    private void play() throws InterruptedException {
        int x;
        int y;
        for(int i=0; i<4;i++){
            x = this.piece[i][0];
            y = this.piece[i][1];
            this.grille[x][y]=2;
        }
        this.affiche();
        while(!this.perdu){
            //changer temps de pause en fonction du niveau du jeu
            Thread.sleep(500);
            if(this.cj.nbrLignesCompletes() != 0){
                System.out.println("des lignes ont étées complétées!");
            }
            this.cj.descendrePiece();
            this.cj.moveDroite();
            this.affiche();
        }
        System.out.println("Perdu");
    }

    private void affiche() {
        for(int i=0; i<4;i++){
            int x = this.piece[i][0];
            int y = this.piece[i][1];
            this.grille[x][y]=2;
        }
        for(int i=0;i<10;i++) {
            for (int j = 0; j < 15; j++) {
                if(this.grille[i][j] == 2)
                    System.out.print((char)27 + "[31m" + this.grille[i][j]);
                else if(this.grille[i][j] == 9)
                    System.out.print((char)27 + "[33m" + this.grille[i][j]);
                else if(this.grille[i][j] == 1)
                    System.out.print((char)27 + "[32m" + this.grille[i][j]);
                else
                    System.out.print((char)27 + "[37m" + this.grille[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }

    public void initGrille() {
        this.grille = new int[10][15];
        for(int i=0;i<10;i++){
            for(int j=0; j<15; j++){
                if(i==0 || i==9 || j==0 || j==14){
                    this.grille[i][j] = 9;
                }else{
                    this.grille[i][j] = 0;
                }
            }
        }
    }
    //supprimer piece courante pour l'afficher ailleurs
    public void rafraichirGrille() {
        for(int i=0;i<10;i++) {
            for (int j = 0; j < 15; j++) {
                if (this.grille[i][j] == 2) {
                    this.grille[i][j] = 0;
                }
            }
        }
    }
    public void bloquerPiece() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 15; j++) {
                if (this.grille[i][j] == 2)
                    this.grille[i][j] = 1;
            }
        }
        this.piece = this.cj.genererPiece();
        for (int i = 0; i < 4; i++) {
            int ligne = this.piece[i][0];
            int colonne = this.piece[i][1];
            if (this.grille[ligne][colonne] == 1) {
                this.perdu = true;
                break;
            }
        }
    }

    public int[][] getPiece() {
        return piece;
    }

    public void setPiece(int[][] piece) {
        this.piece = piece;
    }

    public int[][] getGrille() {
        return grille;
    }
}
