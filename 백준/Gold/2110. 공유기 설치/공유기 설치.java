import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] house;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        house = new int[n];
        for (int i = 0; i < n; i++){
            house[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(house);

        // 이분 탐색
        int min = 0, max = house[n-1] - house[0] + 1;
        int mid, preLoc, count;
        while (min < max) {
            mid = (min + max) / 2;
            preLoc = house[0];
            count = 1;
            for (int i = 1; i < house.length; i++) {
                if (house[i] - preLoc >= mid) {
                    count ++;
                    preLoc = house[i];
                }
            }

            if (count < m) max = mid;
            else min = mid + 1;
        }

        System.out.println(min - 1);

    }
}
