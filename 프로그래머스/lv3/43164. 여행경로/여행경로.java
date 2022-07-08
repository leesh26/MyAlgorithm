import java.util.ArrayList;
import java.util.Collections;

class Solution {
    ArrayList<String> allRoute; 
	boolean[] visited;
	
	public void dfs(String start, String[][] tickets, boolean[] visited, String route, int count) {
		// 모든 티켓을 사용한 경우
		if (count == tickets.length) {
			allRoute.add(route);
			return;
		}
		
		for (int i=0; i<tickets.length; i++) {
        	if (!visited[i] && tickets[i][0].equals(start)) {
	        	visited[i] = true;
	        	dfs(tickets[i][1], tickets, visited, route + " " + tickets[i][1], count + 1);
	        	visited[i] = false;
	        	}
        }
	}
	
	public String[] solution(String[][] tickets) {
		String[] answer = {};
        visited = new boolean[tickets.length]; // 티켓 사용 여부
        allRoute = new ArrayList<String>();
        int count = 0;
        
        // 시작지점은 무조건 인천
        dfs("ICN", tickets, visited, "ICN", count);
        Collections.sort(allRoute);
        answer = allRoute.get(0).split(" ");
        
        return answer;
    }
}