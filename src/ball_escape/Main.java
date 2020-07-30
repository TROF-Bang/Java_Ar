package ball_escape;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static char map[][];
	public static int[] X = new int[] { 1, 0, -1, 0 }; // �ð����
	public static int[] Y = new int[] { 0, 1, 0, -1 };
	public static Queue<Balls> ballAddrs = new LinkedList<Balls>();
	public static HashSet<String> check = new HashSet<String>();
	public static String RBCheak;
	
	public static void main(String[] args) throws Exception {
		System.out.println("�Է� : ");
		BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bis.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		System.out.println("n : " + n);
		System.out.println("m : " + m);
		
		map = new char[n][m];
		Balls balls = new Balls();
		// map ����
		for (int i = 0; i < n; i++) {
			String str = bis.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'R') {
					balls.RAddr[0] = i;
					balls.RAddr[1] = j;
					RBCheak = i+","+j;
				}
				else if(map[i][j] == 'B') {
					balls.BAddr[0] = i;
					balls.BAddr[1] = j;
					RBCheak += ","+i+","+j;
				}				
			}
		}
		ballAddrs.offer(balls);
		System.out.println("RBCheak : " + RBCheak);
		check.add(RBCheak);
		// map Ȯ�� �ڵ�
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		
		System.out.println("Ƚ�� : " + search());
		System.out.println("check ������ : " + check.size());
		System.out.println("ť ������ : " + ballAddrs.size());
	}

	private static int search() {
		int RRow;
		int RCol;
		int BRow;
		int BCol;
		
		while(!ballAddrs.isEmpty()) {
			Balls balls = ballAddrs.poll();
			// Ƚ�� �߰�
			balls.count += 1;
			// Ƚ���� 10ȸ�� �ʰ��ҽ� ���ѷ��� Ż�� 
			if(balls.count > 10) {
				break;
			}
			// ������ �ð� �������� ������.
			for (int i = 0; i < 4; i++) {
				// �� ��ġ �ʱ�ȭ
				RRow = balls.RAddr[0];
				RCol = balls.RAddr[1];
				BRow = balls.BAddr[0];
				BCol = balls.BAddr[1];
				int Bcount = 0;
				int Rcount = 0;
				boolean BallCheak = false;
				
				// ��������
				while(map[RRow - X[i]][RCol + Y[i]] != '#') {
					RRow -= X[i];
					RCol += Y[i];
					Rcount++;
					if(map[RRow][RCol] == 'O') {
						break;
					}
				}
				
				// �Ķ�����
				while(map[BRow - X[i]][BCol + Y[i]] != '#') {
					BRow -= X[i];
					BCol += Y[i];
					Bcount++;
					if(map[BRow][BCol] == 'O') {
						break;
					}
				}
				
				// �Ķ��� ������ 'O'�� ��������, ��� ���� ����
				if(map[BRow][BCol] == 'O') {
	                 continue;
				}
				// ������ ������ 'O'�� �������� ���� ���
				if(map[RRow][RCol] == 'O') {
					return balls.count;
				}
				
				// �������� �Ķ����� ���� ��ġ�� ������ ����
				if(RRow == BRow && RCol == BCol) {
					if(i == 0) {
						if(Rcount > Bcount)
							RRow -= 1;
						else
							BRow -= 1;
					}
					else if(i == 1) {
						if(Rcount > Bcount)
							RCol -= 1;
						else
							BCol -= 1;
					}
					else if(i == 2) {
						if(Rcount > Bcount)
							RRow += 1;
						else
							BRow += 1;
					}
					else {
						if(Rcount > Bcount)
							RCol += 1;
						else
							BCol += 1;
					}
				}
				String check_RB = RRow+","+RCol+","+BRow+","+BCol;
				Iterator<String> iterator = check.iterator();
				while(iterator.hasNext()) {
					String str = iterator.next();
					if(str.equals(check_RB)) {
						BallCheak = true;
						break;
					}
				}
				if(!BallCheak)
					ballAddrs.offer(new Balls(RRow,RCol,BRow,BCol,balls.count));
					check.add(check_RB);
			}
		}
		return -1;
	}
}

	//�Ķ����� �������� Ż�ⱸ ��ü
	class Balls {
		
		int[] BAddr = new int[2];
		int[] RAddr = new int[2];
		int count = 0;
		
		public Balls() {}
		
		public Balls(int RRow, int RCol, int BRow, int BCol, int count) {
			this.RAddr[0] = RRow;
			this.RAddr[1] = RCol;
			this.BAddr[0] = BRow;
			this.BAddr[1] = BCol;
			this.count = count;
		}
}
