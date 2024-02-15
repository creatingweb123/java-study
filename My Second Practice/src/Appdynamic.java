import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 2629
public class Appdynamic {
    public static boolean[][] visited = new boolean[31][30001];

    public static void dynamic(int[]bWeights, int idx, int N, int weight){
        if(weight>=0){
            if(visited[idx][weight]) return;
            visited[idx][weight]=true;
        }else{
            if(visited[idx][15000-weight]) return;
            visited[idx][15000-weight]=true;
        }
        if(idx>=N) return;
        
        dynamic(bWeights,idx+1,N,weight);
        dynamic(bWeights, idx+1, N, weight+bWeights[idx]);
        dynamic(bWeights, idx+1, N,weight-bWeights[idx]);
    }
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in)); 
        
        int N = Integer.parseInt(bf.readLine());
        int[] bWeights = new int[N];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i=0;i<N;i++){
            bWeights[i]=Integer.parseInt(st.nextToken());
        }
        dynamic(bWeights,0,N,0);
        int M = Integer.parseInt(bf.readLine());
        st = new StringTokenizer(bf.readLine());
        StringBuilder result = new StringBuilder();

        for(int i=0;i<M;i++){
            boolean check = false;
            int marble = Integer.parseInt(st.nextToken());
            if(marble<=15000){
                for(int j=1;j<=N;j++){
                    if(visited[j][marble]){
                        check = true;
                        break;
                    }
                }
            }
            result.append(check?"Y":"N").append(" ");
        }
        System.out.println(result.toString());
    }
}