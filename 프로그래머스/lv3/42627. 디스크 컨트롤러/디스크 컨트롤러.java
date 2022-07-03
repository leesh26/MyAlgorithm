import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;

        // 힙생성 (실행시간으로 정렬되도록 오버라이드)
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[1], o2[1]);
            }
        });
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);

        //실행시간이 짧은 것부터 수행해야 평균 시간이 짧음
        int time = 0, n = 0, i = 0;
        while (n != jobs.length){
            // 실행 시간이 되면 힙에 삽입
            while (i < jobs.length && jobs[i][0] <= time){
                minHeap.add(jobs[i++]);
            }

            if (minHeap.isEmpty()){
                time = jobs[i][0]; //실행 시작 시간
            }
            else{
                int[] q = minHeap.poll();
                answer += q[1] + time - q[0]; //실행시간 + 대기시간(실행 시작한 시간 - 대기를 시작한 시간)
                time += q[1]; //실행을 마친 시간으로 변경
                n++; //실행한 프로그램 개수 세기
            }
        }
        return answer / jobs.length;
    }
}