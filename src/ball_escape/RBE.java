package ball_escape;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class RBE {

	 public static final int RED = 0, BLUE = 1;
	 public static int N, M;
	 public static char[][] map;
	 public static boolean[][][][] visited;
	 public static int[] dirX = new int[] { 0, 0, 1, -1 }; // ��������
	 public static int[] dirY = new int[] { 1, -1, 0, 0 };
	 public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	 
	 public static void main(String[] args) throws Exception {
		 System.out.println("�Է� : ");
	     StringTokenizer st = new StringTokenizer(br.readLine());
	     
	     N = Integer.parseInt(st.nextToken());
	     M = Integer.parseInt(st.nextToken());
	 
	     map = new char[N][M];
	     visited = new boolean[10][10][10][10];
	 
	     Node node = new Node();
	     node.cnt = 0;
	 
	     for (int i = 0; i < N; i++) {
	         String str = br.readLine();
	         for (int j = 0; j < M; j++) {
	             map[i][j] = str.charAt(j);
	             if (map[i][j] == 'R') {
	                 node.rRow = i;
	                 node.rCol = j;
	             } else if (map[i][j] == 'B') {
	                 node.bRow = i;
	                 node.bCol = j;
	             }
	         }
	     }
	     bfs(node);
	     showMap(map);
	 }
	 
	 public static void bfs(Node ball) {
	     Queue<Node> q = new LinkedList<Node>();
	     q.offer(ball);
	 
	     while (!q.isEmpty()) {
	 
	         Node node = q.poll();
	         visited[node.rRow][node.rCol][node.bRow][node.bCol] = true;
	 
	         //11�� �̻� ������ ��� -1�� ����Ѵ�.
	         if (node.cnt >= 10) {
	             System.out.println(-1);
	             return;
	         }
	         
	         //���� �� ������ ��ġ�� �������� ��,��,��,�� ���� ��������.
	         for (int dir = 0; dir < 4; dir++) {
	 
	             //�Ķ��� ������ ���� ������.
	             int bnRow = node.bRow;
	             int bnCol = node.bCol;
	             while (map[bnRow + dirX[dir]][bnCol + dirY[dir]] != '#') {
	                 bnRow += dirX[dir];
	                 bnCol += dirY[dir];
	                 if (map[bnRow][bnCol] == 'O') {
	                     break;
	                 }
	             }
	             
	             //�� ����, ������ ������ ������.
	             int rnRow = node.rRow;
	             int rnCol = node.rCol;
	             while (map[rnRow + dirX[dir]][rnCol + dirY[dir]] != '#') {
	                 rnRow += dirX[dir];
	                 rnCol += dirY[dir];
	                 if (map[rnRow][rnCol] == 'O') {
	                     break;
	                 }
	             }
	             
	             //�Ķ��� ������ 'O'�� �����ٸ�, Ž���� �����.
	             if (map[bnRow][bnCol] == 'O') {
	            	 System.out.println("�Ķ����� ���� : " + map[bnRow][bnCol]);
	                 continue;
	             }
	             //������ ������ 'O'�� �����ٸ�, ������ ����Ѵ�.
	             if (map[rnRow][rnCol] == 'O') {
	                 System.out.println(node.cnt + 1);
	                 return;
	             }
	             
	             //�� ������ ��ġ�� ���ٸ�, ��ġ�� �����Ѵ�.
	             if (rnRow == bnRow && rnCol == bnCol) {
	 
	                 switch (dir) {
	 
	                 case 0: // ��
	                     if (node.rCol > node.bCol)
	                         bnCol -= 1;
	                     else
	                         rnCol -= 1;
	                     break;
	                 case 1: // ��
	                     if (node.rCol > node.bCol)
	                         rnCol += 1;
	                     else
	                         bnCol += 1;
	                     break;
	                 case 2: // ��
	                     if (node.rRow > node.bRow)
	                         bnRow -= 1;
	                     else
	                         rnRow -= 1;
	                     break;
	                 case 3: // ��
	                     if (node.rRow > node.bRow)
	                         rnRow += 1;
	                     else
	                         bnRow += 1;
	                     break;
	                 }
	             }
	             //�� ������ ���� ���� ������ ��ġ�� ó�� Ž���ϴ� ���̶�� ť�� �ִ´�.
	             if (!visited[rnRow][rnCol][bnRow][bnCol]) {
	                 q.offer(new Node(rnRow, rnCol, bnRow, bnCol, node.cnt + 1));
	             }
	         }
	     }
	     System.out.println(-1);
	 }
	 
	 public static void showMap(char[][] map) {
	     for (int i = 0; i < N; i++) {
	         for (int j = 0; j < M; j++) {
	             System.out.print(map[i][j] + " ");
	         }
	         System.out.println();
	     }
	     System.out.println();
	 }
	}
	 
	class Node {
	 
	    int rRow;
	    int rCol;
	    int bRow;
	    int bCol;
	    int cnt;
	 
	    public Node(int rRow, int rCol, int bRow, int bCol, int cnt) {
	        this.rRow = rRow;
	        this.rCol = rCol;
	        this.bRow = bRow;
	        this.bCol = bCol;
	        this.cnt = cnt;
	    }
	 
	    public Node() {
	    }
	 

}
