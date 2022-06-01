# 두개 뽑아서 더하기
# https://programmers.co.kr/learn/courses/30/lessons/68644#

sum_ = []

# 주어진 배열
nums = [2, 1, 3, 4, 1]

i=0
for a in nums:
    j=0
    for b in nums:
        if i!=j:
            sum_.append(a+b)
        j+=1
    i+=1

sum_=sorted(list(set(sum_)))
print(sum_)
