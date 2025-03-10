import java.util.ArrayList;
import java.util.List;

class ProgrammersContainer {
    private static final String[] STORAGE = { "AZWQY", "CAABX", "BBDDA", "ACACA" };
    private static final String[] REQUESTS = { "A", "BB", "A" };
    private int[][] condition;
    private char[][] container;

    // right down left up
    private int[][] fourWay = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    private static class Pair {
        private int row;
        private int column;

        public Pair(int row, int column) {
            this.row = row;
            this.column = column;
        }

        public int getRow() {
            return row;
        }

        public int getColumn() {
            return column;
        }
    }

    public static void main(String[] args) {
        new ProgrammersContainer().initMap(STORAGE.length, STORAGE[0].length());
    }

    public int solution(String[] storage, String[] requests) {
        int answer = 0;
        return answer;
    }

    public void initMap(int row, int column) {
        condition = new int[row + 2][column + 2];
        container = new char[row + 2][column + 2];

        // Initialize borders more efficiently
        for (int i = 0; i <= row + 1; i++) {
            if (i < row)
                condition[i + 1][1] = condition[i + 1][column] = 1;
            condition[i][0] = condition[i][column + 1] = 2;
        }

        for (int j = 0; j <= column + 1; j++) {
            if (j < column)
                condition[1][j + 1] = condition[row][j + 1] = 1;
            condition[0][j] = condition[row + 1][j] = 2;
        }

        // Fill container in a single loop
        for (int i = 0; i < row; i++) {
            String currentRow = STORAGE[i];
            for (int j = 0; j < column; j++) {
                container[i + 1][j + 1] = currentRow.charAt(j);
            }
        }
    }

    public void update() {

    }

    public void delete(int row, int column) {
        for (int i = 0; i < REQUESTS.length; i++) {
            String request = REQUESTS[i];
            if (request.length() == 2) {

            }
        }
    }

    public void deleteOne(int row, int column) {
        List<Pair> indexList = new ArrayList<>();
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= column; j++) {
                if (condition[i][j] == 1) {
                    condition[i][j] = 2;
                    indexList.add(new Pair(i, j));
                }
            }
        }
        for (Pair index : indexList) {
            int r = index.getRow();
            int c = index.getColumn();
            for (int w = 0; w < 4; w++) {
                int nextR = r + fourWay[w][0];
                int nextC = c + fourWay[w][1];
                if (condition[nextR][nextC] == 0) {
                    condition[nextR][nextC] = 1;
                }
            }
        }
    }

    public void deleteTwo(int row, int column) {

    }
    // public void circleSearch(boolean type, char request, int rowSize, int
    // columnSize) {
    // int wayIndex = 0;
    // int row = 1, column = 1;

    // while (!visited[row][column]) {
    // visited[row][column] = true;
    // if (container[row][column] == request && condition[row][column] != 2) {
    // // 지게차
    // if (type)
    // deleteOne(row, column);
    // // 크레인
    // else
    // deleteTwo(row, column);

    // }

    // int nextRow = row + fourWay[wayIndex][0];
    // int nextCol = column + fourWay[wayIndex][1];

    // if (visited[nextRow][nextCol]) {
    // wayIndex = (wayIndex + 1) % 4;
    // row = row + fourWay[wayIndex][0];
    // column = column + fourWay[wayIndex][1];
    // continue;
    // } else {
    // row = nextRow;
    // column = nextCol;
    // }
    // }
    // }

}