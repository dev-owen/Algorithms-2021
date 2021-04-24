import java.io.*;
import java.util.*;

public class Kickstart_2020_F_3 {
	static int[][] rooms;
	static int S;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int i = 1; i <= T; i++) {
			st = new StringTokenizer(br.readLine());
			S = Integer.parseInt(st.nextToken());
			int Ra = Integer.parseInt(st.nextToken());
			int Pa = Integer.parseInt(st.nextToken());
			int Rb = Integer.parseInt(st.nextToken());
			int Pb = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			rooms = new int[S + 2][2 * S + 1]; // -1 : under construction, 0 : empty, 1 : Alma, 2 : Berthe
			rooms[Ra][Pa] = 2;
			rooms[Rb][Pb] = 3;
			for (int j = 0; j < C; j++) {
				st = new StringTokenizer(br.readLine());
				int Ri = Integer.parseInt(st.nextToken());
				int Pi = Integer.parseInt(st.nextToken());
				rooms[Ri][Pi] = -1;
			}
			for (int j = 0; j <= S; j++) {
				for (int k = 2 * j; k <= 2 * S; k++) {
					rooms[j][k] = -1;
				}
				rooms[j][0] = -1;
			}
			for (int j = 0; j <= 2 * S; j++) {
				rooms[S + 1][j] = -1;
			}
			int answer = minMax(Ra, Pa, Rb, Pb, true, true); // turn == true -> Alma, false -> Berthe
			System.out.println("Case #" + i + ": " + answer);
		}
	}

	static int minMax(int nowRa, int nowPa, int nowRb, int nowPb, boolean turn, boolean conti) {
		List<Point> neighbors = new ArrayList<>();
		if (turn && nowRa >= 1 && nowRa <= S && nowPa >= 1 && nowPa <= 2 * S - 1) {
			if (rooms[nowRa][nowPa + 1] == 0) neighbors.add(new Point(nowRa, nowPa+1));
			if (rooms[nowRa][nowPa - 1] == 0) neighbors.add(new Point(nowRa, nowPa-1));
			if (nowPa % 2 == 1 && rooms[nowRa + 1][nowPa + 1] == 0) neighbors.add(new Point(nowRa + 1, nowPa + 1));
			if (nowPa % 2 == 0 && rooms[nowRa - 1][nowPa - 1] == 0) neighbors.add(new Point(nowRa - 1, nowPa - 1));
		} else if (!turn && nowRb >= 1 && nowRb <= S && nowPb >= 1 && nowPb <= 2 * S - 1) {
			if (rooms[nowRb][nowPb + 1] == 0) neighbors.add(new Point(nowRb, nowPb + 1));
			if (rooms[nowRb][nowPb - 1] == 0) neighbors.add(new Point(nowRb, nowPb - 1));
			if (nowPb % 2 == 1 && rooms[nowRb + 1][nowPb + 1] == 0) neighbors.add(new Point(nowRb + 1, nowPb + 1));
			if (nowPb % 2 == 0 && rooms[nowRb - 1][nowPb - 1] == 0) neighbors.add(new Point(nowRb - 1, nowPb - 1));
		}
		if(neighbors.size() == 0) {
			if(!conti) return 0;
			return minMax(nowRa, nowPa, nowRb, nowPb, !turn, false);
		} else {
			if(turn) {
				int val = -1000;
				for(Point p : neighbors) {
					rooms[p.row][p.col] = 1;
					val = Math.max(1+minMax(p.row, p.col, nowRb, nowPb, false, true), val);
					rooms[p.row][p.col] = 0;
				}
				return val;
			} else {
				int val = 1000;
				for(Point p : neighbors) {
					rooms[p.row][p.col] = 1;
					val = Math.min(minMax(nowRa, nowPa, p.row, p.col, true, true)-1, val);
					rooms[p.row][p.col] = 0;
				}
				return val;
			}
		}

	}
	static class Point {
		int row, col;
		Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
}
