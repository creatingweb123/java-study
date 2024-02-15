
// 백준 2243
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Vector;

public class SegmentTree7 {

	public static int[] tree;
	public static int[] datas;
	public static Vector<Integer> v;
	public static HashMap<Integer, Integer> map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		tree = new int[N * 4];
		datas = new int[N];
		v = new Vector<>();
		map = new HashMap<>();

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			int a = Integer.parseInt(br.readLine());
			v.add(a);
			datas[i] = a;
		}
		Collections.sort(v);
		for (int i = 0; i < N; i++) {
			map.put(v.get(i), i + 1);
		}
		for (int i = 0; i < N; i++) {
			int dataIndex = map.get(datas[i]);
			sb.append((i + 1 - sum(1, N, 1, 1, dataIndex - 1)) + "\n");
			update(1, N, 1, dataIndex);
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	public static void update(int start, int end, int node, int index) {
		if (index < start || index > end)
			return;
		tree[node] += 1;
		if (start == end)
			return;
		int mid = (start + end) / 2;
		update(start, mid, node * 2, index);
		update(mid + 1, end, node * 2 + 1, index);
	}

	public static int sum(int start, int end, int node, int left, int right) {
		if (right < start || end < left)
			return 0;
		if (left <= start && end <= right)
			return tree[node];
		int mid = (start + end) / 2;
		return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
	}
}