import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] tree = new int[n];

        st = new StringTokenizer(br.readLine());
        // 최대값과 최소값
        int min = 0, max = 0;
        for (int i = 0; i < n; i++){
            tree[i] = Integer.parseInt(st.nextToken());
            if (tree[i] > max) max = tree[i];
        }

        int height = 0;
        long sum = 0;
        while (min < max){
            height = (min + max) / 2;
            sum = 0;
            for (int h : tree){
                if (h > height) sum += (h - height);
            }

            if (sum >= m) min = height + 1; // height는 이미 m보다 큼을 확인했기 때문에 1 높여줌
            else max = height;
        }

        System.out.println(min - 1);

    }
}
