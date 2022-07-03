class Solution {
    public void dfs(int index, int[] visited, int[][] computers) {
		visited[index] = 1;
		
		for (int i=0; i<computers.length; i++) {
			if (visited[i] == 0 && computers[index][i] == 1 && index != i) {
				dfs(i, visited, computers);
			}
		}
	}
	
	public int solution(int n, int[][] computers) {
		int[] visited = new int[n];
		int answer = 0;
		
		for (int i=0; i<n; i++) {
			if (visited[i] == 0) {
				dfs(i, visited, computers);
				answer += 1;
			}
		}
		return answer;
	}
}