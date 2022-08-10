package com.codestates.coplit; 
import java.util.*;
import java.lang.Math;

public class Solution { 
	public int subsetSum(int[] set, int bound) {
		int max = 0;

		boolean[] cached = new boolean[301];

    	// 집합의 요소를 하나씩 순회
		for(int member : set) {
			// subsum을 기록할 리스트
			ArrayList<Integer> reachables = new ArrayList<>();
      
		  // wanted는 1부터 제한 - 현재 요소값 까지 순회한다.
		  // 이 값이 true라면 만들 수 있는 값이고 이 값에 현재 값을 더해 더 만들 수 있는 값을 추가한다.
		  // 만약 이런 배열이 있다고 해보자 {1, 2, 3} -> 처음 반복문이 돌면 1은 true가 될 것이다. 
		  // -> 두번째 반복문을 돌 때는 1이 true가 되어있기 때문에 1 + 2가 reachable로 추가된다. 이로써 1과 2로 만들 수 있는 모든 값들이 확인되는 셈이다.
		  // -> 그리고 세번째 반복문을 돌면 1과 2, 3으로 만들 수 있는 값들이 확인된다.
			for(int wanted = 1; wanted <= bound - member; wanted++) {
				if(cached[wanted]) {
					int reached = wanted + member;
					reachables.add(reached);
					if(reached > max) max = reached;
				}
			}
      
      // 만들 수 있는 값들을 체크
			for(int data : reachables) cached[data] = true;
      
      // member도 체크 
			if (member <= bound) {
				cached[member] = true;
				if (member > max) max = member;
			}
		}
		return max;
	}
}
