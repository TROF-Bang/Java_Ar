package game_2048;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	
	static int n; 
	static int max=0;
	static Stack<Integer> s = new Stack<Integer>();
	static int count = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine(),""); 
		n = Integer.parseInt(st.nextToken()); 
		int[][] map = new int[n][n];
		int idx = 0;
		
		// map에 데이터 저장
		for(int i=0;i<n;i++) { 
		st = new StringTokenizer(br.readLine()," ");		
		idx = 0; 
		
		while(st.hasMoreTokens()) { 
			map[i][idx] = Integer.parseInt(st.nextToken()); 
			idx+=1; 
			} 
		} 
		
		dfs(0,map); 
		System.out.println(max);
		System.out.println(count);
	}
	
	public static void dfs(int cnt, int[][] map) {
		// 최대 5회 까지 이동
		if(cnt == 5) {
			count++;
			return;
		}
		int[][] map2 = new int[n][n];
		
		// 방향키 위쪽
		for (int i = 0; i < n; i++) {

			for (int j = n-1; j >= 0; j--) {
				if(map[j][i] == 0)
					continue;
				s.add(map[j][i]);
			}
			int idx = 0;
			while(!s.isEmpty()) {
				int t = s.pop();
				if(s.size() > 0 && t == s.peek()) {
					map2[idx][i] = 2*t;
					s.pop();
					if(max < 2*t)
						max = 2*t;
				} else {
					map2[idx][i] = t;
					if(max < t)
						max = t;
				}
				idx++;
			}
		}
		dfs(cnt+1, map2);
		
		// 방향키 밑으로
		map2 = new int[n][n];
		for (int i = 0; i < n; i++) {

			for (int j = 0; j < n; j++) {
				if(map[j][i] == 0)
					continue;
				s.add(map[j][i]);
			}
			int idx = n-1;
			while(!s.isEmpty()) {
				int t = s.pop();
				if(s.size() > 0 && t == s.peek()) {
					map2[idx][i] = 2*t;
					s.pop();
					if(max < 2*t)
						max = 2*t;
				} else {
					map2[idx][i] = t;
					if(max < t)
						max = t;
				}
				idx--;
			}
		}
		dfs(cnt+1, map2);
		
		// 방향키 왼쪽
		map2 = new int[n][n];
		for (int i = 0; i < n; i++) {

			for (int j = n-1; j >= 0; j--) {
				if(map[i][j] == 0)
					continue;
				s.add(map[i][j]);
			}
			int idx = 0;
			while(!s.isEmpty()) {
				int t = s.pop();
				if(s.size() > 0 && t == s.peek()) {
					map2[i][idx] = 2*t;
					s.pop();
					if(max < 2*t)
						max = 2*t;
				} else {
					map2[i][idx] = t;
					if(max < t)
						max = t;
				}
				idx++;
			}
		}
		dfs(cnt+1, map2);
		
		// 방향키 오른쪽
		map2 = new int[n][n];
		for (int i = 0; i < n; i++) {

			for (int j = 0; j < n; j++) {
				if(map[i][j] == 0)
					continue;
				s.add(map[i][j]);
			}
			int idx = n-1;
			while(!s.isEmpty()) {
				int t = s.pop();
				if(s.size() > 0 && t == s.peek()) {
					map2[i][idx] = 2*t;
					s.pop();
					if(max < 2*t)
						max = 2*t;
				} else {
					map2[i][idx] = t;
					if(max < t)
						max = t;
				}
				idx--;
			}
		}
		dfs(cnt+1, map2);
	}
	
}
