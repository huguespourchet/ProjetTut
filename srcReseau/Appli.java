import javax.swing.*;
import java.io.IOException;
import java.net.Socket;

public class Appli {
    private static Plateau plateau;
    private static Fenetre fen;

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                int portTetris = 6667;

                String nomMachine = JOptionPane.showInputDialog
                        (null, "Nom de la machine hôte ?");

                Socket socket = null;
                try {
                    socket = new Socket(nomMachine, portTetris);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                JoueurClient joueur = null;
                try {
                    joueur = new JoueurClient(socket);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    ControlGroup control = new ControlGroup();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                JFrame fenetre = new JFrame();
                fenetre.setContentPane(new FenetreJoueurClient(joueur));
                fenetre.pack();
                fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                fenetre.setVisible(true);

            }
        });
    }
}
