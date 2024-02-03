
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		str = str.substring(1);

		return str;
	}

	public static char head(String str) {
		char strC = str.charAt(0);

		return strC;
	}

	public static int levenshtein(String word1, String word2) {
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();

		int a = word1.length();
		int b = word2.length();

		if (a == 0) {
			return b;
		}

		if (b == 0) {
			return a; 
		}

		if (head(word1) == head(word2)) {
			return levenshtein(tail(word1), tail(word2));
		}

		else {
			int one = levenshtein(tail(word1), word2);
			int two = levenshtein(word1, tail(word2));
			int three = levenshtein (tail(word1), tail(word2));

			int min = 1 + Math.min(one, Math.min(two, three));
			return min; 

		}

	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);

		for (int i = 0; i < dictionary.length; i++) {
			dictionary[i] = in.readString();
		}

		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		word = word.toLowerCase();
		String wordF = word;
		for (int i = 0; i < dictionary.length; i++) {
			int thresh1 = levenshtein(word, dictionary[i]);
			
			if (thresh1 <= threshold) {
				threshold = thresh1;
				wordF = dictionary[i];
			}
		}
		return wordF;
	}
}
