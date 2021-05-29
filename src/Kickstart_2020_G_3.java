import java.io.*;
import java.util.*;

public class Kickstart_2020_G_3 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int i = 1; i <= T; i++) {
			st = new StringTokenizer(br.readLine());
			int W = Integer.parseInt(st.nextToken());
			long N = Long.parseLong(st.nextToken());
			long[] numArr = new long[W];
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < W; j++) {
				numArr[j] = Long.parseLong(st.nextToken());
			}
			Arrays.sort(numArr);
			// find minimum distance num
			long minDist = 1000000000L;
			long minMoves = 1000000000L;
			Set<Long> set = new HashSet<>();
			for(int j = 0; j < W-1; j++) {
				if(numArr[j+1] - numArr[j] < minDist) {
					minDist = numArr[j+1] - numArr[j];
					set = new HashSet<>();
					set.add(numArr[j]);
					set.add(numArr[j+1]);
				} else if(numArr[j+1] - numArr[j] == minDist) {
					set.add(numArr[j]);
					set.add(numArr[j+1]);
				}
			}
			if(N - numArr[W-1] + numArr[0] < minDist) {
				set = new HashSet<>();
				set.add(numArr[W-1]);
				set.add(numArr[0]);
			} else if(N - numArr[W-1] + numArr[0] == minDist) {
				set.add(numArr[W-1]);
				set.add(numArr[0]);
			}
			for(long s: set) {
				minMoves = Math.min(minMoves, getMoves(s, numArr, N, 0));
			}
			System.out.println("Case #" + i + ": "+minMoves);
		}
	}

	static long getMoves(long goal, long[] arr, long N, int flag) {
		long cnt = 0;
		for(int i = 0; i < arr.length; i++) {
			cnt += Math.min(Math.abs(arr[i] - goal), N - Math.abs(arr[i] - goal));
		}
		if(goal > 1 && flag != 1) {
			long oneBack = getMoves(goal-1, arr, N, -1);
			if(oneBack <= cnt) return oneBack;
		}
		if(goal < N && flag != -1) {
		  long oneForward = getMoves(goal+1, arr, N, 1);
		  if(oneForward <= cnt) return oneForward;
		}

		return cnt;
	}
}
