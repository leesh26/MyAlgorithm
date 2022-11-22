import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String words = br.readLine();

        // O(n) -> 1000
        int countA = 0;
        int countB = 0;
        for (int i = 0; i < words.length(); i++) {
            if (words.charAt(i) == 'a') countA++;
            else countB++;
        }

        // O(n) -> 1000
        String target = "";
        for (int i = 0; i < countA; i++) {
            target += 'a';
        }
        for (int i = 0; i < countB; i++) {
            target += 'b';
        }

        int len = target.length();
        int minValue = Integer.MAX_VALUE;
        // O(n) -> 1000000
        for (int i = 0; i < len; i++) {
            int count = 0;
            for (int j = 0; j < len; j++) {
                if (target.charAt((i + j) % len) != words.charAt(j)) count += 1;
            }

            if (count % 2 != 0) continue;
            minValue = Math.min(count/2, minValue);
        }
        System.out.println(minValue);
    }
}