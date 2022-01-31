public class PG_targetnumber {
    static int count = 0;
    public int solution(int[] numbers, int target) {
        cal(0, 0, numbers, target);
        return count;
    }
    static void cal(int x, int depth, int []numbers, int target) {
		if(depth == numbers.length) {
			if(target == x) {
				count++;
			}
			return;
		}
		
		int plus = x + numbers[depth];
		int minus = x - numbers[depth];
	
		cal(plus, depth+1, numbers, target);
		cal(minus, depth+1, numbers, target);
		
	}
}