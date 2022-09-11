from collections import deque

n, k = map(int, input().split())

belt = deque(list(map(int, input().split())))
robot = deque([0] * n)

count = 1
while True:
    # 한칸 회전
    belt.rotate(1)
    robot.rotate(1)
    robot[-1] = 0

    # 모든 로봇 이동
    if sum(robot):
        for i in range(n-2, 0, -1):
            # 현재 칸에 로봇이 있는데 옆칸이 비어있고 내구도가 1이상이면
            if belt[i+1] > 0 and robot[i+1] == 0 and robot[i] == 1:
                belt[i+1] -= 1
                robot[i+1] = 1
                robot[i] = 0
            # 맨 끝에 있는 로봇 내리기
            robot[-1] = 0

    # 올리는 위치의 내구도가 0보다 크고 로봇이 없으면 새 로봇 올리기
    if belt[0] > 0 and robot[0] == 0:
        robot[0] = 1
        belt[0] -= 1


    if belt.count(0) >= k:
        print(count)
        break
    else:
        count += 1