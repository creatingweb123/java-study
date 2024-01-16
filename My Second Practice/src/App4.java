import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// 백준 11054
public class App4 {
    public static int findMaxValue(int[] valueCount, int value){
        int maxValue = 0;
        for(int i=0;i<value;i++){
            maxValue = (maxValue>valueCount[i])?maxValue:valueCount[i];
        }
        return maxValue+1;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in)); 
        int N = Integer.parseInt(bf.readLine());
        String[] stringSequence = bf.readLine().split(" ");
        int[] sequence   = new int[N];
        int[] increase   = new int[N];
        int[] decrease   = new int[N];
        int[] valueCount = new int[1001];
        for(int i=0;i<N;i++){
            sequence[i] = Integer.parseInt(stringSequence[i]);
        }
        // make increase list
        for(int i=0;i<N;i++){
            int value = sequence[i];
            valueCount[value] = findMaxValue(valueCount,value);
            increase[i] = valueCount[value];
        }
        // init
        for(int i=0;i<=1000;i++){
            valueCount[i] = 0;
        }
        // make decrease list
        for(int i=N-1;i>=0;i--){
            int value = sequence[i];
            valueCount[value] = findMaxValue(valueCount,value);
            decrease[i] = valueCount[value];
        }
        // find max result
        int result = 0;
        for(int i=0;i<N-1;i++){
            int maxDecrease = 0;
            for(int j=i+1;j<N;j++){
                maxDecrease = (maxDecrease>decrease[j])?maxDecrease:decrease[j];
            }
            int plusValue = increase[i]+maxDecrease;
            if(maxDecrease==increase[i]){plusValue-=1;}
            result = (result>plusValue)?result:plusValue;
        }
        result = (increase[N-1]>result)?increase[N-1]:result;
        result = (decrease[0]>result)?decrease[0]:result;
        System.out.println(result);
    }
}

