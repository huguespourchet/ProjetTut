public class ControlGroup {

    private Plateau plateau;
    private Fenetre fenetre;
    private Jeu jeu;
    public ControlBouton controlButton;
    public ControlMenu controlMenu;
    private ControlJeu controlJeu;
    private Model model;

    public ControlGroup() throws InterruptedException {

        this.model = new Model();
        this.plateau = new Plateau(model);
        this.fenetre = new Fenetre(plateau);
        this.controlJeu = new ControlJeu(model);
        this.controlButton = new ControlBouton(plateau,fenetre);
        this.controlMenu = new ControlMenu(fenetre);
        fenetre.display();
    }
}