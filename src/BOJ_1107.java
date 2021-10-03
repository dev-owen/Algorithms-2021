import java.io.*;
import java.util.*;

public class BOJ_1107 {
	static int firstNum, minResult = Integer.MAX_VALUE;
	static int[] firstNumArr;
	static boolean[] workingBtns;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		firstNum = Integer.parseInt(br.readLine());
		firstNumArr = Arrays.stream(String.valueOf(firstNum).split("")).mapToInt(Integer::parseInt).toArray();
		int notWorkDigits = Integer.parseInt(br.readLine());
		workingBtns = new boolean[10];
		Arrays.fill(workingBtns, true);
		if(notWorkDigits != 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			while (st.hasMoreTokens()) {
				int digit = Integer.parseInt(st.nextToken());
				workingBtns[digit] = false;
			}
		}
		find("");
		System.out.println(Math.min(Math.abs(100-firstNum), minResult));
	}

	static void find(String num) {
		for(int i = 0; i < 10; i++) {
			if(workingBtns[i]) {
				String tmpNum = num + i;
				minResult = Math.min(minResult, Math.abs(firstNum - Integer.parseInt(tmpNum)) + tmpNum.length());
				if (tmpNum.length() < 6) {
					find(tmpNum);
				}
			}
		}
	}
}

