package Gamers;

import java.util.Random;

import Auxiliary.Coordinate;
import Buildings.Base;
import Units.*;

public class Player extends Gamer {

	protected final int INIT_POP = 3;
	
	public Player()
	{
		villagers = new Villager[4];
		foots = new Foot[4];
		archers = new Archer[4];
		vault = 100;
		base = new Base('P', new Coordinate(0, 0));		
	}
	
	public Object[][] InitializeGamer(Object[][] map)
	{
		int baseX, baseY, x, y, i = 0;
		double chance;
		double distance;
		do
		{
			baseX = (int)(Math.random() * 10);
			baseY = (int)(Math.random() * 10);
		}while(map[baseX][baseY] != null);
		
		base.SetCoordinate(new Coordinate(baseX, baseY));
		map[baseX][baseY] = base;
		
		x = baseX;
		y = baseY;
		
		while(i < INIT_POP)
		{
			if(x < map.length && y < map[0].length)
			{
				distance = Math.sqrt(Math.pow((baseX - x), 2) + Math.pow((baseY - y), 2));
				if(distance < 3)
				{
					if(map[x][y] == null)
					{
						if(i == 0)
						{
							villagers[0] = new Villager(1, new Coordinate(x, y), 'P');
							map[x][y] = villagers[0]; 
						}
						else if(i == 1)
						{
							foots[0] = new Foot(1, new Coordinate(x, y), 'P');
							map[x][y] = foots[0]; 
						}
						else if(i == 2)
						{
							archers[0] = new Archer(1, new Coordinate(x, y), 'P');
							map[x][y] = archers[0]; 
						}
						i++;
					}
				}
			}
			chance = Math.random();
			if (chance < 0.5)
			{
				x++;
			}
			else
			{
				y++;
			}
		}

		return map;
	}
	
	public void CreateVillager(Object map[][])
	{
		if(vault >= GetVillagers()[0].GetCost())
		{
			int i = 0;
			int index = FindEmptySlot(villagers);
			
			Random rand = new Random();
			
			if(index != -1)
			{
				while(i == 0)
				{
					int x = rand.nextInt(map.length - 0) + 0;
					int y = rand.nextInt(map[0].length - 0) + 0;
					
					double distance = Math.sqrt(Math.pow(x - base.GetCoordinate().GetX(), 2) + Math.pow(y - base.GetCoordinate().GetY(), 2));
					
					if (distance <= 2 && map[x][y] == null)
					{
						villagers[index] = new Villager(index + 1, new Coordinate(x,y), 'P');
						map[x][y] = villagers[index];
						SetVault(GetVault() - GetVillagers()[0].GetCost());
						i++;
					}
				}			
			}
		}
		
	}
	
	public void CreateFoot(Object map[][])
	{
		if(vault >= GetFoots()[0].GetCost())
		{
			int i = 0;
			int index = FindEmptySlot(foots);
			
			Random rand = new Random();
			
			if(index != -1)
			{
				while(i == 0)
				{
					int x = rand.nextInt(map.length - 0) + 0;
					int y = rand.nextInt(map[0].length - 0) + 0;
					
					double distance = Math.sqrt(Math.pow(x - base.GetCoordinate().GetX(), 2) + Math.pow(y - base.GetCoordinate().GetY(), 2));
					
					if (distance <= 2 && map[x][y] == null)
					{
						foots[index] = new Foot(index + 1, new Coordinate(x,y), 'P');
						map[x][y] = foots[index];
						SetVault(GetVault() - GetFoots()[0].GetCost());
						i++;
					}
				}			
			}
		}
		
	}
	
	public void CreateArcher(Object map[][])
	{
		if(vault >= GetArchers()[0].GetCost())
		{
			int i = 0;
			int index = FindEmptySlot(archers);
			
			Random rand = new Random();
			
			if(index != -1)
			{
				while(i == 0)
				{
					int x = rand.nextInt(map.length - 0) + 0;
					int y = rand.nextInt(map[0].length - 0) + 0;
					
					double distance = Math.sqrt(Math.pow(x - base.GetCoordinate().GetX(), 2) + Math.pow(y - base.GetCoordinate().GetY(), 2));
					
					if (distance <= 2 && map[x][y] == null)
					{
						archers[index] = new Archer(index + 1, new Coordinate(x,y), 'P');
						map[x][y] = archers[index];
						SetVault(GetVault() - GetArchers()[0].GetCost());
						i++;
					}
				}
			}
		}
		
	}
	
	public int FindUnit(Human[] units, String ID)
	{
		int i = 0;
		for(; i < units.length; i++)
		{
			if(units[i].GetID().equals(ID)) {
				return i;
			}
		}
		return -1;
	}
	
	public int FindEmptySlot(Human[] units)
	{
		int i = 0;
		for(; i < units.length; i++)
		{
			if(units[i] == null)
			{
				return i;
			}
		}
		return -1;
	}

	public int GetInitPop()
	{
		return INIT_POP;
	}
	
}
