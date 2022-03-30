package programmers;
class Result{
    boolean win;
    int count;
    
    public Result(boolean win, int count){
        this.win = win;
        this.count = count;
    }
}
public class d220331_사라지는발판 {
	public static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int boardRowLen, boardColLen;
    
    
	public static void main(String[] args) {
		//[[1, 1, 1}, {1, 1, 1}, {1, 1, 1]]	[1, 0]	[1, 2]	5
		int[][] board = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}}; 
		int[] aloc = {1, 0};
		int[] bloc = {1, 2};
		int ans = solution(board, aloc, bloc);
		System.out.println(ans);
	}
	private static int solution(int[][] board, int[] aloc, int[] bloc) {
		boardRowLen = board.length;
        boardColLen = board[0].length;
        return dfs(aloc[0], aloc[1], bloc[0], bloc[1], 0, 0, board).count;
	}
	private static Result dfs(int ax, int ay, int bx, int by, int aDepth, int bDepth, int[][] board) {
		boolean win = false;
        int minCount = 5*5;
        int maxCount = aDepth + bDepth;
        
        if(aDepth == bDepth && board[ax][ay] == 1){
            for(int[] tmp : dir){
                int axTmp = ax + tmp[0];
                int ayTmp = ay + tmp[1];
                if(isValid(axTmp, ayTmp, board)){
                    board[ax][ay] = 0;

                    Result d = dfs(axTmp, ayTmp, bx, by, aDepth+1, bDepth, board);
                    win |= !d.win;
                    if(!d.win) minCount = Math.min(minCount, d.count);
                    else maxCount = Math.max(maxCount, d.count);

                    board[ax][ay] = 1;
                }
            }
        } else if (aDepth > bDepth && board[bx][by] == 1){
            for(int[] tmp : dir){
                int bxTmp = bx + tmp[0];
                int byTmp = by + tmp[1];
                if(isValid(bxTmp, byTmp, board)){
                    board[bx][by] = 0;

                    Result d = dfs(ax, ay, bxTmp, byTmp, aDepth, bDepth+1, board);
                    win |= !d.win;
                    if(!d.win) minCount = Math.min(minCount, d.count);
                    else maxCount = Math.max(maxCount, d.count);

                    board[bx][by] = 1;
                }
            }
        }
        
        return new Result(win, win ? minCount : maxCount);
	}
	private static boolean isValid(int x, int y, int[][] board) {
        if(x < 0 || x >= boardRowLen || y < 0 || y >= boardColLen || board[x][y] == 0) return false;
        return true;
	}

}
