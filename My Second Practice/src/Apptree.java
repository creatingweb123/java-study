import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
// 2213
public class Apptree {
    public static List<Integer>[] trees = new ArrayList[10001];
    public static int[]values = new int[10001];
    public static int[][]dp = new int[10001][2];
    public static boolean[]visited = new boolean[10001];
    public static PriorityQueue<Integer> results = new PriorityQueue<>();
    public static class Pair{
        int current;
        boolean pCheck;
        Pair(int current, boolean pCheck){
            this.current = current;
            this.pCheck  = pCheck;
        }
    }

    public static void dfs(int current){
        dp[current][1]=values[current];
        visited[current] = true;
        for(int next:trees[current]){
            if(visited[next]) continue;
            dfs(next);
            dp[current][1]+=dp[next][0];
            dp[current][0]+=Math.max(dp[next][0],dp[next][1]);
        }
    }
    public static void findRoute(int start,int N){
        for(int i=1;i<=N;i++){visited[i]=false;}
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(start,true));
        while(!queue.isEmpty()){
            boolean notIn = true;
            Pair tmp = queue.poll();
            int current = tmp.current;
            boolean pCheck = tmp.pCheck;
            visited[current]=true;
            if(pCheck){
                if(dp[current][1]>dp[current][0]){
                    results.add(current);
                    notIn = false;
                }
            }
            for(int next:trees[current]){
                if(visited[next])continue;
                queue.add(new Pair(next, notIn));
            }
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in)); 
        
        int N = Integer.parseInt(bf.readLine());
        for(int i=1;i<=N;i++){
            trees[i] = new ArrayList<Integer>();
        }
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i=1;i<=N;i++){
            values[i] = Integer.parseInt(st.nextToken());
        }
        for(int i=1;i<N;i++){
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b  = Integer.parseInt(st.nextToken());
            trees[a].add(b);
            trees[b].add(a);
        }
        dfs(1);
        findRoute(1,N);
        StringBuilder sb = new StringBuilder();
        while(results.size()>0){
            sb.append(results.poll()+" ");
        }
        int maxValue = dp[1][0]>dp[1][1]?dp[1][0]:dp[1][1];
        System.out.println(maxValue);
        System.out.println(sb.toString());
    }
}