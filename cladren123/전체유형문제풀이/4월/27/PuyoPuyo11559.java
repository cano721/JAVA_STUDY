package studyGroup.april.april27;

/*

12개의 줄 6개의 필드 정보

R G B P Y

터질 것이 있는지 탐색하는 함수
중력으로 내리는 함수


*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PuyoPuyo11559 {

    static char[][] board;
    static int[][] visited;

    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,-1,0,1};

    static int answer;


    public static void main(String[] args) throws IOException {
        board = new char[12][6];
        answer = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0; i < 12; i++)
        {
            String s = br.readLine();
            for(int j = 0; j < 6; j++)
            {
                board[i][j] = s.charAt(j);
            }
        }

        int flag = 0;
        while(flag == 0)
        {
            boolean bo = search();
            if(bo == false)
            {
                flag = 1;
            }
            downPuyo();
        }


        System.out.println(answer);

    }

    // 중력으로 내려오는 함수
    public static void downPuyo()
    {
        for(int x = 0; x < 6; x++)
        {
            ArrayList<Character> store = new ArrayList<>();

            for(int y = 11; y >= 0; y--)
            {
                if(board[y][x] != '.')
                {
                    store.add(board[y][x]);
                    board[y][x] = '.';
                }
            }

            int yindex = 11;
            for(int y = 0; y < store.size(); y++)
            {
                board[yindex--][x] = store.get(y);
            }
        }



    }

    // 터질 것이 있는지 탐색 BFS
    public static boolean search()
    {
        visited = new int[12][6];
        int flag = 0;

        for(int i = 0; i < 12; i++)
        {
            for(int j = 0; j < 6; j++)
            {
                if(board[i][j] != '.')
                {
                    boolean bo = bfs(i, j);
                    if(bo)
                    {
                        flag = 1;
                    }
                }
            }
        }





        if(flag == 1)
        {
            answer++;
            return true;
        }

        return false;

    }

    public static boolean bfs(int y, int x)
    {
        Queue<dot> que = new LinkedList<>();

        que.add(new dot(y, x, board[y][x]));
        visited[y][x] = 1;

        ArrayList<int[]> store = new ArrayList<>();
        int[] one = new int[2];
        one[0] = y;
        one[1] = x;
        store.add(one);

        int count = 1;

        while(!que.isEmpty())
        {
            dot d = que.poll();

            for(int i = 0; i < 4; i++)
            {
                int ny = d.y + dy[i];
                int nx = d.x + dx[i];

                if(0 <= ny && ny < 12 && 0 <= nx && nx < 6)
                {
                    if(visited[ny][nx] == 0 && board[ny][nx] == d.word)
                    {
                        visited[ny][nx] = 1;
                        one = new int[2];
                        one[0] = ny;
                        one[1] = nx;
                        store.add(one);
                        count++;
                        que.add(new dot(ny, nx, d.word));
                    }

                }
            }
        }

        // 4개가 연결되어있으면 터뜨린다.
        if(count >= 4)
        {
            for (int[] caseOne : store)
            {
                int oney = caseOne[0];
                int onex = caseOne[1];
                board[oney][onex] = '.';
            }

            return true;
        }

        return false;
    }



    public static class dot
    {
        int y;
        int x;
        char word;

        dot(int y, int x, char word)
        {
            this.y = y;
            this.x = x;
            this.word = word;
        }

    }

}
