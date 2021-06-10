import java.util.Arrays;

public class ControlPlateau {

    private Model model;

    public ControlPlateau(Model model) throws InterruptedException {
        //initialisation de la taille du tableau contenant le tetrimino qui descend
        this.model = model;
    }

    /**
     * augmente le score du joueur
     * @param indiceMultiplicateur
     */
    public void augmenterPoints(int indiceMultiplicateur){
        this.model.augmenterPoints(indiceMultiplicateur);
    }

    /**
     * appel à la rotation du tetrimino
     */
    public void rotationPiece() {
        this.model.rotateTetriminoCourant();
    }

    /**
     * appel au déplacement à gauche du tetrimino
     */
    public void deplacementGauche(){
        this.model.moveGauche();
    }

    /**
     * appel au déplacement à droite du tetrimino
     */
    public void deplacementDroit(){
        this.model.moveDroit();
    }

    /**
     * appel à la descente du tetrimion
     */
    public void descendrePiece(){
        this.model.descendre();
    }

    /**
     * appel à la descente complete du tetrimino
     */
    public void descendreMax(){ this.model.dropDown(); }

    /**
     * appel au cycle de jeu
     */
    public void cycle() { this.model.cycle(); }


}