package game_2048;

public class test {

	public static int count = 0 ;
	
	public static void main(String[] args) {
		st(0);

		System.out.println(count);
	}
	
	public static void st(int i) {
		if(i == 1) {
			count++;
			return;
		}

		st(i+1);
		st(i+1);
		st(i+1);
		st(i+1);
	}
}
