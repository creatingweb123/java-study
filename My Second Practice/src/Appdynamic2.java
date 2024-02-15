import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
// 1005
public class Appdynamic2 {
    static int[] maxValue  = new int[1001];
    static boolean[]visited= new boolean[1001];
    public static void initList(int N){
        for(int i=1;i<=N;i++){
            maxValue[i]=0;
            visited[i]=false;
        }
    }
    public static void dfs(List<Integer>[]arr,int[]times, int target,int N){
        visited[target] = true;
        maxValue[target]= times[target];
        for(int next:arr[target]){
            if(visited[next]==false) dfs(arr, times, next, N);

            maxValue[target] = Math.max(maxValue[target],times[target]+maxValue[next]);

        }

    }
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in)); 
        
        int T = Integer.parseInt(bf.readLine());
        for(int testcase=0;testcase<T;testcase++){
            StringTokenizer st = new StringTokenizer(bf.readLine());
            
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int[] times = new int[N+1];
            List<Integer>[]arr = new ArrayList[N+1];
            for(int i=1;i<=N;i++){
                arr[i] = new ArrayList<Integer>();
            }
            st = new StringTokenizer(bf.readLine());
            for(int i=1;i<=N;i++){
                times[i] = Integer.parseInt(st.nextToken());
            }
            for(int i=0;i<K;i++){
                st = new StringTokenizer(bf.readLine());
                int start = Integer.parseInt(st.nextToken());
                int next = Integer.parseInt(st.nextToken());
                arr[next].add(start);
            }
            int target = Integer.parseInt(bf.readLine());
            initList(N);
            dfs(arr, times, target, N);
            System.out.println(maxValue[target]);
        }
    }
}