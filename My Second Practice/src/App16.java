// 백준 1854 priority queue 다익스트라

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class App16{
	static class Pair implements Comparable<Pair>{
		int y;
		int value;
		Pair(int y, int value){
			this.y    = y;
			this.value= value;
		}
		@Override
		public int compareTo(Pair other){
			if(this.value==other.value){
				return this.y-other.y;
			}else{
				return this.value-other.value;
			}
		}
	}
	public static int[] dijkstra(List<Pair>[]arr, int N, int k){
		PriorityQueue<Integer>[] minValue = new PriorityQueue[N+1];
		for(int i=1;i<=N;i++){
			minValue[i] = new PriorityQueue<>(Collections.reverseOrder());
		}

		PriorityQueue<Pair> queue = new PriorityQueue<>();
		minValue[1].offer(0);
		queue.add(new Pair(1,0));

		while(!queue.isEmpty()){
			Pair cur = queue.poll();
			int cur_dist = cur.value;
			int cur_node = cur.y;
			if((minValue[cur_node].peek()!=null)&&(minValue[cur_node].peek()<cur_dist)&&(minValue[cur_node].size()>=k)) continue;
			for(Pair nextPair: arr[cur_node]){
				int next = nextPair.y;
				int next_dist = cur_dist + nextPair.value;
				if((minValue[next].peek()==null)||next_dist<minValue[next].peek()||minValue[next].size()<k){
					queue.add(new Pair(next,next_dist));
				}
				minValue[next].add(next_dist);
				if(minValue[next].size()>k) minValue[next].poll();
			}
		}
		int[] result = new int[N+1];
		for(int i=1;i<=N;i++){
			if(minValue[i].size()==k){
				result[i] = minValue[i].peek();
			}else{
				result[i] = -1;
			}
		}
		return result;
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		List<Pair>[] arr = new ArrayList[N+1];
		for(int i=1;i<=N;i++){
			arr[i] = new ArrayList<>();
		}
		for(int i=0;i<M;i++){
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			arr[x].add(new Pair(y,value));
		}
		int[]minValue = dijkstra(arr, N, k);
		for(int i=1;i<=N;i++){
			System.out.println(minValue[i]);
		}
	}

}