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


    public void initPlateau(){
        setFocusable(true);
 /*       addKeyListener(new TAdapter());*/
    }

    //à utiliser pour dessiner un carré
    private int getSquareWidth(){
        return (int) getSize().getWidth() / this.model.TAILLE_COLONNES+1;
    }
    private int squareHeight() {
        return (int) getSize().getHeight() / this.model.TAILLE_LIGNES+1;
    }


    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }
    private void doDrawing(Graphics g) {

        var size = getSize();
        int top = (int) size.getHeight() - this.model.TAILLE_LIGNES * squareHeight();
        for (int i = 0; i < this.model.TAILLE_LIGNES; i++) {
            for (int j = 0; j < this.model.TAILLE_COLONNES; j++) {
                if(this.model.getGrille()[i][j] != 0) {
                    g.setColor(this.model.getColor()[this.model.getGrille()[i][j]]);
                }else{
                    g.setColor(this.model.getColor()[0]);
                }
                drawSquare(g, j * getSquareWidth(),
                        top + i * squareHeight());
            }
        }
    }
    private void drawSquare(Graphics g, int x, int y) {
        g.fillRect(x + 1, y + 1, getSquareWidth() - 2, squareHeight() - 2);
    }

    public void play(Fenetre fen) throws InterruptedException {
        int x;
        int y;
        for (int i = 0; i < 4; i++) {
            x = this.model.getPieceInstantanee().getCoordsTetrimino()[i][0];
            y = this.model.getPieceInstantanee().getCoordsTetrimino()[i][1];
        }
        fen.repaint();
/*        Chrono timer = new Chrono(this.model.getVitesse());
        timer.start();*/
        Timer timer = new Timer(this.model.getVitesse(), new GameCycle());
        timer.start();
    }
    private class GameCycle implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            doGameCycle();
        }
    }
    public void doGameCycle() {

        update();
        repaint();
    }

    private void update() {
        if(!this.model.isPause() || !this.model.getPerdu()) {
            if (this.controlPlateau.nbrLignesCompletes() != 0) {
                this.controlPlateau.descendreGrille(this.controlPlateau.nbrLignesCompletes());
            }
            this.controlPlateau.descendrePiece();
            int[][] grillecopy = Arrays.copyOf(this.model.getGrille(), this.model.getGrille().length);
            for (int i = 0; i < 4; i++) {
                int x = this.model.getPieceInstantanee().getCoordsTetrimino()[i][0];
                int y = this.model.getPieceInstantanee().getCoordsTetrimino()[i][1];
                grillecopy[x][y] = this.model.getPieceInstantanee().getIndice();
            }
            this.model.setGrille(grillecopy);
        }
    }


    /*
    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            if (pieceCourrante.getType() == Tetrimino.TypeTetrimino.Vide) {

                return;
            }

            int keycode = e.getKeyCode();

            // Java 12 switch expressions
            switch (keycode) {
                case KeyEvent.VK_P -> pause();
                case KeyEvent.VK_LEFT -> tryMove(pieceCourrante, curX - 1, curY);
                case KeyEvent.VK_RIGHT -> tryMove(pieceCourrante, curX + 1, curY);
                case KeyEvent.VK_DOWN -> tryMove(pieceCourrante.rotationDroite(), curX, curY);
                case KeyEvent.VK_UP -> tryMove(pieceCourrante.rotationGauche(), curX, curY);
                case KeyEvent.VK_SPACE -> dropDown();
                case KeyEvent.VK_D -> oneLineDown();
            }
        }
    }
*/

}
