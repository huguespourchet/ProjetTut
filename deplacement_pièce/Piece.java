import java.awt.Image;
 
public class Piece {
 
    static final int GAUCHE = -1;
    static final int DROITE = 1;
    static final int PAS_DE_DEPLACEMENT = 3;
 
    private int posX;
    private int posY;
    private int direction;
    private Image image;
 
    public void Piece() {
        this.posX = 10;
        this.posY = 10;
    }
    public void update() {
        if(this.direction == GAUCHE)
            posX -= PAS_DE_DEPLACEMENT;
        else if(this.direction == DROITE)
            posX += PAS_DE_DEPLACEMENT;
    }
 
   public void setDirection(int direction) {
      this.direction = direction;
   }
 
   public Image getImage() {
       return this.image;
   }
 
   public int getX() {
       return this.posX;
   }
    
   public int getY() {
       return this.posY;
   }
 
   public int getImageWidth() {
       return this.image.getWidth(null);
   }
    
   public int getImageHeight() {
       return this.image.getHeight(null);
   }
 
}
