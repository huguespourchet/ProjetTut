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
        addKeyListener(new TAdapter());
    }

    //à utiliser pour dessiner dans la grille
    private int getSquareWidth(){
        return (int) getSize().getWidth() / this.model.TAILLE_COLONNES;
    }
    private int squareHeight() {
        return (int) getSize().getHeight() / this.model.TAILLE_LIGNES;
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }

    public void doDrawing(Graphics g) {
        var size = getSize();
        int top = (int) size.getHeight() - this.model.TAILLE_LIGNES * squareHeight();
        for (int i = 0; i < this.model.TAILLE_LIGNES; i++) {
            for (int j = 1; j < this.model.TAILLE_COLONNES; j++) {
                for(int k=0; k<8; k++) {
                    if (Tetrimino.TypeTetrimino.values()[k] == this.model.getGrille()[i][j]) {
                        g.setColor(this.model.getColor()[k]);
                        break;
                    }
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
        repaint();
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
    }

    private void update() {
        if(model.isPause()){
           return;
        }
        else if(!this.model.getPerdu() && this.controlPlateau.nbrLignesCompletes() == 0) {
            this.controlPlateau.descendrePiece();
            Tetrimino.TypeTetrimino[][] grillecopy = Arrays.copyOf(this.model.getGrille(), this.model.getGrille().length);
            for (int i = 0; i < 4; i++) {
                int x = this.model.getPieceInstantanee().getCoordsTetrimino()[i][0];
                int y = this.model.getPieceInstantanee().getCoordsTetrimino()[i][1];
                grillecopy[x][y] = this.model.getPieceInstantanee().getType();
            }
            this.model.setGrille(grillecopy);
        }else{
            this.controlPlateau.descendreGrille(this.controlPlateau.nbrLignesCompletes());
        }
        repaint();
    }

    public void pause() {
        this.model.setPause(!this.model.isPause());
        //afficher pause
    }

    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            if(Plateau.this.model.getPieceInstantanee().getType() == Tetrimino.TypeTetrimino.Vide)
                return;

            int keycode = e.getKeyCode();

            switch (keycode) {
                case KeyEvent.VK_P: Plateau.this.pause();break;
                case KeyEvent.VK_LEFT: Plateau.this.controlPlateau.moveGauche();break;
                case KeyEvent.VK_RIGHT: Plateau.this.controlPlateau.moveDroite();break;
                case KeyEvent.VK_DOWN: Plateau.this.controlPlateau.descendrePiece();break;
                case KeyEvent.VK_UP: Plateau.this.controlPlateau.rotationPiece();break;

                case KeyEvent.VK_SPACE: Plateau.this.controlPlateau.dropDown();break;
            }
        }
    }

}
