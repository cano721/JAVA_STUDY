class Solution {
        public static int solution(int m, int n, String[] board) {
          int answer = 0;
          char[][] map = new char[m][n];
          for(int i=0;i<m;i++){
              for(int j=0;j<n;j++){
                  map[i][j] = board[i].charAt(j);
              }
          }
            
          int a = delete(m,n,map);
          while(a!=0) {
              answer+=a;
              a = delete(m,n,map);
          }
          return answer;
      }
    
    public static int delete(int m,int n, char[][] map) {
        int count = 0;
        boolean[][] check = new boolean[m][n];
        
        for(int i=0;i<m-1;i++){
            for(int j=0;j<n-1;j++) {
                char ch = map[i][j];
                if(ch!='b' && map[i][j+1]==ch && map[i+1][j] == ch && map[i+1][j+1] == ch) {
                    check[i][j] =true; check[i][j+1]=true;check[i+1][j] = true; check[i+1][j+1] =true;
                }
            }
        }
        
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++) {
                if(check[i][j] == true) {
                    count++;
                    map[i][j] = 'b';
                }
            }
        }
        for(int j=0;j<n;j++) {
            String str = "";
            for(int i=m-1;i>=0;i--) {
                if(map[i][j]!='b')
                    str+=map[i][j];
            }
            for(int k=m-1;k>=0;k--) {
                if(m-k-1<str.length()) {
                    map[k][j] = str.charAt(m-k-1);
                }
                else
                    map[k][j] = 'b';
            }
        }
        return count;
    }
}
