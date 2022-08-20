n = int(input())
nums = list(map(int, input().split()))
operators = list(map(int, input().split()))

result = nums[0]
minValue, maxValue = 1e9, -1e9

def dfs(num, result):
    global minValue, maxValue

    if num == n:
        minValue = min(minValue, result)
        maxValue = max(maxValue, result)
        return

    if operators[0]:
        operators[0] -= 1
        dfs(num + 1, result + nums[num])
        operators[0] += 1

    if operators[1]:
        operators[1] -= 1
        dfs(num + 1, result - nums[num])
        operators[1] += 1

    if operators[2]:
        operators[2] -= 1
        dfs(num + 1, result * nums[num])
        operators[2] += 1

    if operators[3]:
        operators[3] -= 1
        if result < 0:
            dfs(num + 1, -1 * ((-1 * result) // nums[num]))
        else:
            dfs(num + 1, result // nums[num])
        operators[3] += 1


dfs(1, result)
print(maxValue)
print(minValue)