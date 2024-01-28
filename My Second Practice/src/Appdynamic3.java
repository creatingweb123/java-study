import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 1005
public class Appdynamic3 {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in)); 
        
        StringTokenizer st = new StringTokenizer(bf.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] memories = new int[N+1];
        int[] times    = new int[N+1];
        int[][]maxTimes= new int[N+1][10001];
        st = new StringTokenizer(bf.readLine());
        for(int i=1;i<=N;i++){
            memories[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(bf.readLine());
        for(int i=1;i<=N;i++){
            times[i] = Integer.parseInt(st.nextToken());
        }
        for(int i=1;i<=N;i++){
            for(int j=0;j<times[i];j++){
                maxTimes[i][j] = maxTimes[i-1][j];
            }
            for(int j=times[i];j<=10000;j++){
                maxTimes[i][j]=Math.max(maxTimes[i-1][j],memories[i]+maxTimes[i-1][j-times[i]]);
            }
        }
        for(int i=0;i<=10000;i++){
            if(maxTimes[N][i]>=M){
                System.out.println(i);
                break;
            }
        }
    }
}