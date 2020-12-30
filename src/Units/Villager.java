package Units;

import Auxiliary.Coordinate;
import Gamers.Gamer;
import Resources.GoldMine;
import javafx.scene.paint.Color;

public class Villager extends Human {

	protected final String TYPE = "VILLAGER";
	protected int collectSpeed;
	
	public Villager(int ID, Coordinate coord, char owner)
	{
		this.ID = "V" + ID;
		life = 100;
		currentLife = life;
		type = TYPE;
		moveSpeed = 1;
		collectSpeed = 100;
		cost = 50;
		direction = ' ';	    
	    coordinate = coord;
	    stamina = 3;
	    currentStamina = stamina;
	    this.owner = owner;
	    
	    if(owner == 'P')
	    {
	    	border.setStroke(Color.BLUE);	    	
	    }
	    else
	    {
	    	border.setStroke(Color.RED);
	    }
        border.setFill(null);
		border.setStrokeWidth(2);
        lblID.setText(this.ID);
        getChildren().addAll(border, lblID);
	}
	
	public int GetCollectSpeed() {
		return collectSpeed;
	}

	public Object[][] Move(Object[][] map, String command)
	{
		if(currentStamina > 0)
		{
			int oldX = coordinate.GetX();
			int oldY = coordinate.GetY();
			
			if(command.equals("left") && oldY - 1 >= 0)
			{
				coordinate.SetY(this.coordinate.GetY() - 1);
				direction = 'l';					
			}
			else if(command.equals("right") && oldY + 1 < map.length)
			{
				coordinate.SetY(this.coordinate.GetY() + 1);
				direction = 'r';
			}
			else if(command.equals("up") && oldX - 1 >= 0)
			{
				coordinate.SetX(this.coordinate.GetX() - 1);
				direction = 'u';
			}
			else if (command.equals("down") && oldX + 1 < map.length)
			{
				coordinate.SetX(this.coordinate.GetX() + 1);
				direction = 'd';
			}
			
			if(map[coordinate.GetX()][coordinate.GetY()] == null)
			{
				map[this.coordinate.GetX()][this.coordinate.GetY()] = this;
				map[oldX][oldY] = null;
				SetCurrentStamina(GetCurrentStamina() - 1);
			}
			else
			{
				coordinate.SetX(oldX);
				coordinate.SetY(oldY);
			}
		}		
		return map;
	}
	
	public Object[][] Collect(Object[][] map, Gamer player)
	{
		if(currentStamina > 0)
		{
			int quantity = 0;
			int x = coordinate.GetX();
			int y = coordinate.GetY();
			if(direction == 'u')
			{
				if(x - 1 >= 0 && map[x - 1][y] instanceof GoldMine)
				{
					quantity = ((GoldMine)map[x - 1][y]).GetCurrentQuantity();
					if(quantity > 0)
					{
						((GoldMine)map[x - 1][y]).SetCurrentQuantity(quantity - collectSpeed);
						player.SetVault(player.GetVault() + collectSpeed);
						SetCurrentStamina(GetCurrentStamina() - 1);
						quantity = ((GoldMine)map[x - 1][y]).GetCurrentQuantity();
						if(quantity <= 0)
						{
							map[x - 1][y] = null;
						}
					}
				}
			}
			else if(direction == 'd')
			{
				if(x + 1 <= map.length && map[x + 1][y] instanceof GoldMine)
				{
					quantity = ((GoldMine)map[x + 1][y]).GetCurrentQuantity();
					if(quantity > 0)
					{
						((GoldMine)map[x + 1][y]).SetCurrentQuantity(quantity - collectSpeed);
						player.SetVault(player.GetVault() + collectSpeed);
						SetCurrentStamina(GetCurrentStamina() - 1);
						quantity = ((GoldMine)map[x + 1][y]).GetCurrentQuantity();
						if(quantity <= 0)
						{
							map[x + 1][y] = null;
						}
					}
				}
			}
			else if(direction == 'r')
			{
				if(y + 1 <= map[0].length && map[x][y + 1] instanceof GoldMine)
				{
					quantity = ((GoldMine)map[x][y + 1]).GetCurrentQuantity();
					if(quantity > 0)
					{
						((GoldMine)map[x][y + 1]).SetCurrentQuantity(quantity - collectSpeed);
						player.SetVault(player.GetVault() + collectSpeed);
						SetCurrentStamina(GetCurrentStamina() - 1);
						quantity = ((GoldMine)map[x][y + 1]).GetCurrentQuantity();
						if(quantity <= 0)
						{
							map[x][y + 1] = null;
						}
					}
				}
			}
			else if(direction == 'l')
			{
				if(y - 1 >= 0 && map[x][y - 1] instanceof GoldMine)
				{
					quantity = ((GoldMine)map[x][y - 1]).GetCurrentQuantity();
					if(quantity > 0)
					{
						((GoldMine)map[x][y - 1]).SetCurrentQuantity(quantity - collectSpeed);
						player.SetVault(player.GetVault() + collectSpeed);
						SetCurrentStamina(GetCurrentStamina() - 1);
						quantity = ((GoldMine)map[x][y - 1]).GetCurrentQuantity();
						if(quantity <= 0)
						{
							map[x][y - 1] = null;
						}
					}
				}
			}
		}
		return map;
	}
}
