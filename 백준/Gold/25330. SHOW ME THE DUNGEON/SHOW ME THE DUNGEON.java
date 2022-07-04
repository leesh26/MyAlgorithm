import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int maxValue;
    static int[] attack;
    static int[] people;
    static int n, m;

    public static void dfs(boolean[] visited, int ans, int hurt, int hp){
        if (hp < 0) return;
        maxValue = Math.max(maxValue, ans);

        for (int i = 0; i < n; i++){
            if (!visited[i] && hp >= attack[i]){
                visited[i] = true;
                dfs(visited, ans + people[i], hurt + attack[i], hp - hurt - attack[i]);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 마을의 수
        m = Integer.parseInt(st.nextToken()); //초기 체력

        st = new StringTokenizer(br.readLine());
        attack = new int[n];
        for (int i = 0; i < n; i++){
            attack[i] = Integer.parseInt(st.nextToken());
        }

        people = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++){
            people[i] = Integer.parseInt(st.nextToken());
        }

        boolean[] visited = new boolean[n];
        dfs(visited, 0, 0, m);

        System.out.println(maxValue);
    }
}
