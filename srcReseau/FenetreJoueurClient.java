import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class FenetreJoueurClient extends Box implements ActionListener, Observer {

	private JLabel nombrePoints = new JLabel("0");
	private JLabel action = new JLabel("attendre");
	private JoueurClient joueur;
	private JLabel nombrePointsAdversaire = new JLabel("0");
	private JButton boutonChoixAdversaire;
	private JButton boutonChoisi;

	public FenetreJoueurClient(JoueurClient joueur) {
		super(BoxLayout.Y_AXIS);
		this.joueur = joueur;
		joueur.addObserver(this);

		JPanel panneau = new JPanel();
		panneau.add(new JLabel("Le joueur : "));
		panneau.add(action);
		add(panneau);

		panneau = new JPanel();
		panneau.add(new JLabel("Nombre de points"));
		panneau.add(nombrePoints);
		add(panneau);

		panneau = new JPanel();

		add(panneau);
		if (!joueur.jouer) {
			//si le joueur n'a pas encore joué
		}
		else action.setText("Vous pouvez jouer");
			//actions

		add(new JLabel("L'adversaire"));
		panneau = new JPanel();
		add(panneau);

		panneau = new JPanel();
		panneau.add(new JLabel("Nombre de points de l'adversaire "));
		panneau.add(nombrePointsAdversaire);
		add(panneau);
	}

	public void actionPerformed(ActionEvent e) {
		boutonChoisi = (JButton) e.getSource();
		boutonChoisi.setBackground(Color.RED);
		joueur.choix = Choix.valueOf(boutonChoisi.getActionCommand());
		joueur.out.println(Constantes.CHOIX_JOUEUR + " " + boutonChoisi.getActionCommand());
		action.setText("Attendre");
		joueur.jouer = false;
		indiquerChoixAdversaire();
	}

	public void indiquerChoixAdversaire() {
		Choix choixAdversaire = joueur.adversaire.choix;

		if ((joueur.choix != null) &&( choixAdversaire != null)) {

		}
	}
	
	public void update(Observable o, Object obj) {
		nombrePoints.setText(Integer.toString(joueur.nbPoints));
		nombrePointsAdversaire.setText(Integer.toString(joueur.adversaire.nbPoints));
		indiquerChoixAdversaire();		
		if (joueur.partieGagnee) {
			action.setText("Vous avez gagné");
		}
		else if (joueur.partiePerdue) {
			action.setText("Vous avez perdu");
		}  
		else if (joueur.jouer) {
			action.setText("Vous pouvez jouer");
		}
	}
}