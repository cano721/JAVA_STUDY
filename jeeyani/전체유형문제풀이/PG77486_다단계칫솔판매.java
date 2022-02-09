package 전체유형문제풀이;

import java.util.*;

public class PG77486_다단계칫솔판매 {

	public static void main(String[] args) {

		String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
		String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
		String[] seller = {"young", "john", "tod", "emily", "mary"};
		int[] amount = {12, 4, 2, 5, 10};
		
		int[] result = solution(enroll,referral,seller,amount);
		
		for(int ans : result) {
			System.out.println(ans+" ");
		}
		
		

	}

	static HashMap<String, String> relationship;
    static HashMap<String, Integer> profit;
    
    public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        
        //수량에 따른 판매액계산
        int[] pay = new int[amount.length];
        for(int i = 0; i< amount.length; i++){
            pay[i] = amount[i]*100;
        }
        
       relationship = new HashMap<>(); //관계저장
       profit = new HashMap<>(); //전체 판매원이 얻은 이익
        
        //관계 재정의
        for(int i = 0; i< enroll.length; i++){
            relationship.put(enroll[i],referral[i]);
            
            profit.put(enroll[i],0);
        }
        
        //배당금 계산하기
        for(int i = 0; i< seller.length; i++){
            
            int per = pay[i]/10;
            int money = pay[i]-pay[i]/10;
            profit.replace(seller[i],money-per);
            String sell  = seller[i];
           
            getPay(sell,per,money);
            /*
            //관계성을 이용해서 배당금 할당하기
            while(true){
                
                //돈이 1원미만인 경우 분배하지않음 & 더 이상 부모가 아닌 경우
                if(money < 1) break;
                if("-".equals(sell)) break;
                
                돈 계산하기
                //1. -10%
                profit.put(sell,payMap.get(sell)-per);
                
                //2. 부모에게 할당하기
                profit.put(relationship.get(sell),per);
 
            }
            */
        }  
        
        for(int i=0; i<enroll.length; i++){
            answer[i] = profit.get(enroll[i]);
        }
        
        return answer;
    }
    
    public static void getPay(String sell,int per,int money){
        
        //돈이 1원미만인 경우 분배하지않음 & 더 이상 부모가 아닌 경우
        if(money <= 1) return;
        if("-".equals(sell)) return;
                
        /*돈 계산하기*/
        //1. -10%
        profit.replace(sell, profit.get(sell)-per);
        
        //2. 부모에게 할당하기
        profit.replace(relationship.get(sell),per);
        
        getPay(relationship.get(sell), money/10, money-money/10);
        
        
    }
}
