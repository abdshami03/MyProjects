package hash;

public class driver {

	public static void main(String[] args) {
		LinearHashing<Integer> lh = new LinearHashing<>(5);
		
		lh.insert(10);
		lh.insert(5);
		lh.insert(15);
		lh.insert(77);
		
		lh.delete(9);
		lh.delete(77);
		
		

		System.out.println(lh);
		System.out.println(lh.search(15));
		System.out.println(lh.search(77));

	}

}
