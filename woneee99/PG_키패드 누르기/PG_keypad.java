public class PG_keypad {
	class Solution {
	    public String solution(int[] numbers, String hand) {
	        StringBuilder sb = new StringBuilder();
	        
	        int left = 10, right = 12;
	        for (int i=0; i<numbers.length; i++) {
	            if (numbers[i] == 1 || numbers[i] == 4 || numbers[i] == 7) {
	                left = numbers[i];
	                sb.append("L");
	            }
	            else if (numbers[i] == 3 || numbers[i] == 6 || numbers[i] == 9) {
	                right = numbers[i];
	                sb.append("R");
	            }
	            else {
	                if (numbers[i] == 0) numbers[i] = 11;
	                    
	                int dist1 = dist(left-1, numbers[i]-1);
	                int dist2 = dist(right-1, numbers[i]-1);
	                
	                if (dist1 < dist2) {
	                    left = numbers[i];
	                    sb.append("L");
	                }
	                else if (dist1 > dist2) {
	                    right = numbers[i];
	                    sb.append("R");
	                }
	                else {
	                    if (hand.equals("left")) {
	                        left = numbers[i];
	                        sb.append("L");
	                    }
	                    else {
	                        right = numbers[i];
	                        sb.append("R");
	                    }
	                }
	            }
	        }
	        return sb.toString();
	    }
	    
	    public int dist(int a, int b) {
	        int x1 = a / 3, y1 = a % 3;
	        int x2 = b / 3, y2 = b % 3;
	        
	        return Math.abs(x2 - x1) + Math.abs(y2 - y1);
	    }
	}
}