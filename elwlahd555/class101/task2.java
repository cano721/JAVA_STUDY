package elwlahd555.class101;

public class task2 {
	public static void main(String[] args) {
		int A[]= {100,200,100};
		int B[]= {50,100,100};
		int X=100;
		int Y=100;
		System.out.println(solution(A, B, X, Y));
	}
    public static int solution(int[] A, int[] B, int X, int Y) {
    	int answer=-1;
    	
    	for (int i = 0; i < A.length; i++) {
			double temp = Math.sqrt(Math.pow(Math.abs(A[i]-X),2)+Math.pow(Math.abs(B[i]-Y), 2));
			if(temp<=20){
				answer=i;
			}
		}
		return answer;
    }
}
