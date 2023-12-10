import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class App3 {

    public static class Pair{
        int x,y;
        Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static Stack<Pair> stack = new Stack<>();
    static int result = 0;
    public static int dfs(int[][] mapList,int[][]visited, Pair s, int fx, int fy){
        int x,y;
        if(visited[s.x][s.y]==-1){
            visited[s.x][s.y] = 0;
        }
        if(s.x==fx&&s.y==fy){return 1;}
        stack.push(s);
        while(!stack.isEmpty()){
            Pair tmp = stack.pop();
            if(tmp.x==fx&&tmp.y==fy){result+=1;}
            x = tmp.x;  y=tmp.y;
            if(mapList[x+1][y]<mapList[x][y]){
                visited[][]=dfs(mapList,new Pair(x+1,y),fx,fy);
            }if(mapList[x-1][y]<mapList[x][y]){
                dfs(mapList,new Pair(x-1,y),fx,fy);
            }if(mapList[x][y+1]<mapList[x][y]){
                dfs(mapList,new Pair(x,y+1),fx,fy);
            }if(mapList[x][y-1]<mapList[x][y]){
                dfs(mapList,new Pair(x,y-1),fx,fy);
            }
        }
        return 0;
    }
    // public static int bfs(int[][] mapList, Pair s, int fx, int fy){
    //     int count = 0;
    //     int x,y;
    //     Queue<Pair> queue = new LinkedList<>();
    //     queue.add(s);
    //     while(true){
    //         if(queue.isEmpty())break;
    //         Pair tmp = queue.poll();
    //         if(tmp.x==fx&&tmp.y==fy){count+=1;}
    //         x = tmp.x;  y=tmp.y;
    //         if(mapList[x+1][y]<mapList[x][y]){
    //             queue.add(new Pair(x+1,y));
    //         }if(mapList[x-1][y]<mapList[x][y]){
    //             queue.add(new Pair(x-1,y));
    //         }if(mapList[x][y+1]<mapList[x][y]){
    //             queue.add(new Pair(x,y+1));
    //         }if(mapList[x][y-1]<mapList[x][y]){
    //             queue.add(new Pair(x,y-1));
    //         }
    //     }
    //     return count;
    // }
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in)); 
        String[] size = bf.readLine().split(" ");
        int x = Integer.parseInt(size[0]);
        int y = Integer.parseInt(size[1]);
        String[] row;
        int[][] map  = new int[x+2][y+2];
        int[][]visited= new int[x+2][y+2];
        for(int i=1;i<=x;i++){
            map[i][0]  = 10001;
            map[i][y+1]= 10001;
        }
        for(int i=1;i<=y;i++){
            map[0][i]  = 10001;
            map[x+1][i]= 10001;
        }
        for(int i=1;i<=x;i++){
            row = bf.readLine().split(" ");
            for(int j=1;j<=y;j++){
                visited[i][j] = -1;
                map[i][j] = Integer.parseInt(row[j-1]);
            }
        }
        
        System.out.println(value);
    }
}
