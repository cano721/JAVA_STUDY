import java.util.*;
import java.io.*;
class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder arr = new StringBuilder();
        StringBuilder answer = new StringBuilder();
        int val =0;
        while(arr.length() <= t*m){
            arr.append(Integer.toString(val++, n).toUpperCase());
        }
        System.out.println(arr);
        int index = p;
        for(int i =0; i<t; i++){
            answer.append(arr.charAt(index-1));
            index += m;
        }
        
        return answer.toString();
    }
}