import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    private char[][] container;
    private int[][] fourWay = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
    private String[] storage;
    private String[] requests;

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

    public int solution(String[] storage, String[] requests) {
        int answer = 0;
        this.storage = storage;
        this.requests = requests;
        int row = storage.length;
        int column = storage[0].length();
        initMap(row, column);
        delete(row, column);
        answer = countContainer(row, column);
        return answer;
    }

    public void initMap(int row, int column) {
        container = new char[row + 2][column + 2];

        // Initialize borders more efficiently
        for (int i = 0; i <= row + 1; i++) {
            if (i < row)
                container[i + 1][1] = container[i + 1][column] = '0';
            container[i][0] = container[i][column + 1] = '0';
        }

        for (int j = 0; j <= column + 1; j++) {
            if (j < column)
                container[1][j + 1] = container[row][j + 1] = '0';
            container[0][j] = container[row + 1][j] = '0';
        }

        // Fill container in a single loop
        for (int i = 1; i <= row; i++) {
            String currentRow = storage[i - 1];
            for (int j = 1; j <= column; j++) {
                container[i][j] = currentRow.charAt(j - 1);
            }
        }
    }

    public void delete(int row, int column) {
        for (int i = 0; i < requests.length; i++) {
            String request = requests[i];
            if (request.length() == 2) {
                deleteTwo(request.charAt(0), row, column); // 크레인으로 컨테이너 이동
            } else {
                deleteOne(request.charAt(0), row, column); // 지게차로 컨테이너 이동
            }
            update(row, column); // 컨테이너 업데이트
        }
    }

    // 지게차로 컨테이너를 옮기는 함수
    public void deleteOne(char request, int row, int column) {
        List<Pair> indexList = new ArrayList<>();
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= column; j++) {
                if (container[i][j] == request) {
                    for (int w = 0; w < 4; w++) {
                        int nextR = i + fourWay[w][0];
                        int nextC = j + fourWay[w][1];
                        if (container[nextR][nextC] == '0') {
                            indexList.add(new Pair(i, j));
                            break;
                        }
                    }
                }
            }
        }
        for (Pair index : indexList) {
            int r = index.getRow();
            int c = index.getColumn();
            container[r][c] = '0';
        }
    }

    // 크레인으로 컨테이너를 옮기는 함수
    public void deleteTwo(char request, int row, int column) {
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= column; j++) {
                if (container[i][j] == request) {
                    container[i][j] = '1';
                }
            }
        }
    }

    public void update(int row, int column) {
        boolean[][] visited = new boolean[row + 2][column + 2];
        Queue<Pair> indexList = new LinkedList<>(); // <> 추가
        indexList.add(new Pair(0, 0));
        visited[0][0] = true;

        while (!indexList.isEmpty()) {
            Pair value = indexList.remove();
            int r = value.row;
            int c = value.column;

            for (int w = 0; w < 4; w++) {
                int nextR = r + fourWay[w][0];
                int nextC = c + fourWay[w][1];
                if (nextR >= 0 && nextR < row + 2 && nextC >= 0 && nextC < column + 2 && !visited[nextR][nextC]) {
                    if (container[nextR][nextC] == '1') {
                        container[nextR][nextC] = '0';
                        indexList.add(new Pair(nextR, nextC));
                        visited[nextR][nextC] = true;
                    } else if (container[nextR][nextC] == '0') {
                        indexList.add(new Pair(nextR, nextC));
                        visited[nextR][nextC] = true;
                    }
                }
            }
        }
    }

    public int countContainer(int row, int column) {
        int count = 0;
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= column; j++) {
                if (container[i][j] == '0' || container[i][j] == '1')
                    continue;

                count += 1;
            }
        }
        return count;
    }
}