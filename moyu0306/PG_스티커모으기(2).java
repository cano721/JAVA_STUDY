class Solution {
    int[] DP1;
    int[] DP2;
    public int solution(int sticker[]) {
        if(sticker.length == 1) return sticker[0];
        DP1 = new int[sticker.length];
        DP2 = new int[sticker.length];
        DP1[0] = 0;
        DP1[1] = sticker[1];
        for(int i= 2; i<sticker.length; i++){
        DP1[i] = Integer.max(DP1[i-1],DP1[i-2]+sticker[i]);
        }
        
        
        DP2[0] = sticker[0];
        DP2[1] = sticker[0];
        for(int i= 2; i<sticker.length-1; i++){
        DP2[i] = Integer.max(DP2[i-1],DP2[i-2]+sticker[i]);
        }
        int answer = Integer.max(DP1[sticker.length-1],DP2[sticker.length-2]);

        return answer;
    }
}