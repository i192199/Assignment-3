public class Word {
	String data;
	int freq;

	Word() {
		data = null;
		freq = 0;
	}

	Word(String d) {
		data = d;
		freq = 1;
	}

	public void increase() {
		freq++;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getFreq() {
		return freq;
	}

	public void setFreq(int freq) {
		this.freq = freq;
	}
}
