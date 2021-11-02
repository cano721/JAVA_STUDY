import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Queue;
import java.util.Stack;

public class BJ2606 {
    public static Queue<Integer> dfs (HashMap<Integer,ArrayList<Integer>> network, int start){
        Queue<Integer> visited = new LinkedList<>();
        Stack<Integer> needvisit = new Stack<>();

        needvisit.addAll(network.get(start));

        while(!needvisit.isEmpty()){
            int temp = needvisit.pop();
            if(!visited.contains(temp)){
                visited.add(temp);
                for(int i : network.get(temp)){
                    needvisit.push(i);
                }
            }
        }

        return visited;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int computerNumber = Integer.parseInt(br.readLine());
        int networkNumber = Integer.parseInt(br.readLine());

        HashMap <Integer, ArrayList<Integer>> network = new HashMap<>();
        for(int i = 1;i<=computerNumber;i++){
            network.put(i,new ArrayList<>());
        }

        int first, second;
        StringTokenizer st;
        for(int i = 0;i<networkNumber;i++){
            st = new StringTokenizer(br.readLine()," ");
            first = Integer.parseInt(st.nextToken());
            second  = Integer.parseInt(st.nextToken());

            network.get(first).add(second);
            network.get(second).add(first);
        }

        System.out.println(dfs(network,1).size()-1);

    }
}
