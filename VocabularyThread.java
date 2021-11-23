import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class VocabularyThread extends Thread {
	BST bst;
	String filename;

	public VocabularyThread(String filename, BST bst) {
		// store BST
		this.bst = bst;
		this.filename = filename;
	}

	public synchronized void run() {
		try {
			FileReader fr = new FileReader(filename);
			Scanner sc = new Scanner(fr);
			while (sc.hasNextLine()) {
				String data = sc.nextLine();
//				System.out.println(data);
				bst.insert(data);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
//		bst.inorder();
	}
	
}