import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class JoueurServeur extends Joueur implements Runnable  {
	private ServeurTetris serveur;

	public JoueurServeur(Socket socket, int numero, ServeurTetris serveur) {
		super(socket, numero);
		this.serveur = serveur;
	}

	/**
	 * envoyer le numéro (id) du joueur au serveur
	 */
	public void envoyerNumero() {
		out.println(Constantes.NUM + " " + numero);
	}

	/**
	 * traite le message envoyé par un joueur
	 * @param message : message à traiter
	 */
	void traiterMessage(String message) {
		int type;
		Scanner scan = new Scanner(message);
		type = scan.nextInt();
	}

	/**
	 * définit si le joueur a gagné
	 */
	public void aGagne() {
		super.aGagne();
		out.println(Constantes.GAGNE);
		if (partieGagnee()) out.println(Constantes.PARTIE_GAGNEE); 
	}

	/**
	 * définit si le joueur a gagné
	 */
	public void aPerdu() {
		super.aPerdu();
		out.println(Constantes.PERDU);
		if (adversaire.partieGagnee()) out.println(Constantes.PARTIE_PERDUE);
	}

	/**
	 * définit si les joueurs sont à égalité
	 */
	public void egalite() {
		System.out.println("egalite");
		super.egalite();
		out.println(Constantes.EGALITE);
	}

	public void run() {
		String message;
		try {
			message = in.readLine();
			while (!jeuFini) {
				traiterMessage(message);
				message = in.readLine();
			}
		}
		catch(IOException exc) {

		}
	}
}