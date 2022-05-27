class Solution {
    public int solution(int[] a) {
        int length = a.length;
        int answer = 0;
        int temp[] = new int[length];
        
        int minValue = Integer.MAX_VALUE;
        for (int i = 0; i < length; i++) {
            //지금까지 최소값보다 a[i]값이 더 크면 temp[i]++;
            if (minValue < a[i]) temp[i]++;
            //최소값 갱신
            if (a[i] < minValue) {
                minValue = a[i];
            }
        }
        
        minValue = Integer.MAX_VALUE;
        for (int i = length-1; i > -1; i--) {
            if (minValue < a[i]) temp[i]++;
            if (a[i] < minValue) {
                minValue = a[i];
            }
        }
        
        for (int i = 0; i < length; i++) {
            if (temp[i] < 2) answer++;
        }
        return answer;
    }
}
