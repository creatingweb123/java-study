import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
// 2533
public class Appdynamic1 {
    public static List<Integer>[] trees = new ArrayList[1000001];
    public static int[][]dp = new int[1000001][2];
    public static boolean[]visited = new boolean[1000001];
    public static void dfs(int current){
        dp[current][0]=1;
        visited[current] = true;
        for(int next:trees[current]){
            if(visited[next]) continue;
            dfs(next);
            dp[current][1]+=dp[next][0];
            dp[current][0]+=Math.min(dp[next][0],dp[next][1]);
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in)); 
        
        int N = Integer.parseInt(bf.readLine());
        boolean[]checkC = new boolean[N+1];
        for(int i=1;i<=N;i++){
            trees[i] = new ArrayList<Integer>();
        }

        for(int i=0;i<N-1;i++){
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child  = Integer.parseInt(st.nextToken());
            trees[parent].add(child);
            trees[child].add(parent);
        }
        dfs(1);
        System.out.println(Math.min(dp[1][0],dp[1][1]));
    }
}