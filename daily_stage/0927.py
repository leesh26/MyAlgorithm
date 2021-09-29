#5 크로아티아 알파벳
# https://www.acmicpc.net/problem/2941

word = input()
length = len(word)

croatia = ['c=', 'c-', 'dz=', 'd-', 'lj', 'nj', 's=', 'z=']
# 내가 푼 방법 --> 투 포인터 방법
left_p, right_p = 0, 1
count = 0
while length:
    temp_word = word[left_p:right_p+1]
    count += 1
    if temp_word == 'dz' and word[right_p+1]=='=':
        left_p += 3
        length -= 3
    else:
        if temp_word in croatia:
            left_p += 2
            length -= 2
        else :
            left_p += 1
            length -= 1
    right_p = left_p + 1
print(count)

# study --------------------------------------------------------

# 위의 방법은 런타임 오류 발생
# 기본적으로 제공되는 replace 함수를 이용해 간단히 해결할 수 있다.
for i in croatia:
    word = word.replace(i, '*')
    # 리스트에 있는 문자를 통째로 *로 바꿀 수 있음
print(len(word))

# --------------------------------------------------------------

# 6 손익분기점
# https://www.acmicpc.net/problem/1712

# 그냥 입력받으면 문자형으로 받아지기 때문에 int형으로 바꿔줘야 함
# map 함수를 이용해 리스트의 모든 문자에 적용
a, b, c = map(int, input().split(" "))
print(a, b, c)

n = 1
if c-b <= 0 :
    print (-1)
else :
    # 반복해서 더하는 방식 보다는 규칙을 찾아내 간단한 식으로 나타내는 것이 중요
    n = a / (c - b)
    n += 1
    print(int(n)) # 출력 형식 주의! 나눗셈 뒤에 소수점 자리가 남아있음

# -------------------------------------------------------------

# 7 벌집
# https://www.acmicpc.net/problem/2292

n = int(input())

temp = (n-2)//6
num = 0
k = 1
while num <= temp:
    num = num + k
    k += 1
print(k)
