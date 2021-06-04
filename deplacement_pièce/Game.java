import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
 
class Game implements KeyListener
{
    Boolean gameRunning = true;
    Fenetre fenetre     = new Fenetre(this);
    Piece piece     = new Piece();
     
    public void run() {
        while(gameRunning) {
            this.logic();
            this.render();
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
 
    public void logic() {
        piece.update();
    }
 
    public void render() {
        fenetre.repaint();
    }
 
    public Piece getpiece() {
        return this.piece;
    }
     
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == 37) {  // Touche gauche.
            this.piece.setDirection(Piece.GAUCHE);
        }
        else if (e.getKeyCode() == 39) { // Touche droite.
            this.piece.setDirection(Piece.DROITE);
        }
    }
 
    @Override
    public void keyReleased(KeyEvent arg0) {
        this.piece.setDirection(0);
    }
 
    @Override
    public void keyTyped(KeyEvent arg0) {
        // TODO Auto-generated method stub
    }
}
