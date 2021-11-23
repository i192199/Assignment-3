import java.io.*;
import java.util.*;

public class Main {
	public static void main(String args[]) throws EmptyStringException {
//	String inputFile = args[0];
		BST bst = new BST();
		Vector<String> v = new Vector<String>(100);
		Iterator it = v.iterator();
		Vector<Word> w = new Vector<Word>(100);
		Iterator wt = w.iterator();

		//// VT
		VocabularyThread VT = new VocabularyThread(args[0], bst);
		VT.start();
		try {
			VT.join();
		} catch (Exception e) {
			System.out.println("error");
		}
//		 bst.inorder();

		//// IT
		int numberOfThreads = args.length - 1; // number of Input threads
		// System.out.println(numberOfThreads);
		InputThread IT[] = new InputThread[numberOfThreads];

		for (int i = 1; i < args.length; i++) { // starting from 1 as first arg is vocabulary.txt
			IT[i - 1] = new InputThread(args[i], v); // args[1]
			IT[i - 1].start();
		}

		for (int i = 1; i < args.length; i++) { // waiting for threads
			try {
				IT[i - 1].join();
			} catch (Exception e) {
				System.out.println("error");
			}
			// System.out.println(v);
		}
		// bst.inorder();

		// matching words
		for (int i = 0; i < v.size(); i++) {
			// System.out.println(v.get(i));
			// System.out.print(v.get(i) + " ");
			if (bst.search(v.get(i))) {
//				System.out.println("found");
//			System.out.println("///////////////");
				boolean flag = false;
				for (int j = 0; j < w.size(); j++) {
					// System.out.println(w.get(j).getData()+"=="+v.get(i));
					String VectorString = v.get(i);
					if (VectorString.equalsIgnoreCase(w.get(j).getData())) { // match found
						w.get(j).increase();// increase freq
						flag = true;
						break;
					}
				}
				if (flag == false) {
					Word word = new Word(v.get(i)); // get word at v[0]
					w.add(word); // add word to w[0]
				}
			}
		}
//		System.out.println(w);
		int totalFiles = args.length;
		System.out.println("Total number of input files: " + (totalFiles - 1));
		System.out.println("File names:");
		for (int i = 1; i < totalFiles; i++) {
			System.out.println(args[i]);
		}

		/////////////////// Menu//////////////////
		Scanner sc = new Scanner(System.in);
		int input = 0;
		do {
			System.out.println("Enter One of the following number to choose option:");
			System.out.println("1. Display BST (inorder) from vocabulary file");
			System.out.println("2. Display Vector from Input files");
			System.out.println("3. View matching words and their frequency");
			System.out.println("4. Search a query");
			System.out.println("5. quit");
			input = sc.nextInt();
			switch (input) {
			case 1:
				bst.inorder();
				break;
			case 2:
				System.out.println(v);
				break;
			case 3:
				for (int i = 0; i < w.size(); i++) {
					System.out.print(w.get(i).getData() + " ");
					System.out.println(w.get(i).getFreq());
				}
				break;
			case 4:
				String query;
				Scanner SC = new Scanner(System.in);
				query = SC.nextLine();
				if (query.equals("")) {
					throw new EmptyStringException("Input Query is null");
				}
				StringTokenizer ST = new StringTokenizer(query);
				Vector<String> V = new Vector<String>(100);
				boolean matched = false;
				while (ST.hasMoreTokens()) {
					V.add(ST.nextToken());
				}
				int BSTmatch = 0;
				for (int i = 0; i < V.size(); i++) {
					if (bst.search(V.get(i))) {
						BSTmatch++;
					}
				}
				System.out.println("Total number of words matched in BST = " + BSTmatch);
				for (int i = 1; i < args.length; i++) { // starting from 1 as first arg is vocabulary.txt
					String fileData = IT[i - 1].getFile(); // args[1]
					try {
						IT[i - 1].join();
					} catch (Exception e) {
						System.out.println("error");
					}
					if (query.equalsIgnoreCase(fileData)) {
						System.out.println("Proper file match found in file: " + args[i]);
						matched = true;
						break;
					}	
				}
				if(!matched) {
					System.out.println("No proper match in any input file");
				}
				break;
			case 5:
				return;
			}
		} while (input != 5);

	}
}
