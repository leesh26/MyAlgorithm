import java.lang.Math;

class Solution { 
    public String change(String s){
        s = s.replaceAll("C#", "S");
        s = s.replaceAll("D#", "T");
        s = s.replaceAll("F#", "U");
        s = s.replaceAll("G#", "V");
        s = s.replaceAll("A#", "W");
        return s;
    }
    
    public String solution(String m, String[] musicinfos) {
        String answer = "";
        int maxValue = -1;
        
        for (int i = 0; i < musicinfos.length; i++){
            String[] strList = musicinfos[i].split(",");
            int min = Integer.parseInt(strList[1].split(":")[0])
                - Integer.parseInt(strList[0].split(":")[0]);
            int sec = Integer.parseInt(strList[1].split(":")[1])
                - Integer.parseInt(strList[0].split(":")[1]);
            
            // 재생 시간 계산 (초단위)
            int musicLen = min * 60 + sec;
            
            // 악보만들기 
            String music = "";
            int index = 0;
            int len = 0;
            
            String pattern = change(strList[3]);
            m = change(m);
            
            while (len < musicLen){
                index %= pattern.length();
                char now = pattern.charAt(index);
                
                // 현재 위치가 #이면 인덱스만 이동
                index ++;
                len ++;
                music += now;
            }
            
            if (music.contains(m) && musicLen > maxValue) {
                answer = strList[2];
                maxValue = musicLen;
            }
        }
        
        return maxValue != -1 ? answer : "(None)";
    }
}