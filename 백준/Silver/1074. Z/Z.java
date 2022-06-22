import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int num = 0;
    static int n, r, c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        int size = (int) Math.pow(2, n);
        dfs(size, r, c);
        System.out.println(num);
    }


    static void dfs(int size, int x, int y){
        // 좌표 위치에 따라 재귀
        if (size == 1) return;

        // 1사분면
        if (x < size/2 && y < size/2) {
            dfs(size/2, x, y);
        }

        // 2사분면
        else if (x < size/2 && y >= size/2){
            num += size * size / 4;
            dfs(size/2, x, y - size/2);
        }

        // 3사분면
        else if (x >= size/2 && y < size/2){
            num += size * size / 2;
            dfs(size/2, x- size/2, y);
        }

        else{
            num += size * size / 4 * 3;
            dfs(size/2, x - size/2, y - size/2);
        }
    }

    // 처음 방식 - 완전탐색 : 시간초과ㅠㅠ
//    static void dfs(int size, int start_x, int start_y){
//        if (size == 2){
//            for (int i = 0; i < 2; i++){
//                for (int j = 0; j < 2; j++){
//                    if (start_x + i == r && start_y + j == c){
//                        System.out.println(num);
//                    }
//                    num ++;
//                }
//            }
//            return;
//        }
//
//        for (int i = 0; i < 2; i ++){
//            for (int j = 0; j < 2; j++){
//                dfs(size/2, start_x + size/2*i, start_y + size/2*j);
//            }
//        }
//
//    }
}
