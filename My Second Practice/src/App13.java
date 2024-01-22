// 백준 1753 priority queue 다익스트라

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class App13{
	static class Pair implements Comparable<Pair>{
		int value;
		int y;
		Pair(int value, int y){
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
	public static int[] dijkstra(List<Pair>[]arr, int N, int M, int start){
		int[] minValue = new int[N+1];
		PriorityQueue<Pair> queue = new PriorityQueue<>();
		for(int i=1;i<=N;i++){
			minValue[i] = M*10+1;
		}
		minValue[start] = 0;
		queue.add(new Pair(0,start));
		while(!queue.isEmpty()){
			Pair cur = queue.poll();
			int cur_dist = cur.value;
			int cur_node = cur.y;
			if(minValue[cur_node]<cur_dist) continue;
			for(int i=0;i<arr[cur_node].size();i++){
				int next = arr[cur_node].get(i).y;
				int next_dist = cur_dist + arr[cur_node].get(i).value;
				if(next_dist<minValue[next]){
					minValue[next] = next_dist;
					queue.add(new Pair( next_dist,next));
				}
			}
		}
		return minValue;
	}
	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String[] inputS = bf.readLine().split(" ");
		int start = Integer.parseInt(bf.readLine());
		int N = Integer.parseInt(inputS[0]);
		int M = Integer.parseInt(inputS[1]);
		int x,y,value;
		List<Pair>[] arr = new ArrayList[N+1];
		for(int i=1;i<=N;i++){
			arr[i] = new ArrayList<>();
		}
		for(int i=0;i<M;i++){
			inputS = bf.readLine().split(" ");
			x   = Integer.parseInt(inputS[0]);
			y   = Integer.parseInt(inputS[1]);
			value=Integer.parseInt(inputS[2]);
			arr[x].add(new Pair(value,y));
		}
		int[]minValue = dijkstra(arr,N,M,start);
		for(int i=1;i<=N;i++){
			if(minValue[i]==M*10+1){
				System.out.println("INF");
			}
			else{
				System.out.println(minValue[i]);
			}
		}
	}

}