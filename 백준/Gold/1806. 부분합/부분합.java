import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        int[] sum = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            int temp = Integer.parseInt(st.nextToken());
            sum[i] = sum[i - 1] + temp;
        }

        if (sum[n] < s) System.out.println(0);
        else {
            int left = 0;
            int right = n;

            while (left < right){
                boolean check = false;
                int len = (left + right) / 2;
                // 해당 길이로 가능한지 확인
                for (int i = 0; i <= n - len; i++) {
                    if (sum[i + len] - sum[i] >= s){
                        check = true;
                        break;
                    }
                }

                if (check) right = len;
                else left = len + 1;
            }
            System.out.println(left);
        }
    }
}