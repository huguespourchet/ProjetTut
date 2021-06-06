import java.util.Arrays;

public class ControlJeu {

    private Model model;

    public ControlJeu(Model model) throws InterruptedException {
        //initialisation de la taille du tableau contenant le tetrimino qui descend
        this.model = model;
    }
    private void play() throws InterruptedException {
        int x;
        int y;
        for (int i = 0; i < 4; i++) {
            x = this.model.getPieceInstantanee().getCoordsTetrimino()[i][0];
            y = this.model.getPieceInstantanee().getCoordsTetrimino()[i][1];
        //placer piece sur la grille avec coordonnes x et y
        }
        while (!this.model.getPerdu()) {
            Thread.sleep(this.model.getVitesse());
            if (this.nbrLignesCompletes() != 0) {
                this.descendreGrille(this.nbrLignesCompletes());
            }
            this.descendrePiece();
        }
        //perdu
    }

    private void descendreGrille(int nbrLignesCompletes) {
        for (int i = this.model.TAILLE_LIGNES-1; i > this.model.TAILLE_LIGNES-1 - 4; i--) {
            for (int j = 1; j < this.model.TAILLE_COLONNES; j++) {
                this.model.getPlateau().getGrille()[i][j] = 0;
            }
        }
        for (int i = 8 - this.model.TAILLE_LIGNES; i > 0; i--) {
            for (int j = 13; j > 0; j--) {
                if (this.model.getPlateau().getGrille()[i][j] == 1) {
                    this.model.getPlateau().getGrille()[i - this.model.TAILLE_LIGNES][j] = 1;
                    this.model.getPlateau().getGrille()[i][j] = 0;
                }
            }
        }
    }


    public void descendrePiece() {
        if(!isBloqueBas()) {
            int[][] tetri = new int[4][2];
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 2; j++) {
                    tetri[i][j] = this.model.getPieceInstantanee().getCoordsTetrimino()[i + 1][j];
                }
            }
            this.model.getPieceInstantanee().setCoordsTetrimino(tetri);
        }
    }

    private boolean isBloqueBas() {
        boolean res = false;
        for(int i=0;i<4;i++) {
            //verification sur la ligne suivante
            int ligne = this.model.getPieceInstantanee().getCoordsTetrimino()[i][0]+1;
            int colonne = this.model.getPieceInstantanee().getCoordsTetrimino()[i][1];
            if(this.model.getPlateau().getGrille()[ligne][colonne] != 0 && this.model.getPlateau().getGrille()[ligne][colonne] != 2){
                res = true;
                this.bloquerPiece();
            }
        }
        return res;
    }

    private void bloquerPiece() {

    }

    public void moveGauche() {
        this.pieceInstant = this.j.getPiece();
        if(!this.isMurGauche()){
            for(int i=0;i<4;i++) {
                this.pieceInstant[i][1] -= 1;
            }
            this.j.rafraichirGrille();
            this.j.setPiece(this.pieceInstant);
        }
    }
    public boolean isMurGauche(){
        boolean res = false;
        for(int i=0;i<4;i++) {
            //verification sur la colonne précédente
            int ligne = this.pieceInstant[i][0];
            int colonne = this.pieceInstant[i][1]-1;
            if(this.j.getGrille()[ligne][colonne] != 0 && this.j.getGrille()[ligne][colonne] != 2){
                res = true;
            }
        }
        return res;
    }
    public void moveDroite() {
        this.pieceInstant = this.j.getPiece();
        if(!this.isMurDroite()){
            for(int i=0;i<4;i++) {
                this.pieceInstant[i][1] += 1;
            }
            this.j.rafraichirGrille();
            this.j.setPiece(this.pieceInstant);
        }
    }
    public boolean isMurDroite() {
        boolean res = false;
        for(int i=0;i<4;i++) {
            //verification sur la colonne précédente
            int ligne = this.pieceInstant[i][0];
            int colonne = this.pieceInstant[i][1]+1;
            if(this.j.getGrille()[ligne][colonne] != 0 && this.j.getGrille()[ligne][colonne] != 2){
                res = true;
            }
        }
        return res;
    }


    /*public void augmenterPoints(int nbrLignesDetruites){
        this.model.addPoints(nbrLignesDetruites);

    }


*/
    private boolean isLigneComplete(int ligne){
        for(int i=0; i<15; i++) {
            if(this.j.getGrille()[ligne][i] == 0){
                return false;
            }
        }
        return true;
    }
    public int nbrLignesCompletes(){
        int lignes =0;
        for(int i=8;i>8-4; i--){
            if(isLigneComplete(i)) {
                lignes++;
            }
        }
        return lignes;
    }


    /*
    public boolean isPieceStockee() {
        return !(this.getStockerPiece() == null);
    }
    public boolean isPieceStockeeDejaAppelee(){
        return this.model.getUtiliserPiece();
    }*/

/*    public void utiliserPieceStockee() {
        int[][] pieceCourante = this.model.getPieceStockee();
        this.model.setPieceStockee(this.model.getPieceInstantanee());
        this.model.setPieceInstantanee(pieceCourante);
    }*/

}