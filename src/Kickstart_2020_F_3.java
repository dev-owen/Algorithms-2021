import java.io.*;
import java.util.*;

public class Kickstart_2020_F_3 {
	static int[][] rooms;
	static int S, minMax = 0;

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
			minMax = 0;
			calcScore(Ra, Pa, Rb, Pb, 1, 1, true, true); // turn == true -> Alma, false -> Berthe
			System.out.println("Case #" + i + ": " + minMax);
		}
	}

	static void calcScore(int nowRa, int nowPa, int nowRb, int nowPb, int aScore, int bScore, boolean turn, boolean conti) {
		boolean goFurther = false;
		if (turn && nowRa >= 1 && nowRa <= S && nowPa >= 1 && nowPa <= 2 * S - 1) {
			if (rooms[nowRa][nowPa + 1] == 0) {
				goFurther = true;
				rooms[nowRa][nowPa + 1] = 2;
				calcScore(nowRa, nowPa + 1, nowRb, nowPb, aScore + 1, bScore, false, true);
				rooms[nowRa][nowPa + 1] = 0;
			}
			if (rooms[nowRa][nowPa - 1] == 0) {
				goFurther = true;
				rooms[nowRa][nowPa - 1] = 2;
				calcScore(nowRa, nowPa - 1, nowRb, nowPb, aScore + 1, bScore, false, true);
				rooms[nowRa][nowPa - 1] = 0;
			}
			if (nowPa % 2 == 1 && rooms[nowRa + 1][nowPa + 1] == 0) {
				goFurther = true;
				rooms[nowRa + 1][nowPa + 1] = 2;
				calcScore(nowRa + 1, nowPa + 1, nowRb, nowPb, aScore + 1, bScore, false, true);
				rooms[nowRa + 1][nowPa + 1] = 0;
			}
			if (nowPa % 2 == 0 && rooms[nowRa - 1][nowPa - 1] == 0) {
				goFurther = true;
				rooms[nowRa - 1][nowPa - 1] = 2;
				calcScore(nowRa - 1, nowPa - 1, nowRb, nowPb, aScore + 1, bScore, false, true);
				rooms[nowRa - 1][nowPa - 1] = 0;
			}
			if(!goFurther) {
				if(conti) calcScore(nowRa, nowPa, nowRb, nowPb, aScore, bScore, false, false);
				else {
					minMax = Math.min(minMax, aScore - bScore);
					return;
				}
			}
		} else if (!turn && nowRb >= 1 && nowRb <= S && nowPb >= 1 && nowPb <= 2 * S - 1) {
			if (rooms[nowRb][nowPb + 1] == 0) {
				goFurther = true;
				rooms[nowRb][nowPb + 1] = 3;
				calcScore(nowRa, nowPa, nowRb, nowPb + 1, aScore, bScore + 1, true, true);
				rooms[nowRb][nowPb + 1] = 0;
			}
			if (rooms[nowRb][nowPb - 1] == 0) {
				goFurther = true;
				rooms[nowRb][nowPb - 1] = 3;
				calcScore(nowRa, nowPa, nowRb, nowPb - 1, aScore, bScore + 1, true, true);
				rooms[nowRb][nowPb - 1] = 0;
			}
			if (nowPb % 2 == 1 && rooms[nowRb + 1][nowPb + 1] == 0) {
				goFurther = true;
				rooms[nowRb + 1][nowPb + 1] = 3;
				calcScore(nowRa, nowPa, nowRb + 1, nowPb + 1, aScore, bScore + 1, false, true);
				rooms[nowRb + 1][nowPb + 1] = 0;
			}
			if (nowPb % 2 == 0 && rooms[nowRb - 1][nowPb - 1] == 0) {
				goFurther = true;
				rooms[nowRb - 1][nowPb - 1] = 3;
				calcScore(nowRa, nowPa, nowRb - 1, nowPb - 1, aScore, bScore + 1, false, true);
				rooms[nowRb - 1][nowPb - 1] = 0;
			}
			if(!goFurther) {
				if(conti) calcScore(nowRa, nowPa, nowRb, nowPb, aScore, bScore, true, false);
				else {
					minMax = Math.max(minMax, aScore - bScore);
					return;
				}
			}
		}
		if (!goFurther) {
			minMax = Math.max(minMax, aScore - bScore);
		}
	}
}
