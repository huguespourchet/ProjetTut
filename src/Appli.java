
public class Appli {
    private static Plateau plateau;
    private static Fenetre fen;

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    ControlGroup control = new ControlGroup();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
