// 백준 9376 priority queue 다익스트라

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class App17{
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
	public static int dijkstra(int[][] arr, int x, int y, List<Pair>where){
		int[][] around   = {{1,0},{-1,0},{0,1},{0,-1}};
		int[][][] minValueList = new int[3][x+2][y+2];

		for(int i=0;i<x+2;i++){
			Arrays.fill(minValueList[0][i],10001);
			Arrays.fill(minValueList[1][i],10001);
			Arrays.fill(minValueList[2][i],10001);
		}

		for(int i=0;i<3;i++){
			PriorityQueue<Pair> queue = new PriorityQueue<>();
			queue.add(where.get(i));
			while(!queue.isEmpty()){
				Pair cur = queue.poll();
				int cur_x = cur.x;
				int cur_y = cur.y;
				int cur_dist = cur.value;
				if(minValueList[i][cur_x][cur_y]<cur_dist) continue;
				for(int[]a: around){
					int next_x = cur_x + a[0];
					int next_y = cur_y + a[1];
					// 범위 초과
					if((next_x>x+1)||(next_x<0)||(next_y>y+1)||(next_y<0)) continue;
					// 지나지 못하는 블럭
					if(arr[next_x][next_y]==-1)continue;
					int next_dist = cur_dist + arr[next_x][next_y];
	
					if(next_dist<minValueList[i][next_x][next_y]){
						minValueList[i][next_x][next_y] = next_dist;
						queue.add(new Pair(next_x, next_y, next_dist));
					}
				}
			}
		}
		int[][] result = new int[x+2][y+2];
		int minValue = Integer.MAX_VALUE;
		for(int i=0;i<=x;i++){
			for(int j=0;j<=y;j++){
				result[i][j] = minValueList[0][i][j]+minValueList[1][i][j]+minValueList[2][i][j];
				if(arr[i][j]==1){result[i][j]-=2;}
				minValue = minValue<result[i][j]?minValue:result[i][j];
			}
		}
		
		return minValue;
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		for(int i=0;i<N;i++){
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int[][] arr    = new int[x+2][y+2];
			List<Pair>where= new ArrayList<>();
			where.add(new Pair(0,0,0));
			for(int j=1;j<=x;j++){
				st = new StringTokenizer(br.readLine());
				String ss = st.nextToken();
				for(int k=1;k<=y;k++){
					char s = ss.charAt(k-1);
					if(s=='*'){arr[j][k]=-1;}
					else if(s=='#'){arr[j][k]=1;}
					else if(s=='$'){
						arr[j][k]=0;
						where.add(new Pair(j,k,0));
					}else{arr[j][k]=0;}
				}
			}
			System.out.println(dijkstra(arr, x, y, where));
		}
	}

}

// *5**6**1*
// *4**5**9*
// *3**4**8*
// *2**.**7*
// *1*3.4*6*
// *$12*56$*
// *1*****5*
// *.2.3.4.*
// *********