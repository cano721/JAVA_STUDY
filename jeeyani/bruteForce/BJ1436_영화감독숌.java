package bruteForce;

import java.io.*;

public class BJ1436_영화감독숌 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	     
	     int n = Integer.parseInt(br.readLine());
	   
		Integer moveName = 666;
		int cnt = 1;
		
		while(true){
		
			
			if(moveName.toString().contains("666")){
			
				if(cnt==n){
				
					System.out.println(moveName);
					break;
				
				}
				cnt++;
			}
			moveName++;
		}


	}

}
