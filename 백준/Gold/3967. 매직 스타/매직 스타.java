import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Objects;

public class Main {
    static int[][] nums = {{2, 4, 12, 8}, {1, 4, 10, 11}, {11, 5, 3, 7}, {7, 6, 12, 1},
            {2, 10, 5, 9}, {9, 3, 6, 8}, {8, 12, 4, 2}};
    static boolean check = false;
    static String[][] board = {{".", ".", ".", ".", "1", ".", ".", ".", "."}, {".", "2", ".", "4", ".", "12", ".", "8", "."},
            {".", ".", "10", ".", ".", ".", "6", ".", "."}, {".", "11", ".", "5", ".", "3", ".", "7", "."},
            {".", ".", ".", ".", "9", ".", ".", ".", "."}};
    static int[] ans = new int[12];

    // 매직스타인지 확인
    public static boolean isMagic(int[] alpha){
        for (int i = 0; i < 6; i++){
            int[] num = nums[i];
            int sum = 0;
            for (int j = 0; j < 4; j ++){
                sum += (alpha[nums[i][j] - 1] + 1);
            }

            if (sum != 26) return false;
        }

        return true;
    }

    public static void dfs(int idx, boolean[] visited, int[] alpha){
        if (check) return;

        // 모든 줄이 통과하면 체크
        if (idx == 12){
            if (isMagic(alpha)) {
                System.arraycopy(alpha, 0, ans, 0, alpha.length);
                check = true;
            }
            return;
        }

        // 값을 채우기
        if (alpha[idx] == -1){
            for (int i = 0; i < 12; i++){
                if (!visited[i]){
                    visited[i] = true;
                    alpha[idx] = i;
                    dfs(idx + 1, visited, alpha);
                    alpha[idx] = -1;
                    visited[i] = false;
                }
            }
        }
        else dfs(idx + 1, visited, alpha);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] alpha = new int[12];
        boolean[] visited = new boolean[12];

        // 이미 나온 숫자 체크하기
        Arrays.fill(alpha, -1);
        for (int i = 0; i < 5; i++){
            String temp = br.readLine();
            for (int j = 0; j < 9; j++){
                if (temp.charAt(j) != 'x' && temp.charAt(j) != '.') {
                    alpha[Integer.parseInt(board[i][j]) - 1] = temp.charAt(j) - 'A';
                    visited[temp.charAt(j) - 'A'] = true;
                }
            }
        }

        dfs(0, visited, alpha);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 9; j++){
                if (!Objects.equals(board[i][j], ".")) {
                    sb.append((char) (ans[Integer.parseInt(board[i][j]) - 1] + 'A'));
                }else sb.append(".");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
