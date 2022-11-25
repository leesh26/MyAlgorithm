def solution(n, lost, reserve):
    students = n
    
    lost.sort(reverse = True)
    reserve.sort(reverse = True)
    
    lostaaaaa = lost.copy()
    reserve_ = reserve.copy()
    
    for p in lostaaaaa:
        # 여벌 있는 학생이 도난당한 경우
        print(p)
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