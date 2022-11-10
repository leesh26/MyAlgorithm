import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int subin = Integer.parseInt(st.nextToken());
        int younger = Integer.parseInt(st.nextToken());
        int limit = 100000;

        int[] time = new int[limit * 2 + 1];
        Arrays.fill(time, Integer.MAX_VALUE);

        Queue<Point> q = new LinkedList<>();
        q.add(new Point(subin, 0));
        time[subin] = 0;

        int minValue = Integer.MAX_VALUE;
        while (!q.isEmpty()){
            Point p = q.poll();
            if (p.position == younger) {
                minValue = Math.min(minValue, p.time);
                continue;
            }

            if (2 * p.position < 2 * limit && time[2 * p.position] > p.time) {
                q.add(new Point(2 * p.position, p.time));
                time[2 * p.position] = p.time;
            }

            if (p.position - 1 >= 0 && time[p.position - 1] > p.time + 1) {
                q.add(new Point(p.position - 1, p.time + 1));
                time[p.position - 1] = p.time + 1;
            }

            if (p.position + 1 < 2 * limit && time[p.position + 1] > p.time + 1) {
                q.add(new Point(p.position + 1, p.time + 1));
                time[p.position + 1] = p.time + 1;
            }
        }
        System.out.println(time[younger]);
    }

    static class Point{
        int position;
        int time;

        Point(int position, int time){
            this.position = position;
            this.time = time;
        }
    }
}
