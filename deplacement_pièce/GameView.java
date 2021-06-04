import java.awt.Color;
import java.awt.Graphics;
 
import javax.swing.JPanel;
 
class GameView extends JPanel
{
 
    Game game;
    
    public GameView(Game game) {
        this.game = game;
    }
 
    public void paintComponent(Graphics g) {
        this.drawBackGround(g);
    this.drawVoiture(g);
    }
 
    public void drawBackGround(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, 600, 600);
    }
 
    public void drawVoiture(Graphics g) {
       Piece voiture = game.getpiece();
       g.setColor(Color.black);
       g.fillRect(voiture.getX(), voiture.getY(), 10, 10);
 
      //g.drawImage(voiture.getImage(),voiture.getX(),voiture.getY(),voiture.getImageWidth(),voiture.getImageHeight(),null);
    }
}