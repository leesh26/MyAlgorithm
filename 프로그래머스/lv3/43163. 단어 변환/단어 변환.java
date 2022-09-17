class Solution {
    String target;
    String[] words;
    int minValue = 10;
    
    public int solution(String begin, String target, String[] words) {
        boolean[] visited = new boolean[words.length];
        this.target = target;
        this.words = words;
        
        int answer = dfs(0, begin, visited);
        if (answer == 10) return 0; //변환할 수 없는 경우
        return answer;
    }
    
    //깊이우선탐색으로 모든 경우의 수 탐색
    public int dfs(int count, String now, boolean[] visited){
        // target과 같아졌다면 count로 몇 단계를 거쳤는지 체크
        if (now.equals(target)){
            minValue = Math.min(minValue, count);
            return minValue;
        }
        
        for (int i = 0; i < words.length; i++){
            // 철자 한개만 바뀐다면 방문처리 & 현재 단어(now)로 변경
            if (!visited[i] && isPossible(now, words[i])){
                visited[i] = true;
                dfs(count + 1, words[i], visited);
                visited[i] = false;
            }
        }
        
        return minValue;
    }
    
    public boolean isPossible(String s1, String s2){
        int count = 0;
        for (int i = 0; i < s1.length(); i++){
            if (s1.charAt(i) != s2.charAt(i)) count += 1;
        }
        if (count >= 2) return false; //두개이상 다른 값이 있다면 바꿀 수 없음
        return true;
    }
    
}