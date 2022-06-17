def cross(arr):
    temp = [[0] * n for _ in range(n)]
    for i in range(n):
        for j in range(n):
            temp[i][j] = arr[j][i]
    return temp

def check(arr):
    sw = [False for i in range(n)]
    for i in range(n-1):
        if arr[i] == arr[i+1]:
            continue
        if abs(arr[i]-arr[i+1]) > 1:
            return False
        if arr[i] > arr[i+1]:
            temp = arr[i+1]
            for j in range(i+1, i+1+l):
                # j는 범위 내에 있어야 함, 아닌 경우 false 반환
                if 0<=j<n:
                    # 이미 있거나 같은 값이 아니면 False 반환
                    if temp != arr[j]: return False
                    if sw[j] == True: return False
                    sw[j] = True
                else:
                    return False
        else:
            temp = arr[i]
            for j in range(i, i - l, -1):
                if 0 <= j < n:
                    if temp != arr[j] : return False
                    if sw[j] == True : return False
                    sw[j] = True

                else:
                    return False
    return True

def solution(fullArr):
    global ans
    for li in fullArr:
        if check(li):
            ans += 1


n, l = map(int, input().split())
village = [list(map(int, input().split())) for _ in range(n)]
village_2 = cross(village)
ans = 0


solution(village)
solution(village_2)
print(ans)