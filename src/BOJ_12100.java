import java.io.*;
import java.util.*;

public class BOJ_12100 {
	static int maxRes = 0, N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int[][] matrix = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i = 0; i < 4; i++) { // 0 : 위, 1 : 오른쪽, 2 : 아래, 3 : 왼쪽
			swipe(matrix, i, 0);
		}
		System.out.println(maxRes);
	}

	private static void swipe(int[][] prevMatrix, int dir, int cnt) {
		if(cnt == 5) {
			int tmpRes = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(tmpRes < prevMatrix[i][j]) tmpRes = prevMatrix[i][j];
				}
			}
			if(tmpRes > maxRes) maxRes = tmpRes;
			return;
		}
		int[][] newMatrix = new int[N][N];
		Stack<Integer> stack = new Stack<>();
		if(dir == 0) { // 위로
			for(int i = 0; i < N; i++) {
				boolean isMerged = false;
				for(int j = 0; j < N; j++) {
					if(stack.isEmpty()) stack.push(prevMatrix[j][i]);
					else if(stack.peek() == prevMatrix[j][i] && !isMerged) {
						stack.pop();
						stack.push(prevMatrix[j][i]*2);
						isMerged = true;
					} else {
						stack.push(prevMatrix[j][i]);
						isMerged = false;
					}
				}
				for(int j = stack.size()-1; j >= 0; j--) {
					newMatrix[j][i] = stack.pop();
				}
			}
		} else if(dir == 1) { // 오른쪽으로
			for(int i = 0; i < N; i++) {
				boolean isMerged = false;
				for(int j = 0; j < N; j++) {
					if(stack.isEmpty()) stack.push(prevMatrix[i][j]);
					else if(stack.peek() == prevMatrix[i][j] && !isMerged) {
						stack.pop();
						stack.push(prevMatrix[i][j]*2);
						isMerged = true;
					} else {
						stack.push(prevMatrix[i][j]);
						isMerged = false;
					}
				}
				for(int j = stack.size()-1; j >= 0; j--) {
					newMatrix[i][N-j-1] = stack.pop();
				}
			}
		} else if (dir == 2) { // 아래로
			for(int i = 0; i < N; i++) {
				boolean isMerged = false;
				for(int j = 0; j < N; j++) {
					if(stack.isEmpty()) stack.push(prevMatrix[j][i]);
					else if(stack.peek() == prevMatrix[j][i] && !isMerged) {
						stack.pop();
						stack.push(prevMatrix[j][i]*2);
						isMerged = true;
					} else {
						stack.push(prevMatrix[j][i]);
						isMerged = false;
					}
				}
				for(int j = stack.size()-1; j >= 0; j--) {
					newMatrix[N-j-1][i] = stack.pop();
				}
			}
		} else if (dir == 3) { // 왼쪽으로
			for(int i = 0; i < N; i++) {
				boolean isMerged = false;
				for(int j = 0; j < N; j++) {
					if(stack.isEmpty()) stack.push(prevMatrix[i][j]);
					else if(stack.peek() == prevMatrix[i][j] && !isMerged) {
						stack.pop();
						stack.push(prevMatrix[i][j]*2);
						isMerged = true;
					} else {
						stack.push(prevMatrix[i][j]);
						isMerged = false;
					}
				}
				for(int j = stack.size()-1; j >= 0; j--) {
					newMatrix[i][j] = stack.pop();
				}
			}
		}
		for(int i = 0; i < 4; i++) {
			swipe(newMatrix, i, cnt+1);
		}
	}
}
