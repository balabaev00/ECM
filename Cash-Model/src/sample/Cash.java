package sample;

public class Cash {
    private int[][][] array;

    public int[][][] getArray() {
        return array;
    }

    public void setArray(int[][][] array) {
        this.array = array;
    }

    public void setArrayValue(int value, int page, int n, int m) {
        array[page][n][m] = value;
    }

    public int getArrayValue(int page, int n, int m) {
        return array[page][n][m];
    }

    public Cash(int page,int n, int m) {
        array = new int[page][n][m];
    }

}
