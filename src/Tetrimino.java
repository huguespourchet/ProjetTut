import java.util.Arrays;
import java.util.Random;

public class Tetrimino {

    public enum TypeTetrimino {
        Z, S, I, T, O, L, J, Vide;

    }
    private int[][] coordsTetrimino;
    private int[][][] tableTetriminos;
    private TypeTetrimino type;
    private int indice;
    private int[][] cooBase;
    private Model model;

    public Tetrimino(Model model){
        this.tableTetriminos = new int[][][] {
                { { 0, -1 },  { 0, 0 },   { -1, 0 },  { -1, 1 } },
                { { 0, -1 },  { 0, 0 },   { 1, 0 },   { 1, 1 } },
                { { 0, -1 },  { 0, 0 },   { 0, 1 },   { 0, 2 } },
                { { -1, 0 },  { 0, 0 },   { 1, 0 },   { 0, 1 } },
                { { 0, 0 },   { 1, 0 },   { 0, 1 },   { 1, 1 } },
                { { -1, -1 }, { 0, -1 },  { 0, 0 },   { 0, 1 } },
                { { 1, -1 },  { 0, -1 },  { 0, 0 },   { 0, 1 } }
        };
        this.model = model;
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
                this.coordsTetrimino[i][j] = this.tableTetriminos[indice][i][j];
                this.cooBase[i][j] = this.tableTetriminos[indice][i][j];
            }
        }
        this.type = values[indice];
    }

    public int[][] descendrePiece() {
        int[][]ancienneCoos = new int[4][2];
        int[][] tetri = new int[4][2];
            for (int i = 0; i < 4; i++) {
                tetri[i][0] = this.getCoordsTetrimino()[i][0]+1;
                tetri[i][1] = this.getCoordsTetrimino()[i][1];
                ancienneCoos[i][0] = this.getCoordsTetrimino()[i][0];
                ancienneCoos[i][1] = this.getCoordsTetrimino()[i][1];
            }
            this.setCoordsTetrimino(tetri);
            return ancienneCoos;
    }

    public boolean isBloqueBas() {
        Tetrimino.TypeTetrimino[][] grillecopy = Arrays.copyOf(this.model.getGrille(), this.model.getGrille().length);
        for(int i=0; i<4; i++){
            grillecopy[this.getCoordsTetrimino()[i][0]][this.getCoordsTetrimino()[i][1]] = Tetrimino.TypeTetrimino.Vide;
        }
        for(int i=0;i<4;i++) {
            //verification sur la ligne suivante
            int ligne = this.getCoordsTetrimino()[i][0]+1;
            int colonne = this.getCoordsTetrimino()[i][1];
            if(ligne >= this.model.TAILLE_LIGNES)
                return true;
            if(grillecopy[ligne][colonne] != Tetrimino.TypeTetrimino.Vide){
                return true;
            }
        }
        return false;
    }

    public void moveGauche() {
        if(!isMurGauche()) {
            int[][] tetri = new int[4][2];
            for (int i = 0; i < 4; i++) {
                tetri[i][0] = this.getCoordsTetrimino()[i][0];
                tetri[i][1] = this.getCoordsTetrimino()[i][1]-1;
            }
            this.setCoordsTetrimino(tetri);
        }
        else {
            int[][] tetri = new int[4][2];
            for (int i = 0; i < 4; i++) {
                tetri[i][0] = this.getCoordsTetrimino()[i][0];
                tetri[i][1] = this.getCoordsTetrimino()[i][1];
            }
            this.setCoordsTetrimino(tetri);
        }
    }
    public boolean isMurGauche(){
        Tetrimino.TypeTetrimino[][] grillecopy = Arrays.copyOf(this.model.getGrille(), this.model.getGrille().length);
        for(int i=0; i<4; i++){
            grillecopy[this.getCoordsTetrimino()[i][0]][this.getCoordsTetrimino()[i][1]] = Tetrimino.TypeTetrimino.Vide;
        }
        for(int i=0;i<4;i++) {
            //verification sur la ligne suivante
            int ligne = this.getCoordsTetrimino()[i][0];
            int colonne = this.getCoordsTetrimino()[i][1]-1;
            if(colonne == 0)
                return true;
            if(grillecopy[ligne][colonne] != Tetrimino.TypeTetrimino.Vide){
                return true;
            }
        }
        return false;
    }
    public void moveDroite() {
        if (!isMurDroite()) {
            int[][] tetri = new int[4][2];
            for (int i = 0; i < 4; i++) {
                tetri[i][0] = this.getCoordsTetrimino()[i][0];
                tetri[i][1] = this.getCoordsTetrimino()[i][1]+1;
            }
            this.setCoordsTetrimino(tetri);
        }
    }
    public boolean isMurDroite() {
        Tetrimino.TypeTetrimino[][] grillecopy = Arrays.copyOf(this.model.getGrille(), this.model.getGrille().length);
        for(int i=0; i<4; i++){
            grillecopy[this.getCoordsTetrimino()[i][0]][this.getCoordsTetrimino()[i][1]] = Tetrimino.TypeTetrimino.Vide;
        }
        for(int i=0;i<4;i++) {
            //verification sur la ligne suivante
            int ligne = this.getCoordsTetrimino()[i][0];
            int colonne = this.getCoordsTetrimino()[i][1]+1;
            if(colonne >= this.model.TAILLE_COLONNES)
                return true;
            if(grillecopy[ligne][colonne] != Tetrimino.TypeTetrimino.Vide){
                return  true;
            }
        }
        return false;
    }

    public void rotate() {
        for(int i=0; i<4; i++){
            this.tableTetriminos[this.indice][i][0] = -this.tableTetriminos[this.indice][i][1];
            this.tableTetriminos[this.indice][i][1] = this.tableTetriminos[this.indice][i][0];
            this.coordsTetrimino[i][0] += this.tableTetriminos[this.indice][i][0];
            this.coordsTetrimino[i][1] += this.tableTetriminos[this.indice][i][1];
        }
    }

    public int[][] getCoordsTetrimino() {
        return coordsTetrimino;
    }

    public int[][][] getCoordsTable() {
        return tableTetriminos;
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
