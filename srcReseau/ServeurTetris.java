import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class ServeurTetris {
	private JoueurServeur[] lesJoueurs;
	private int numSuivant = 0;
	public ServeurTetris(JoueurServeur[] lesJoueurs) {
		this.lesJoueurs = lesJoueurs;
	}

	public void ajouterJoueur(Socket socket) {
		lesJoueurs[numSuivant] = new JoueurServeur(socket, numSuivant, this);
		numSuivant++;
		if (numSuivant == 2) confirmer();
	}

	public void traiterMessage(String message, Joueur joueur) {
		System.out.println(message);
		Scanner scan = new Scanner(message);
		int type = scan.nextInt();
	}

	public void confirmer() {
		for (JoueurServeur j : lesJoueurs) j.envoyerNumero();
	}

	public void attendre() {
		try {
			Thread.sleep(3000);
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	public static void main(String[] arg) throws Exception {
		int portTetris = 6667;
		ServerSocket receptionniste = new ServerSocket(portTetris);
		JoueurServeur[] lesJoueurs = new JoueurServeur[2];

		ServeurTetris serveur = new ServeurTetris(lesJoueurs);
		for (int i = 0; i < 2; i++)	serveur.ajouterJoueur(receptionniste.accept());

		new ServeurTetris(lesJoueurs);
	}
}