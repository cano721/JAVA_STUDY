package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
class Block
{
    int x,y,r_cnt,size;
    String points;

    public Block(int x, int y, int r_cnt,int size,String points)
    {
        this.x = x;
        this.y = y;
        this.r_cnt = r_cnt;
        this.size = size;
        this.points = points;
    }
    public Block(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

}
public class d220329_상어중학교 {
	static int N, M, answer = 0;
	static int BLANK = 6;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {0, 0, -1, 1}, dy = {1, -1, 0, 0};
	static PriorityQueue<Block> pq;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
        
        pq = new PriorityQueue<>(new Comparator<Block>() {
            @Override
            public int compare(Block o1, Block o2) {
                if (o1.size == o2.size) {
                    if (o1.r_cnt == o2.r_cnt) {
                        if (o1.x == o2.x) return o2.y - o1.y;
                        return o2.x - o1.x;
                    }
                    return o2.r_cnt - o1.r_cnt;
                }
                return o2.size - o1.size;
            }
        });
        find();
        while(!pq.isEmpty())
        {
            Block cur = pq.poll();
            st = new StringTokenizer(cur.points);
            while(st.hasMoreTokens())
            {
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                map[x][y] = BLANK;
            }
            answer += (cur.size * cur.size);

            gravity();
            rotate();
            gravity();
            pq.clear();
            find();
        }
        System.out.print(answer);
    }
	private static void find() {
		visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(!visited[i][j] && map[i][j] > 0 && map[i][j] <= M) {
					visited[i][j] = true;
					makeBlock(i, j);
				}
			}
		}
		
	}
	private static void makeBlock(int x, int y) {
		int size = 1;
        int r_cnt = 0;
        int color = map[x][y];
        StringBuilder sb = new StringBuilder(x+" "+y);
        StringBuilder rainbow = new StringBuilder();
        Queue<Block> q = new LinkedList<>();
        q.add(new Block(x,y,0,0,""));

        while(!q.isEmpty())
        {
            Block cur = q.poll();
            for(int dir = 0; dir < 4; ++dir)
            {
                int mx = cur.x + dx[dir];
                int my = cur.y + dy[dir];

                if(!isValid(mx,my) || visited[mx][my] || map[mx][my] == BLANK) continue;
                if(map[mx][my] == 0)
                {
                    q.add(new Block(mx,my));
                    rainbow.append(mx+" "+my+" ");
                    r_cnt++;
                }
                else if(map[mx][my] == color) q.add(new Block(mx,my));
                else continue;
                visited[mx][my] = true;
                size++;
                sb.append(" "+mx+" "+my);
            }
            if(q.isEmpty())
            {
                if(size >= 2)
                {
                    pq.add(new Block(x,y,r_cnt,size,sb.toString()));
                    if(r_cnt > 0)
                    {
                        StringTokenizer st = new StringTokenizer(rainbow.toString());
                        while(st.hasMoreTokens())
                        {
                            int fst = Integer.parseInt(st.nextToken());
                            int sec = Integer.parseInt(st.nextToken());
                            visited[fst][sec] = false;
                        }
                    }
                }
            }
        }
	}
	static void gravity()
    {
        for(int i = 0; i < N; ++i)
        {
            for(int j = N-1; j >= 0; --j)
            {
                if(map[j][i] == BLANK || map[j][i] == -1) continue;
                int dest = j+1;
                while(true)
                {
                    if(dest == N) break;
                    if(map[dest][i] == BLANK) dest++;
                    else break;
                }
                if(dest == j+1) continue;
                map[dest-1][i] = map[j][i];
                map[j][i] = BLANK;
            }
        }
    }
    static void rotate()
    {
        int [][] tmp = new int[N][N];
        for(int i = 0; i < N; ++i)
        {
            for(int j = 0; j < N; ++j)
            {
                tmp[N-j-1][i] = map[i][j];
            }
        }
        for(int i = 0; i < N; ++i)
        {
            System.arraycopy(tmp[i],0,map[i],0,N);
        }
    }
    static boolean isValid(int x, int y)
    {
        return x >= 0 && y >= 0 && x < N && y < N;
    }

}
