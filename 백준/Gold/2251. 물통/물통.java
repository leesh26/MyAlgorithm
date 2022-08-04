import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    static List<Integer> ans = new ArrayList<>();
    static boolean[][] visited;
    static int[] bottles;

    public static void func(int a, int b, int c){
        if (visited[a][b]) return;

        if (a == 0) {
            ans.add(c);
        }
        visited[a][b] = true;

        // a -> b
        if (bottles[1] >= a + b){
            func(0, a + b, c);
        }
        else func(a - (bottles[1] - b), bottles[1], c);

        // b -> a
        if (bottles[0] >= a + b){
            func(a + b, 0, c);
        }
        else func(bottles[0], b - (bottles[0] - a), c);

        // c -> a
        if (bottles[0] >= a + c){
            func(a + c, b, 0);
        }
        else func(bottles[0], b,c - (bottles[0] - a));

        // c -> b
        if (bottles[1] >= a + b){
            func(0, a + b, c);
        }
        else func(a - (bottles[1] - b), bottles[1], c);

        // c로 꽉차게 가는 경우는 처음 상태와 같음!
        func(0, b, a + c);
        func(a, 0, b + c);

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        bottles = new int[3];
        bottles[0] = sc.nextInt();
        bottles[1] = sc.nextInt();
        bottles[2] = sc.nextInt();

        visited = new boolean[201][201]; // a용량과 b용량 방문 처리를 위한 배열
        func(0, 0, bottles[2]);
        ans = ans.stream().distinct().sorted().collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();
        for (int n : ans){
            sb.append(n);
            sb.append(" ");
        }
        System.out.println(sb.toString());
    }
}
