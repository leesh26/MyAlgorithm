num = int(input())
nums = []
for _ in range(num):
    nums.append(int(input()))

nums.sort()
for i in range(num):
    print(nums[i])