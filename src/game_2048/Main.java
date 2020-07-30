package game_2048;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static int[][] map;
	public static int[] X = new int[] { 1, 0, -1, 0 }; // 시계방향
	public static int[] Y = new int[] { 0, 1, 0, -1 };
	public static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		System.out.println("입력 : ");
		int input = new Integer(br.readLine());
		System.out.println("input : " + input);
		map = new int[input][input];
		
		for (int i = 0; i < input; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < input; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// map확인
		for (int i = 0; i < input; i++) {
			for (int j = 0; j < input; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		
		// 최대 5회로 가장 큰수 찾기
		
	}
}
