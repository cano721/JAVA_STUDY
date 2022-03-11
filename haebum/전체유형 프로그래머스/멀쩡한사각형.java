/**
    
    1. 가로와 세로의 최소 공약수를 찾기
    
    2. 공약수로 나눈 가로와 세로의 직사각형이 공약수만큼 있음
    
    3. 가로와 세로 직사각형에서 사용불가능한 사각형의 개수는 (가로+세로-공약수)
    
    4. 전체개수 - 사용불가능한 사각형의 개수(가로+세로-공약수)

**/

class Solution {
    public long solution(int w, int h) {
        
        long all = (long)w*h;
        
        int g = gcd(w,h);
        System.out.println(g);
        return all -(w+h-g);
    }
    
    public int gcd(int w, int h){
        
        if(h == 0) return w;
        
        return gcd(h,w%h);
    }
}