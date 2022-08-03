import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] origin;

    public static int[][] power(long b, int[][] arr){
        if (b == 1L) return arr;

        int[][] ans;
        ans = power(b / 2, arr);
        ans = multiply(ans, ans);

        // 홀수인 경우 한번 더 곱해줌
        if (b % 2 == 1) {
            ans = multiply(ans, origin);
        }
        return ans;
    }

    public static int[][] multiply(int[][] nums1, int[][] nums2){
        int[][] ans = new int[n][n];
        for (int x = 0; x < n; x++){
            for (int y = 0; y < n; y++){
                for (int z = 0; z < n; z++){
                    ans[x][y] += (nums1[x][z] * nums2[z][y]);
                    ans[x][y] %= 1000;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        origin = new int[n][n];
        for (int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++){
                origin[i][j] = Integer.parseInt(st.nextToken()) % 1000;
            }
        }

        int[][] ans;
        ans = power(b, origin);;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i ++){
            if (i != 0) sb.append("\n");
            for (int j = 0; j < n; j++){
                sb.append(ans[i][j] + " ");
            }
        }
        System.out.println(sb.toString());
    }
}
