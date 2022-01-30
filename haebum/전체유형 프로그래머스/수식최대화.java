/**
    1. 연산자 우선순위 선정
    1-1) 재귀로 선정
    
    2. 문자열 파싱
    2-1) arrayList 2개만들어서 숫자와 연산자 별도 보관
    
    3. 연산자 순서에 따른 계산
    3-1) 새로운 어레이리스트 생성하여 파싱한 정보 넣기
    3-2) 연산자 우선순위대로 돌고, 연산자 어레이리스트 돌기
    3-3) 동일한 연산자면 해당 위치의 숫자 어레이리스트에서 두개 빼내고 연산자도 빼내서 계산
    3-4) 계산 후 해당 위치에 다시 집어넣기
    3-5) 다시 그 순서부터 시작하게 --해주기
    
    4. 최대치 반환
**/

import java.util.*;

class Solution {
    
    public String oper = "*+-";
    public boolean[] visited = new boolean[3];
    public long answer;
    
    public ArrayList<Character> opers = new ArrayList<>();
    public ArrayList<Long> nums = new ArrayList<>();
    
    public long solution(String expression) {
        
        parsing(expression);
        operChoice("",0);
        
        return answer;
    }
    
    
    // 문자열 파싱 함수
    public void parsing(String expression){
        
        int idx = 0;
        for(int i = 0; i < expression.length(); i++){
            char c = expression.charAt(i);
            // 연산자일때
            if(Character.isDigit(c) == false){
                opers.add(c);
                String sub = expression.substring(idx,i);
                long num = Long.parseLong(sub);
                nums.add(num);
                idx = i+1;
            }
            if(i == expression.length()-1){
                String sub = expression.substring(idx,i+1);
                long num = Long.parseLong(sub);
                nums.add(num);
            }
        }
    }
    
    // 연산자 우선순위 선택 함수
    public void operChoice(String str,int stage){
        // 3개 다 선택하면 계산
        if(stage == 3){
            makeAnswer(str);
            return;
        }
        
        // 연산자 선택
        for(int i = 0; i < 3; i++){
            if(visited[i] == true) continue;
            visited[i] = true;
            operChoice(str + oper.charAt(i),stage+1);
            visited[i] = false;
        }
    }
    
    // 연산자에따른 계산함수
    public long calculate(long num1, long num2, char c){
        if(c == '+'){
            return num1+num2;
        }else if(c == '-'){
            return num1-num2;
        }else{
            return num1*num2;
        }
    }
    
    // 정답 구하는 함수
    public void makeAnswer(String oper){
        ArrayList<Character> opers2 = new ArrayList<>(opers);
        ArrayList<Long> nums2 = new ArrayList<>(nums);
        
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < opers2.size(); j++){
                if(oper.charAt(i) == opers2.get(j)){
                    long calNum = calculate(nums2.remove(j),nums2.remove(j),opers2.remove(j));
                    nums2.add(j,calNum);
                    j--;
                }
            }
        }
        
        long lastNum = Math.abs(nums2.get(0));
        answer = Math.max(answer,lastNum);
    }
}