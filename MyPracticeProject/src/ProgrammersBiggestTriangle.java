import java.util.LinkedList;
import java.util.Queue;

public class ProgrammersBiggestTriangle {

    public static void main(String[] args) {
        ProgrammersBiggestTriangle p = new ProgrammersBiggestTriangle();

        int[][] grid = { { 1 } };

        int answer = p.solution(grid);
        System.err.println("answer : " + answer);
    }
    // 0: 위쪽, 1: 아래쪽, 2: 왼쪽, 3: 오른쪽

    private int[][] direction = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    private NodeType[][] nodeTypeList;
    private boolean[][][] visited;

    public int solution(int[][] grid) {
        int answer = 0;
        initValue(grid);
        int rows = grid.length;
        int cols = grid[0].length;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int upTriangle = bfs(row, col, 0, rows, cols);
                int downTriangle = bfs(row, col, 1, rows, cols);
                answer = Math.max(answer, upTriangle);
                answer = Math.max(answer, downTriangle);
            }
        }
        return answer;
    }

    public void initValue(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        nodeTypeList = new NodeType[rows][cols];
        visited = new boolean[2][rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                nodeTypeList[i][j] = new NodeType();
                nodeTypeList[i][j].setConnection(grid[i][j]);
            }
        }
    }

    // type 0: up triangle, 1: down triangle
    public int bfs(int startRow, int startCol, int sType, int rows, int cols) {
        if (visited[sType][startRow][startCol])
            return 0;
        Queue<Node> queue = new LinkedList<>();
        boolean[][] tmpVisited = new boolean[rows][cols];

        queue.add(new Node(sType, startRow, startCol));

        int otherDirection = nodeTypeList[startRow][startCol].getConnection(sType);
        int maxRow = nodeTypeList.length;
        int maxCol = nodeTypeList[0].length;
        queue.add(new Node(otherDirection, startRow, startCol));

        visited[sType][startRow][startCol] = true;
        tmpVisited[startRow][startCol] = true;
        int size = -1;

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            int type = current.getType();
            int row = current.getRow();
            int col = current.getCol();
            size++;
            type = nodeTypeList[row][col].getConnection(type);

            int newType = typeChange(type);
            int newRow = row + direction[type][0];
            int newCol = col + direction[type][1];

            if (newRow >= 0 && newRow < maxRow && newCol >= 0 && newCol < maxCol) {
                int triangleType = nodeTypeList[newRow][newCol].triangleType(newType);
                if (visited[triangleType][newRow][newCol] || tmpVisited[newRow][newCol])
                    continue;

                visited[triangleType][newRow][newCol] = true;
                tmpVisited[newRow][newCol] = true;
                queue.add(new Node(newType, newRow, newCol));
            }
        }

        return size;
    }

    public int typeChange(int x) {
        switch (x) {
            case 0:
                return 1;
            case 1:
                return 0;
            case 2:
                return 3;
            default:
                return 2;
        }
    }

    public class NodeType {
        int[] connection = new int[4];

        public int getConnection(int x) {
            return connection[x];
        }

        public void setConnection(int x) {
            if (x == -1) {
                this.connection = new int[] { 3, 2, 1, 0 };
            } else {
                this.connection = new int[] { 2, 3, 0, 1 };
            }
        }

        public int triangleType(int x) {
            if (x == 0 || x == 1)
                return x;
            return connection[x];
        }
    }

    public class Node {
        private int type;
        private int row;
        private int col;

        public Node(int type, int row, int col) {
            this.type = type;
            this.row = row;
            this.col = col;
        }

        public int getType() {
            return type;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }
    }

}
