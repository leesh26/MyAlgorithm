import java.util.Stack;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        
        int height = board.length;
        Stack<Integer> stack = new Stack<>();
        for (int line : moves){
            int now = 0;
            line--;
            
            // 인형 집기 > 해당 칸이 0이 아니거나 바닥에 도달할 때까지 좌표를 1씩 더함
            while (now != height && board[now][line] == 0) now++;
            if (now == height) continue; // 인형이 없는 경우 > pass 
            
            // 인형이 있는 경우 > 인형을 스택에 넣음
            if (!stack.isEmpty()){
                // 이전에 쌓여있던 인형이 같은 인형이면 삭제
                if (stack.peek() == board[now][line]) {
                    stack.pop();
                    answer += 2;
                }
                else stack.push(board[now][line]);
            }
            else stack.push(board[now][line]);
            
            board[now][line] = 0;
        }
        
        return answer;
    }
}