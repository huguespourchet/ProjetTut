import java.awt.event.*;
import javax.swing.*;

public class ControlMenu implements ActionListener {

    Fenetre fenetre;

    public ControlMenu(Fenetre fenetre) { this.fenetre = fenetre; }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == fenetre.getVersion1()){
            try {
                fenetre.changerVersion(1);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
        else if(e.getSource() == fenetre.getVersion2()){
            try {
                fenetre.changerVersion(2);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }else if(e.getSource() == fenetre.getPause()){
            //fenetre.changerVersion(2); //Pause
        }else if(e.getSource() == fenetre.getMuteSound()){
            //fenetre.changerVersion(2); //Mute Sound
        }
  }

}

