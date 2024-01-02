import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class App3 {

    public static class Node{
        int idx,dist;
        Node(int idx, int dist){
            this.idx = idx;
            this.dist = dist;
        }
    }
    public static int[] bfs(List<Node>[]list, int start){
        Queue<Integer> queue = new LinkedList<>();
        int[] idxList    = new int[list.length];
        boolean[] visited= new boolean[list.length];
        visited[start] = true;
        queue.add(start);
        while(!queue.isEmpty()){
            int x = queue.poll();
            for(Node node:list[x]){
                if(!visited[node.idx]){
                    idxList[node.idx] = idxList[x]+node.dist;
                    visited[node.idx] = true;
                    queue.add(node.idx);
                }
            }
        }
        return idxList;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in)); 
        int vertexNum = Integer.parseInt(bf.readLine());
        int vertex, idx, dist;
        List<Node>[] list = new ArrayList[vertexNum+1];
        for(int i=1;i<=vertexNum;i++){
            String[] vertexData = bf.readLine().split(" ");
            vertex = Integer.parseInt(vertexData[0]);
            list[vertex]=new ArrayList<>();
            for(int j=1;j<vertexData.length-1;j+=2){
                idx  = Integer.parseInt(vertexData[j]);
                dist = Integer.parseInt(vertexData[j+1]);
                list[vertex].add(new Node(idx,dist));
            }
        }
        int[] firstList = bfs(list,1);
        int maxIndex = 1;
        int maxValue = 0;
        for(int index=1;index<firstList.length;index++){
            if(firstList[index]>maxValue){
                maxIndex = index;
                maxValue = firstList[index];
            }
        }
        int[] secondList = bfs(list,maxIndex);
        maxValue = 0;
        for(int value:secondList){
            maxValue = (value>maxValue) ? value : maxValue;
        }
        System.out.println(maxValue);
    }
}
