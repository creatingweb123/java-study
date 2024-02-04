// 백준 2357

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SegmentTree2{
	public static int[] datas;
	public static int[][] tree;
	public static int[] value = new int[]{1000000001,0};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		datas = new int[N+1];
		tree  = new int[N*4][2];
		// 0: min, 1: max
		for(int i=1;i<=N;i+=1){
			datas[i] = Integer.parseInt(br.readLine());	
		}
		initTree(1, N, 1);
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<M;i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int[]result = calc(1, N, 1, a, b);
			sb.append(result[0]+" "+result[1]+"\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	public static int[] initTree(int start, int end, int node){
		if(start==end) {
			return tree[node] = new int[]{datas[start], datas[start]};
		} 
		int mid = (start+end)/2;
		return tree[node] = compare(initTree(start, mid, node*2),initTree(mid+1, end, node*2+1));
	}
	public static int[] calc(int start, int end, int node, int left, int right){
		if(left>end||right<start) return value;
		if(left<=start&&right>=end) return tree[node];
		int mid = (start+end)/2;
		return compare(calc(start, mid, node*2, left, right),calc(mid+1, end, node*2+1, left, right));
	}

	public static int[] compare(int[]a, int[]b){
		int[]result = {Math.min(a[0],b[0]),Math.max(a[1],b[1])};
		return result;
	}
}