import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {


	public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out));
	    int n, m, min, sum, sum1;
        char[][] arr;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer( br.readLine() );
		n = Integer.parseInt( st.nextToken() );
		m = Integer.parseInt( st.nextToken() );
		String tmp = "";
		
		arr = new char[n][m];
		
		for( int i=0; i<n; i++ ) {
			tmp = br.readLine();
			for( int j=0; j<m; j++ ) arr[i][j] = tmp.charAt(j);
		}
		
		sum = Integer.MAX_VALUE;
		min = Integer.MAX_VALUE;
		for( int i=0; i<n-7; i++ ) {
			for( int j=0; j<m-7; j++ ) {
				char c = arr[i][j];
				sum1 = 0;
							
				for( int k=i; k<i+8; k++ ) {
					for( int z=j; z<j+8; z++ ) {
						if( arr[k][z] != c ) sum1++;
						
						if( z==j+7 ) continue;
						else if( c=='W' ) c='B';
						else if( c=='B' ) c='W';
					}
				}
				sum = Math.min( sum1, 64-sum1 );
				min = Math.min( sum, min );
			}
		}
		
		bw.write( min + "\n" );
		bw.flush();
		bw.close();
		br.close();
	}
}
