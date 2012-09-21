

public class World
{
	private int grid[][];
	private int worldWidth, worldHeight;
	private int maxObjects;
	
	private boolean debug = false; // Set to true to display runtimes.
	
	public void generateObjects(int maxX, int maxY, int maxObjects)
	{
		long start = System.currentTimeMillis();
		
		// Save the parameters
		this.setWorldWidth(maxX);
		this.setWorldHeight(maxY);
		this.setMaxObjects(maxObjects);
		
		this.grid = new int[maxX][maxY];
		 
		for(int i = 0; i < maxObjects; i++)
		{
			int x = (int) (Math.random() * maxX);
			int y = (int) (Math.random() * maxY);
			this.grid[x][y]++;
		}
		
		long end = System.currentTimeMillis();
		
		if(debug)
			System.out.println("Runtime generating: " + (end - start) + " ms");
	}
	
	public void printWorld()
	{
		System.out.print("[1a] World: "+this.getWorldWidth()+"x"+this.getWorldHeight()+", objects: "+this.getMaxObjects()+"\n\n");

		if(this.debug == false)
		{
			for(int i = 0; i < this.getWorldWidth(); i++)
			{
				System.out.print("|");
				for(int j = 0; j < this.getWorldHeight(); j++)
				{
					if(this.grid[i][j] > 0)
						System.out.print(this.grid[i][j]+"|");
					else
						System.out.print(" |");
				}
				
				System.out.print("   [1b.b] "+this.getNumberOfObjectsForRow(i)+"        [1b.e] "+this.getFirstFilledTileForRow(i)+"\n");
			}
		}

		int numberOfObjectsOnBottomHalf = this.getNumberOfObjectsOnBottomHalf();
		if(this.debug == false)
			System.out.println("[1b.a] Number of objects on bottom half: "+numberOfObjectsOnBottomHalf);
		
		// 1b.b
		if(this.debug)
			this.getNumberOfObjectsPerRow();
		
		int numberOfMultiTiles = this.getNumberOfMultiTiles();
		if(this.debug == false)
			System.out.println("[1b.c] Number of tiles with more than 1 object: "+numberOfMultiTiles);
		
		int numberOfEmptyTiles = this.getNumberOfEmptyTiles();
		if(this.debug == false)
			System.out.println("[1b.d] Number of empty tiles: "+numberOfEmptyTiles);
		
		// 1b.e
		if(this.debug)
			this.getFirstFilledTilePerRow();
		
		// 1b.f
		int longestSequence = this.findLongestSequence();
		if(this.debug == false)
			System.out.println("[1b.f] Longest connecting sequence: "+longestSequence);
	}
	
	public boolean hasMultiTile()
	{
		for(int i = 0; i < this.getWorldWidth(); i++)
		{
			for(int j = 0; j < this.getWorldHeight(); j++)
			{
				if(this.grid[i][j] > 1)
					return true;
			}
		}
		return false;
	}
	
	// Opdracht 1b.a
	public int getNumberOfObjectsOnBottomHalf()
	{
		long start = System.currentTimeMillis();
		
		int numberOfObjects = 0;
		int halfRows = this.getWorldHeight() / 2;
		
		for(int i = halfRows; i < this.getWorldHeight(); i++)
		{
			numberOfObjects += getNumberOfObjectsForRow(i);
		}
		
		long end = System.currentTimeMillis();
		if(this.debug)
			System.out.println("Runtime [1b.a]: " + (end - start) + " ms");
		
		return numberOfObjects;
	}
	
	// Opdracht 1b.b
	public int[] getNumberOfObjectsPerRow()
	{
		long start = System.currentTimeMillis();
		
		int[] rows = new int[this.getWorldHeight()];
		for(int i = 0; i < this.getWorldHeight(); i++)
			rows[i] = this.getNumberOfObjectsForRow(i);
		
		long end = System.currentTimeMillis();
		if(this.debug)
			System.out.println("Runtime [1b.b]: " + (end - start) + " ms");
		
		return rows;
	}
	public int getNumberOfObjectsForRow(int x)
	{
		int number = 0;
		for(int j = 0; j < this.getWorldHeight(); j++)
		{
			number += this.grid[x][j];
		}

		return number;
	}
	
	// Opdracht 1b.c
	public int getNumberOfMultiTiles()
	{
		long start = System.currentTimeMillis();
		
		int numberOfMultiTiles = 0;
		for(int i = 0; i < this.getWorldWidth(); i++)
		{
			for(int j = 0; j < this.getWorldHeight(); j++)
			{
				if(this.grid[i][j] > 1)
					numberOfMultiTiles++;
			}
		}
		
		long end = System.currentTimeMillis();
		if(this.debug)
			System.out.println("Runtime [1b.c]: " + (end - start) + " ms");
		
		return numberOfMultiTiles;
	}
	
	// Opdracht 1b.d
	public int getNumberOfEmptyTiles()
	{
		long start = System.currentTimeMillis();
		
		int numberOfEmptyTiles = 0;
		for(int i = 0; i < this.getWorldWidth(); i++)
		{
			for(int j = 0; j < this.getWorldHeight(); j++)
			{
				if(this.grid[i][j] == 0)
					numberOfEmptyTiles++;
			}
		}
		
		long end = System.currentTimeMillis();
		if(this.debug)
			System.out.println("Runtime [1b.d]: " + (end - start) + " ms");
		
		return numberOfEmptyTiles;
	}

	// Opdracht 1b.e
	public int[] getFirstFilledTilePerRow()
	{
		long start = System.currentTimeMillis();
		
		int[] rows = new int[this.getWorldHeight()];
		for(int i = 0; i < this.getWorldHeight(); i++)
		{
			rows[i] = this.getFirstFilledTileForRow(i);
		}
		
		long end = System.currentTimeMillis();
		if(this.debug)
			System.out.println("Runtime [1b.e]: " + (end - start) + " ms");
		
		return rows;
	}
	public int getFirstFilledTileForRow(int x)
	{
		int tile = 0;
		for(int j = 0; j < this.getWorldWidth(); j++)
		{
			if(this.grid[x][j] > 0)
			{
				tile = (j+1);
				break;
			}
		}
		return tile;
	}
	
	// Opdracht 1b.f (cookies!)
	public int findLongestSequence()
	{
		long start = System.currentTimeMillis();
		
		int longestSequence = 0;
		
		for(int i = 0; i < this.getWorldWidth(); i++)
		{
			int rowSequence = 0;
			for(int j = 0; j < this.getWorldHeight(); j++)
			{
				if(this.grid[i][j] > 0)
				{
					rowSequence++;
				}
				else
				{
					if(rowSequence > longestSequence)
						longestSequence = rowSequence;
					
					rowSequence = 0;
				}
			}
		}
		
		long end = System.currentTimeMillis();
		if(this.debug)
			System.out.println("Runtime [1b.f]: " + (end - start) + " ms");
		
		return longestSequence;
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
