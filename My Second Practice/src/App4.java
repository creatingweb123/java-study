import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// 백준 10844
public class App4 {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in)); 
        int N = Integer.parseInt(bf.readLine());
        int[][] array = new int[10][N];
        int result = 0;
        for(int i=1;i<10;i++) array[i][0]=1;
        for(int i=1;i<N;i++){
            for(int j=0;j<10;j++){
                if      (j==0){ array[j][i] = array[1][i-1];}
                else if (j==9){ array[j][i] = array[8][i-1];}
                else{ 
                    array[j][i] = (array[j-1][i-1]+array[j+1][i-1]) % 1000000000;
                }
            }
        }
        for(int i=0;i<10;i++){
            result =  result + array[i][N-1];
            result %= 1000000000;
        }
        System.out.println(result);
    }
}
