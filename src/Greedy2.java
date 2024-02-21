
// 백준 2437
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Greedy2 {
    static class Box {
        int h1, h2;
        int c;

        public Box(int h1, int h2, int c) {
            this.h1 = h1;
            this.h2 = h2;
            this.c = c;
        }
    }

    static int[] tree;
    static int[] lazy;
    final static int MAXVALUE = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(br.readLine());
        tree = new int[N * 4];
        lazy = new int[N * 4];
        initTree(1, N, 1, C);
        List<Box> array = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int h1 = Integer.parseInt(st.nextToken());
            int h2 = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            array.add(new Box(h1, h2, c));
        }
        Collections.sort(array, (a, b) -> {
            return a.h2 == b.h2 ? a.h1 - b.h1 : a.h2 - b.h2;
        });
        int result = 0;
        for (int i = 0; i < array.size(); i++) {
            Box tmp = array.get(i);
            int start = tmp.h1;
            int end = tmp.h2 - 1;
            int minValue = findMin(1, N, 1, start, end);
            if (minValue != 0) {
                int value = Math.min(minValue, tmp.c);
                result += value;
                updateRange(1, N, 1, start, end, -value);
            }
        }
        System.out.println(result);
    }

    public static int initTree(int start, int end, int node, int c) {
        if (start == end) {
            return tree[node] = c;
        }
        int mid = (start + end) / 2;
        return tree[node] = Math.min(initTree(start, mid, node * 2, c), initTree(mid + 1, end, node * 2 + 1, c));
    }

    public static int findMin(int start, int end, int node, int left, int right) {
        lazyUpdate(start, end, node);
        if (right < start || end < left) {
            return MAXVALUE;
        }
        if (left <= start && end <= right) {
            return tree[node];
        }
        int mid = (start + end) / 2;
        return Math.min(findMin(start, mid, node * 2, left, right), findMin(mid + 1, end, node * 2 + 1, left, right));
    }

    public static void lazyUpdate(int start, int end, int node) {
        if (lazy[node] != 0) {
            tree[node] += lazy[node];
            if (start != end) {
                lazy[node * 2] += lazy[node];
                lazy[node * 2 + 1] += lazy[node];
            }
            lazy[node] = 0;
        }
    }

    public static void updateRange(int start, int end, int node, int left, int right, int dif) {
        lazyUpdate(start, end, node);
        if (right < start || end < left)
            return;
        if (left <= start && end <= right) {
            tree[node] += dif;
            if (start != end) {
                lazy[node * 2] += dif;
                lazy[node * 2 + 1] += dif;
            }
            return;
        }
        int mid = (start + end) / 2;
        updateRange(start, mid, node * 2, left, right, dif);
        updateRange(mid + 1, end, node * 2 + 1, left, right, dif);
        tree[node] = Math.min(tree[node * 2], tree[node * 2 + 1]);
    }
}