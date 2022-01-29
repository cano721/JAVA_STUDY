/**
    1. n 숫자 k진수로 변환
    1-1) n %k = 나머지를 놔두고, /10씩해서 구하기
    
    2. 변환된 숫자를 조건에 따라 분할
    2-1) String k진수를 0을 만날때마다 분할
    
    3. 해당 조건의 숫자가 소수인지 확인
    
    4. 맞으면 개수 추가
    
**/

import java.util.*;
import java.io.*;

class Solution {
    
    public ArrayList<Long> checkNum = new ArrayList<>();
    public int answer = 0;
    
    public int solution(int n, int k) {
        
        parsingK(n,k);
        checkPrime();
        
        return answer;
    }
    
    public void checkPrime(){
        for(long v: checkNum){
            if(prime(v)) answer++;
        }
    }
    
    // 소수판별
    public boolean prime(long v){
        if(v < 2) return false;
        
        if(v == 2) return true;
        
        for(long i = 2; i <= Math.sqrt(v); i++){
            if(v%i == 0){
                return false;
            }
        }
        
        return true;
    }
    
    public void parsingK(int n, int k){
        String str = "";
        String temp = "";
        while(n > 0){
            str = String.valueOf(n%k) + str;
            n/= k;
        }
        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            if(c == '0'){
                if(!temp.isEmpty()){
                    checkNum.add(Long.parseLong(temp));
                    temp = "";
                }
            }else{
                temp += c +"";
            }
            
            if(i == str.length()-1){
                if(!temp.isEmpty()){
                    System.out.println(temp);
                    checkNum.add(Long.parseLong(temp));
                }
            }
        }
    }
}