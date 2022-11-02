import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	
	static int h, w;
	static char[][] map;
	static boolean[][] visit;
	static int[][] distance;
	static int dirty;
	static ArrayList<Node> list;
	
	static boolean[] check;
	static int result;
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        while (true) {
        	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        	w = Integer.parseInt(st.nextToken());
        	h = Integer.parseInt(st.nextToken());
        	
        	if (w == 0 && h == 0) break;
        	
        	map = new char[h][w];
        	list = new ArrayList<>();
        	
        	for (int i = 0; i < h; i++) {
        		String input = br.readLine();
        		for (int j = 0; j < w; j++) {
        			map[i][j] = input.charAt(j);
        			
        			if (map[i][j] == 'o') {
        				list.add(0, new Node(i, j, 0));
        			} else if (map[i][j] == '*') {
        				list.add(new Node(i, j, 0));
        			}
        		}
        	}
        	distance = new int[list.size()][list.size()];
        	dirty = 0;
        	for (int i = 0; i < list.size(); i++) {
        		bfs(list.get(i), i);
        	}
        	
        	if (dirty == list.size() - 1) {
        		result = Integer.MAX_VALUE;
            	check = new boolean[list.size()];
            	check[0] = true;
            	find(0, 1, 0);
            	sb.append(result + "\n");
        	} else {
        		sb.append(-1 + "\n");
        	}
        	
        }
        System.out.println(sb);
        
    }
    static void bfs(Node node, int start) {
    	visit = new boolean[h][w];
    	Queue<Node> q = new LinkedList<>();
    	q.offer(node);
    	visit[node.x][node.y] = true;
    	
    	while(!q.isEmpty()) {
    		Node next = q.poll();
    		
    		if (map[next.x][next.y] == '*') {
    			if (start == 0) dirty++;
    			for (int i = 1; i < list.size(); i++) {
    				if(next.x == list.get(i).x && next.y == list.get(i).y) {
    					distance[start][i] = next.move;
    				}
        		}
    		}
    		
    		for (int i = 0; i < 4; i++) {
    			int newX = next.x + dx[i];
    			int newY = next.y + dy[i];
    			
    			if (newX < 0 || newY < 0 || newX >= h || newY >= w) continue;
    			if (map[newX][newY] == 'x' || visit[newX][newY]) continue;
    			
    			q.offer(new Node(newX, newY, next.move + 1));
    			visit[newX][newY] = true;
    		}
    	}
    }
    
    static void find(int start, int cnt, int dist) {
    	if (cnt == list.size()) {
    		result = Math.min(result, dist);
    	}
    	
    	for (int i = 1; i < list.size(); i++) {
    		if (!check[i]) {
    			check[i] = true;
    			find(i, cnt + 1, dist + distance[start][i]);
        		check[i] = false;
    		}
    	}
    }
}

class Node {
	int x;
	int y;
	int move;
	
	Node (int x, int y, int move) {
		this.x = x;
		this.y = y;
		this.move = move;
	}
}