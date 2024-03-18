
public class Driver {

	public static void main(String[] args) {
		BST<Integer> bst = new BST<>();
		
		 bst.insert(50);
	        bst.insert(30);
	        bst.insert(70);
	        bst.insert(20);
	        bst.insert(40);
	        bst.insert(60);
	        bst.insert(80);

	        bst.traverseInOrder();
	        System.out.println(" ");
	        bst.levelOrderTraversal();

	        System.out.println("\nTree Size: " + bst.size());

	        System.out.println("Number of Parents: " + bst.countParents());

	        int elementToDelete = 60;
	        TNode<Integer> deletedNode = bst.delete(elementToDelete);
	        System.out.println("\nDeleted Node data " + elementToDelete + ": " + deletedNode);

	        System.out.println("In-Order Traversal after deleting:");
	        bst.traverseInOrder();
	        
	        System.out.println("\nTree Height: " + bst.height());

	        System.out.println("Is Full: " + bst.isFull());

	        int searchElement = 60;
	        TNode<Integer> foundNode = bst.search(searchElement);
	        System.out.println("Search for " + searchElement + ": " + foundNode);

	        System.out.println("Is Complete: " + bst.isComplete());

	        System.out.println("Pre-Order Traversal:");
	        bst.preOrder();
	        
	        System.out.println("\nPost-Order Traversal:");
	        bst.postOrder();
	}

}
