import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class BJ11279 {
    private static ArrayList<Integer> heapArray = null;

    private static void insert(int data){
        // 힙이 비었거나 만들어져있지 않을때는?

        heapArray.add(data);
        int index = heapArray.size()-1;
        while(index>1){
            if(heapArray.get(index)>heapArray.get(index/2)){
                Collections.swap(heapArray,index,index/2);
                index = index / 2;
            }else{
                break;
            }
        }
    }


    private static int pop(){
        if(heapArray==null){
            return -1;
        }
        else if (heapArray.size() == 2){
            return heapArray.remove(1);
        }else{
            int returnData = heapArray.get(1);
            heapArray.set(1, heapArray.remove(heapArray.size()-1));

            int index = 1;
            int leftIndex = 0, rightIndex = 0;
            while(index<heapArray.size()){
                leftIndex = index * 2;
                rightIndex = index * 2 + 1;

                // 자식이 모두 없을 때
                if(leftIndex>=heapArray.size()){
                    break;
                }
                // 자식이 왼쪽 한명 뿐일때
                else if (rightIndex>=heapArray.size()){
                    if(heapArray.get(index) < heapArray.get(leftIndex)){
                        Collections.swap(heapArray,index,leftIndex);
                        index = leftIndex;
                    }else{
                        break;
                    }
                }
                // 자식이 둘 다 있을 때
                else{
                    if(heapArray.get(leftIndex) > heapArray.get(rightIndex)){
                        if(heapArray.get(leftIndex) > heapArray.get(index)){
                            Collections.swap(heapArray,index,leftIndex);
                            index = leftIndex;
                        }else{
                            break;
                        }
                    }else{
                        if(heapArray.get(rightIndex) > heapArray.get(index)){
                            Collections.swap(heapArray,index,rightIndex);
                            index = rightIndex;
                        }else{
                            break;
                        }
                    }
                }
            }
            return returnData;
        }
    }
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        heapArray = new ArrayList<>();
        heapArray.add(null);

        StringBuilder sb = new StringBuilder();
        for(int i = 0;i<N;i++){
            int M = Integer.parseInt(br.readLine());
            if(M == 0){
                if(heapArray.size()<=1){
                    sb.append(0).append('\n');
                }else{
                    sb.append(pop()).append('\n');
                }
            }else{
                insert(M);
            }
        }
        System.out.println(sb);
    }
}
import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.util.ArrayList;
        import java.util.Collections;

public class Main {
    private static ArrayList<Integer> heapArray = null;

    private static void insert(int data){
        // 힙이 비었거나 만들어져있지 않을때는?

        heapArray.add(data);
        int index = heapArray.size()-1;
        while(index>1){
            if(heapArray.get(index)>heapArray.get(index/2)){
                Collections.swap(heapArray,index,index/2);
                index = index / 2;
            }else{
                break;
            }
        }
    }


    private static int pop(){
        if(heapArray==null){
            return -1;
        }
        else if (heapArray.size() == 2){
            return heapArray.remove(1);
        }else{
            int returnData = heapArray.get(1);
            heapArray.set(1, heapArray.remove(heapArray.size()-1));

            int index = 1;
            int leftIndex = 0, rightIndex = 0;
            while(index<heapArray.size()){
                leftIndex = index * 2;
                rightIndex = index * 2 + 1;

                // 자식이 모두 없을 때
                if(leftIndex>=heapArray.size()){
                    break;
                }
                // 자식이 왼쪽 한명 뿐일때
                else if (rightIndex>=heapArray.size()){
                    if(heapArray.get(index) < heapArray.get(leftIndex)){
                        Collections.swap(heapArray,index,leftIndex);
                        index = leftIndex;
                    }else{
                        break;
                    }
                }
                // 자식이 둘 다 있을 때
                else{
                    if(heapArray.get(leftIndex) > heapArray.get(rightIndex)){
                        if(heapArray.get(leftIndex) > heapArray.get(index)){
                            Collections.swap(heapArray,index,leftIndex);
                            index = leftIndex;
                        }else{
                            break;
                        }
                    }else{
                        if(heapArray.get(rightIndex) > heapArray.get(index)){
                            Collections.swap(heapArray,index,rightIndex);
                            index = rightIndex;
                        }else{
                            break;
                        }
                    }
                }
            }
            return returnData;
        }
    }
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        heapArray = new ArrayList<>();
        heapArray.add(null);

        StringBuilder sb = new StringBuilder();
        for(int i = 0;i<N;i++){
            int M = Integer.parseInt(br.readLine());
            if(M == 0){
                if(heapArray.size()<=1){
                    sb.append(0).append('\n');
                }else{
                    sb.append(pop()).append('\n');
                }
            }else{
                insert(M);
            }
        }
        System.out.println(sb);
    }
}
