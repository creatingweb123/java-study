// 백준 2042

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
// bufferedwriter은 close해야한다
// bufferedreader은 하지 않는게 좋다.
// 선언만 하고 main 함수에서 할당가능
public class SegmentTree{
	public static long[] datas;
	public static long[] tree;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		datas = new long[N+1];
		tree  = new long[N*4];
		for(int i=1;i<=N;i+=1){
			datas[i] = Long.parseLong(br.readLine());	
		}
		initTree(1, N, 1);
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<M+K;i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			if(a==1){
				long dif = c-datas[b];
				datas[b] = c;
				update(1,N,1,b,dif);
			}else{
				sb.append(sum(1, N, 1, b, (int)c)+"\n");
			}
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	public static long initTree(int start, int end, int node){
		if(start==end) return tree[node]=datas[start];
		int mid = (start+end)/2;
		return tree[node] = initTree(start, mid, node*2) + initTree(mid+1, end, node*2+1);
	}
	public static long sum(int start, int end, int node, int left, int right){
		if(left>end||right<start) return 0;
		if(left<=start&&right>=end) return tree[node];
		int mid = (start+end)/2;
		return sum(start, mid, node*2, left, right)+sum(mid+1, end, node*2+1, left, right);
	}
	public static void update(int start, int end, int node, int index, long dif){
		if(index<start||index>end) return;
		tree[node] += dif;

		if(start==end) return;
		int mid = (start+end)/2;
		update(start, mid, node*2, index, dif);
		update(mid+1, end, node*2+1, index, dif);
	}
}