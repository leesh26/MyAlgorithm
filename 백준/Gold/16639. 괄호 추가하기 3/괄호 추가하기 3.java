import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    // 네가지 경우 -> 최소 (연산자) 최소 / 최소 (연산자) 최대 / 최대 (연산자) 최소 / 최대 (연산자) 최대

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String math = br.readLine();

        int[][] max = new int[n][n];
        int[][] min = new int[n][n];

        // 초기값 세팅
        for (int i = 0; i < n; i++) {
            Arrays.fill(max[i], Integer.MIN_VALUE);
            Arrays.fill(min[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i < n; i++) {
            if (Character.isDigit(math.charAt(i))) {
                max[i][i] = min[i][i] = math.charAt(i) - '0';
            }
        }

        for (int i = 2; i < n; i+=2) {
            for (int j = 0; j < n-i; j+=2) {
                for (int k = 2; k <= i; k+=2) {
                    int[] temp = new int[4];
                    temp[0] = cal(min[j][j + k - 2], min[j + k][j + i], math.charAt(j + k - 1));
                    temp[1] = cal(min[j][j + k - 2], max[j + k][j + i], math.charAt(j + k - 1));
                    temp[2] = cal(max[j][j + k - 2], min[j + k][j + i], math.charAt(j + k - 1));
                    temp[3] = cal(max[j][j + k - 2], max[j + k][j + i], math.charAt(j + k - 1));

                    Arrays.sort(temp);
                    max[j][i + j] = Math.max(max[j][i + j], temp[3]);
                    min[j][i + j] = Math.min(min[j][i + j], temp[0]);
                }
            }
        }

        System.out.println(max[0][n-1]);
    }

    private static int cal(int a, int b, char op) {
        if (op == '+') return a + b;
        else if (op == '-') return a - b;
        else return a * b;
    }

}
