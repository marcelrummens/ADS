import java.util.HashMap;


public class World
{
	private HashMap<String, WorldObject> objectArray = new HashMap<String, WorldObject>();
	private int worldWidth, worldHeight;
	private int maxObjects;
	
	public void generateObjects(int maxX, int maxY, int maxObjects)
	{
		// Save the parameters
		this.setWorldWidth(maxX);
		this.setWorldHeight(maxY);
		this.setMaxObjects(maxObjects);
		
		// 
		for(int i = 0; i < maxObjects; i++)
		{
			int x = (int) (Math.random() * maxX);
			int y = (int) (Math.random() * maxY);
			this.addObject(new WorldObject(x,y));
		}
	}
	
	public void printWorld()
	{
		for(int i = 0; i < this.getWorldWidth(); i++)
		{
			System.out.print("|");
			for(int j = 0; j < this.getWorldHeight(); j++)
			{
				if(this.hasObjectOnTile(i,j))
					System.out.print("x|");
				else
					System.out.print(" |");
			}
			System.out.print("\n");
		}
	}
	
	public double calculateDoubleObjectChance()
	{
		return 0;
	}
	
	private boolean hasObjectOnTile(int x, int y)
	{
		return (this.getObjects().containsKey(""+x+y));
	}
	
	private HashMap<String, WorldObject> getObjects()
	{
		return this.objectArray;
	}
	
	private void addObject(WorldObject object)
	{
		this.objectArray.put(""+object.getX()+object.getY(), object);
	}

	public int getWorldWidth() {
		return worldWidth;
	}

	public void setWorldWidth(int worldWidth) {
		this.worldWidth = worldWidth;
	}

	public int getWorldHeight() {
		return worldHeight;
	}

	public void setWorldHeight(int worldHeight) {
		this.worldHeight = worldHeight;
	}

	public int getMaxObjects() {
		return maxObjects;
	}

	public void setMaxObjects(int maxObjects) {
		this.maxObjects = maxObjects;
	}
}
