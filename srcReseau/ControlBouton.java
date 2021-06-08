import java.awt.event.*;

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
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        } else if(e.getSource() == fenetre.getReseau() ){
            System.out.println("reseau");
        } else if(e.getSource() == fenetre.getParam() ){
            System.out.println("paramètre");
        } else if(e.getSource() == fenetre.getExit() ){
            fenetre.dispose();
        }



    }
}
