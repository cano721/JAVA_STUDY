package 전체유형문제풀이;

public class PG43165_타켓넘버 {

	public static void main(String[] args) {
		
		//testCase
		int[] arr = new int[5];
		
        for (int i = 0; i < 5; i++) {
            arr[i] = 1;
        }
        int target = 3;
 
        System.out.println(new Solution().solution(arr, target));
	}
}

class Solution {
    public int solution(int[] numbers, int target) {
        return DFS(numbers, target, 0, 0);
    }

	private int DFS(int[] numbers, int target, int index, int num) {
		
		if(index == numbers.length) { //제일 깊은 곳까지 탐색
			return num == target ? 1:0; //타켓값과 같으면 1, 아니면 0
		}else {
			return DFS(numbers, target, index+1, num+numbers[index])+DFS(numbers, target, index+1, num-numbers[index]);
		}

	}
}