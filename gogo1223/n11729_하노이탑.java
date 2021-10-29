import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class n11729_하노이탑 {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		
		int n = Integer.parseInt(st.nextToken());
		//코드 참조
		bw.write((int)Math.pow(2,n)-1 + "\n");
        move(n, 1, 2, 3);
        bw.flush();
        bw.close();
	}

	private static void move(int n, int start, int mid, int end) throws IOException {
		if(n == 1) {
			bw.write(start + " " + end + "\n");
            return;
		}
		//n-1개를 start -> mid 이동
        move(n-1, start, end, mid);

        //1개 옮기기
        bw.write(start + " " + end + "\n");

        //n-1개 mid -> end 이동
        move(n-1, mid, start, end);
		
	}

}
