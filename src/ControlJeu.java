import java.util.Arrays;

public class ControlJeu {

    private int[][] pieceInstant;
    private Model model;

    public ControlJeu(Model model) throws InterruptedException {
        //initialisation de la taille du tableau contenant le tetrimino qui descend
        this.pieceInstant = new int[4][2];
        this.model = model;
    }

    public int[][] genererPiece(){
        //prend le tableau de tetriminos et choisit au hasard une pièce
        //return this.Tetrimino.getTetrimino(Math.random()*this.model.getTailleTableauTetrmino());
        //return de test en attendant les class nécessaires

    }

    public void tournerPiece() {
        if(this.model.getIndiceY() == 3)
            this.model.setIndiceY(0);
        else
            this.model.setIndiceY(this.model.getIndiceY()+1);
        int[][] newPiece = this.model.getTetrimino(this.model.getIndiceX(),this.model.getIndiceY());
        for(int i=0; i<4;i++){
            newPiece[i][0] += this.model.getBas();
            newPiece[i][1] += this.model.getLateral();
        }
        //verification que la pièce tournée n'est pas obstruée, sinon on la remonte ou la déplace latéralement
        for(int indice=0;indice<4;indice++) {
            if (this.model.getGrille()[newPiece[indice][0]][newPiece[indice][1]] == 1 || newPiece[indice][0]>=this.model.TAILLE_LIGNES-1) {
                for (int j = 0; j < 4; j++) {
                    newPiece[j][0]--;
                }
            }
            if(this.model.getGrille()[newPiece[indice][0]][newPiece[indice][1]] == 9 || newPiece[indice][1]<=0 || newPiece[indice][1]>=this.model.TAILLE_COLONNES-1){
                for (int j = 0; j < 4; j++) {
                    if(this.model.getLateral()<0)
                        newPiece[j][1]++;
                    else
                        newPiece[j][1]--;
                }
            }
        }
        this.model.setTetriminoInstantane(newPiece);
    }

    public void descendrePiece() throws InterruptedException {
        this.pieceInstant = this.j.getPiece();
        if(!this.isBloqueBas()){
            for(int i=0;i<4;i++) {
                this.pieceInstant[i][0] += 1;
            }
            this.j.rafraichirGrille();
            this.j.setPiece(this.pieceInstant);
        }
        this.model.setBas(this.model.getBas()++);
    }

    private boolean isBloqueBas() throws InterruptedException {
        boolean res = false;
        for(int i=0;i<4;i++) {
            //verification sur la ligne suivante
            int ligne = this.pieceInstant[i][0]+1;
            int colonne = this.pieceInstant[i][1];
            if(this.j.getGrille()[ligne][colonne] != 0 && this.j.getGrille()[ligne][colonne] != 2){
                res = true;
                this.j.bloquerPiece();
            }
        }
        return res;
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