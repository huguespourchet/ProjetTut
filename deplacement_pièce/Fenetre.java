import javax.swing.JFrame;
 
class Fenetre extends JFrame
{
    GameView gameView;
    Game game;
    
    public Fenetre(Game game) {
        this.game = game;
        this.addKeyListener(game);
        this.gameView = new GameView(this.game);
        this.setSize(600,600);
        this.setContentPane(gameView);
        this.setVisible(true);
    }
 
    public void repaint() {
        gameView.repaint(); // Fait appel à paintComponent(Graphics) de GameView.
    }
}