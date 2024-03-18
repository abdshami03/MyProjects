package hash;

public class LinearHashing<T extends Comparable<T>> {
	HNode<T>[] table;
	int m;

	public LinearHashing(int n) {
		this.m = nextPrime(2 * n);
		this.table = (HNode<T>[]) new HNode[m];
		for (int i = 0; i < m; i++) {
			this.table[i] = new HNode<>();
		}
	}

	private int nextPrime(int n) {
		if (n <= 1) {
			return 2;
		}
		int prime = n;
		boolean found = false;

		while (!found) {
			prime++;
			found = isPrime(prime);
		}
		return prime;
	}

	private boolean isPrime(int n) {
		if (n <= 1) {
			return false;
		}
		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;

	}

	public void insert(T data) {
		int key = data.hashCode();
		int h0 = key % m;
		int index = h0, i = 0;
		while (!(table[index].isEmpty())) {
			i++;
			index = (h0 + i) % m;
		}
		table[index] = new HNode(data);

	}

	public void delete(T data) {
		int key = data.hashCode();
		int h0 = key % m;
		int index = h0;
		int i = 0;

		while (table[index] != null && !table[index].isEmpty()) {
			if (table[index].getData().compareTo(data) == 0) {
				table[index].setDeleted();
				return;
			}

			i++;
			index = (h0 + i) % m;
		}
		System.out.println("data not found");
	}

	public boolean search(T data) {
		int key = data.hashCode();
		int h0 = key % m;
		int index = h0;
		int i = 0;

		while (table[index] != null && !table[index].isEmpty() && i < m) {
			if (!table[index].isDeleted()&& table[index].getData().compareTo(data) == 0) {

				return true;
			}
			i++;
			index = (h0 + i) % m;
		}

		return false;
	}

	@Override
	public String toString() {
		String s = "index\tdata" + "\n";
		for (int i = 0; i < table.length; i++) {
			s += i + "\t" + table[i] + "\n";
		}
		return s;
	}

}
