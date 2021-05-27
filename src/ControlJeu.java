public class ControlJeu {


/*    public Tetrimino genererPiece(){
        //prend le tableau de tetriminos et choisit au hasard une pièce
        return this.model.getTetrimino(Math.random()*this.model.getTailleTableauTetrmino());
    }*/

    /*//a mettre dans modele jeu
    public void stockerPiece(Tetrimino tetrimino) {
        this.setStockerPiece(tetrimino);
    }*/

    public void descendrePiece() {
        //récupérer le tertrimono
        //+1 ligne à toutes les coordonnées du tetriminos
        y = this.model.getX();
        if(!isBas()) {
            this.model.setX(x++);
            //fonction qui affiche la grille et les pièces à leurs emplacements et couleurs
            this.jeu.refreshGrille();
        }else{
            this.model.setTetriminoInstantane(null);
        }
    }

    public void moveGauche() {
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

}