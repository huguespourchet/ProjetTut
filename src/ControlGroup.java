public class ControlGroup {

    private Plateau plateau;
    private Fenetre fenetre;
    private Jeu jeu;
    public ControlBouton controlButton;
    public ControlMenu controlMenu;
    private ControlJeu controlJeu;


    public ControlGroup() throws InterruptedException {

        this.plateau = plateau;
        this.fenetre = new Fenetre(plateau);

        this.jeu = new Jeu();
        this.controlJeu = new ControlJeu(this.jeu);
        this.controlButton = new ControlBouton(plateau,fenetre);
        this.controlMenu = new ControlMenu(fenetre);


        fenetre.display();
    }
}