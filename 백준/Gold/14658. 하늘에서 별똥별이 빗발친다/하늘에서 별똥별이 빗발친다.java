import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int m, n, l, k;
    static int maxValue = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); //가로
        m = Integer.parseInt(st.nextToken()); //세로
        l = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        // 별똥별 좌표를 저장할 배열
        int[][] stars = new int[k][2];
        
        // 별똥별 저장
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            stars[i][0] = x;
            stars[i][1] = y;
        }

        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                int stanX = stars[i][0];
                int stanY = stars[j][1]; // 기준이 되는 지점

                // 위의 지점 안에 속하는 별 세기
                int count = 0;
                for (int o = 0; o < k; o++) {
                    if (stanX <= stars[o][0] && stanX + l >= stars[o][0]
                    && stanY <= stars[o][1] && stanY + l >= stars[o][1]) count += 1;
                }
                maxValue = Math.max(maxValue, count);
            }
        }

        System.out.println(k - maxValue);
    }
}
