import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[][] max = new int[n][3];
        int[][] min = new int[n][3];
        for (int i = 0; i < 3; i++) {
            int temp = Integer.parseInt(st.nextToken());
            max[0][i] = temp;
            min[0][i] = temp;
        }


        for (int i = 1; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int mid = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            max[i][0] = Math.max(max[i-1][0], max[i-1][1]) + left;
            max[i][1] = Math.max(Math.max(max[i-1][0], max[i-1][1]), max[i-1][2]) + mid;
            max[i][2] = Math.max(max[i-1][2], max[i-1][1]) + right;

            min[i][0] = Math.min(min[i-1][0], min[i-1][1]) + left;
            min[i][1] = Math.min(Math.min(min[i-1][0], min[i-1][1]), min[i-1][2]) + mid;
            min[i][2] = Math.min(min[i-1][2], min[i-1][1]) + right;
        }

        int ansMax = Arrays.stream(max[n-1]).max().getAsInt();
        int ansMin = Arrays.stream(min[n-1]).min().getAsInt();
        System.out.println(ansMax + " " + ansMin);
    }
}
