import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App7 {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in)); 
        String[] inputString = bf.readLine().split(" ");
        int N = Integer.parseInt(inputString[0]);
        int K = Integer.parseInt(inputString[1]);
        int[][] goods   = new int[N+1][2];
        int[][] maxValue= new int[N+1][K+1];
        for(int i=1;i<=N;i++){
            inputString = bf.readLine().split(" ");
            goods[i][0]= Integer.parseInt(inputString[0]);
            goods[i][1]= Integer.parseInt(inputString[1]);
        }
        for(int i=1;i<=N;i++){
            for(int j=1;j<goods[i][0]&&j<=K;j++){
                maxValue[i][j]=maxValue[i-1][j];
            }
            for(int j=goods[i][0];j<=K;j++){
                maxValue[i][j]=Math.max(maxValue[i-1][j],maxValue[i-1][j-goods[i][0]]+goods[i][1]);
            }
        }
        System.out.println(maxValue[N][K]);

    }
}
