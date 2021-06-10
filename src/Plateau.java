import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Time;
import java.util.Arrays;

public class Plateau extends JPanel {

    private Model model;
    private ControlPlateau controlPlateau;

    public Plateau(Model model, ControlPlateau controlPlateau){
        this.model = model;
        this.controlPlateau = controlPlateau;
        initPlateau();
    }

    /**
     * relie plateau de jeu à panel de la fenetre
     */
    public void initPlateau(){
        setFocusable(true);
        addKeyListener(new TAdapter());
    }

    /**
     * donne les coordonnées correspondant à une case de la grille de jeu dans la fenetre
     * @return la taille d'une case en largeur
     */
    private int getSquareWidth(){
        return (int) getSize().getWidth() / this.model.TAILLE_COLONNES;
    }
/**
 * donne les coordonnées correspondant à une case de la grille de jeu dans la fenetre
 * @return la taille d'une case en hauteur
 * */
    private int squareHeight() {
        return (int) getSize().getHeight() / this.model.TAILLE_LIGNES;
    }

    /**
     * redéfinition de la focntion repaint()
     * @param g Graphics object
     */
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }

    /**
     * dessin de carrés colorés dans la grille de jeu, en fonction du type de chaque case
     * @param g Graphics object
     */
    public void doDrawing(Graphics g) {
        var size = getSize();
        int top = (int) size.getHeight() - this.model.TAILLE_LIGNES * squareHeight();
        //parcours de la grille de jeu
        for (int i = 0; i < this.model.TAILLE_LIGNES; i++) {
            for (int j = 1; j < this.model.TAILLE_COLONNES; j++) {
                //initialise la couleur en fonction du type piece(blanc pour vide)
                for(int k=0; k<8; k++) {
                    if (Tetrimino.TypeTetrimino.values()[k] == this.model.getGrille()[i][j]) {
                        g.setColor(this.model.getColor()[k]);
                        break;
                    }
                }
                //dessin aux coordonnées dans la fenetre
                drawSquare(g, j * getSquareWidth(),
                        top + i * squareHeight());
            }
        }
    }

    /**
     * dessin d'un carré de taille de d'emplacement prédéfinis
     * @param g Graphics object
     * @param x
     * @param y
     */
    private void drawSquare(Graphics g, int x, int y) {
        g.fillRect(x + 1, y + 1, getSquareWidth() - 2, squareHeight() - 2);
    }

    /**
     * initialise le chronometre executant un cycle de jeu
     * @param fen
     * @throws InterruptedException
     */
    public void play(Fenetre fen) throws InterruptedException {
        repaint();
        Timer timer = new Timer(this.model.getVitesse(), new GameCycle());
        timer.start();
    }

    private class GameCycle implements ActionListener {
        /**
         * action listener reliant le chronomètre à la fonction d'exécution du cycle
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            doGameCycle();
        }
    }

    /**
     * appel le controller relié à la classe plateau pour exécuter le cycle
     */
    public void doGameCycle() {
        this.controlPlateau.cycle();
        repaint();

    }

    /**
     * met ou enlève la pause du jeu
     */
    public void pause() {
        this.model.setPause(!this.model.isPause());
        //afficher pause
    }

    class TAdapter extends KeyAdapter {
        /**
         * écoute les touches de clavier et appel les fonctions correspondantes
         * @param e
         */
        @Override
        public void keyPressed(KeyEvent e) {

            if(Plateau.this.model.getPieceInstantanee().getType() == Tetrimino.TypeTetrimino.Vide)
                return;

            int keycode = e.getKeyCode();

            switch (keycode) {
                case KeyEvent.VK_P: Plateau.this.pause();break;
                case KeyEvent.VK_LEFT: Plateau.this.controlPlateau.deplacementGauche();break;
                case KeyEvent.VK_RIGHT: Plateau.this.controlPlateau.deplacementDroit();break;
                case KeyEvent.VK_DOWN: Plateau.this.controlPlateau.descendrePiece();break;
                case KeyEvent.VK_UP: Plateau.this.controlPlateau.rotationPiece();break;

                case KeyEvent.VK_SPACE: Plateau.this.controlPlateau.descendreMax();break;
            }
        }
    }

}
