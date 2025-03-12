import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ProgrammersOddEvenTree {
    // Node class
    private static class Node {
        private List<Integer> child = new ArrayList<>();

        public void addChild(int childNum) {
            this.child.add(childNum);
        }

        public List<Integer> getChildren() {
            return child;
        }

    }

    public static void main(String[] args) {
        ProgrammersOddEvenTree tree = new ProgrammersOddEvenTree();
        int[] result = tree.solution();
        System.out.println("Result: " + result[0] + ", " + result[1]);
    }

    private static final int MAX_NODES = 400001;
    private Node[] nodeLink = new Node[MAX_NODES];
    private boolean[] visited = new boolean[MAX_NODES];
    // parent 홀짝child 역홀짝child
    // parent 홀짝 => 0, 역홀짝 => 1
    // child는 갯수
    private int[] checkBox = new int[3];

    private int[] nodes = { 9, 15, 14, 7, 6, 1, 2, 4, 5, 11, 8, 10 };
    private int[][] edges = { { 5, 14 }, { 1, 4 }, { 9, 11 }, { 2, 15 }, { 2, 5 }, { 9, 7 }, { 8, 1 }, { 6, 4 } };

    public int[] solution() {
        int[] answer = new int[2];
        initNode();
        checkTree(answer);
        checkState(answer);
        answer[0] -= 1;
        return answer;
    }

    // nodeLink, visited init
    public void initNode() {

        for (int i : nodes) {
            nodeLink[i] = new Node();
        }
        for (int[] edge : edges) {

            nodeLink[edge[0]].addChild(edge[1]);
            nodeLink[edge[1]].addChild(edge[0]);
        }
    }

    public void checkTree(int[] result) {
        Queue<Integer> queue = new LinkedList<>();
        int index = 0;
        queue.add(nodes[index++]);

        while (index < nodes.length) { // index <= size 대신 queue가 비어있지 않을 때까지
            if (queue.isEmpty()) {
                while (index < nodes.length && visited[nodes[index]]) {
                    index += 1;
                }

                if (index < nodes.length) {
                    queue.add(nodes[index++]);
                } else {
                    break;
                }
            }
            // queue pop
            int nodeNumber = queue.poll();
            // root node일때
            boolean isEven = nodeNumber % 2 == 0;
            boolean hasEvenChildren = nodeLink[nodeNumber].getChildren().size() % 2 == 0;
            if (!visited[nodeNumber]) {
                // 이전의 tree를 통해 update
                checkState(result);
                Arrays.fill(checkBox, 0);
                // 0: 홀짝트리, 1: 역홀짝트리리
                checkBox[0] = (isEven == hasEvenChildren) ? 0 : 1;

            } else {// 자식 node 일때
                // 부모 노드로 인해 해당 노드의 자식 노드의 개수가 1 추가된다.
                if (isEven == hasEvenChildren) {
                    checkBox[2]++;
                } else {
                    checkBox[1]++;
                }

            }
            visited[nodeNumber] = true;
            for (int child : nodeLink[nodeNumber].getChildren()) {
                if (!visited[child]) { // 방문하지 않은 노드만 큐에 추가
                    queue.add(child);
                    visited[child] = true;
                }
            }
            System.out.println("check2");
        }
    }

    public void checkState(int[] result) {

        if (checkBox[0] == checkBox[2]) {
            result[0] += (checkBox[0] == 0 || checkBox[1] > 0) ? 1 : 0;
        } else {
            result[1] += (checkBox[0] + checkBox[1] == 1 && (checkBox[1] == 0 || checkBox[2] > 0)) ? 1 : 0;
        }
    }
}
