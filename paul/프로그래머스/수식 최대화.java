import java.util.*;
class Solution {
    static char[] oper = {'+', '*', '-'};
    static boolean[] vis = new boolean[3];
    static ArrayList<Long> numbers = new ArrayList<>();
    static ArrayList<Character> opers = new ArrayList<>();
    static long answer = 0;
    public long solution(String expression) {
        //숫자와 연산자 분리.
        int idx = 0;
        for(int i =0; i<expression.length(); i++){
            char k = expression.charAt(i);
            if(Character.isDigit(k) == false){
                String str = expression.substring(idx, i);
                numbers.add(Long.parseLong(str));
                opers.add(k);
                idx = i+1;
            }
        }
        
        numbers.add(Long.parseLong(expression.substring(idx)));
        
       
        //우선순위 찾기
        dfs(0, "");
        
        return answer;
    }
    
    
    //우선순위 만들기.
    static void dfs(int picked, String order){
        if(picked == 3){
            getCalculate(order);
            return;
        }
    
        for(int i =0; i <3; i++){
            if(vis[i]) continue;
            vis[i] = true;
            dfs(picked+1, order+oper[i]);
            vis[i] = false;
        }
    }
    
    //우선순위에 따른 계산
    static void getCalculate(String order){
        ArrayList<Long> num = new ArrayList<>(numbers);
        ArrayList<Character> op = new ArrayList<>(opers);
        
        //order에 있는 연산자와 같은 기호를 만날 때까지 op를 돌리고 같은 연산자를 만나면 해당 부분 먼저 계산
        for(int i =0; i<order.length(); i++){
            for(int j=0; j<op.size(); j++){
                if(order.charAt(i) == op.get(j)){
                    long val = calc(num.get(j), num.get(j+1), op.get(j));
                    num.set(j, val);
                    num.remove(j+1);
                    op.remove(j--);
                }
            }
        }
        System.out.println(order);
        answer = Math.max(answer, Math.abs(num.get(0)));
        System.out.println(answer);
    }
    
    static long calc(long num1, long num2, char op){
        switch(op){
            case '+':
                return num1+num2;
            case '*':
                return num1*num2;
            case '-':
                return num1-num2;
        }
        return 0;
    }
    
}