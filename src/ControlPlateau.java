import java.util.Arrays;

public class ControlPlateau {

    private Model model;

    public ControlPlateau(Model model) throws InterruptedException {
        //initialisation de la taille du tableau contenant le tetrimino qui descend
        this.model = model;
    }
    public void augmenterPoints(int indiceMultiplicateur){
        this.model.augmenterPoints(indiceMultiplicateur);
    }

    public void rotationPiece() {
        this.model.rotateTetriminoCourant();
    }

    public void deplacementGauche(){
        this.model.moveGauche();
    }

    public void deplacementDroit(){
        this.model.moveDroit();
    }

    public void descendrePiece(){
        this.model.descendre();
    }

    public void descendreMax(){ this.model.dropDown(); }

    public void cycle() { this.model.cycle(); }


}