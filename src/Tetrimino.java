import java.util.Random;

public class Tetrimino {
    
    public enum TypeTetrimino {
        Z, S, I, T, O, L, J, Vide;

    }
    private int[][] coordsTetrimino;
    private int[][][] coordsTable;
    private TypeTetrimino type;
    private int indice;
    private int[][] cooBase;

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
        initTetrimino();
    }

    public Tetrimino(Tetrimino t){
        this.coordsTetrimino = t.coordsTetrimino;
    }

    public void initTetrimino(){
        TypeTetrimino[] values = TypeTetrimino.values();
        this.indice = (int)(Math.random()*7);
        this.coordsTetrimino = new int[4][2];
        this.cooBase = new int[4][2];
        for(int i=0;i<4;i++){
            for(int j=0;j<2;j++){
                this.coordsTetrimino[i][j] = this.coordsTable[indice][i][j];
                this.cooBase[i][j] = this.coordsTable[indice][i][j];
            }
        }
        this.type = values[indice];
    }

    private void setX(int index, int x) {
        this.coordsTetrimino[index][0] = x;
    }
    private void setY(int index, int y) {
        this.coordsTetrimino[index][1] = y;
    }
    public int x(int index) {
        return this.coordsTetrimino[index][0];
    }
    public int y(int index) {
        return this.coordsTetrimino[index][1];
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

    public TypeTetrimino getType() {
        return this.type;
    }

    public void setType(TypeTetrimino type) {
        this.type = type;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public int[][] getCooBase() {
        return cooBase;
    }

    public void setCooBase(int[][] cooBase) {
        this.cooBase = cooBase;
    }
}
