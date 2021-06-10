import java.util.Arrays;
import java.util.Random;

public class Tetrimino {

    public enum TypeTetrimino {
        S, Z, I, T, O, L, J, Vide;

    }
    private int[][] coordsTetrimino;
    private int[][][] tableTetriminos;
    private TypeTetrimino type;
    private int indice;
    private int[][] cooBase;
    private Model model;

    public Tetrimino(Model model){
        this.tableTetriminos = new int[][][] {
                { { 0, -1 },  { 0, 0 },   { -1, 0 },  { -1, 1 } },// tetrimino de forme S
                { { 0, -1 },  { 0, 0 },   { 1, 0 },   { 1, 1 } },// tetrimino de form Z
                { { 0, -1 },  { 0, 0 },   { 0, 1 },   { 0, 2 } },//tetrimino de forme I
                { { -1, 0 },  { 0, 0 },   { 1, 0 },   { 0, 1 } },// tetrimino de forme T
                { { 0, 0 },   { 1, 0 },   { 0, 1 },   { 1, 1 } },//tetrimino de forme carré (O comme type)
                { { -1, -1 }, { 0, -1 },  { 0, 0 },   { 0, 1 } },// tetrimino de forme L
                { { 1, -1 },  { 0, -1 },  { 0, 0 },   { 0, 1 } }// tetrimino de forme J
        };
        this.model = model;
        initTetrimino();
    }

    public Tetrimino(Tetrimino t){
        this.coordsTetrimino = t.coordsTetrimino;
    }

    /**
     * initialise une pièce du tetris en prenant une pièce aléatoire du tableau de tetriminos
     */
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

    /**
     * descend de une case le tetriminos concerné dans la grille de jeu
     * @return les coordonnées du tetrimino avant de se déplacer pour l'effacer de la grille
     */
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

    /**
     * vérifie que la case en dessous du tetrimino est vide et ne sort pas de la grille
     * @return boolean
     */
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

    /**
     * déplace de une case à gauche le tetriminos concerné dans la grille de jeu
     */
    public void moveGauche() {
        if(!isMurGauche()) {
            int[][] tetri = new int[4][2];
            for (int i = 0; i < 4; i++) {
                tetri[i][0] = this.getCoordsTetrimino()[i][0];
                tetri[i][1] = this.getCoordsTetrimino()[i][1]-1;
            }
            this.setCoordsTetrimino(tetri);
        }
    }

    /**
     * vérifie que la case à gauche est vide et ne sort pas de la grille
     * @return boolean
     */
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

    /**
     * déplace le tetrimino d'une case à droite
     */
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

    /**
     * vérifie que la case à droite est vide et ne sort pas de la grille
     *@return boolean
     */
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

    /**
     * tourne le tetrimino sur lui même dans le sens horaire
     */
    public void rotate() {
        if(this.type == TypeTetrimino.O)
            return;
        for(int i=0; i<4; i++){
            this.tableTetriminos[this.indice][i][0] = -this.tableTetriminos[this.indice][i][1];
            this.tableTetriminos[this.indice][i][1] = this.tableTetriminos[this.indice][i][0];
            this.coordsTetrimino[i][0] += (this.tableTetriminos[this.indice][i][0]*2);
            this.coordsTetrimino[i][1] += (this.tableTetriminos[this.indice][i][1]*2);
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
