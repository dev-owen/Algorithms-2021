import java.io.*;
import java.util.*;

public class Kickstart_2020_H_1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int i = 1; i <= T; i++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int S = Integer.parseInt(st.nextToken());
			long cnt = K;
			cnt += Math.min(K-S+N-S,N);
			System.out.println("Case #" + i + ": "+cnt);
		}
	}
}
