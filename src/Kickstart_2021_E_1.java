import java.io.*;
import java.util.*;

public class Kickstart_2021_E_1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int i = 1; i <= T; i++) {
			st = new StringTokenizer(br.readLine());
			char[] charArr = st.nextToken().toCharArray();
			char[] ansArr = new char[charArr.length];
			ArrayList<ArrayList<Character>> aryLst = new ArrayList<>();
			Map<Character, Integer> unSortedMap = new HashMap<>();
			for (int j = 0; j < charArr.length; j++) {
				aryLst.add(new ArrayList<>());
				if (unSortedMap.containsKey(charArr[j])) unSortedMap.put(charArr[j], unSortedMap.get(charArr[j]) + 1);
				else unSortedMap.put(charArr[j], 1);
			}

			LinkedHashMap<Character, Integer> sortedMap = new LinkedHashMap<>();

			unSortedMap.entrySet()
					.stream()
					.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
					.forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));

			Iterator<Character> keys = sortedMap.keySet().iterator();
			while (keys.hasNext()) {
				char iterKey = keys.next();
				int iterValue = sortedMap.get(iterKey);
				for(int k = 0; k < charArr.length; k++) {
					if(charArr[k] != iterKey) {
						aryLst.get(k).add(iterKey);
					}
				}
			}
			boolean flag = true;
			for(int j = 0; j < charArr.length; j++) {
				if (aryLst.get(j).size() == 0) {
					flag = false;
					break;
				} else {
					// aryLst.get(j) -> sort
					aryLst.get(j).sort((a, b) -> unSortedMap.get(b) - unSortedMap.get(a));
					for(int k = 0; k < aryLst.get(j).size(); k++) {
						if(unSortedMap.get(aryLst.get(j).get(k)) > 0) {
							ansArr[j] = aryLst.get(j).get(k);
							unSortedMap.put(aryLst.get(j).get(k), unSortedMap.get(aryLst.get(j).get(k)) - 1);
							break;
						}
					}
					if(ansArr[j] == '\u0000') {
						// TODO - last one edge case handling
						// aabbddeefffffffgggggggggg
						flag = false;
						break;
					}
				}
			}
			if (flag) System.out.println("Case #" + i + ": " + String.valueOf(ansArr));
			else System.out.println("Case #" + i + ": IMPOSSIBLE");
		}
	}
}
