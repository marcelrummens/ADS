
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		long start = System.currentTimeMillis();

		World world = new World();
		world.generateObjects(20, 20, 50);
		
		world.printWorld();

		long end = System.currentTimeMillis();
		System.out.println("\nTotal Runtime: " + (end - start) + " ms");
	}

}
