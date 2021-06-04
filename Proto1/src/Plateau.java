import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Plateau extends JPanel implements ActionListener {

    private final int nbCasesLargeur = 10;
    private final int nbCasesHauteur = 22; // (On peut aussi choisir 20)
    private JLabel scoreLabel;
    private int largeurMenu;
    private ControleurPlateau controleur;
    //private ControleurIA IA;
    public Plateau(Fenetre fen){
        super();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
