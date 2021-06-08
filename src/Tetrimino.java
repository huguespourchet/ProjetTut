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



    // Rotation Droite
//    public Tetrimino rotationDroite() {
//        Tetrimino result = new Tetrimino();
//        for (int i = 0; i < 4; ++i) {
//            result.setX(i, -y(i));
//            result.setY(i, x(i));
//        }
//        return result;
//    }

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
