import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long num = Long.parseLong(br.readLine());
        long sum = 0;
        long i = 0;
        while (sum <= num){
            i++;
            sum += i;
        }
        System.out.println(i - 1);
    }
}
