import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        Map<String, Integer> hash1 = new HashMap<>(); // 유저, 신고 당한 횟수
        Map<String, HashSet<String>> hash2 = new HashMap<>(); // 유저, 신고한 id 리스트
        
        for (String id : id_list){
            hash1.put(id, 0);
            hash2.put(id, new HashSet<>());
        }
        
        for (int i = 0; i < report.length; i++){
            String[] temp = report[i].split(" ");
            if (hash2.get(temp[0]).contains(temp[1])) continue; // 이미 신고한 유저면 넘어가기 (한사람이 다른 한사람을 여러번 중복해서 신고할 수 없음)
            
            hash1.put(temp[1], hash1.get(temp[1]) + 1);
            hash2.get(temp[0]).add(temp[1]);
        }
        
        int count;
        int[] ans = new int[id_list.length];
        for (int i = 0; i < id_list.length; i++){
            HashSet<String> tempL = hash2.get(id_list[i]);
            count = 0;
            Iterator iter = tempL.iterator();
            while(iter.hasNext()) {
                if (hash1.get(iter.next()) >= k) count++;
            }
            ans[i] = count;
        }
        
        return ans;
    }
}