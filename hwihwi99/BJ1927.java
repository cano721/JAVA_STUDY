import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class BJ1927
{
    private static ArrayList<Integer> heaparray = null;

    public static void insert (int M){
        heaparray.add(M);
        int index = heaparray.size()-1;
        while (index > 1){
            if(heaparray.get(index)<heaparray.get(index/2)){
                Collections.swap(heaparray,index,index/2);
                index = index/2;
            }else
                break;
        }
    }

    public static int pop(){
        if(heaparray==null)
            return -1;
        else if (heaparray.size()==2)
            return heaparray.remove(1);
        else {
            int returnData = heaparray.get(1);
            heaparray.set(1, heaparray.remove(heaparray.size()-1));
            int index = 1;
            int leftIndex = 0, rightIndex = 0;
            while (index<heaparray.size()){
                leftIndex = index * 2;
                rightIndex = index * 2 + 1;

                if(leftIndex >= heaparray.size()){
                    break;
                }else if (rightIndex >= heaparray.size()){
                    if(heaparray.get(leftIndex)<heaparray.get(index)){
                        Collections.swap(heaparray,leftIndex,index);
                        index = leftIndex;
                    }else
                        break;
                }else{
                    if(heaparray.get(leftIndex)<heaparray.get(rightIndex)){
                        if(heaparray.get(leftIndex)<heaparray.get(index)){
                            Collections.swap(heaparray,leftIndex,index);
                            index = leftIndex;
                        }else
                            break;
                    }else{
                        if(heaparray.get(rightIndex)<heaparray.get(index)){
                            Collections.swap(heaparray,rightIndex,index);
                            index = rightIndex;
                        }else
                            break;
                    }
                }
            }
            return returnData;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        heaparray = new ArrayList<>();
        heaparray.add(null);

        for(int i = 0;i<N;i++){
            int M = Integer.parseInt(br.readLine());
            if(M==0){
                if(heaparray.size()<=1){
                    sb.append(0).append('\n');
                }else {
                    sb.append(pop()).append('\n');
                }
            }else {
                insert(M);
            }
        }
        System.out.println(sb);
    }
}