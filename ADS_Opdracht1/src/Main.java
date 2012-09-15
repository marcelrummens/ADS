
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		World world = new World();
		world.generateObjects(20, 20, 50);
		
		world.printWorld();
	}

}
