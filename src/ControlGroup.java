public class ControlGroup {

    private Plateau plateau;
    private Fenetre fenetre;
    public ControlBouton controlButton;
    public ControlMenu controlMenu;
    private ControlPlateau controlP;
    private Model model;

    public ControlGroup() throws InterruptedException {

        this.model = new Model();
        this.controlButton = new ControlBouton(plateau,fenetre);
        this.controlP = new ControlPlateau(model);
        this.plateau = new Plateau(model, controlP);
        this.fenetre = new Fenetre(plateau, controlP, model);
        fenetre.display();
    }
}