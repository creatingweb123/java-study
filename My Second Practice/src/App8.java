import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 백준 12865
public class App8 {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in)); 

        int N = Integer.parseInt(bf.readLine());
        int[][] electricCords= new int[N][2];
        String[] inputCord;
        int[]      lineWeight = new int[500];
        boolean[][]crossCheck = new boolean[501][501];

        for(int i=0;i<N;i++){
            inputCord= bf.readLine().split(" ");
            electricCords[i][0] = Integer.parseInt(inputCord[0]);
            electricCords[i][1] = Integer.parseInt(inputCord[1]);
        }
        Arrays.sort(electricCords,(o1,o2)->{
            return o1[0]-o2[0];
        });
        for(int i=1;i<N;i++){
            for(int j=0;j<i;j++){
                if(electricCords[i][1]<electricCords[j][1]){
                    lineWeight[electricCords[i][0]]+=1;
                    lineWeight[electricCords[i][0]]+=1;
                }
            }
        }

    }
}
