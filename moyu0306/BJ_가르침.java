public class Main {
    static int inputBitMask[];
    static int max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line1 = br.readLine().split(" ");
        String word;
        int N = Integer.parseInt(line1[0]);
        int K = Integer.parseInt(line1[1])-5;
        inputBitMask = new int[N];
        if(K<0) {System.out.println(0); return;}
        if(K==21) {System.out.println(N); return;}
        for(int i=0;i<N;i++){
            word = br.readLine();
            int bitMask = 0;
            char[] characters = word.toCharArray();
            for(char character : characters){
                bitMask|=(1<<(character-'a'));
            }
            inputBitMask[i] = bitMask;
        }
        int mask = 0;
        mask|=(1<<('a'-'a'));
        mask|=(1<<('n'-'a'));
        mask|=(1<<('t'-'a'));
        mask|=(1<<('c'-'a'));
        mask|=(1<<('i'-'a'));

        checkComb(0,0,K,mask);
        System.out.println(max);
    }
    public static int count(int bitMask){
        int cnt =0;
      for(int input : inputBitMask){
          if((bitMask&input) == input) cnt++;
      }
      return cnt;
    }
    public static void checkComb(int stage,int idx,int K, int bitMask){
        if(stage==K){
           max = Math.max(max,count(bitMask));
           return;
        }
        for(int i=idx; i<26;i++){
            int newBitMask = 1<<i;
            if((bitMask & newBitMask)>0) continue;
            checkComb(stage+1,i+1,K,bitMask|newBitMask);
        }
        return;
    }
}