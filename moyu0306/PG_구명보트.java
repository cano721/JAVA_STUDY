import java.util.Arrays;

class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        int i=0, j = people.length-1;
        int cnt =0;
        for(i = 0;i<j;j--){
            if(people[i]+people[j]>limit) cnt++;
            else i++;
        }
        if(i==j) i++;
        return i+cnt;
    }
}