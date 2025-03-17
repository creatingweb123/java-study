import java.util.HashMap;
import java.util.Map;

public class ProgrammersOddEvenTree {
    public static void main(String[] args) {
        ProgrammersOddEvenTree tree = new ProgrammersOddEvenTree();
        int[] nodes = { 9, 15, 14, 7, 6, 1, 2, 4, 5, 11, 8, 10 };
        int[][] edges = { { 5, 14 }, { 1, 4 }, { 9, 11 }, { 2, 15 }, { 2, 5 }, {
                9, 7
        }, { 8, 1 }, { 6, 4 } };
        int[] result = tree.solution(nodes, edges);
        System.out.println("Result: " + result[0] + ", " + result[1]);
    }

    private int[] parent;
    private int[] indegree;
    private Map<Integer, TreeInfo> treeList;

    public void initTree(int[] nodes, int[][] edges) {
        int index = 0;
        for (int node : nodes) {
            index = Math.max(index, node);
        }
        parent = new int[index + 1];
        indegree = new int[index + 1];

        for (int node : nodes) {
            parent[node] = node;
        }

        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            indegree[a]++;
            indegree[b]++;
            merge(a, b);
        }
    }

    public int find(int num) {
        if (parent[num] == num)
            return num;
        return parent[num] = find(parent[num]);
    }

    public void merge(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);
        if (parentA != parentB) {
            parent[parentA] = parentB;
        }
    }

    public int[] solution(int[] nodes, int[][] edges) {
        int[] answer = new int[2];
        initTree(nodes, edges);
        treeList = new HashMap<>();
        for (int node : nodes) {
            int parentNode = find(node);
            boolean isEven = node % 2 == 0;
            boolean hasEvenChildren = indegree[node] % 2 == 0;
            // 부모
            TreeInfo parent = treeList.getOrDefault(parentNode, new TreeInfo());

            if (parentNode == node) {
                parent.parentCheck(isEven != hasEvenChildren);
            } else {
                if (isEven == hasEvenChildren) {
                    // child - parent edge로 child - child_child edge의 개수가 1 많아지기에 역홀짝트리를 증가
                    parent.addReverseOddEven();
                } else {
                    parent.addOddEven();
                }
            }
            treeList.put(parentNode, parent);
        }
        for (TreeInfo tmp : treeList.values()) {
            int check0 = tmp.isReverseOddEven;
            int check1 = tmp.getOddEven();
            int check2 = tmp.getReverseOddEven();
            if (check0 == check2) {
                answer[0] += (check1 + check2 == 1 || check2 == 0 || check1 > 0) ? 1 : 0;
                answer[1] += (check1 + check2 == 1) ? 1 : 0;
            } else {
                answer[1] += (check0 + check1 == 1 && (check1 == 0 || check2 > 0)) ? 1 : 0;
            }
        }
        return answer;
    }

    public class TreeInfo {
        private int isReverseOddEven;
        private int oddEven = 0;
        private int reverseOddEven = 0;

        public int isReverseOddEven() {
            return isReverseOddEven;
        }

        public int getOddEven() {
            return oddEven;
        }

        public int getReverseOddEven() {
            return reverseOddEven;
        }

        public void parentCheck(boolean check) {
            this.isReverseOddEven = check ? 1 : 0;
        }

        public void addOddEven() {
            this.oddEven++;
        }

        public void addReverseOddEven() {
            this.reverseOddEven++;
        }

    }
}
