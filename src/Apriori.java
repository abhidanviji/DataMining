import java.io.*;
import java.util.*;

public class Apriori {
	public static int sup = 0, con = 0;
	public static FileWriter writer;
	public static ArrayList<String> al = new ArrayList<String>();
	public static ArrayList<String> al1 = new ArrayList<String>();
	public static Map<String, Integer> total = new LinkedHashMap<String, Integer>();
	public static ArrayList<String> conf = new ArrayList<String>();
	public static boolean flag = true;
	public static String t[] = new String[20];
	public static String s[][] = new String[20][];
	public static int c = 0, count = 0;

	public static int nthIndexOf(String source, String sought, int n) {
		int index = source.indexOf(sought);
		if (index == -1)
			return -1;

		for (int i = 1; i < n; i++) {
			index = source.indexOf(sought, index + 1);
			if (index == -1)
				return -1;
		}
		return index;
	}

	public static void onepass() throws IOException {
		writer.write("First Pass\n------------------------------------------" + "\n");
		for (int ar = 0; ar < al.size(); ar++) {
			for (int i = 0; i < 20; i++) {
				for (int j = 1; j < s[i].length; j++) {
					if (al.get(ar).equals(s[i][j])) {
						count++;
					}
				}
			}
			supCalc(al.get(ar), count);
		}
		count = 0;
		al.removeAll(al);
		for (int i = 0; i < al1.size() - 1; i++) {
			for (int j = i + 1; j < al1.size(); j++) {
				al.add(al1.get(i) + "," + al1.get(j));
			}
		}
		al1.removeAll(al1);
	}

	public static void twopass() throws IOException {
		writer.write("\nSecond Pass\n------------------------------------------" + "\n");
		for (int a = 0; a < al.size(); a++) {
			for (int i = 0; i < 20; i++) {
				int c = 0;
				for (int j = 1; j < s[i].length; j++) {
					if (al.get(a).substring(0, al.get(a).indexOf(",")).equals(s[i][j])
							|| al.get(a).substring(al.get(a).indexOf(",") + 1).equals(s[i][j])) {
						c++;
					}
				}
				if (c == 2) {
					count++;
				}
			}
			supCalc(al.get(a), count);
		}
		count = 0;
		al.removeAll(al);
		for (int i = 0; i < al1.size() - 1; i++) {
			for (int j = i + 1; j < al1.size(); j++) {
				if (al1.get(i).substring(0, al1.get(i).indexOf(","))
						.equals(al1.get(j).substring(0, al1.get(j).indexOf(",")))) {
					al.add(al1.get(i).substring(0, al1.get(i).indexOf(",")) + ","
							+ al1.get(i).substring(al1.get(i).indexOf(",") + 1) + ","
							+ al1.get(j).substring(al1.get(j).indexOf(",") + 1));
				}
			}
		}
		al1.removeAll(al1);

	}

	public static void threepass() throws IOException {
		writer.write("\nThird Pass\n------------------------------------------" + "\n");
		for (int a = 0; a < al.size(); a++) {
			for (int i = 0; i < 20; i++) {
				int c = 0;
				for (int j = 1; j < s[i].length; j++) {
					int in = nthIndexOf(al.get(a), ",", 1);
					int in1 = nthIndexOf(al.get(a), ",", 2);
					if (al.get(a).substring(0, in).equals(s[i][j]) || al.get(a).substring(in + 1, in1).equals(s[i][j])
							|| al.get(a).substring(in1 + 1).equals(s[i][j])) {
						c++;
					}
				}
				if (c == 3) {
					count++;
				}
			}
			supCalc(al.get(a), count);
		}
		count = 0;
		al.removeAll(al);
		for (int i = 0; i < al1.size() - 1; i++) {
			for (int j = i + 1; j < al1.size(); j++) {
				int in = nthIndexOf(al1.get(i), ",", 2);
				int in1 = nthIndexOf(al1.get(j), ",", 2);
				if (al1.get(i).substring(0, in).equals(al1.get(j).substring(0, in1))) {
					al.add(al1.get(i).substring(0, in) + "," + al1.get(i).substring(in + 1) + ","
							+ al1.get(j).substring(in1 + 1));
				}
			}
		}
		al1.removeAll(al1);

	}

	public static void fourpass() throws IOException {
		writer.write("\nFourth Pass\n------------------------------------------" + "\n");
		for (int a = 0; a < al.size(); a++) {
			for (int i = 0; i < 20; i++) {
				int c = 0;
				for (int j = 1; j < s[i].length; j++) {
					int in = nthIndexOf(al.get(a), ",", 1);
					int in1 = nthIndexOf(al.get(a), ",", 2);
					int in2 = nthIndexOf(al.get(a), ",", 3);
					if (al.get(a).substring(0, in).equals(s[i][j]) || al.get(a).substring(in + 1, in1).equals(s[i][j])
							|| al.get(a).substring(in1 + 1, in2).equals(s[i][j])
							|| al.get(a).substring(in2 + 1).equals(s[i][j])) {
						c++;
					}
				}
				if (c == 4) {
					count++;
				}
			}
			supCalc(al.get(a), count);
		}
		count = 0;
		al.removeAll(al);
		for (int i = 0; i < al1.size() - 1; i++) {
			for (int j = i + 1; j < al1.size(); j++) {
				int in = nthIndexOf(al1.get(i), ",", 3);
				int in1 = nthIndexOf(al1.get(j), ",", 3);
				if (al1.get(i).substring(0, in).equals(al1.get(j).substring(0, in1))) {
					al.add(al1.get(i).substring(0, in) + "," + al1.get(i).substring(in + 1) + ","
							+ al1.get(j).substring(in1 + 1));
				}
			}
		}
		al1.removeAll(al1);
	}

	public static void fivepass() throws IOException {
		writer.write("\nFifth Pass\n------------------------------------------" + "\n");
		for (int a = 0; a < al.size(); a++) {
			for (int i = 0; i < 20; i++) {
				int c = 0;
				for (int j = 1; j < s[i].length; j++) {
					int in = nthIndexOf(al.get(a), ",", 1);
					int in1 = nthIndexOf(al.get(a), ",", 2);
					int in2 = nthIndexOf(al.get(a), ",", 3);
					int in3 = nthIndexOf(al.get(a), ",", 4);
					if (al.get(a).substring(0, in).equals(s[i][j]) || al.get(a).substring(in + 1, in1).equals(s[i][j])
							|| al.get(a).substring(in1 + 1, in2).equals(s[i][j])
							|| al.get(a).substring(in2 + 1, in3).equals(s[i][j])
							|| al.get(a).substring(in3 + 1).equals(s[i][j])) {
						c++;
					}
				}
				if (c == 5) {
					count++;
				}
			}
			supCalc(al.get(a), count);
		}
		count = 0;
		al.removeAll(al);
		for (int i = 0; i < al1.size() - 1; i++) {
			for (int j = i + 1; j < al1.size(); j++) {
				int in = nthIndexOf(al1.get(i), ",", 4);
				int in1 = nthIndexOf(al1.get(j), ",", 4);
				if (al1.get(i).substring(0, in).equals(al1.get(j).substring(0, in1))) {
					al.add(al1.get(i).substring(0, in) + "," + al1.get(i).substring(in + 1) + ","
							+ al1.get(j).substring(in1 + 1));
				}
			}
		}
		al1.removeAll(al1);

	}

	public static void sixpass() throws IOException {
		writer.write("\nSixth Pass\n------------------------------------------" + "\n");
		for (int a = 0; a < al.size(); a++) {
			for (int i = 0; i < 20; i++) {
				int c = 0;
				for (int j = 1; j < s[i].length; j++) {
					int in = nthIndexOf(al.get(a), ",", 1);
					int in1 = nthIndexOf(al.get(a), ",", 2);
					int in2 = nthIndexOf(al.get(a), ",", 3);
					int in3 = nthIndexOf(al.get(a), ",", 4);
					int in4 = nthIndexOf(al.get(a), ",", 5);
					if (al.get(a).substring(0, in).equals(s[i][j]) || al.get(a).substring(in + 1, in1).equals(s[i][j])
							|| al.get(a).substring(in1 + 1, in2).equals(s[i][j])
							|| al.get(a).substring(in2 + 1, in3).equals(s[i][j])
							|| al.get(a).substring(in3 + 1, in4).equals(s[i][j])
							|| al.get(a).substring(in4 + 1).equals(s[i][j])) {
						c++;
					}
				}
				if (c == 6) {
					count++;
				}
			}
			supCalc(al.get(a), count);
		}
		count = 0;
		al.removeAll(al);
		for (int i = 0; i < al1.size() - 1; i++) {
			for (int j = i + 1; j < al1.size(); j++) {
				int in = nthIndexOf(al1.get(i), ",", 5);
				int in1 = nthIndexOf(al1.get(j), ",", 5);
				if (al1.get(i).substring(0, in).equals(al1.get(j).substring(0, in1))) {
					al.add(al1.get(i).substring(0, in) + "," + al1.get(i).substring(in + 1) + ","
							+ al1.get(j).substring(in1 + 1));
				}
			}
		}
		al1.removeAll(al1);

	}

	public static void sevenpass() throws IOException {
		writer.write("\nSeventh Pass\n------------------------------------------" + "\n");
		for (int a = 0; a < al.size(); a++) {
			for (int i = 0; i < 20; i++) {
				int c = 0;
				for (int j = 1; j < s[i].length; j++) {
					int in = nthIndexOf(al.get(a), ",", 1);
					int in1 = nthIndexOf(al.get(a), ",", 2);
					int in2 = nthIndexOf(al.get(a), ",", 3);
					int in3 = nthIndexOf(al.get(a), ",", 4);
					int in4 = nthIndexOf(al.get(a), ",", 5);
					int in5 = nthIndexOf(al.get(a), ",", 6);
					if (al.get(a).substring(0, in).equals(s[i][j]) || al.get(a).substring(in + 1, in1).equals(s[i][j])
							|| al.get(a).substring(in1 + 1, in2).equals(s[i][j])
							|| al.get(a).substring(in2 + 1, in3).equals(s[i][j])
							|| al.get(a).substring(in3 + 1, in4).equals(s[i][j])
							|| al.get(a).substring(in4 + 1, in5).equals(s[i][j])
							|| al.get(a).substring(in5 + 1).equals(s[i][j])) {
						c++;
					}
				}
				if (c == 7) {
					count++;
				}
			}
			supCalc(al.get(a), count);
		}
		count = 0;
		al.removeAll(al);
		for (int i = 0; i < al1.size() - 1; i++) {
			for (int j = i + 1; j < al1.size(); j++) {
				int in = nthIndexOf(al1.get(i), ",", 6);
				int in1 = nthIndexOf(al1.get(j), ",", 6);
				if (al1.get(i).substring(0, in).equals(al1.get(j).substring(0, in1))) {
					al.add(al1.get(i).substring(0, in) + "," + al1.get(i).substring(in + 1) + ","
							+ al1.get(j).substring(in1 + 1));
				}
			}
		}
		al1.removeAll(al1);

	}

	public static void eightpass() throws IOException {
		writer.write("\nEigth Pass\n------------------------------------------" + "\n");
		for (int a = 0; a < al.size(); a++) {
			for (int i = 0; i < 20; i++) {
				int c = 0;
				for (int j = 1; j < s[i].length; j++) {
					int in = nthIndexOf(al.get(a), ",", 1);
					int in1 = nthIndexOf(al.get(a), ",", 2);
					int in2 = nthIndexOf(al.get(a), ",", 3);
					int in3 = nthIndexOf(al.get(a), ",", 4);
					int in4 = nthIndexOf(al.get(a), ",", 5);
					int in5 = nthIndexOf(al.get(a), ",", 6);
					int in6 = nthIndexOf(al.get(a), ",", 7);
					if (al.get(a).substring(0, in).equals(s[i][j]) || al.get(a).substring(in + 1, in1).equals(s[i][j])
							|| al.get(a).substring(in1 + 1, in2).equals(s[i][j])
							|| al.get(a).substring(in2 + 1, in3).equals(s[i][j])
							|| al.get(a).substring(in3 + 1, in4).equals(s[i][j])
							|| al.get(a).substring(in4 + 1, in5).equals(s[i][j])
							|| al.get(a).substring(in5 + 1, in6).equals(s[i][j])
							|| al.get(a).substring(in6 + 1).equals(s[i][j])) {
						c++;
					}
				}
				if (c == 8) {
					count++;
				}
			}
			supCalc(al.get(a), count);
		}
		count = 0;
		al.removeAll(al);
		for (int i = 0; i < al1.size() - 1; i++) {
			for (int j = i + 1; j < al1.size(); j++) {
				int in = nthIndexOf(al1.get(i), ",", 7);
				int in1 = nthIndexOf(al1.get(j), ",", 7);
				if (al1.get(i).substring(0, in).equals(al1.get(j).substring(0, in1))) {
					al.add(al1.get(i).substring(0, in) + "," + al1.get(i).substring(in + 1) + ","
							+ al1.get(j).substring(in1 + 1));
				}
			}
		}
		al1.removeAll(al1);

	}

	public static void ninepass() throws IOException {
		writer.write("\nNinth Pass\n------------------------------------------" + "\n");
		for (int a = 0; a < al.size(); a++) {
			for (int i = 0; i < 20; i++) {
				int c = 0;
				for (int j = 1; j < s[i].length; j++) {
					int in = nthIndexOf(al.get(a), ",", 1);
					int in1 = nthIndexOf(al.get(a), ",", 2);
					int in2 = nthIndexOf(al.get(a), ",", 3);
					int in3 = nthIndexOf(al.get(a), ",", 4);
					int in4 = nthIndexOf(al.get(a), ",", 5);
					int in5 = nthIndexOf(al.get(a), ",", 6);
					int in6 = nthIndexOf(al.get(a), ",", 7);
					int in7 = nthIndexOf(al.get(a), ",", 8);
					if (al.get(a).substring(0, in).equals(s[i][j]) || al.get(a).substring(in + 1, in1).equals(s[i][j])
							|| al.get(a).substring(in1 + 1, in2).equals(s[i][j])
							|| al.get(a).substring(in2 + 1, in3).equals(s[i][j])
							|| al.get(a).substring(in3 + 1, in4).equals(s[i][j])
							|| al.get(a).substring(in4 + 1, in5).equals(s[i][j])
							|| al.get(a).substring(in5 + 1, in6).equals(s[i][j])
							|| al.get(a).substring(in6 + 1, in7).equals(s[i][j])
							|| al.get(a).substring(in7 + 1).equals(s[i][j])) {
						c++;
					}
				}
				if (c == 9) {
					count++;
				}
			}
			supCalc(al.get(a), count);
			count = 0;
		}
		count = 0;
		al.removeAll(al);
		for (int i = 0; i < al1.size() - 1; i++) {
			for (int j = i + 1; j < al1.size(); j++) {
				int in = nthIndexOf(al1.get(i), ",", 8);
				int in1 = nthIndexOf(al1.get(j), ",", 8);
				if (al1.get(i).substring(0, in).equals(al1.get(j).substring(0, in1))) {
					al.add(al1.get(i).substring(0, in) + "," + al1.get(i).substring(in + 1) + ","
							+ al1.get(j).substring(in1 + 1));
				}
			}
		}
		al1.removeAll(al1);

	}

	public static void tenpass() throws IOException {
		writer.write("\nTenth Pass\n------------------------------------------" + "\n");
		for (int a = 0; a < al.size(); a++) {
			for (int i = 0; i < 20; i++) {
				int c = 0;
				for (int j = 1; j < s[i].length; j++) {
					int in = nthIndexOf(al.get(a), ",", 1);
					int in1 = nthIndexOf(al.get(a), ",", 2);
					int in2 = nthIndexOf(al.get(a), ",", 3);
					int in3 = nthIndexOf(al.get(a), ",", 4);
					int in4 = nthIndexOf(al.get(a), ",", 5);
					int in5 = nthIndexOf(al.get(a), ",", 6);
					int in6 = nthIndexOf(al.get(a), ",", 7);
					int in7 = nthIndexOf(al.get(a), ",", 8);
					int in8 = nthIndexOf(al.get(a), ",", 9);
					if (al.get(a).substring(0, in).equals(s[i][j]) || al.get(a).substring(in + 1, in1).equals(s[i][j])
							|| al.get(a).substring(in1 + 1, in2).equals(s[i][j])
							|| al.get(a).substring(in2 + 1, in3).equals(s[i][j])
							|| al.get(a).substring(in3 + 1, in4).equals(s[i][j])
							|| al.get(a).substring(in4 + 1, in5).equals(s[i][j])
							|| al.get(a).substring(in5 + 1, in6).equals(s[i][j])
							|| al.get(a).substring(in6 + 1, in7).equals(s[i][j])
							|| al.get(a).substring(in7 + 1, in8).equals(s[i][j])
							|| al.get(a).substring(in8 + 1).equals(s[i][j])) {
						c++;
					}
				}
				if (c == 10) {
					count++;
				}
			}
			supCalc(al.get(a), count);
			
		}
		count = 0;
		al.removeAll(al);
		al1.removeAll(al1);

	}
	public static void supCalc(String s,int num) throws IOException {
		if (((double) num / 20.0) * 100 >= sup) {
			total.put(s, num);
			writer.write(s+" = "+ num + "\n");
			al1.add(s);
		}
		count = 0;
	}

	public static boolean confCalc(double num, double val) {
		double result = (num / val) * 100;
		if (result > con)
			return true;
		else
			return false;
	}

	public static void main(String[] args) throws IOException {

		sup = Integer.parseInt(args[1]);
		con = Integer.parseInt(args[2]);
		writer = new FileWriter("output.txt");
		BufferedReader reader = new BufferedReader(new FileReader(args[0]));
		String line;
		while ((line = reader.readLine()) != null) {
			t[c] = line;
			s[c] = t[c].split(",");
			c++;
		}

		for (int i = 0; i < 20; i++) {
			for (int j = 1; j < s[i].length; j++) {
				if (!al.contains(s[i][j])) {
					al.add(s[i][j]);
				}
			}
		}
		writer.write("Frequent Item Sets\n------------------------------------------" + "\n");
		while (flag) {
			if (al.size() == 0) {
				writer.write("\nNo more Passes\n------------------------------------------" + "\n");
				for (Map.Entry<String, Integer> entry : total.entrySet()) {
					String news[] = entry.getKey().split(",");
					if (news.length == 2) {
						if (confCalc(entry.getValue(), total.get(news[0]))) {
							conf.add(news[0] + "=>" + news[1]);
						}
						if (confCalc(entry.getValue(), total.get(news[1]))) {
							conf.add(news[1] + "=>" + news[0]);
						}

					}
			if (news.length == 3) {
				for (int i = 0; i < news.length - 1; i++) {
					for (int j = i + 1; j < news.length; j++) {
						if (confCalc(entry.getValue(), total.get(news[i] + "," + news[j]))) {
									conf.add(news[i] + "," + news[j] + "=>" + news[3 - (i + j)]);
								}}}}
			if (news.length == 4) {
				for (int i = 0; i < news.length - 2; i++) {
					for (int j = i + 1; j < news.length - 1; j++) {
						for (int k = j + 1; k < news.length; k++) {
							if (confCalc(entry.getValue(),
											total.get(news[i] + "," + news[j] + "," + news[k]))) {
										String sn = entry.getKey().replace(",", "");
										sn = sn.replace(news[i], "");
										sn = sn.replace(news[j], "");
										sn = sn.replace(news[k], "");
										conf.add(news[i] + "," + news[j] + "," + news[k] + "=>" + sn);
									}}}}}
			if (news.length == 5) {
				for (int i = 0; i < news.length - 3; i++) {
					for (int j = i + 1; j < news.length - 2; j++) {
						for (int k = j + 1; k < news.length - 1; k++) {
							for (int l = k + 1; l < news.length; l++) {
								if (confCalc(entry.getValue(),
												total.get(news[i] + "," + news[j] + "," + news[k] + "," + news[l]))) {
											String sn = entry.getKey().replace(",", "");
											sn = sn.replace(news[i], "");
											sn = sn.replace(news[j], "");
											sn = sn.replace(news[k], "");
											sn = sn.replace(news[l], "");
											conf.add(news[i] + "," + news[j] + "," + news[k] + "," + news[l] + "=>"
													+ sn);
										}}}}}}
			if (news.length == 6) {
				for (int i = 0; i < news.length - 4; i++) {
					for (int j = i + 1; j < news.length - 3; j++) {
						for (int k = j + 1; k < news.length - 2; k++) {
							for (int l = k + 1; l < news.length - 1; l++) {
								for (int m = l + 1; m < news.length; m++) {
									if (confCalc(entry.getValue(), total.get(news[i] + "," + news[j] + ","
													+ news[k] + "," + news[l] + "," + news[m]))) {
												String sn = entry.getKey().replace(",", "");
												sn = sn.replace(news[i], "");
												sn = sn.replace(news[j], "");
												sn = sn.replace(news[k], "");
												sn = sn.replace(news[l], "");
												sn = sn.replace(news[m], "");
												conf.add(news[i] + "," + news[j] + "," + news[k] + "," + news[l] + ","
														+ news[m] + "=>" + sn);
											}}}}}}}
			if (news.length == 7) {
				for (int i = 0; i < news.length - 5; i++) {
					for (int j = i + 1; j < news.length - 4; j++) {
						for (int k = j + 1; k < news.length - 3; k++) {
							for (int l = k + 1; l < news.length - 2; l++) {
								for (int m = l + 1; m < news.length - 1; m++) {
									for (int n = m + 1; n < news.length; n++) {
										if (confCalc(entry.getValue(), total.get(news[i] + "," + news[j] + ","
														+ news[k] + "," + news[l] + "," + news[m] + "," + news[n]))) {
													String sn = entry.getKey().replace(",", "");
													sn = sn.replace(news[i], "");
													sn = sn.replace(news[j], "");
													sn = sn.replace(news[k], "");
													sn = sn.replace(news[l], "");
													sn = sn.replace(news[m], "");
													sn = sn.replace(news[n], "");
													conf.add(news[i] + "," + news[j] + "," + news[k] + "," + news[l]
															+ "," + news[m] + "," + news[n] + "=>" + sn);
												}}}}}}}}
			if (news.length == 8) {
				for (int i = 0; i < news.length - 6; i++) {
					for (int j = i + 1; j < news.length - 5; j++) {
						for (int k = j + 1; k < news.length - 4; k++) {
							for (int l = k + 1; l < news.length - 3; l++) {
								for (int m = l + 1; m < news.length - 2; m++) {
									for (int n = m + 1; n < news.length - 1; n++) {
										for (int o = n + 1; o < news.length; o++) {
											if (confCalc(entry.getValue(),
															total.get(news[i] + "," + news[j] + "," + news[k] + ","
																	+ news[l] + "," + news[m] + "," + news[n] + ","
																	+ news[o]))) {
														String sn = entry.getKey().replace(",", "");
														sn = sn.replace(news[i], "");
														sn = sn.replace(news[j], "");
														sn = sn.replace(news[k], "");
														sn = sn.replace(news[l], "");
														sn = sn.replace(news[m], "");
														sn = sn.replace(news[n], "");
														sn = sn.replace(news[o], "");
														conf.add(news[i] + "," + news[j] + "," + news[k] + "," + news[l]
																+ "," + news[m] + "," + news[n] + "," + news[o] + "=>"
																+ sn);
													}}}}}}}}}
			if (news.length == 9) {
				for (int i = 0; i < news.length - 7; i++) {
					for (int j = i + 1; j < news.length - 6; j++) {
						for (int k = j + 1; k < news.length - 5; k++) {
							for (int l = k + 1; l < news.length - 4; l++) {
								for (int m = l + 1; m < news.length - 3; m++) {
									for (int n = m + 1; n < news.length - 2; n++) {
										for (int o = n + 1; o < news.length - 1; o++) {
											for (int p = o + 1; p < news.length; p++) {
												if (confCalc(entry.getValue(),
																total.get(news[i] + "," + news[j] + "," + news[k] + ","
																		+ news[l] + "," + news[m] + "," + news[n] + ","
																		+ news[o] + "," + news[p]))) {
															String sn = entry.getKey().replace(",", "");
															sn = sn.replace(news[i], "");
															sn = sn.replace(news[j], "");
															sn = sn.replace(news[k], "");
															sn = sn.replace(news[l], "");
															sn = sn.replace(news[m], "");
															sn = sn.replace(news[n], "");
															sn = sn.replace(news[o], "");
															sn = sn.replace(news[p], "");
															conf.add(news[i] + "," + news[j] + "," + news[k] + ","
																	+ news[l] + "," + news[m] + "," + news[n] + ","
																	+ news[o] + "," + news[p] + "=>" + sn);
														}}}}}}}}}}
													
			if (news.length == 10) {
				for (int i = 0; i < news.length - 8; i++) {
					for (int j = i + 1; j < news.length - 7; j++) {
						for (int k = j + 1; k < news.length - 6; k++) {
							for (int l = k + 1; l < news.length - 5; l++) {
								for (int m = l + 1; m < news.length - 4; m++) {
									for (int n = m + 1; n < news.length - 3; n++) {
										for (int o = n + 1; o < news.length - 2; o++) {
											for (int p = o + 1; p < news.length - 1; p++) {
												for (int q = p + 1; q < news.length; q++) {
								                  if (confCalc(entry.getValue(),total.get(news[i] + "," + news[j] + "," + news[k]
																			+ "," + news[l] + "," + news[m] + ","
																			+ news[n] + "," + news[o] + "," + news[p]
																			+ "," + news[q]))) {
																String sn = entry.getKey().replace(",", "");
																sn = sn.replace(news[i], "");
																sn = sn.replace(news[j], "");
																sn = sn.replace(news[k], "");
																sn = sn.replace(news[l], "");
																sn = sn.replace(news[m], "");
																sn = sn.replace(news[n], "");
																sn = sn.replace(news[o], "");
																sn = sn.replace(news[p], "");
																sn = sn.replace(news[q], "");
																conf.add(news[i] + "," + news[j] + "," + news[k] + ","
																		+ news[l] + "," + news[m] + "," + news[n] + ","
																		+ news[o] + "," + news[p] + "," + news[q] + "=>"
																		+ sn);
															}}}}}}}}}}}}
														
				writer.write("\nAssociation Rule(s)\n------------------------------------------" + "\n\n");
				for (String str : conf) {
					writer.write(str + "\n");

				}
				writer.write("\nNumber of Association Rule(s) - "+conf.size());
				writer.close();

				flag = false;
			} else {
				String test[] = al.get(0).split(",");
				switch (test.length) {
				case 1: onepass(); break;
				case 2: twopass(); break;
				case 3: threepass(); break;
				case 4: fourpass();	break;
				case 5:	fivepass();	break;
				case 6:	sixpass(); break;
				case 7: sevenpass(); break;
				case 8: eightpass(); break;
				case 9: ninepass(); break;
				case 10: tenpass(); break;
				}
			}
		}

	}
}
