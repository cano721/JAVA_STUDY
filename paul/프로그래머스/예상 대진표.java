class Solution
{
    public int solution(int n, int a, int b)
    {
        int answer = 0;
        a--;
        b--;
        while(true){
            answer++;
           
            a /=2;
            b /=2;
            if(a == b) break;
            
        }

        return answer;
    }
}