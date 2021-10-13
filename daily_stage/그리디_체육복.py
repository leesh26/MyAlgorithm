# 처음 푼 풀이
# 여벌이 있는 경우 아래 조건문까지 이어져 다른 친구가 체육복을 못빌리게 함
def solution(n, lost, reserve):
  students = n
  lost.sort(reverse=True)
  for p in lost:
      # 여벌 있는 학생이 도난당한 경우
      if p in reserve:
          reserve.remove(p)
          
      # 여벌 없는 학생이 도난당한 경우
      if p+1 in reserve:
          reserve.remove(p+1)
      elif p-1 in reserve:
          reserve.remove(p-1)
      else:
          students -= 1   

  answer = students 
  return answer

-------------------------------------------------------------
# 수정 오답 하나 발생ㅠㅠ
def solution(n, lost, reserve):
    students = n
    lost.sort(reverse=True)
    for p in lost:
        # 여벌 있는 학생이 도난당한 경우
        if p in reserve:
            reserve.remove(p)
        else:
            # 여벌 없는 학생이 도난당한 경우
            if p+1 in reserve:
                reserve.remove(p+1)
            elif p-1 in reserve:
                reserve.remove(p-1)
            else:
                students -= 1   
            
    answer = students 
    return answer
  
  -------------------------------------------------------
  def solution(n, lost, reserve):
    students = n
    
    lost.sort(reverse = True)
    reserve.sort(reverse = True)
    
    lostaaaaa = lost.copy()
    reserve_ = reserve.copy()
    
    for p in lostaaaaa:
        # 여벌 있는 학생이 도난당한 경우
        if p in reserve_:
            reserve.remove(p)
            lost.remove(p)
    
    for p in lost:
        # 여벌 없는 학생이 도난당한 경우
        if p+1 in reserve:
            reserve.remove(p+1)
        elif p-1 in reserve:
            reserve.remove(p-1)
        else:
            students -= 1

    return students
