// 백준 1916 priority queue 다익스트라

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class App18{
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
	public static int dijkstra(List<Pair>[]arr, int N, int start, int end){
		int[] minValue = new int[N+1];
		Arrays.fill(minValue,Integer.MAX_VALUE);
		minValue[0] = 0;

		PriorityQueue<Pair> queue = new PriorityQueue<>();
		minValue[start] = 0;
		queue.add(new Pair(start,0));

		while(!queue.isEmpty()){
			Pair cur = queue.poll();
			int cur_node = cur.y;
			int cur_dist = cur.value;

			if(minValue[cur_node]<cur_dist) continue;
			for(Pair nextPair: arr[cur_node]){
				int next = nextPair.y;
				int next_dist = cur_dist + nextPair.value;

				if(next_dist<minValue[next]){
					minValue[next] = next_dist;
					queue.add(new Pair(next,next_dist));
				}
			}
		}
		return minValue[end];
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		int start, end;
		List<Pair>[] arr = new ArrayList[N+1];
		StringTokenizer st;
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
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end   = Integer.parseInt(st.nextToken());

		System.out.println(dijkstra(arr, N, start, end));
	}

}