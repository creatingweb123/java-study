import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class App2 {

    public static class Pair{
        int x,y;
        Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    public static int bfs(int[][] array, Queue<Pair> queue){
        int bfsLen = 0;
        while(true){
            Pair current = queue.poll();
            if(current.x==0&&current.y==0){
                bfsLen +=1;
                if(queue.isEmpty()) break;
                queue.add(new Pair(0,0));
            }else{
                int cX = current.x; // current X
                int cY = current.y; // current Y
                if(array[cY][cX+1] == 0){
                    array[cY][cX+1] = 1;
                    queue.add(new Pair(cX+1,cY));
                }if(array[cY][cX-1] == 0){
                    array[cY][cX-1] = 1;
                    queue.add(new Pair(cX-1,cY));
                }if(array[cY+1][cX] == 0){
                    array[cY+1][cX] = 1;
                    queue.add(new Pair(cX,cY+1));
                }if(array[cY-1][cX] == 0){
                    array[cY-1][cX] = 1;
                    queue.add(new Pair(cX,cY-1));
                }
            }
        }
        return bfsLen;
    }

    public static void main(String[] args) throws IOException  {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in)); 
        String[] size = bf.readLine().split(" ");
        int x = Integer.parseInt(size[0]);
        int y = Integer.parseInt(size[1]);

        int[][] array = new int[y+2][x+2];
        String[] box;
        Queue<Pair> queue = new LinkedList<>();
        for(int i=1;i<=x;i++){
            array[0][i]   = -1;
            array[y+1][i] = -1;
        }
        for(int i=1;i<=y;i++){
            array[i][0]   = -1;
            array[i][x+1] = -1;
        }
        for(int i=1;i<=y;i++){
            box = bf.readLine().split(" ");
            for(int j=1;j<=x;j++){
                int data = Integer.parseInt(box[j-1]);
                array[i][j] = data;
                if(data==1){queue.add(new Pair(j,i));}
            }
        }
        queue.add(new Pair(0,0));
        int bfsLen = bfs(array, queue);
        for(int i=1;i<=y;i++){
            for(int j=1;j<=x;j++){
                if(array[i][j]==0){
                    System.out.println(-1);
                    return;
                }
            }
        }
        System.out.println(bfsLen-1);
    }
}
