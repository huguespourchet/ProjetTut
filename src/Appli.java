public class Appli {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Plateau plateau = new Plateau();
                try {
                    ControlGroup control = new ControlGroup();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
