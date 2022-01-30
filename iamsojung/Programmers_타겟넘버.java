package january;

public class 타겟넘버 {

    public static void main(String[] args) {
        int[] numbers = {1, 1, 1, 1, 1};
        int target = 3;

        solution(numbers,target);
    }
    static int answer = 0;

    static public int solution(int[] numbers, int target) {
        dfs(numbers, target,0,0);
        return answer;
    }

    private static void dfs(int[] numbers, int target, int idx, int sum) {
        if(idx==numbers.length){
            if(sum==target){
                answer++;
            }
            return;
        }
        sum = sum+numbers[idx];
        dfs(numbers,target,idx+1,sum);
        sum = sum-numbers[idx];
        sum = sum+(-1*numbers[idx]);
        dfs(numbers,target,idx+1,sum);
    }

}
