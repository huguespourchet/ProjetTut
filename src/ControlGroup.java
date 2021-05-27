public class ControlGroup {

    private Plateau plateau;
    private Fenetre fenetre;
    public ControlBouton controlButton;
    public ControlMenu controlMenu;
    private ControlJeu controlJeu;


    public ControlGroup(Plateau plateau) {

        this.plateau = plateau;
        this.fenetre = new Fenetre(plateau);
        this.controlJeu = new ControlJeu();
        this.controlButton = new ControlBouton(plateau,fenetre);
        this.controlMenu = new ControlMenu(fenetre);

        fenetre.display();
    }
}