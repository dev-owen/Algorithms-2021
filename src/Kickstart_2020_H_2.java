import java.io.*;
import java.util.*;

public class Kickstart_2020_H_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int i = 1; i <= T; i++) {
			st = new StringTokenizer(br.readLine());
			long L = Long.parseLong(st.nextToken());
			long R = Long.parseLong(st.nextToken());
			System.out.println("Case #" + i + ": " + (boring(R, 1) - boring(L-1, 1)));
			System.out.println(boring(R, 1));
			System.out.println(boring(L-1, 1));
		}
	}

	static int digits(long l) { // 123 -> 3, 123456 -> 6
		if (l < 10) return 1;
		else return 1 + digits(l / (long) 10);
	}

	static long boring(long l, int position) {
		if(l < 10) return position%2 == 0 ? l/2 : (l+1)/2;
		long ans = 0;
		int lDigits = digits(l);
		long lthNumber = l/(long)Math.pow(10, lDigits-1);
		if(position%2 == 0) ans += ((lthNumber/2)+1)*Math.pow(5, lDigits-1);
		else ans += ((lthNumber+1)/2)*Math.pow(5, lDigits-1);
		if(lthNumber%2 == position%2) ans += boring(l%10, position+1);
		return ans + boring(l%10, position);
	}
}