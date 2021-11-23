import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

public class InputThread extends Thread {
	String filename;
	Vector<String> v;

	public InputThread(String filename, Vector<String> v) {
		this.filename = filename;
		this.v = v;
	}

	public synchronized void run() {
		try {
			FileReader fr = new FileReader(filename);
			Scanner sc = new Scanner(fr);
			while (sc.hasNextLine()) {
				String data = sc.nextLine();
//				System.out.println(data);
				StringTokenizer ST = new StringTokenizer(data);
				while (ST.hasMoreTokens()) {
//					System.out.println(ST.nextToken());
					v.add(ST.nextToken());
				}
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
	}
	
	public synchronized String getFile() {
		FileReader fr;
		Scanner sc ;
		String data="" ;
		try{
			fr = new FileReader(filename);
			sc = new Scanner(fr);
			data = sc.nextLine();
		}
		catch(FileNotFoundException e) {
			System.out.println("File not found");
		}
		return data;		
	}
}
