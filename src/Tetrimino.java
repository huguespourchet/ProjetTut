import java.util.Random;

public class Tetrimino {

    private int[][] coordsTetrimino;
    private int[][][] coordsTable;
    private int indice;


    public Tetrimino(){
        this.coordsTable = new int[][][] {
                { { 0, -1 },  { 0, 0 },   { -1, 0 },  { -1, 1 } },
                { { 0, -1 },  { 0, 0 },   { 1, 0 },   { 1, 1 } },
                { { 0, -1 },  { 0, 0 },   { 0, 1 },   { 0, 2 } },
                { { -1, 0 },  { 0, 0 },   { 1, 0 },   { 0, 1 } },
                { { 0, 0 },   { 1, 0 },   { 0, 1 },   { 1, 1 } },
                { { -1, -1 }, { 0, -1 },  { 0, 0 },   { 0, 1 } },
                { { 1, -1 },  { 0, -1 },  { 0, 0 },   { 0, 1 } }
        };
        initCoordTetrimino();
    }

    public Tetrimino(Tetrimino t){
        this.coordsTetrimino = t.coordsTetrimino;
    }

    public void initCoordTetrimino(){
        this.indice = (int)(Math.random()*7);
        this.coordsTetrimino = new int[4][2];
        for(int i=0;i<4;i++){
            for(int j=0;j<2;j++){
                this.coordsTetrimino[i][j] = this.coordsTable[indice][i][j];
            }
        }
    }


    private void setX(int index, int x) {
        coordsTetrimino[index][0] = x;
    }
    private void setY(int index, int y) {
        coordsTetrimino[index][1] = y;
    }
    public int x(int index) {
        return coordsTetrimino[index][0];
    }
    public int y(int index) {
        return coordsTetrimino[index][1];
    }

    public int minX() {
        int m = coordsTetrimino[0][0];
        for (int i = 0; i < 4; i++) {
            m = Math.min(m, coordsTetrimino[i][0]);
        }
        return m;
    }
    public int minY() {
        int m = coordsTetrimino[0][1];
        for (int i = 0; i < 4; i++) {
            m = Math.min(m, coordsTetrimino[i][1]);
        }
        return m;
    }
    // Rotation Gauche
    public Tetrimino rotationGauche() {
        Tetrimino result = new Tetrimino();
        for (int i = 0; i < 4; ++i) {
            result.setX(i, y(i));
            result.setY(i, -x(i));
        }
        return result;
    }
    // Rotation Droite
    public Tetrimino rotationDroite() {
        Tetrimino result = new Tetrimino();
        for (int i = 0; i < 4; ++i) {
            result.setX(i, -y(i));
            result.setY(i, x(i));
        }
        return result;
    }

    public int[][] getCoordsTetrimino() {
        return coordsTetrimino;
    }

    public int[][][] getCoordsTable() {
        return coordsTable;
    }

    public void setCoordsTetrimino(int[][] coordsTetrimino) {
        this.coordsTetrimino = coordsTetrimino;
    }

    public int getIndice() {
        return indice;
    }
}
