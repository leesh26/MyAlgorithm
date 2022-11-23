import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            HashMap<Character, List<Integer>> hashMap = new HashMap<>();
            String s = br.readLine();
            int maxValue = -1;
            int minValue = Integer.MAX_VALUE;
            int k = Integer.parseInt(br.readLine());
            if (k == 1) {
                System.out.println("1 1");
                continue;
            }

            for (int j = 0; j < s.length(); j++) {
                // 만약 이 단어가 hashMap에 없다면 추가
                if (!hashMap.containsKey(s.charAt(j))){
                    List<Integer> l = new ArrayList<>();
                    l.add(j);
                    hashMap.put(s.charAt(j), l);
                }
                // 있다면 찾아서 endIdx 변경, maxValue 체크
                // -> 추가로 count가 k가 되면 minValue
                else {
                    hashMap.get(s.charAt(j)).add(j);

                    List<Integer> p = hashMap.get(s.charAt(j));
                    if (p.size() >= k) {
                        // 추가한 값 기준 길이만큼
                        int len = p.get(p.size() - 1) - p.get(p.size() - k) + 1;
                        minValue = Math.min(minValue, len);
                        maxValue = Math.max(maxValue, len);
                    }
                }
            }
            if (minValue == Integer.MAX_VALUE) System.out.println(-1);
            else System.out.println(minValue + " " + maxValue);
        }
    }
}
