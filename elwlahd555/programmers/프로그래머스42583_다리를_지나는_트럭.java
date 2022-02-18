package elwlahd555.programmers;

public class 프로그래머스42583_다리를_지나는_트럭 {
	public static void main(String[] args) {
		int bridge_length = 2;
		int weight = 10;
		int[] truck_weights = { 7, 4, 5, 6 };
		System.out.println(solution(bridge_length, weight, truck_weights));
	}

	public static int solution(int bridge_length, int weight, int[] truck_weights) {
		int answer = 0;
		int arr[] = new int[bridge_length];
		int temp = 0;
		int k = 0;
		while (true) {
			int num = 0;
			int num2 = 0;
			for (int i = 0; i < arr.length; i++) {
				num = arr[i];
				arr[i] = num2;
				num2 = num;
			}
			temp -= num2;
			if (k < truck_weights.length && temp + truck_weights[k] <= weight) {
				temp += truck_weights[k];
				arr[0] = truck_weights[k];
				k++;
			}
			answer++;
			if (check(arr)) {
				break;
			}
		}
		return answer;
	}

	public static boolean check(int[] arr) {
		int n = 0;
		for (int i = 0; i < arr.length; i++) {
			n += arr[i];
		}
		if (n == 0) {
			return true;
			
		}
		return false;
	}

}
