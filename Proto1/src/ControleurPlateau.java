import javax.swing.*;

public class ControleurPlateau {
    // Parametres du Jeu
    public Plateau vuePlateau;
    // Variables de la partie en cours
    public int iteration = 0;
    public int nblignesup;
    public boolean finChute = false;
    public Tetrimino tetriminoActuel;
    public Tetrimino.TypeTetrimino[] etatPlateau;
    public Tetrimino.TypeTetrimino[] saveetatPlateau;
    public boolean iamode = false;
    public Tetrimino.TypeTetrimino savetype;
    private int nbCasesLargeur;
    private int nbCasesHauteur;
    private boolean debutJeu = false;
    private boolean enPause = false;
    private int positionX = 0;
    private int positionY = 0;
    private Timer timer;
    private int score = 0;
    private int max = 0;
    private int nbrot1 = 0;
    private int indicedep1 = 0;
    private int nbrot2 = 0;
    private int indicedep2 = 0;
}
