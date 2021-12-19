package BOJ;

import java.util.*;
import java.io.*;


public class Main{
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        
        ArrayList<Dot> arr = new ArrayList<Dot>();
        ArrayList<Link> linkList = new ArrayList<Link>();
        
        for(int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());;
        	
        	arr.add(new Dot(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken())));
        }
        
        for(int i = 0; i < N-1; i++) {
        	Dot dot = arr.get(i);
        	
        	for(int j = i+1; j < N; j++) {
        		Dot dot2 = arr.get(j);
        		
        		linkList.add(new Link(i, j, getDist(dot, dot2)));
        	}
        }
        
        Collections.sort(linkList, new Comparator<Link>() {
        	@Override
        	public int compare(Link o1, Link o2) {
        		return Double.compare(o1.weight, o2.weight);
        	}
        });
        
        
        double sum = 0;
        int[] set = new int[N];
        for(int i = 0; i < N; i++) {
        	set[i] = i;
        }
        
        for(int i = 0; i < linkList.size(); i++) {
        	Link link = linkList.get(i);
        	if(getParent(set, link.from) != getParent(set, link.to)) {
        		union(set, link.from, link.to);
        		sum += link.weight;
        	}
        }
        
        System.out.println(Math.floor(sum*100)/100.0);
        
        
    }
    static double getDist(Dot a, Dot b) {
    	return Math.sqrt(Math.pow(a.a - b.a, 2) + Math.pow(a.b - b.b, 2));
    }
    static int getParent(int[] set, int a) {
    	if(set[a] == a) return a;
    	return set[a] = getParent(set, set[a]);
    }
    
    static void union(int[] set, int a, int b) {
    	a = getParent(set, a);
    	b = getParent(set, b);
    	
    	if(a < b) set[b] = a;
    	else set[a] = b;
    }
    static class Dot{
    	double a;
    	double b;
    	
    	public Dot(double a, double b) {
    		this.a = a;
    		this.b = b;
    	}
    }
    static class Link{
    	int from;
    	int to;
    	double weight;
    	
    	public Link(int from, int to, double weight) {
    		this.from = from;
    		this.to = to;
    		this.weight = weight;
    	}
    }
}