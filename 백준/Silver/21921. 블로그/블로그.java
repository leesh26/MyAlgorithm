import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        int[] nums = new int[n]; // 방문자수를 저장할 배열
        int sum = 0; // 초기값 = 앞에서부터 X개 숫자의 합
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++){
            nums[i] = Integer.parseInt(st.nextToken());
            if (i < x) sum += nums[i];
        }

        int maxValue = sum;
        int count = 1;
        for (int i = x; i < n; i++){
            sum -= nums[i-x];
            sum += nums[i];
            if (maxValue < sum) {
                count = 1;
                maxValue = sum;
            }
            else if (maxValue == sum) count ++;
        }
        if (maxValue == 0) System.out.println("SAD");
        else System.out.print(maxValue + "\n" + count);
    }
}
