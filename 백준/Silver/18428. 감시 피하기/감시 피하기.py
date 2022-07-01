n = int(input())
way, teachers = [], []
for i in range(n):
    way.append(list(input().split()))
    for j in range(n):
        if way[i][j] == 'T':
            teachers.append((i, j))

mx = [0, 0, -1, 1]
my = [-1, 1, 0, 0]

def check(direction, t, way):
    global check_yes
    if check_yes == False:
        return
    
    nx = t[0] + mx[direction]
    ny = t[1] + my[direction]
    if 0 <= nx < n and 0 <= ny < n :
        if way[nx][ny] == 'S':
            check_yes = False
            
        elif way[nx][ny] == 'X':
            check(direction, (nx, ny), way)
        
    
def solution(obs):
    global possible
    global check_yes
    
    if obs == 3:
        count = 0
        for t in teachers:
            for k in range(4):
                check_yes = True
                check(k, t, way)
                count = count + 1 if check_yes == False else count

        if count == 0:
            possible = True
        return
                
    
    for i in range(n):
        for j in range(n):
            if way[i][j] == 'X':
                obs += 1
                way[i][j] = 'O'
                solution(obs)
                obs -= 1
                way[i][j] = 'X'
    
possible = False
solution(0)
if possible :
    print("YES")
else:
    print("NO")