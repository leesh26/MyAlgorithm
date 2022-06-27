import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int getParent(int[] parent, int a){
        if (parent[a] == a) return a;
        return parent[a] = getParent(parent, parent[a]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] parent = new int[n + 1];

        // 자기 자신을 부모로 초기화
        for (int i = 0; i <= n; i++){
            parent[i] = i;
        }

        for (int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int o = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (o == 0){
                // 합집합
                int p_a = getParent(parent, a); // a = 3, p_a = 1
                int p_b = getParent(parent, b);// b = 7, p_b = 6

                if (p_a == p_b) continue;
                if (p_a < p_b) parent[p_b] = p_a; // parent[7] = 1 parent[p_b] = p_a
                else parent[p_a] = p_b;
            }

            else{
                // 같은 집합에 속하는지 여부 확인
                if (getParent(parent, a) == getParent(parent, b)){
                    System.out.println("YES");
                }
                else System.out.println("NO");
            }
        }
    }
}
