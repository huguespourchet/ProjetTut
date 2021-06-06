import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Plateau extends JPanel {

    private Model model;
    private int[][]  grille;

    public Plateau(Model model){
        this.model = model;
        this.grille = new int[this.model.TAILLE_LIGNES][this.model.TAILLE_COLONNES];
        initGrille();
        initPlateau();
    }

    public void initGrille(){
        for(int i=0; i<this.model.TAILLE_LIGNES;i++){
            for(int j=0;j<this.model.TAILLE_COLONNES;j++){
                this.grille[i][j] = 0;
            }
        }
        //clear le panel
    }

    public void initPlateau(){
        setFocusable(true);
        addKeyListener(new TAdapter());
    }

    //à utiliser pour dessiner un carré
    private int getSquareWidth(){
        return (int) getSize().getWidth() / this.model.TAILLE_COLONNES;
    }
    private int squareHeight() {
        return (int) getSize().getHeight() / this.model.TAILLE_LIGNES;
    }



    private void drawSquare(Graphics g, int x, int y, Tetrimino.TypeTetrimino type) {

        Color colors[] = {new Color(0, 0, 0), new Color(222, 99, 99),
                new Color(99, 222, 99), new Color(99, 99, 222),
                new Color(222, 222, 99), new Color(222, 99, 222),
                new Color(99, 222, 222), new Color(222, 166, 0)
        };

        var color = colors[type.ordinal()];

        g.setColor(color);
        g.fillRect(x + 1, y + 1, getSquareWidth() - 2, squareHeight() - 2);

        g.setColor(color.brighter());
        g.drawLine(x, y + squareHeight() - 1, x, y);
        g.drawLine(x, y, x + getSquareWidth() - 1, y);

        g.setColor(color.darker());
        g.drawLine(x + 1, y + squareHeight() - 1,
                x + getSquareWidth() - 1, y + squareHeight() - 1);
        g.drawLine(x + getSquareWidth() - 1, y + squareHeight() - 1,
                x + getSquareWidth() - 1, y + 1);
    }



    public void setScoreLabel(String text) {
        scoreLabel.setText(text);
    }

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

    public int[][] getGrille() {
        return grille;
    }

}
