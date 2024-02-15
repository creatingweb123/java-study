// 백준 4485 priority queue 다익스트라

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class App15{
	static class Pair implements Comparable<Pair>{
		int x;
		int y;
		int value;
		Pair(int x, int y, int value){
			this.x    = x;
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
	public static int dijkstra(int[][] arr, int N){
		int[][] around   = {{1,0},{-1,0},{0,1},{0,-1}};
		int[][] minValue = new int[N][N];
		for(int i=0;i<N;i++){
			Arrays.fill(minValue[i],Integer.MAX_VALUE);
		}

		PriorityQueue<Pair> queue = new PriorityQueue<>();
		queue.add(new Pair(0,0,arr[0][0]));

		while(!queue.isEmpty()){
			Pair cur = queue.poll();
			int cur_x = cur.x;
			int cur_y = cur.y;
			int cur_dist = cur.value;
			if(minValue[cur_x][cur_y]<cur_dist) continue;
			for(int[]a: around){
				int next_x = cur_x + a[0];
				int next_y = cur_y + a[1];
				if((next_x>=N)||(next_x<0)||(next_y>=N)||(next_y<0)) continue;
					
				int next_dist = cur_dist + arr[next_x][next_y];

				if(next_dist<minValue[next_x][next_y]){
					minValue[next_x][next_y] = next_dist;
					queue.add(new Pair(next_x, next_y, next_dist));
				}
			}
		}
		return minValue[N-1][N-1];
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int number= 1;
		while(true){
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			if(N==0) break;
			int[][] arr = new int[N][N];
			for(int i=0;i<N;i++){
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++){
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			System.out.println("Problem "+number+": "+dijkstra(arr, N));
			number+=1;
		}
	}

}