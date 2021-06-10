import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlBouton implements ActionListener{

    Fenetre fenetre;
    Plateau plateau;

    public ControlBouton(Plateau plateau, Fenetre fenetre){
        this.fenetre = fenetre;
        this.plateau = plateau;
        //fen.setButtonControler(this);

    }


    public void actionPerformed(ActionEvent e){
        if(e.getSource() == fenetre.getPlay() ){
            try {
                fenetre.changerVersion(1);
            } catch (Exception interruptedException) {
                interruptedException.printStackTrace();
            }
        } else if(e.getSource() == fenetre.getReseau() ){
            try {
                fenetre.changerVersion(4);
            } catch (Exception interruptedException) {
                interruptedException.printStackTrace();
            }
        } else if(e.getSource() == fenetre.getParam() ){
            try {
                fenetre.changerVersion(3);
            } catch (Exception interruptedException) {
                interruptedException.printStackTrace();
            }
        } else if(e.getSource() == fenetre.getExit() ){
            fenetre.dispose();
        } else if(e.getSource() == fenetre.getRetour() ){
            try {
                fenetre.changerVersion(2);
            } catch (Exception interruptedException) {
                interruptedException.printStackTrace();
            }
        }



    }
}
