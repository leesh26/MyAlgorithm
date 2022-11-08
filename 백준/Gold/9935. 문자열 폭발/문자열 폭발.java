import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String target = br.readLine();

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            stack.push(s.charAt(i));
            // 길이가 3이상이면 AWS가 있는지 탐색
            if (stack.size() >= target.length()){
                boolean check = true;
                for (int j = target.length() - 1; j > -1; j--) {
                    if (stack.get(stack.size() - 1 - j) != target.charAt(target.length() - 1 - j)){
                        check = false;
                        break;
                    }
                }

                // 있다면 pop
                if (check){
                    for (int k = 0; k < target.length(); k++) {
                        stack.pop();
                    }
                }
            }
        }

        // 스택에 남은 것들을 반환
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < stack.size(); i++) {
            sb.append(stack.get(i));
        }

        if (sb.length() == 0) System.out.println("FRULA");
        else System.out.println(sb.toString());
    }
}
