
// 백준 12844
// segment tree with lazy propagation
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SegmentTree11 {
	public static int[] datas;
	public static List<Integer>[] tree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(br.readLine());

		datas = new int[N + 1];
		tree = new ArrayList[N * 4];

		for (int i = 1; i <= N; i++) {
			datas[i] = Integer.parseInt(st.nextToken());
		}
		init(1, N, 1);
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			List<Integer> d = find(1, N, 1, a, b);
			int value = upperBound(d, c) + 1;
			sb.append((d.size() - value) + "\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	public static List<Integer> init(int start, int end, int node) {
		if (start == end) {
			tree[node] = new ArrayList<>();
			tree[node].add(datas[start]);
			return tree[node];
		}
		int mid = (start + end) / 2;
		return tree[node] = merge(init(start, mid, node * 2), init(mid + 1, end, node * 2 + 1));
	}

	public static List<Integer> merge(List<Integer> a, List<Integer> b) {
		int aStart = 0;
		int bStart = 0;

		int aLength = a.size();
		int bLength = b.size();
		if (aLength == 0)
			return b;
		if (bLength == 0)
			return a;

		List<Integer> c = new ArrayList<>();
		for (; aStart < aLength && bStart < bLength;) {
			if (a.get(aStart) < b.get(bStart)) {
				c.add(a.get(aStart++));
			} else {
				c.add(b.get(bStart++));
			}
		}
		for (; aStart < aLength;) {
			c.add(a.get(aStart++));
		}
		for (; bStart < bLength;) {
			c.add(b.get(bStart++));
		}
		return c;
	}

	public static List<Integer> find(int start, int end, int node, int left, int right) {
		if (right < start || end < left) {
			return new ArrayList<>();
		}
		if (left <= start && end <= right) {
			return tree[node];
		}
		int mid = (start + end) / 2;
		return merge(find(start, mid, node * 2, left, right), find(mid + 1, end, node * 2 + 1, left, right));
	}

	public static int upperBound(List<Integer> a, int key) {
		int front = 0;
		int rear = a.size() - 1;
		int mid;
		while (front < rear) {
			mid = (front + rear) / 2;
			if (a.get(mid) < key)
				front = mid + 1;
			else
				rear = mid;
		}
		return rear;
	}
}