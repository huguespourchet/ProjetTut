public class Model {
    private Grille grille;
    private int[][] tetriminoInstantanne;
    private Piece tabTetriminos;
    private boolean perdu;
    private int indiceTetriminoRotation;
    private int basDepart;
    private int lateraldepart;
    private int bas;
    private int lateral;
    public final int TAILLE_LIGNES = 15;
    public final int TAILLE_COLONNES = 10;


    public Model(){
        this.grille = new Grille(TAILLE_LIGNES, TAILLE_COLONNES);
        this.indiceTetriminoRotation = 0;
        this.bas = 0;
        this.lateral = 0;
        this.basDepart = 2;
        this.lateraldepart = (int)TAILLE_COLONNES/2;
    }

}
