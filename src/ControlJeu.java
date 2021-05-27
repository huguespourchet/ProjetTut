public class ControlJeu {

    private int[][] pieceInstant;
    private Jeu j;

    public ControlJeu(Jeu j) throws InterruptedException {
        this.pieceInstant = new int[4][2];
        this.j = j;
    }

/*    public Tetrimino genererPiece(){
        //prend le tableau de tetriminos et choisit au hasard une pièce
        return this.model.getTetrimino(Math.random()*this.model.getTailleTableauTetrmino());
    }*/

    /*//a mettre dans modele jeu
    public void stockerPiece(Tetrimino tetrimino) {
        this.setStockerPiece(tetrimino);
    }*/

    public void descendrePiece() {
        this.pieceInstant = this.j.getPiece();
        if(!this.isBloqueBas()){
            for(int i=0;i<4;i++) {
                this.pieceInstant[i][0] += 1;
            }
            this.j.rafraichirGrille();
            this.j.setPiece(this.pieceInstant);
        }
    }

    private boolean isBloqueBas() {
        boolean res = false;
        for(int i=0;i<4;i++) {
            //verification sur la ligne suivante
            int ligne = this.pieceInstant[i][0]+1;
            int colonne = this.pieceInstant[i][1];
            if(this.j.getGrille()[ligne][colonne] != 0 && this.j.getGrille()[ligne][colonne] != 2){
                res = true;
            }
        }
        return res;
    }

   /* public void moveGauche() {
        //récupérer le tertrimono
        if(!this.isMurGauche()) {
            this.model.setY(this.model.getY()-1);
        }
        this.jeu.refreshGrille();
    }
    public void moveDroite() {
        //récupérer le tertrimono
        if(!isMurDroite()) {
            this.model.setY(this.model.getY()+1);
        }
        this.jeu.refreshGrille();
    }


    public void augmenterPoints(int nbrLignesDetruites){
        this.model.addPoints(nbrLignesDetruites);

    }
    public boolean isMurGauche(){

    }
    public boolean isMurDroite() {
        //meme chose mais vérifier si mlur à doirte
    }
    public boolean isBas() {
        //vérifier si une case d'une ligne en dessous du tetrimmino est différent de 0
    }
    public boolean isLigneComplete(int ligne){
        Grille grille = this.model.getGrille();
        for(int i=0; i<grille.nbrColones; i++) {
            if(grille[ligne][i] == 0){
                return false;
            }
        }
        return true;
    }
    public int nbrLignesCompletes(){
        int lignes =0;
        for(int i=grille.getNbrLignes();i>grille.getNbrLignes()-4; i--){
            if(isLigneComplete(i)) {
                lignes++;
            }
        }
        return lignes;
    }
    public boolean isPieceStockee() {
        return !(this.getStockerPiece() == null);
    }
    public boolean isPieceStockeeDejaAppelee(){
        return this.model.getUtiliserPiece();
    }
    public boolean isPerdu() {
        Grille grille = this.model.getGrille();
        for(int i=0; i<grille.nbrColonnes(); i++){
            if(grille[0][i] == 1) {
                return true;
            }
        }
        return false;
    }

    //trouver comment réupérer les coordonnées d'une piece dans la grille
*/
}