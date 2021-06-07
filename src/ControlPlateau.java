import java.util.Arrays;

public class ControlPlateau {

    private Model model;

    public ControlPlateau(Model model) throws InterruptedException {
        //initialisation de la taille du tableau contenant le tetrimino qui descend
        this.model = model;
    }

    public void descendreGrille(int nbrLignesCompletes) {
        for (int i = this.model.TAILLE_LIGNES-1; i > this.model.TAILLE_LIGNES-1 - 4; i--) {
            for (int j = 1; j < this.model.TAILLE_COLONNES; j++) {
                this.model.getGrille()[i][j] = 0;
            }
        }
        for (int i = 8 - this.model.TAILLE_LIGNES; i > 0; i--) {
            for (int j = 13; j > 0; j--) {
                if (this.model.getGrille()[i][j] == 1) {
                    this.model.getGrille()[i - this.model.TAILLE_LIGNES][j] = 1;
                    this.model.getGrille()[i][j] = 0;
                }
            }
        }
    }


    public void descendrePiece() {
        if(!isBloqueBas()) {
            int[][] grillecopy = Arrays.copyOf(this.model.getGrille(), this.model.getGrille().length);
            int[][] tetri = new int[4][2];
            for (int i = 0; i < 4; i++) {
                tetri[i][0] = this.model.getPieceInstantanee().getCoordsTetrimino()[i][0]+1;
                tetri[i][1] = this.model.getPieceInstantanee().getCoordsTetrimino()[i][1];
                grillecopy[this.model.getPieceInstantanee().getCoordsTetrimino()[i][0]][this.model.getPieceInstantanee().getCoordsTetrimino()[i][1]] = 0;
            }
            this.model.setGrille(grillecopy);
            this.model.getPieceInstantanee().setCoordsTetrimino(tetri);
        }
    }

    private boolean isBloqueBas() {
        boolean res = false;
        for(int i=0;i<4;i++) {
            //verification sur la ligne suivante
            int ligne = this.model.getPieceInstantanee().getCoordsTetrimino()[i][0]+1;
            int colonne = this.model.getPieceInstantanee().getCoordsTetrimino()[i][1];
            if(ligne >= this.model.TAILLE_LIGNES || (this.model.getGrille()[ligne][colonne] != 0 && this.model.getGrille()[ligne][colonne] != this.model.getPieceInstantanee().getIndice())){
                res = true;
                this.bloquerPiece();
            }
        }
        return res;
    }

    private void bloquerPiece() {
        Tetrimino tetrimino = new Tetrimino();
        this.model.setPieceInstantanee(tetrimino);
        this.model.initPiece();
    }

    public void moveGauche() {
        if(!isMurGauche()) {
            int[][] grillecopy = Arrays.copyOf(this.model.getGrille(), this.model.getGrille().length);
            int[][] tetri = new int[4][2];
            for (int i = 0; i < 4; i++) {
                tetri[i][0] = this.model.getPieceInstantanee().getCoordsTetrimino()[i][0];
                tetri[i][1] = this.model.getPieceInstantanee().getCoordsTetrimino()[i][1]-1;
                grillecopy[this.model.getPieceInstantanee().getCoordsTetrimino()[i][0]][this.model.getPieceInstantanee().getCoordsTetrimino()[i][1]] = 0;
            }
            this.model.setGrille(grillecopy);
            this.model.getPieceInstantanee().setCoordsTetrimino(tetri);
        }
    }
    public boolean isMurGauche(){
        boolean res = false;
        for(int i=0;i<4;i++) {
            //verification sur la ligne suivante
            int ligne = this.model.getPieceInstantanee().getCoordsTetrimino()[i][0];
            int colonne = this.model.getPieceInstantanee().getCoordsTetrimino()[i][1]-1;
            if(ligne >= this.model.TAILLE_COLONNES || (this.model.getGrille()[ligne][colonne] != 0 && this.model.getGrille()[ligne][colonne] != this.model.getPieceInstantanee().getIndice())){
                res = true;
            }
        }
        return res;
    }
    public void moveDroite() {
        if (!isMurDroite()) {
            int[][] grillecopy = Arrays.copyOf(this.model.getGrille(), this.model.getGrille().length);
            int[][] tetri = new int[4][2];
            for (int i = 0; i < 4; i++) {
                tetri[i][0] = this.model.getPieceInstantanee().getCoordsTetrimino()[i][0];
                tetri[i][1] = this.model.getPieceInstantanee().getCoordsTetrimino()[i][1]+1;
                grillecopy[this.model.getPieceInstantanee().getCoordsTetrimino()[i][0]][this.model.getPieceInstantanee().getCoordsTetrimino()[i][1]] = 0;
            }
            this.model.setGrille(grillecopy);
            this.model.getPieceInstantanee().setCoordsTetrimino(tetri);
        }
    }
    public boolean isMurDroite() {
        boolean res = false;
        for(int i=0;i<4;i++) {
            //verification sur la ligne suivante
            int ligne = this.model.getPieceInstantanee().getCoordsTetrimino()[i][0];
            int colonne = this.model.getPieceInstantanee().getCoordsTetrimino()[i][1]+1;
            if(ligne >= this.model.TAILLE_COLONNES || (this.model.getGrille()[ligne][colonne] != 0 && this.model.getGrille()[ligne][colonne] != this.model.getPieceInstantanee().getIndice())){
                res = true;
            }
        }
        return res;
    }

    public void augmenterPoints(int nbrLignesDetruites){
        this.model.addPoints(nbrLignesDetruites);

    }


    private boolean isLigneComplete(int ligne){
        for(int i=0; i<this.model.TAILLE_COLONNES; i++) {
            if(this.model.getGrille()[ligne][i] == 0){
                return false;
            }
        }
        return true;
    }
    public int nbrLignesCompletes(){
        int lignes =0;
        for(int i=this.model.TAILLE_LIGNES-1;i>this.model.TAILLE_LIGNES-4; i--){
            if(isLigneComplete(i)) {
                lignes++;
            }
        }
        return lignes;
    }



    public boolean isPieceStockee() {
        return !(this.model.getPieceStockee().getCoordsTetrimino() == null);
    }
    public boolean isPieceStockeeDejaAppelee(){
        return this.model.isPieceStockeeAppelee();
    }

    public void utiliserPieceStockee() {
        Tetrimino pivot = new Tetrimino(this.model.getPieceInstantanee());
        this.model.getPieceInstantanee().setCoordsTetrimino(this.model.getPieceStockee().getCoordsTetrimino());
        this.model.setPieceStockee(pivot);
    }

}