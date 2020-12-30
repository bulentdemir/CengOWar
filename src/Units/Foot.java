package Units;

import Auxiliary.Coordinate;
import Buildings.Building;
import javafx.scene.paint.Color;

public class Foot extends Human implements ISoldier {

	protected final String TYPE = "FOOT";
	protected int attackSpeed;
	protected int attackDamage;
	
	public Foot(int ID, Coordinate coord, char owner)
	{
		this.ID = "F" + ID;
		life = 200;
		currentLife = life;
		type = TYPE;
		moveSpeed = 1;
		cost = 100;
		direction = ' ';	    
	    coordinate = coord;
	    attackDamage = 100;
	    attackSpeed = 1;
	    stamina = 2;
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
	
	public int GetAttackSpeed() {
		return attackSpeed;
	}
	
	public int GetAttackDamage() {
		return attackDamage;
	}
	
	public Object[][] Attack(Object[][] map)
	{
		if(currentStamina > 0)
		{
			int cLife = 0;
			int x = coordinate.GetX();
			int y = coordinate.GetY();
			if(direction == 'u')
			{
				if(x - 1 >= 0 && (map[x - 1][y] instanceof Human || map[x - 1][y] instanceof Building));
				{
					if(map[x - 1][y] instanceof Human && ((Human)map[x - 1][y]).GetOwner() != owner)
					{
						cLife = ((Human)map[x - 1][y]).GetCurrentLife();
						if(cLife > 0)
						{
							((Human)map[x - 1][y]).SetCurrentLife(cLife - (attackSpeed * attackDamage));
							SetCurrentStamina(GetCurrentStamina() - 1);
							cLife = ((Human)map[x - 1][y]).GetCurrentLife();
							if(cLife <= 0)
							{
								map[x - 1][y] = null;
							}
						}
					}
					else if(map[x - 1][y] instanceof Building && ((Building)map[x - 1][y]).GetOwner() != owner)
					{
						cLife = ((Building)map[x - 1][y]).GetCurrentDP();
						if(cLife > 0)
						{
							((Building)map[x - 1][y]).SetCurrentDP(cLife - (attackSpeed * attackDamage));
							SetCurrentStamina(GetCurrentStamina() - 1);
							cLife = ((Building)map[x - 1][y]).GetCurrentDP();
							if(cLife <= 0)
							{
								map[x - 1][y] = null;
							}
						}
					}
				}
			}
			else if(direction == 'd')
			{
				if(x + 1 < map.length && (map[x + 1][y] instanceof Human || map[x + 1][y] instanceof Building))
				{
					if(map[x + 1][y] instanceof Human && ((Human)map[x + 1][y]).GetOwner() != owner)
					{
						cLife = ((Human)map[x + 1][y]).GetCurrentLife();
						if(cLife > 0)
						{
							((Human)map[x + 1][y]).SetCurrentLife(cLife - (attackSpeed * attackDamage));
							SetCurrentStamina(GetCurrentStamina() - 1);
							cLife = ((Human)map[x + 1][y]).GetCurrentLife();
							if(cLife <= 0)
							{
								map[x + 1][y] = null;
							}
						}
					}
					else if(map[x + 1][y] instanceof Building && ((Building)map[x + 1][y]).GetOwner() != owner)
					{
						cLife = ((Building)map[x + 1][y]).GetCurrentDP();
						if(cLife > 0)
						{
							((Building)map[x + 1][y]).SetCurrentDP(cLife - (attackSpeed * attackDamage));
							SetCurrentStamina(GetCurrentStamina() - 1);
							cLife = ((Building)map[x + 1][y]).GetCurrentDP();
							if(cLife <= 0)
							{
								map[x + 1][y] = null;
							}
						}
					}
				}
			}
			else if(direction == 'r')
			{
				if(y + 1 < map[0].length && (map[x][y + 1] instanceof Human || map[x][y + 1] instanceof Building))
				{
					if(map[x][y + 1] instanceof Human && ((Human)map[x][y + 1]).GetOwner() != owner)
					{
						cLife = ((Human)map[x][y + 1]).GetCurrentLife();
						if(cLife > 0)
						{
							((Human)map[x][y + 1]).SetCurrentLife(cLife - (attackSpeed * attackDamage));
							SetCurrentStamina(GetCurrentStamina() - 1);
							cLife = ((Human)map[x][y + 1]).GetCurrentLife();
							if(cLife <= 0)
							{
								map[x][y + 1] = null;
							}
						}
					}
					else if(map[x][y + 1] instanceof Building && ((Building)map[x][y + 1]).GetOwner() != owner)
					{
						cLife = ((Building)map[x][y + 1]).GetCurrentDP();
						if(cLife > 0)
						{
							((Building)map[x][y + 1]).SetCurrentDP(cLife - (attackSpeed * attackDamage));
							SetCurrentStamina(GetCurrentStamina() - 1);
							cLife = ((Building)map[x][y + 1]).GetCurrentDP();
							if(cLife <= 0)
							{
								map[x][y + 1] = null;
							}
						}
					}
				}
			}
			else if(direction == 'l')
			{
				if(y - 1 >= 0 && (map[x][y - 1] instanceof Human || map[x][y - 1] instanceof Building))
				{
					if(map[x][y - 1] instanceof Human && ((Human)map[x][y - 1]).GetOwner() != owner)
					{
						cLife = ((Human)map[x][y - 1]).GetCurrentLife();
						if(cLife > 0)
						{
							((Human)map[x][y - 1]).SetCurrentLife(cLife - (attackSpeed * attackDamage));
							SetCurrentStamina(GetCurrentStamina() - 1);
							cLife = ((Human)map[x][y - 1]).GetCurrentLife();
							if(cLife <= 0)
							{
								map[x][y - 1] = null;
							}
						}
					}
					else if(map[x][y - 1] instanceof Building && ((Building)map[x][y - 1]).GetOwner() != owner)
					{
						cLife = ((Building)map[x][y - 1]).GetCurrentDP();
						if(cLife > 0)
						{
							((Building)map[x][y - 1]).SetCurrentDP(cLife - (attackSpeed * attackDamage));
							SetCurrentStamina(GetCurrentStamina() - 1);
							cLife = ((Building)map[x][y - 1]).GetCurrentDP();
							if(cLife <= 0)
							{
								map[x][y - 1] = null;
							}
						}
					}
				}
			}
		}		
		return map;
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
	
}
