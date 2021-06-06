public class Model {
    private Plateau plateau;
    private Tetrimino pieceInstantanee;
    private boolean perdu;
    private int basDepart;
    private int lateraldepart;
    private int bas;
    private int lateral;
    public final int TAILLE_LIGNES = 15;
    public final int TAILLE_COLONNES = 22;
    private int vitesse;
    private boolean isPause;


    public Model(){
        this.plateau.initPlateau();
        initModel();
        this.pieceInstantanee = new Tetrimino();
    }
    public void initModel() {
        this.bas = 0;
        this.lateral = 0;
        this.basDepart = 2;
        this.lateraldepart = (int)TAILLE_COLONNES/2;
        this.perdu = false;
        this.vitesse = 400;
        this.isPause = false;
    }

    public Tetrimino getPieceInstantanee() {
        return pieceInstantanee;
    }

    public Plateau getPlateau() {
        return plateau;
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

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
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

    public int getBas() {
        return bas;
    }

    public void setBas(int bas) {
        this.bas = bas;
    }

    public int getLateral() {
        return lateral;
    }

    public void setLateral(int lateral) {
        this.lateral = lateral;
    }

    public void setPieceInstantanee(Tetrimino pieceInstantanee) {
        this.pieceInstantanee = pieceInstantanee;
    }
}
