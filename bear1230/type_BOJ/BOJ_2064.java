import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int[] ip = new int[n];
        int netAddress;
        int netMask = 0;

		for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), ".");
			int temp = 0;
			for (int j = 0; j < 4; j++){
                int m = Integer.parseInt(st.nextToken());
                temp <<= 8;
                temp += m;
            }
			ip[i] = temp;	
		}
        for(int i = 31; i >= 0; i--){
            int bit = 1 << i;
            boolean check = false;
            for (int j = 1; j < n; j++) {
                if ((ip[0] & bit) != (ip[j] & bit)) {
                    check = true;
                    break;
                }
            }
            if (check) {
                break;
            } else {
                netMask |= bit;
            }
        }
        netAddress = ip[0] & netMask;


		String address = "";
		String mask = "";

		int check = (1 << 8) -1;

		int k = 3;
		while(true) {
			int anum = netAddress >> (8*k) & check;
			int mnum = netMask >> (8*k) & check;

			address += String.valueOf(anum);
			mask += String.valueOf(mnum);
            
            netAddress &= ((1<<(8*k))-1); 
            netMask &= ((1<<(8*k))-1);
			k--;

			if(k == -1) break;

			address += ".";
			mask += ".";
		}

		System.out.println(address);
		System.out.println(mask);
		
	}
}