import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution {
    List<String> list = new ArrayList<>();
    List<String[]> words;

    public void combination(int idx, String comb){
        if (idx == 4){
            list.add(comb);
            return;
        }

        for (int i = 0; i < words.get(idx).length; i++){
            combination(idx + 1, comb + words.get(idx)[i] + " ");
        }
    }

    public int[] solution(String[] info, String[] query) {
        words = new ArrayList<>();
        words.add(new String[]{"cpp", "java", "python"});
        words.add(new String[]{"backend", "frontend"});
        words.add(new String[]{"junior", "senior"});
        words.add(new String[]{"chicken", "pizza"});

        // 모든 경우의 수 리스트 만들기
        combination(0, "");

        // info를 저장할 배열 만들기
        List<Integer>[] score = new List[24];
        for (int i = 0; i < 24; i++) score[i] = new ArrayList<>();

        for (String value : info) {
            Integer s = Integer.parseInt(value.replaceAll("[^0-9]", ""));
            String t = value.replaceAll("[0-9]", "");

            // 해당하는 값에 점수 저장
            score[list.indexOf(t)].add(s);
        }
        for (int i = 0; i < 24; i++) Collections.sort(score[i]);

        // query
        int[] answer = new int[query.length];
        boolean[] check;
        for (int i = 0; i < query.length; i++){
            check = new boolean[24];
            Arrays.fill(check, true);

            int s = Integer.parseInt(query[i].replaceAll("[^0-9]", ""));
            String t = query[i].replaceAll("[0-9]", "");
            t = t.replaceAll("and ", "");

            // 확인할 수 있는 곳 체크하기
            String[] sList = t.split(" ");
            for (int j = 0; j < sList.length; j++){
                if (!sList[j].equals("-")){
                    for (int k = 0; k < 24; k++) {
                        if (!check[k]) continue;
                        String[] wow = list.get(k).split(" ");
                        if (!wow[j].equals(sList[j])) check[k] = false; //같지 않은 경우 모두 false 처리
                    }
                }
            }

//             체크된 곳에서 점수 조건에 맞는 사람 수 세기
            int count = 0;
            for (int j = 0; j < 24; j++) {
                if (!check[j]) continue;
                if (score[j].size() == 0) continue;

                // 이분 탐색
                int low = 0;
                int high = score[j].size();

                while (low < high){
                    int mid = (low + high) / 2;
                    if (s <= score[j].get(mid)){
                        high = mid;
                    }
                    else {
                        low = mid + 1;
                    }
                }
                count += score[j].size() - low;
            }

            // 정답에 추가
            answer[i] = count;
        }

        return answer;
    }
}
