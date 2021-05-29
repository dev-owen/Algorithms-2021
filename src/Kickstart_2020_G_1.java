import java.io.*;
import java.util.*;

public class Kickstart_2020_G_1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int i = 1; i <= T; i++) {
			char[] charArr = br.readLine().toCharArray();
			ArrayList<Integer> kicks = new ArrayList<>();
			ArrayList<Integer> starts = new ArrayList<>();
			long cnt = 0L;
			for(int j = 0; j < charArr.length; j++) {
				if(charArr[j] == 'K' && (j+3 < charArr.length)) {
					if(charArr[j+1] == 'I' && charArr[j+2] == 'C' && charArr[j+3] == 'K') kicks.add(j);
				} else if(charArr[j] == 'S' && (j+4 < charArr.length)) {
					if(charArr[j+1] == 'T' && charArr[j+2] == 'A' && charArr[j+3] == 'R' && charArr[j+4] == 'T') {
						starts.add(j);
						cnt += kicks.size();
					}
				}
			}
			System.out.println("Case #" + i + ": " + cnt);
		}
	}
}
