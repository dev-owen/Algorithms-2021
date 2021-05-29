import java.io.*;
import java.util.*;

public class Kickstart_2020_G_3 {
	static long[] prefix;
	static int W, N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int i = 1; i <= T; i++) {
			st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			long[] numArr = new long[W];
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				numArr[j] = Long.parseLong(st.nextToken());
			}
			Arrays.sort(numArr);
			prefix = new long[W];
			prefix[0] = numArr[0];
			for (int j = 1; j < W; j++) {
				prefix[j] = prefix[j - 1] + numArr[j];
			}
			long res = Long.MAX_VALUE;
			for (int j = 0; j < W; j++) {
				long op1 = 0, op2 = 0;
				int p = 0, q = 0;

				int lo = 0, hi = j;

				while (lo <= hi) {
					int mid = lo + (hi - lo) / 2;
					op1 = numArr[j] - numArr[mid];
					op2 = N - op1;

					if (op1 <= op2) {
						p = mid;
						hi = mid - 1;
					} else {
						lo = mid + 1;
					}
				}

				lo = i;
				hi = W - 1;
				while (lo <= hi) {
					int mid = lo + (hi - lo) / 2;
					op1 = numArr[mid] - numArr[j];
					op2 = N - op1;
					if (op1 <= op2) {
						q = mid;
						lo = mid + 1;
					} else {
						hi = mid - 1;
					}
				}
				long ans = 0;
				long sm1 = (j - p + 1) * numArr[j] - getSum(p, j);
				long sm2 = p * (N - numArr[j]) + getSum(0, p - 1);
				ans += sm1 + sm2;

				sm1 = getSum(j, q) - (q - j + 1) * numArr[j];
				sm2 = (N + numArr[j]) * (W - q - 1) - getSum(q + 1, W - 1);
				ans += sm1 + sm2;
				res = Math.min(res, ans);
			}
			System.out.println("Case #" + i + ": " + res);
		}
	}

	static long getSum(int l, int r) {
		if (l <= r) {
			if (l <= 0) return prefix[r];
			return prefix[r] - prefix[l - 1];
		}
		return 0;
	}
}