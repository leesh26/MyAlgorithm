class Solution {
	public int count = 0;
	
	public void dfs(int res, int n, int[] numbers, int target) {
		if (n == numbers.length) {
			if (res == target) {
				count += 1;
			}
			return;
		}
		
		dfs(res-numbers[n], n+1, numbers, target);
		dfs(res+numbers[n], n+1, numbers, target);
	}
	
	public int solution(int[] numbers, int target) {
        dfs(0, 0, numbers, target);
        
        return count;
    }
	
}