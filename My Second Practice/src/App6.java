import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class App6 {
    public static void main(String[] args) throws IOException{
        //BufferedReader bf = new BufferedReader(new InputStreamReader(System.in)); 
        ArrayList<Integer> a = new ArrayList<>(Arrays.asList(1,21,3,4,5,35,5,4,3,5,98,21,14,17,32));
        Collections.sort(a);
        System.out.println(a);
        int result = 0;
        System.out.println(a.size());
        while(a.size()>1){
            int size = a.size();
            for(int i=0;i<size/2;i++){
                int value  = a.remove(0) + a.remove(size-2*i-2); 
                result += value;
                a.add(value);
            }
        }
        // while()
        // for(int i=0;i<a.size();i++){
        //     System.out.print(a[i]+a[a.length-1-i]);
        //     System.out.print(" ");
        // }
    }
}