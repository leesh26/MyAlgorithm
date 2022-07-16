import java.lang.Math;

class Solution {
    String[][] keypad = {{"1", "2", "3"}, {"4", "5", "6"}, {"7", "8", "9"}, {"*", "0", "#"}};
    
    public int[] findNum(int num){
        for (int i = 0; i < keypad.length; i++){
            for (int j = 0; j < keypad[0].length; j++){
                if (keypad[i][j].equals(String.valueOf(num))){
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
    
    
    public String solution(int[] numbers, String hand) {
        String answer = "";
        int[] nowL = {3, 0}, nowR = {3, 2};
        int distanceL, distanceR;
        
        for (int n : numbers){
            if (n == 1 || n == 4 || n == 7){
                answer += "L";
                nowL = findNum(n);
            }
            else if (n == 3 || n == 6 || n == 9){
                answer += "R";
                nowR = findNum(n);
            }
            else{
                int[] now = findNum(n);
                distanceL = Math.abs(nowL[0] - now[0]) + Math.abs(nowL[1] - now[1]);
                distanceR = Math.abs(nowR[0] - now[0]) + Math.abs(nowR[1] - now[1]);
                
                if (distanceL > distanceR){
                    nowR = new int[]{now[0], now[1]};
                    answer += "R";
                }
                else if (distanceL < distanceR){
                    nowL = new int[]{now[0], now[1]};
                    answer += "L";
                }
                else {
                    if (hand.equals("right")) {
                        nowR = new int[]{now[0], now[1]};
                        answer += "R";
                    }
                    else{
                        nowL = new int[]{now[0], now[1]};
                        answer += "L";
                    }
                }
            }
        }
        return answer;
    }
}