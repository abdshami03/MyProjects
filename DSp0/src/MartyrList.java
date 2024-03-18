
public class MartyrList {
	Record[] records;
	int size;

	public MartyrList() {
		this.records = new Record[16];
		this.size = 0;
	}

	public void add(Record record) {
		if (size == records.length) {
			resize();
		}
		records[size] = record;
		size++;
	}

	private void resize() {
		int newS;
		if (records.length == 0) {
			newS = 16;
		} else {
			newS = records.length * 2;
		}

		Record[] newRecords = new Record[newS];
		for (int i = 0; i < records.length; i++) {
			newRecords[i] = records[i];
		}
		records = newRecords;
	}

	public boolean delete(String name) {
		for (int i = 0; i < size; i++) {
			if (records[i].getName().equals(name)) {
				for (int j = i; j < size - 1; j++) {
					records[j] = records[j + 1];
				}
				size--;
				return true;
			}
		}
		return false;
	}

	public Record search(String name) {
		for (int i = 0; i < size; i++) {
			if (records[i].getName().equals(name)) {
				return records[i];
			}
		}
		return null;
	}

	public int countByAge(int age) {
		int count = 0;
		for (int i = 0; i < size; i++) {
			if (records[i].getAge() == age) {
				count++;
			}
		}
		return count;
	}

	public int countByGender(char gender) {
		int count = 0;
		for (int i = 0; i < size; i++) {
			if (records[i].getGender() == gender) {
				count++;
			}
		}
		return count;
	}

	public int countByDate(String date) {
		int count = 0;
		for (int i = 0; i < size; i++) {
			if (records[i].getDOD().equals(date)) {
				count++;
			}
		}
		return count;
	}

}
