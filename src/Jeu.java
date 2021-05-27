public class Jeu {
    private int[][] grille;
    private int[][] piece;
    private ControlJeu cj;

    public Jeu() throws InterruptedException {
        this.initGrille();
        this.piece = new int[][]{
                {1, 4},
                {2, 4},
                {3, 4},
                {3, 5}
        };
        cj = new ControlJeu(this);
        this.play();

    }

    private void play() throws InterruptedException {
        int x;
        int y;
        for(int i=0; i<4;i++){
            x = this.piece[i][0];
            y = this.piece[i][1];
            this.grille[x][y]=2;
        }
        this.affiche();
        for(int i=0; i<8; i++){
            Thread.sleep(500);
            this.cj.descendrePiece();
            this.affiche();
        }
    }

    private void affiche() {
        for(int i=0; i<4;i++){
            int x = this.piece[i][0];
            int y = this.piece[i][1];
            this.grille[x][y]=2;
        }
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
    }
    public void rafraichirGrille() {
        for(int i=0;i<10;i++) {
            for (int j = 0; j < 15; j++) {
                if (this.grille[i][j] == 2) {
                    this.grille[i][j] = 0;
                }
            }
        }
    }

    public int[][] getPiece() {
        return piece;
    }

    public void setPiece(int[][] piece) {
        this.piece = piece;
    }

    public int[][] getGrille() {
        return grille;
    }
}
