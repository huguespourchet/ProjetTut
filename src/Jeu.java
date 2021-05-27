public class Jeu {
    private int[][] grille;
    private int[][] piece;
    private ControlJeu controlJ;

    public Jeu(){
        this.initGrille();
        this.piece = new int[][]{
                {2, 4},
                {3, 4},
                {4, 4},
                {4, 5}
        };
        this.play();
    }

    public static void main(String[] args) {
        Jeu j = new Jeu();
    }

    private void play() {
        int x;
        int y;
        for(int i=0; i<4;i++){
            x = this.piece[i][0];
            y = this.piece[i][1];
            this.grille[x][y]=2;
        }
        this.affiche();
        for(int i=0; i<8; i++){
            this.
        }
    }

    private void affiche() {
        for(int i=0;i<10;i++) {
            for (int j = 0; j < 15; j++) {
                System.out.print(this.grille[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }

    public void initGrille() {
        this.grille = new int[10][15];
        for(int i=0;i<10;i++){
            for(int j=0; j<15; j++){
                if(i==0 || i==9 || j==0 || j==14){
                    this.grille[i][j] = 9;
                }else{
                    this.grille[i][j] = 0;
                }
            }
        }
        this.affiche();
    }
}
