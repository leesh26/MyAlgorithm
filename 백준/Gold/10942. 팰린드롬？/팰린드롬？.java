import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][] dp;
    static int[] nums;

    public static int dfs(int start, int end){
        if (start == end || start > end) {
            return 1;
        }

        // -1 이면 아직 탐색이 안된 지점
        if (dp[start][end] != -1) return dp[start][end];

        // 추가하는 지점이 같다면 안쪽으로 이동
        if (nums[start] == nums[end]) dp[start][end] = dfs(start + 1, end - 1);
        else dp[start][end] = 0;

        return dp[start][end];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        nums = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            sb.append(dfs(a, b) + "\n");
        }

        System.out.println(sb.toString());;
    }
}