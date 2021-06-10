import java.awt.*;
import java.util.Arrays;

public class Model {

    public static final int TAILLE_LIGNES = 18;
    public static final int TAILLE_COLONNES = 20;

    private Tetrimino.TypeTetrimino[][] grille;
    private Tetrimino pieceInstantanee;
    private Tetrimino pieceStockee;
    private Color[] color;

    private boolean perdu;
    private int basDepart;
    private int lateraldepart;
    private int vitesse;
    public boolean isPause;
    private int points;
    private boolean pieceStockeeAppelee;
    private boolean isPiecePlace;

    public Model(){
        initModel();
        this.pieceInstantanee = new Tetrimino(this);
        this.pieceInstantanee.initTetrimino();
        initPiece();
        this.grille = new Tetrimino.TypeTetrimino[TAILLE_LIGNES][TAILLE_COLONNES];
        initGrille();
    }
    public void initGrille() {
        for (int i = 0; i < TAILLE_LIGNES; i++) {
            for (int j = 0; j < this.TAILLE_COLONNES; j++) {
                this.grille[i][j] = Tetrimino.TypeTetrimino.Vide;
            }
        }
    }

    public void initPiece(){
        int[][] tetri = new int[4][2];
        for(int i=0;i<4;i++){
            tetri[i][0] = this.pieceInstantanee.getCoordsTetrimino()[i][0] += this.basDepart;
            tetri[i][1] = this.pieceInstantanee.getCoordsTetrimino()[i][1] += this.lateraldepart;
        }
        this.pieceInstantanee.setCoordsTetrimino(tetri);
    }

    public void initModel() {
        this.isPiecePlace = false;
        this.basDepart = 2;
        this.lateraldepart = (int)TAILLE_COLONNES/2;
        this.perdu = false;
        this.vitesse = 300;
        this.isPause = false;
        this.points = 0;
        this.color = new Color[]{
                new Color(186, 10, 10),
                new Color(1, 212, 1), new Color(19, 19, 186),
                new Color(220, 220, 8), new Color(199, 11, 199),
                new Color(15, 229, 229), new Color(243, 188, 30),
                new Color(255, 255, 255)

        };
    }

    public void moveGauche(){
        if(!this.pieceInstantanee.isMurGauche())
            this.pieceInstantanee.moveGauche();
    }
    public void moveDroit(){
        if(!this.pieceInstantanee.isMurDroite())
            this.pieceInstantanee.moveDroite();
    }
    public void descendre(){
        int[][] effacer = new int[4][2];
        if(!this.pieceInstantanee.isBloqueBas()) {
            effacer = this.pieceInstantanee.descendrePiece();
            for(int i=0; i<4; i++){
                this.grille[effacer[i][0]][effacer[i][1]] = Tetrimino.TypeTetrimino.Vide;
            }
        }
        else
            bloquerPiece();
    }
    public void dropDown() {
        while (!this.pieceInstantanee.isBloqueBas()) {
            descendre();
        }
        this.bloquerPiece();
    }
    private void bloquerPiece() {
        for(int i=0; i<4; i++){
            grille[this.pieceInstantanee.getCoordsTetrimino()[i][0]][this.pieceInstantanee.getCoordsTetrimino()[i][1]] = this.pieceInstantanee.getType();
        }
        Tetrimino tetrimino = new Tetrimino(this);
        this.setPieceInstantanee(tetrimino);
        this.initPiece();
        for(int i=0;i<4;i++){
            if(this.grille[this.pieceInstantanee.getCoordsTetrimino()[i][0]][this.pieceInstantanee.getCoordsTetrimino()[i][1]] != Tetrimino.TypeTetrimino.Vide){
                this.perdu = true;
            }
        }

    }

    public int nbrLignesCompletes(){
        int lignes =0;
        for(int i=0; i<TAILLE_LIGNES; i++) {
            if (isLigneComplete(i))
                lignes++;
        }
        this.addPoints(lignes);
        return lignes;
    }

    private boolean isLigneComplete(int ligne){
        for(int i=0; i<this.TAILLE_COLONNES; i++) {
            if (this.grille[ligne][i] == Tetrimino.TypeTetrimino.Vide) {
                return false;
            }
        }
        return true;
    }

    public void descendreGrille(int nbrLignesCompletes) {
        for (int i = this.TAILLE_LIGNES-1; i > this.TAILLE_LIGNES-1 - 4; i--) {
            for (int j = 0; j < this.TAILLE_COLONNES; j++) {
                this.grille[i][j] = Tetrimino.TypeTetrimino.Vide;
            }
        }
        for (int i =this.TAILLE_LIGNES-1; i > 0; i--) {
            for (int j = this.TAILLE_COLONNES-1; j > 0; j--) {
                if (this.grille[i][j] == Tetrimino.TypeTetrimino.Vide) {
                    grille[i][j] = this.grille[i-1][j];
                    grille[i-1][j] = Tetrimino.TypeTetrimino.Vide;
                }
            }
        }
    }

    public void cycle(){
        if (this.isPause)
            return;
        else if (!this.perdu && this.nbrLignesCompletes() == 0) {
            if(!this.pieceInstantanee.isBloqueBas())
                this.pieceInstantanee.descendrePiece();
            else
                bloquerPiece();
            for (int i = 0; i < 4; i++) {
                int x = this.pieceInstantanee.getCoordsTetrimino()[i][0];
                int y = this.pieceInstantanee.getCoordsTetrimino()[i][1];
                this.grille[x][y] = this.pieceInstantanee.getType();
            }
        } else {
            if(this.nbrLignesCompletes() != 0)
                this.descendreGrille(this.nbrLignesCompletes());
            //else
                //perdu
        }
    }

    public boolean isPieceStockee() {
        return (this.getPieceStockee().getType()!= null);
    }
    public void utiliserPieceStockee() {
        Tetrimino pivot = new Tetrimino(this.getPieceInstantanee());
        this.pieceInstantanee.setCoordsTetrimino(this.pieceInstantanee.getCoordsTetrimino());
        this.setPieceStockee(pivot);
    }

    public void rotateTetriminoCourant() {
        for(int i=0;i<4;i++){
            this.grille[this.pieceInstantanee.getCoordsTetrimino()[i][0]][this.pieceInstantanee.getCoordsTetrimino()[i][1]] = Tetrimino.TypeTetrimino.Vide;
        }
        this.pieceInstantanee.rotate();
    }


    public Tetrimino getPieceInstantanee() {
        return pieceInstantanee;
    }

    public void augmenterPoints(int indiceMultiplicateur) {

    }

    public boolean getPerdu() {
        return this.perdu;
    }

    public int getVitesse() {
        return this.vitesse;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    public boolean isPause() {
        return isPause;
    }

    public void setPause(boolean pause) {
        isPause = pause;
    }

    public boolean isPerdu() {
        return perdu;
    }

    public void setPerdu(boolean perdu) {
        this.perdu = perdu;
    }

    public int getBasDepart() {
        return basDepart;
    }

    public void setBasDepart(int basDepart) {
        this.basDepart = basDepart;
    }

    public int getLateraldepart() {
        return lateraldepart;
    }

    public void setLateraldepart(int lateraldepart) {
        this.lateraldepart = lateraldepart;
    }

    public void setPieceInstantanee(Tetrimino pieceInstantanee) {
        this.pieceInstantanee = pieceInstantanee;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Tetrimino getPieceStockee() {
        return pieceStockee;
    }

    public void setPieceStockee(Tetrimino pieceStockee) {
        this.pieceStockee = pieceStockee;
    }

    public boolean isPieceStockeeAppelee() {
        return pieceStockeeAppelee;
    }

    public void setPieceStockeeAppelee(boolean pieceStockeeAppelee) {
        this.pieceStockeeAppelee = pieceStockeeAppelee;
    }

    public Color[] getColor() {
        return color;
    }

    public void setColor(Color[] color) {
        this.color = color;
    }

    public Tetrimino.TypeTetrimino[][] getGrille() {
        return grille;
    }

    public void setGrille(Tetrimino.TypeTetrimino[][] grille) {
        this.grille = grille;
    }
    

    public boolean isPiecePlace() {
        return isPiecePlace;
    }

    public void setPiecePlace(boolean piecePlace) {
        isPiecePlace = piecePlace;
    }

}