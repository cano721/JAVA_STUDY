package programmers;

public class d220131_타겟넘버 {

	public static void main(String[] args) {
		int[] numbers = {4, 1, 2, 1};
		int target = 4;
		int answer = solution(numbers,target);
		
		System.out.println(answer);

	}
	public static int solution(int[] numbers, int target) {
        int answer = 0;
        answer = Dfs(0, 0, numbers, target);
        return answer;
    }
    
    public static int Dfs(int i, int sum, int[] numbers, int target){
        if(i == numbers.length){
            if(sum==target) return 1;
            else return 0;
        }
        int result = 0;
        result += Dfs(i+1, sum+numbers[i], numbers, target);
        result += Dfs(i+1, sum-numbers[i], numbers, target);
        return result;
    }
}
