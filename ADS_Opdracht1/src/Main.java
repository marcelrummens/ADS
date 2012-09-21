
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
		
//		int numberOfWorlds = 1000000;
//		int numberOfDoubles = 0;
//		
//		for(int i = 0; i < numberOfWorlds; i++)
//		{
//			World world = new World();
//			world.generateObjects(20, 20, 50);
//			
//			if(world.hasMultiTile())
//				numberOfDoubles++;
//		}
//		
//		System.out.println("Number of overpopulated tiles: "+numberOfDoubles+" / "+numberOfWorlds);
//		System.out.println("Chance of at least 1 overpopulated tile: "+(numberOfDoubles / (double) numberOfWorlds * 100));
	}

}
