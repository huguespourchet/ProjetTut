import java.util.Random;

public class Tetrimino {

    private TypeTetrimino type;
    private int[][] coords;

    //Les types de Tetriminos
    public enum TypeTetrimino {
        Vide, Z, S, I, T, O, L, J
    }

    // Toutes les coordonnees possibles
    private int[][][] coordsTetrimino = new int[][][]{
            {{0, 0}, {0, 0}, {0, 0}, {0, 0}}, // Vide
            {{0, -1}, {0, 0}, {-1, 0}, {-1, 1}}, // Type Z
            {{0, -1}, {0, 0}, {1, 0}, {1, 1}}, // Type S
            {{0, -1}, {0, 0}, {0, 1}, {0, 2}}, // Type I
            {{-1, 0}, {0, 0}, {1, 0}, {0, 1}}, // Type T
            {{0, 0}, {1, 0}, {0, 1}, {1, 1}}, // Type O
            {{-1, -1}, {0, -1}, {0, 0}, {0, 1}}, // Type L
            {{1, -1}, {0, -1}, {0, 0}, {0, 1}} // Type J
    };

    // Definit le Tetrimino actuel, et charge ses coordonnees.
    public void setType(TypeTetrimino type) {
        for (int i = 0; i < 4; i++) {
            System.arraycopy(coordsTetrimino[type.ordinal()][i], 0, coords[i], 0, 2);
        }
        this.type = type;
    }

    public void setTypeAleatoire() {
        Random r = new Random();
        int x = Math.abs(r.nextInt()) % 7 + 1;
        TypeTetrimino[] values = TypeTetrimino.values();
        setType(values[x]);
    }

    private void setX(int index, int x) {
        coords[index][0] = x;
    }
    private void setY(int index, int y) {
        coords[index][1] = y;
    }
    public int x(int index) {
        return coords[index][0];
    }
    public int y(int index) {
        return coords[index][1];
    }

    public int minX() {
        int m = coords[0][0];
        for (int i = 0; i < 4; i++) {
            m = Math.min(m, coords[i][0]);
        }
        return m;
    }
    public int minY() {
        int m = coords[0][1];
        for (int i = 0; i < 4; i++) {
            m = Math.min(m, coords[i][1]);
        }
        return m;
    }
    // Rotation Gauche
    public Tetrimino rotationGauche() {
        if (type == TypeTetrimino.O)
            return this;
        Tetrimino result = new Tetrimino();
        result.type = type;
        for (int i = 0; i < 4; ++i) {
            result.setX(i, y(i));
            result.setY(i, -x(i));
        }
        return result;
    }
    // Rotation Droite
    public Tetrimino rotationDroite() {
        if (type == TypeTetrimino.O)
            return this;
        Tetrimino result = new Tetrimino();
        result.type = type;
        for (int i = 0; i < 4; ++i) {
            result.setX(i, -y(i));
            result.setY(i, x(i));
        }
        return result;
    }

}
