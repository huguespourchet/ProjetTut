import java.awt.*;

public class Model {
    private int[][] grille;
    private Tetrimino pieceInstantanee;
    private boolean perdu;
    private int basDepart;
    private int lateraldepart;
    public final int TAILLE_LIGNES = 18;
    public final int TAILLE_COLONNES = 20;
    private int vitesse;
    private boolean isPause;
    private int points;
    private Tetrimino pieceStockee;
    private boolean pieceStockeeAppelee;
    private Color[] color;
    private boolean lateralMouvement;
    private boolean lateralMouvement2;

    public Model(){
        initModel();
        this.pieceInstantanee = new Tetrimino();
        this.pieceInstantanee.initCoordTetrimino();
        initPiece();
        this.grille = new int[TAILLE_LIGNES][TAILLE_COLONNES];
        initGrille();
    }
    public void initGrille() {
        for (int i = 0; i < TAILLE_LIGNES; i++) {
            for (int j = 0; j < this.TAILLE_COLONNES; j++) {
                this.grille[i][j] = 0;
            }
        }
        //clear le panel
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
        this.basDepart = 2;
        this.lateraldepart = (int)TAILLE_COLONNES/2;
        this.perdu = false;
        this.vitesse = 500;
        this.isPause = false;
        this.points = 0;
        this.lateralMouvement = false;
        this.lateralMouvement2 = false;
        this.color = new Color[]{new Color(222, 99, 99),
                new Color(99, 222, 99), new Color(99, 99, 222),
                new Color(222, 222, 99), new Color(222, 99, 222),
                new Color(99, 222, 222), new Color(222, 166, 0),
                new Color(255, 255, 255)
        };
    }

    public Tetrimino getPieceInstantanee() {
        return pieceInstantanee;
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

    public int[][] getGrille() {
        return grille;
    }

    public void setGrille(int[][] grille) {
        this.grille = grille;
    }

    public boolean isLateralMouvement() {
        return lateralMouvement;
    }

    public void setLateralMouvement(boolean lateralMouvement) {
        this.lateralMouvement = lateralMouvement;
    }

    public boolean isLateralMouvement2() {
        return lateralMouvement2;
    }

    public void setLateralMouvement2(boolean lateralMouvement2) {
        this.lateralMouvement2 = lateralMouvement2;
    }
}
