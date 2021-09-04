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
			System.out.println("Case #" + i + ": " + (boringCnt(R) - boringCnt(L - 1)));
		}
	}

	static int digits(long l) { // 123 -> 3, 123456 -> 6
		if (l < 10) return 1;
		else return 1 + digits(l / (long) 10);
	}

	static int findNum(long l, int i) { // 2345, 3 -> 4
		int digit = digits(l);
		return (int) (l / (long) Math.pow(10, digit - i)) % 10;
	}

	static long boringCnt(long l) {
		long res = 0;
		int digit = digits(l);
		for (int i = 1; i < digit; i++) {
			res += Math.pow(5, i);
		}
		for (int i = 1; i <= digit; i++) {
			if(i == digit) {
				if (i % 2 == 1) res += (findNum(l, digit) + 1) / 2;
				else res += findNum(l, digit) / 2 + 1;

				break;
			}
			if (i % 2 == 1) res += (findNum(l, i) / 2) * Math.pow(5, digit - i);
			else res += ((findNum(l, i) + 1) / 2) * Math.pow(5, digit - i);

			if(findNum(l, i) % 2 != i % 2) break;
		}
		System.out.println(l + " : " + res);
		return res;
	}
}