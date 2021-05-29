import java.io.*;
import java.util.*;

public class Kickstart_2020_G_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int i = 1; i <= T; i++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int[][] coins = new int[N+2][N+2];
			for (int j = 1; j <= N; j++) {
				st = new StringTokenizer(br.readLine());
				for(int k = 1; k <= N; k++) {
					coins[j][k] = Integer.parseInt(st.nextToken());
				}
			}
			long max = 0;
			for(int j = 1; j <= N; j++) {
				long cnt = 0;
				for(int k = 1; k <= j; k++) {
					cnt += coins[k][N-j+k];
				}
				max = Math.max(max, cnt);
			}
			for(int j = 1; j <= N-1; j++) {
				long cnt = 0;
				for(int k = 1; k <= j; k++) {
					cnt += coins[N-j+k][k];
				}
				max = Math.max(max, cnt);
			}
			System.out.println("Case #" + i + ": " +max);
		}
	}
}
