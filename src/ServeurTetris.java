import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class ServeurTetris {
	public static int nbJoueurs = 1;
	private JoueurServeur[] lesJoueurs;
	private int numSuivant = 0;
	public ServeurTetris(JoueurServeur[] lesJoueurs) {
		this.lesJoueurs = lesJoueurs;
	}

	/**
	 * ajoute un joueur dans le serveur
	 * @param socket : serveur
	 */
	public void ajouterJoueur(Socket socket) {
		lesJoueurs[numSuivant] = new JoueurServeur(socket, numSuivant, this);
		numSuivant++;
		if (numSuivant == 2) confirmer();
	}

	/**
	 * confirme l'arrivée du joueur dans le serveur
	 */
	public void confirmer() {
		for (JoueurServeur j : lesJoueurs) j.envoyerNumero();
	}

	public static void main(String[] arg) throws Exception {
		int portTetris = 6667;
		ServerSocket receptionniste = new ServerSocket(portTetris);
		JoueurServeur[] lesJoueurs = new JoueurServeur[2];

		ServeurTetris serveur = new ServeurTetris(lesJoueurs);
		for (int i = 0; i < 4; i++){
			serveur.ajouterJoueur(receptionniste.accept());
			System.out.println("Nouveau Joueur");
			nbJoueurs+=1;
		}

		new ServeurTetris(lesJoueurs);
	}
}