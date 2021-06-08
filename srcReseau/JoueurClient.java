import java.net.Socket;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class JoueurClient extends Joueur implements Runnable  {
	public JoueurClient() {}

	public JoueurClient(Socket socket)  throws Exception{	
		super(socket);
		adversaire = new JoueurClient();
	}	

	public void traiterMessage(String message) {
		Scanner scan = new Scanner(message);
		int type = scan.nextInt();
		switch(type) {
		case Constantes.NUM : 
			numero = scan.nextInt();
			jouer = true;
			break;
		case Constantes.GAGNE : 
			aGagne();
			break;
		case Constantes.PERDU : 
			aPerdu();
			break;
		case Constantes.EGALITE : 
			egalite();
			break;
		case Constantes.PARTIE_GAGNEE : 
			partieGagnee = true;
			jeuFini = true;
			break;
		case Constantes.PARTIE_PERDUE : 
			partiePerdue = true;
			jeuFini = true;
			break;
		case Constantes.CHOIX_ADVERSAIRE : 
			adversaire.choix = Choix.valueOf(scan.next());
			break;
		}
		setChanged();
		notifyObservers();
	}

	public void run() {
		while(!jeuFini)
			try {
				String message = in.readLine();
				traiterMessage(message);
			}
		catch(Exception exc) {
			exc.printStackTrace();
		}
	}

	public static void main(String[] arg) throws Exception {
		int portTetris = 6667;

		String nomMachine = JOptionPane.showInputDialog
		(null, "Nom de la machine hôte ?");

		Socket socket = new Socket(nomMachine, portTetris);
		JoueurClient joueur = new JoueurClient(socket);
		JFrame fenetre = new JFrame();
		fenetre.setContentPane(new FenetreJoueurClient(joueur));
		fenetre.pack();
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setVisible(true);
	}
}