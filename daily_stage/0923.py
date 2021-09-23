
#1 더하기 사이클
# https://www.acmicpc.net/problem/1110

input = str(input())

input = input + '0' if int(input) < 10 else input

num = 100 # 우연히 맞지 않도록
cycle = 0
while num != input:
    num = input if num == 100 else num
    first = num[1]
    last = str(int(num[0])+int(num[1]))[-1]
    # 이 문제는 형변환해서 하지 않고
    # 10으로 나눈 몫이나 나머지로도 풀 수 있다 --> 더 간단하고 메모리나 시간이 적게 듦
    num = first+last
    cycle+=1
print(cycle)

#2 함수
# https://www.acmicpc.net/status?from_problem=1&problem_id=15596

def solve(a):
    ans = sum(a)
    # 리스트는 합 기능을 제공한다, 2차원 이상은 for문 불가피
    # for문으로 sum을 구하게 되면 2 배이상의 시간이 소요됨
    # 하지만 메모리는 동일
    return ans
# 이 문제는 기존 함수를 복사 하는 것 만으로도 해결 가능
# solve = sum..

#3 단어 공부
# https://www.acmicpc.net/problem/1157
word = input().upper()
word_set = list(set(word))
word_list = []

for w in word_set:
    count = word.count(w) # 리스트는 count 함수를 이용해 알파벳 개수 셀 수 있음
    word_list.append(count)

if word_list.count(max(word_list)) >= 2:
    print("?")
else:
    print(word_set[word_list.index(max(word_list))])

#4 단어의 개수
#https://www.acmicpc.net/problem/1152

word = input().strip().split(" ") # 쉬운 문젠데 공백이 함정 ㅎㅎ..
if '' in word:
    print(0)
else:
    print(len(word))

#5 크로아티아 알파벳
# https://www.acmicpc.net/problem/2941
