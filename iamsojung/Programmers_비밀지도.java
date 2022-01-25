class Solution {
    public String decoding(int n1, int n2, int n){
        String zero = "";
        String s = Integer.toBinaryString(n1 | n2);
        if(s.length() < n) {
            for(int i = s.length(); i < n; i++){
                zero += "0";
            }
        }
        return zero + s;
    }

    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        for(int i = 0; i < n; i++){
            answer[i] = decoding(arr1[i], arr2[i], n);
            answer[i] = answer[i].replaceAll("[1]", "#");
            answer[i] = answer[i].replaceAll("[0]", " ");
        }
        return answer;
    }
}