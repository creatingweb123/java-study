// 백준 11505

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SegmentTree1{
	public static int[] datas;
	public static int[] tree;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		datas = new int[N+1];
		tree  = new int[N*4];
		for(int i=1;i<=N;i+=1){
			datas[i] = Integer.parseInt(br.readLine());	
		}
		initTree(1, N, 1);
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<M+K;i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if(a==1){
				datas[b] = c;
				update(1,N,1,b,c);
			}else{
				sb.append(sum(1, N, 1, b, (int)c)+"\n");
			}
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	public static int initTree(int start, int end, int node){
		if(start==end) return tree[node]=datas[start];
		int mid = (start+end)/2;
		return tree[node] = (int)((long)initTree(start, mid, node*2)*(long)initTree(mid+1, end, node*2+1)%1000000007);
	}
	public static int sum(int start, int end, int node, int left, int right){
		if(left>end||right<start) return 1;
		if(left<=start&&right>=end) return tree[node];
		int mid = (start+end)/2;
		return (int)(((long)sum(start, mid, node*2, left, right)*(long)sum(mid+1, end, node*2+1, left, right))%1000000007);
	}
	public static int update(int start, int end, int node, int index, int dif){
		if(index<start||index>end) return tree[node];
		if(start==end) return tree[node]=dif;
		int mid = (start+end)/2;
		return tree[node] = 
		(int)((long)update(start, mid, node*2, index, dif)*
		(long)update(mid+1, end, node*2+1, index, dif)%1000000007);
	}
}