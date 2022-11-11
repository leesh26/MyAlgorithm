n = int(input())
classroom = [[0] * n for _ in range(n)]

num_list, like_list = [], []
for _ in range(n**2):
    temp = list(map(int, input().split()))
    num_list.append(temp[0])
    like_list.append(temp[1:])

position = [(0, 0) for _ in range(n ** 2)]

mx = [-1, 0, 1, 0]
my = [0, -1, 0, 1]

# n ** 2 번 학생까지 반복
score = 0
for aa in range(n**2):
    num, like = num_list[aa], like_list[aa]
    max_like, max_blank = -1, -1

    for i in range(n):
        for j in range(n):
            if classroom[i][j] != 0:
                continue

            # 사방을 확인해 좋아하는 학생의 명수 세기
            count_blank, count_like = 0, 0
            for k in range(4):
                nx = i + mx[k]
                ny = j + my[k]

                if 0 <= nx < n and 0 <= ny < n:
                    if classroom[nx][ny] == 0:
                        count_blank += 1

                    if classroom[nx][ny] in like:
                        count_like += 1

            # 좋아하는 사람이 인접한 개수가 같은데 빈칸이 더 많으면 갱신
            if count_like > max_like or count_like == max_like and count_blank > max_blank:
                max_like = count_like
                max_blank = count_blank
                position[aa] = (i, j)

    classroom[position[aa][0]][position[aa][1]] = num

# 점수 계산
for i in range(n**2):
    s = 0
    for k in range(4):
        nx = position[i][0] + mx[k]
        ny = position[i][1] + my[k]

        if 0 <= nx < n and 0 <= ny < n:
            if classroom[nx][ny] in like_list[i]:
                s += 1

    if s == 4: score += 1000
    if s == 3: score += 100
    if s == 2: score += 10
    if s == 1: score += 1

print(score)