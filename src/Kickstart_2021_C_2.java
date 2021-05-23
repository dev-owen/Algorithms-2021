import java.io.*;
import java.util.*;

public class Kickstart_2021_C_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		long[] dp = new long[1000001];
		dp[1] = 1L;
		for(int n = 2; n <= 1000000; n++) {
			dp[n] = dp[n-1] + n;
		}
		for (int i = 1; i <= T; i++) {
			long G = Long.parseLong(br.readLine());
			int cnt = 0, start = 0, end = 1;
			long tmp = 0;
			while(start < end && end <= 1000000) {
				tmp = dp[end] - dp[start];
				if(tmp < G) end++;
				else if(tmp > G) start++;
				else {
					cnt++;
					start++;
				}
			}
			System.out.println("Case #" + i + ": " + cnt);
		}
	}
}
