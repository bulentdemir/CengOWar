package Units;

import Auxiliary.Coordinate;
import Buildings.Building;
import javafx.scene.paint.Color;

public class Archer extends Human implements ISoldier {

	protected final String TYPE = "ARCHER";
	protected int attackSpeed;
	protected int attackDamage;
	protected int range;
	
	public Archer(int ID, Coordinate coord, char owner)
	{
		this.ID = "A" + ID;
		life = 150;
		currentLife = life;
		type = TYPE;
		moveSpeed = 1;
		cost = 150;
		direction = ' ';	    
	    coordinate = coord;
	    attackDamage = 50;
	    attackSpeed = 1;
	    range = 3;
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

	public int GetRange() {
		return range;
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
	
	public Object[][] Attack(Object[][] map)
	{
		if(currentStamina > 0)
		{
			int cLife;
			int x = coordinate.GetX();
			int y = coordinate.GetY();
			if(direction == 'u')
			{
				for(int i = 1; i <= range; i++)
				{
					if(x - i >= 0 && (map[x - i][y] instanceof Human || map[x - i][y] instanceof Building));
					{
						if(map[x - i][y] instanceof Human && ((Human)map[x - i][y]).GetOwner() != owner)
						{
							cLife = ((Human)map[x - i][y]).GetCurrentLife();
							if(cLife > 0)
							{
								((Human)map[x - i][y]).SetCurrentLife(cLife - (attackSpeed * attackDamage));
								SetCurrentStamina(GetCurrentStamina() - 1);
								cLife = ((Human)map[x - i][y]).GetCurrentLife();
								if(cLife <= 0)
								{
									map[x - i][y] = null;
								}
							}
							break;
						}
						else if(map[x - i][y] instanceof Building && ((Building)map[x - i][y]).GetOwner() != owner)
						{
							cLife = ((Building)map[x - i][y]).GetCurrentDP();
							if(cLife > 0)
							{
								((Building)map[x - i][y]).SetCurrentDP(cLife - (attackSpeed * attackDamage));
								SetCurrentStamina(GetCurrentStamina() - 1);
								cLife = ((Building)map[x - i][y]).GetCurrentDP();
								if(cLife <= 0)
								{
									map[x - i][y] = null;
								}
							}
							break;
						}
					}
				}
			}
			else if(direction == 'd')
			{
				for(int i = 1; i <= range; i++)
				{
					if(x + i < map.length && (map[x + i][y] instanceof Human || map[x + i][y] instanceof Building))
					{
						if(map[x + i][y] instanceof Human && ((Human)map[x + i][y]).GetOwner() != owner)
						{
							cLife = ((Human)map[x + i][y]).GetCurrentLife();
							if(cLife > 0)
							{
								((Human)map[x + i][y]).SetCurrentLife(cLife - (attackSpeed * attackDamage));
								SetCurrentStamina(GetCurrentStamina() - 1);
								cLife = ((Human)map[x + i][y]).GetCurrentLife();
								if(cLife <= 0)
								{
									map[x + i][y] = null;
								}
							}
							break;
						}
						else if(map[x + i][y] instanceof Building && ((Building)map[x + i][y]).GetOwner() != owner)
						{
							cLife = ((Building)map[x + i][y]).GetCurrentDP();
							if(cLife > 0)
							{
								((Building)map[x + i][y]).SetCurrentDP(cLife - (attackSpeed * attackDamage));
								SetCurrentStamina(GetCurrentStamina() - 1);
								cLife = ((Building)map[x + i][y]).GetCurrentDP();
								if(cLife <= 0)
								{
									map[x + i][y] = null;
								}
							}
							break;
						}
					}
				}
			}
			else if(direction == 'r')
			{
				for(int i = 1; i <= range; i++)
				{
					if(y + i < map[0].length && (map[x][y + i] instanceof Human || map[x][y + i] instanceof Building))
					{
						if(map[x][y + i] instanceof Human && ((Human)map[x][y + i]).GetOwner() != owner)
						{
							cLife = ((Human)map[x][y + i]).GetCurrentLife();
							if(cLife > 0)
							{
								((Human)map[x][y + i]).SetCurrentLife(cLife - (attackSpeed * attackDamage));
								SetCurrentStamina(GetCurrentStamina() - 1);
								cLife = ((Human)map[x][y + i]).GetCurrentLife();
								if(cLife <= 0)
								{
									map[x][y + i] = null;
								}
							}
							break;
						}
						else if(map[x][y + i] instanceof Building && ((Building)map[x][y + i]).GetOwner() != owner)
						{
							cLife = ((Building)map[x][y + i]).GetCurrentDP();
							if(cLife > 0)
							{
								((Building)map[x][y + i]).SetCurrentDP(cLife - (attackSpeed * attackDamage));
								SetCurrentStamina(GetCurrentStamina() - 1);
								cLife = ((Building)map[x][y + i]).GetCurrentDP();
								if(cLife <= 0)
								{
									map[x][y + i] = null;
								}
							}
							break;
						}
					}
				}
			}
			else if(direction == 'l')
			{
				for(int i = 1; i <= range; i++)
				{
					if(y - i >= 0 && (map[x][y - i] instanceof Human || map[x][y - i] instanceof Building))
					{
						if(map[x][y - i] instanceof Human && ((Human)map[x][y - i]).GetOwner() != owner)
						{
							cLife = ((Human)map[x][y - i]).GetCurrentLife();
							if(cLife > 0)
							{
								((Human)map[x][y - i]).SetCurrentLife(cLife - (attackSpeed * attackDamage));
								SetCurrentStamina(GetCurrentStamina() - 1);
								cLife = ((Human)map[x][y - i]).GetCurrentLife();
								if(cLife <= 0)
								{
									map[x][y - i] = null;
								}
							}
							break;
						}
						else if(map[x][y - i] instanceof Building && ((Building)map[x][y - i]).GetOwner() != owner)
						{
							cLife = ((Building)map[x][y - i]).GetCurrentDP();
							if(cLife > 0)
							{
								((Building)map[x][y - i]).SetCurrentDP(cLife - (attackSpeed * attackDamage));
								SetCurrentStamina(GetCurrentStamina() - 1);
								cLife = ((Building)map[x][y - i]).GetCurrentDP();
								if(cLife <= 0)
								{
									map[x][y - i] = null;
								}
							}
							break;
						}
					}
				}
			}
		}		
		return map;
	}
	
}
