import java.util.Arrays;

public class ControlPlateau {

    private Model model;

    public ControlPlateau(Model model) throws InterruptedException {
        //initialisation de la taille du tableau contenant le tetrimino qui descend
        this.model = model;
    }

    public void descendrePiece() {
        if(!isBloqueBas()){
            int[][] tetri = new int[4][2];
            for (int i = 0; i < 4; i++) {
                tetri[i][0] = this.model.getPieceInstantanee().getCoordsTetrimino()[i][0]+1;
                tetri[i][1] = this.model.getPieceInstantanee().getCoordsTetrimino()[i][1];
            }
            this.model.getPieceInstantanee().setCoordsTetrimino(tetri);

        }else
            this.bloquerPiece();
    }

    private boolean isBloqueBas() {
        Tetrimino.TypeTetrimino[][] grillecopy = Arrays.copyOf(this.model.getGrille(), this.model.getGrille().length);
        for(int i=0; i<4; i++){
            grillecopy[this.model.getPieceInstantanee().getCoordsTetrimino()[i][0]][this.model.getPieceInstantanee().getCoordsTetrimino()[i][1]] = Tetrimino.TypeTetrimino.Vide;
        }
        for(int i=0;i<4;i++) {
            //verification sur la ligne suivante
            int ligne = this.model.getPieceInstantanee().getCoordsTetrimino()[i][0]+1;
            int colonne = this.model.getPieceInstantanee().getCoordsTetrimino()[i][1];
            if(ligne >= this.model.TAILLE_LIGNES)
                return true;
            if(grillecopy[ligne][colonne] != Tetrimino.TypeTetrimino.Vide){
                return true;
            }
        }
        return false;
    }

    public void dropDown() {
        while (!isBloqueBas()) {
            descendrePiece();
        }
        bloquerPiece();
    }

    private void bloquerPiece() {
        Tetrimino.TypeTetrimino[][] grillecopy = Arrays.copyOf(this.model.getGrille(), this.model.getGrille().length);
        for(int i=0; i<4; i++){
            grillecopy[this.model.getPieceInstantanee().getCoordsTetrimino()[i][0]][this.model.getPieceInstantanee().getCoordsTetrimino()[i][1]] = this.model.getPieceInstantanee().getType();
        }
        Tetrimino tetrimino = new Tetrimino();
        this.model.setPieceInstantanee(tetrimino);
        this.model.initPiece();
    }

    public void moveGauche() {
        if(!isMurGauche()) {
            int[][] tetri = new int[4][2];
            for (int i = 0; i < 4; i++) {
                tetri[i][0] = this.model.getPieceInstantanee().getCoordsTetrimino()[i][0];
                tetri[i][1] = this.model.getPieceInstantanee().getCoordsTetrimino()[i][1]-1;
            }
            this.model.getPieceInstantanee().setCoordsTetrimino(tetri);
        }
        else {
            int[][] tetri = new int[4][2];
            for (int i = 0; i < 4; i++) {
                tetri[i][0] = this.model.getPieceInstantanee().getCoordsTetrimino()[i][0];
                tetri[i][1] = this.model.getPieceInstantanee().getCoordsTetrimino()[i][1];
            }
            this.model.getPieceInstantanee().setCoordsTetrimino(tetri);
        }
    }
    public boolean isMurGauche(){
        Tetrimino.TypeTetrimino[][] grillecopy = Arrays.copyOf(this.model.getGrille(), this.model.getGrille().length);
        for(int i=0; i<4; i++){
            grillecopy[this.model.getPieceInstantanee().getCoordsTetrimino()[i][0]][this.model.getPieceInstantanee().getCoordsTetrimino()[i][1]] = Tetrimino.TypeTetrimino.Vide;
        }
        for(int i=0;i<4;i++) {
            //verification sur la ligne suivante
            int ligne = this.model.getPieceInstantanee().getCoordsTetrimino()[i][0];
            int colonne = this.model.getPieceInstantanee().getCoordsTetrimino()[i][1]-1;
            if(colonne == 0)
                return true;
            if(grillecopy[ligne][colonne] != Tetrimino.TypeTetrimino.Vide){
                return true;
            }
        }
        return false;
    }
    public void moveDroite() {
        if (!isMurDroite()) {
            int[][] tetri = new int[4][2];
            for (int i = 0; i < 4; i++) {
                tetri[i][0] = this.model.getPieceInstantanee().getCoordsTetrimino()[i][0];
                tetri[i][1] = this.model.getPieceInstantanee().getCoordsTetrimino()[i][1]+1;
            }
            this.model.getPieceInstantanee().setCoordsTetrimino(tetri);
        }
    }
    public boolean isMurDroite() {
        Tetrimino.TypeTetrimino[][] grillecopy = Arrays.copyOf(this.model.getGrille(), this.model.getGrille().length);
        for(int i=0; i<4; i++){
            grillecopy[this.model.getPieceInstantanee().getCoordsTetrimino()[i][0]][this.model.getPieceInstantanee().getCoordsTetrimino()[i][1]] = Tetrimino.TypeTetrimino.Vide;
        }
        for(int i=0;i<4;i++) {
            //verification sur la ligne suivante
            int ligne = this.model.getPieceInstantanee().getCoordsTetrimino()[i][0];
            int colonne = this.model.getPieceInstantanee().getCoordsTetrimino()[i][1]+1;
            if(colonne >= this.model.TAILLE_COLONNES)
                return true;
            if(grillecopy[ligne][colonne] != Tetrimino.TypeTetrimino.Vide){
                return  true;
            }
        }
        return false;
    }

    public void augmenterPoints(int nbrLignesDetruites){
        this.model.addPoints(nbrLignesDetruites);

    }

    public boolean isLigneComplete(int ligne){
        for(int i=0; i<this.model.TAILLE_COLONNES-1; i++) {
            if (this.model.getGrille()[ligne][i] == Tetrimino.TypeTetrimino.Vide) {
                System.out.println(this.model.getGrille()[ligne][i]);
                return false;
            }
        }

        return true;
    }
    public int nbrLignesCompletes(){
        int lignes =0;
        for(int i=this.model.TAILLE_LIGNES-1;i>this.model.TAILLE_LIGNES-5; i--) {
            if (isLigneComplete(i))
                lignes++;
        }
        System.out.println(lignes);
        this.model.addPoints(lignes);
        return lignes;
    }

        public void clearPlateau() {
            Tetrimino.TypeTetrimino[][] grillecopy = Arrays.copyOf(this.model.getGrille(), this.model.getGrille().length);
            for (int i = 0; i < this.model.TAILLE_LIGNES; i++){
                for(int j=0;j<this.model.TAILLE_COLONNES; j++) {
                    grillecopy[i][j] = Tetrimino.TypeTetrimino.Vide;
                }
            }
            this.model.setGrille(grillecopy);
            this.model.initModel();
        }
    public void rotationPiece(){
        int[][] tetri = new int[4][2];
        for (int i = 0; i < 4; i++) {
            tetri[i][0] = this.model.getPieceInstantanee().getCooBase()[i][1] - this.model.getPieceInstantanee().getCoordsTetrimino()[i][0];
            tetri[i][1] = -this.model.getPieceInstantanee().getCooBase()[i][0] - this.model.getPieceInstantanee().getCoordsTetrimino()[i][1];
        }
        for (int i = 0; i < 4; i++) {
            System.out.println(tetri[i][0] + "|" + tetri[i][1]);
        }
        this.model.getPieceInstantanee().setCoordsTetrimino(tetri);
    }

    public void descendreGrille(int nbrLignesCompletes) {
        for (int i = this.model.TAILLE_LIGNES-1; i > this.model.TAILLE_LIGNES-1 - 4; i--) {
            for (int j = 0; j < this.model.TAILLE_COLONNES; j++) {
                this.model.getGrille()[i][j] = Tetrimino.TypeTetrimino.Vide;
            }
        }
        Tetrimino.TypeTetrimino[][] grillecopy = Arrays.copyOf(this.model.getGrille(), this.model.getGrille().length);
        for (int i =this.model.TAILLE_LIGNES-1; i > 0; i--) {
            for (int j = this.model.TAILLE_COLONNES-1; j > 0; j--) {
                if (this.model.getGrille()[i][j] == Tetrimino.TypeTetrimino.Vide) {
                    grillecopy[i][j] = this.model.getGrille()[i-1][j];
                    grillecopy[i-1][j] = Tetrimino.TypeTetrimino.Vide;
                }
            }
        }
    }


    public boolean isPieceStockee() {
        return (this.model.getPieceStockee().getType()!= null);
    }
    public void utiliserPieceStockee() {
        Tetrimino pivot = new Tetrimino(this.model.getPieceInstantanee());
        this.model.getPieceInstantanee().setCoordsTetrimino(this.model.getPieceStockee().getCoordsTetrimino());
        this.model.setPieceStockee(pivot);
    }

}