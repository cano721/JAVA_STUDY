package 유형별문제풀이.bruteForce;

import java.io.*;
import java.util.*;


public class BJ2309_일곱난쟁이 {

	static List<Integer> dwarf = new ArrayList<>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < 9; i++) {
			dwarf.add(Integer.parseInt(br.readLine()));
		}
		
		Collections.sort(dwarf);
		
		
		int sum = 0;
		for (int i = 0; i < 9; i++) {
			sum +=dwarf.get(i);
		}
		
		sum = sum-100;
		sum %=100;
		
		
		ff:for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
			
				if(i==j) continue;
				
				if((sum-dwarf.get(i)) == dwarf.get(j)) {
					int f = dwarf.get(i);
					int s = dwarf.get(j);
					dwarf.remove(Integer.valueOf(f));
					dwarf.remove(Integer.valueOf(s));
					break ff;
				}	
			}
		}
		for (int i = 0; i < 7; i++) {
			System.out.println(dwarf.get(i));
		}
		
		
	}

}
