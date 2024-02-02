

public class HashTagTokenizer {

	public static void main(String[] args) {

		String hashTag = args[0];
		String[] dictionary = readDictionary("dictionary.txt");
		System.err.println(existInDictionary("cow", dictionary));
		breakHashTag(hashTag, dictionary);
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);
		
		for (int i = 0; i < 3000; i++) {
			dictionary[i] = in.readString();
		}

		return dictionary;
	}

	public static boolean existInDictionary(String word, String []dictionary) {
		boolean inDic = false; 
		for (int i = 0; i < dictionary.length; i++) {
			if (word.equals(dictionary[i])) {
				inDic = true;
				System.err.println(word);
				break;
			}
		}

		return inDic;

	}

	public static void breakHashTag(String hashtag, String[] dictionary) {
		// Base case: do nothing (return) if hashtag is an empty string.
        if (hashtag.isEmpty()) {
            return;
        }
 
        int N = hashtag.length();
		String lHashtag = hashtag.toLowerCase();
        
		for (int i = 1; i <= N; i++) {
			String subHash = lHashtag.substring(0,i);
			if (existInDictionary(subHash, dictionary) == true) {
				System.err.println(lHashtag.substring(0,i));
				breakHashTag(lHashtag.substring(i), dictionary);
				return; 
			}
		}
    }

}
