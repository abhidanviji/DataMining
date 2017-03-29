import java.util.Random;

public class RandDB {

	public static void main(String[] args) {
		int num = 1;
		String str = "";
		String tr[] = new String[20];
		for (int i = 0; i < 20; i++) {
			tr[i] = "T";
		}
		boolean flag = true;
		String s[] = { "mug", "plate", "sugar", "cornflakes", "shampoo", "hanger", "pillow", "frame", "bread",
				"headphone" };
		System.out.println("hi");

		

		
		for (int t = 1; t <= 20; t++) {
			tr[t - 1] = tr[t - 1] + t + ",";
			while (flag) {
				num = rand();
				if (num != 0) {
					flag = false;
				}
			}
			System.out.println(num);
			for (int i = 0; i < num; i++) {
				int newrand = rand();
				if (!str.contains(s[newrand])) {
					str = str + s[newrand] + ",";
				} else {
					i--;
				}
			}
			str = str.substring(0, str.length() - 1);
			tr[t - 1] = tr[t - 1] + str;
			str="";
			flag=true;
		}

		for (int i = 0; i < 20; i++) {
			System.out.println(tr[i]);
		}

	}

	public static int rand() {
		Random rand = new Random();
		int n = rand.nextInt(9);
		return n;
	}

}
