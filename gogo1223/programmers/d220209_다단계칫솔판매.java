package programmers;

import java.util.ArrayList;
import java.util.HashMap;

public class d220209_다단계칫솔판매 {

	public static void main(String[] args) {
		String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"}; 
		String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"}; 
		String[] seller = {"young", "john", "tod", "emily", "mary"};
		int[] amount = {12, 4, 2, 5, 10};
		int[] answer = solution(enroll, referral, seller, amount);
		//360, 958, 108, 0, 450, 18, 180, 1080
		for (int i = 0; i < answer.length; i++) {
			System.out.print(answer[i]+" ");
		}
	}

	private static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
		HashMap<String, Integer> enrollIdx = new HashMap<String, Integer>();
		
		int[] answer = new int[enroll.length];
		
		for (int i = 0; i < enroll.length; i++) {
			enrollIdx.put(enroll[i], i);
		}
		for (int i = 0; i < seller.length; i++) {
			String nowName = seller[i];
			int won = amount[i] * 100;
			
			while(!nowName.equals("-")) {
				int recommandProfit = won / 10;
				int myProfit = won - recommandProfit;
				answer[enrollIdx.get(nowName)] += myProfit;
				
				nowName = referral[enrollIdx.get(nowName)];
				won /= 10;
				if(won < 1) break;
			}
		}
		return answer;
	}

}
