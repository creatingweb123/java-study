
//13537 
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SegmentTree11 {
	public static int[] datas;
	public static int[][] tree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(br.readLine());

		datas = new int[N + 1];
		tree = new int[N * 4][];

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
			sb.append(find(1, N, 1, a, b, c) + "\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	public static void init(int start, int end, int node) {
		if (start == end) {
			tree[node] = new int[] { datas[start] };
			return;
		}
		int mid = (start + end) / 2;
		init(start, mid, node * 2);
		init(mid + 1, end, node * 2 + 1);
		tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
	}

	public static int[] merge(int[] a, int[] b) {
		int aStart = 0;
		int bStart = 0;

		int aLength = a.length;
		int bLength = b.length;
		int[] c = new int[aLength + bLength];
		for (int i = 0; i < c.length; i++) {
			if (aStart < aLength && (bStart >= bLength || a[aStart] < b[bStart])) {
				c[i] = a[aStart++];
			} else {
				c[i] = b[bStart++];
			}
		}
		return c;
	}

	public static int find(int start, int end, int node, int left, int right, int value) {
		if (right < start || end < left) {
			return 0;
		}
		if (left <= start && end <= right) {
			int len = tree[node].length;
			int idx = upperBound(tree[node], value);
			return len - idx;
		}
		int mid = (start + end) / 2;
		return find(start, mid, node * 2, left, right, value) + find(mid + 1, end, node * 2 + 1, left, right, value);
	}

	public static int upperBound(int[] a, int key) {
		int front = 0;
		int rear = a.length;
		int mid;
		while (front < rear) {
			mid = (front + rear) / 2;
			if (a[mid] <= key)
				front = mid + 1;
			else
				rear = mid;
		}
		return rear;
	}
}