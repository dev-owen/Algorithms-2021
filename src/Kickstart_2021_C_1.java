import java.io.*;
import java.util.*;

public class Kickstart_2021_C_1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int i = 1; i <= T; i++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			char[] arr = br.readLine().toCharArray();
			long[] dp = new long[100001];
			long sum = 0L;
			for (int j = (N-1)/2; j >= 0; j--) {
				boolean flag = true;
				if(j == (N-1)/2) {
					for(int k = j; k >= 0; k--) {
						if(arr[N-k-1] - arr[k] > 0) break;
						else if(arr[N-k-1] - arr[k] < 0) {
							flag = false;
							break;
						} else {
							if(k == 0) flag = false;
							else continue;
						}
					}
					if(flag) sum += 1;
				}
				long tmp = 1;
				for (int k = j; k <= (N-1)/2; k++) {
					if(k == j) tmp *= (arr[k] - 'a');
					else tmp *= K;
				}
				sum += (long)(arr[j] - 'a')*Math.pow(K, ((N-1)/2 - j));
				sum %= 1000000007L;
			}
			System.out.println("Case #" + i + ": "+sum);
		}
	}
}
