import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static Set<String> keywords;
    static Set<String> visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        keywords = new HashSet<>();
        visited = new HashSet<>();
        for (int i = 0; i < n; i++) {
            keywords.add(br.readLine());
        }

        for (int i = 0; i < m; i++) {
            String[] split = br.readLine().split(",");
            for (String s : split) {
                // 있는 것 체크
                if (keywords.contains(s) && !visited.contains(s)) visited.add(s);
            }

            System.out.println(n - visited.size());
        }

    }
}