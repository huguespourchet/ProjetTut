
public class Appli {
    private static Plateau plateau;
    private static Fenetre fen;

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {


                Plateau plateau = new Plateau(fen);
                try {
                    ControlGroup control = new ControlGroup(plateau);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
