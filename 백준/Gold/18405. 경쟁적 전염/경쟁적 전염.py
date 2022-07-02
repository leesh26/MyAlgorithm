from collections import deque

n, k = map(int, input().split())
graph = []
for _ in range(n):
    graph.append(list(map(int, input().split())))
s, x, y = map(int, input().split())

virus = []
for i in range(n):
    for j in range(n):
        if graph[i][j] != 0:
            virus.append([0, graph[i][j], i, j])
virus.sort()
virus = deque(virus)
            
time = 0
mx = [0, 0, -1, 1]
my = [-1, 1, 0, 0]

while virus:
    now, virus_num, a, b = virus.popleft()
    
    if now == s:
        break

    for i in range(4):
        nx = a + mx[i]
        ny = b + my[i]

        if 0<=nx<n and 0<=ny<n:
            if graph[nx][ny] == 0:
                virus.append([now+1, virus_num, nx, ny])
                graph[nx][ny] = virus_num
                    
print(graph[x-1][y-1])