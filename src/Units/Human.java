package Units;

import Auxiliary.Coordinate;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

public abstract class Human extends StackPane{
	
	protected Rectangle border = new Rectangle(35,35);
    protected Label lblID = new Label();
    protected String ID;
	protected String type;
	protected int life;
	protected int currentLife;
	protected int cost;
	protected Coordinate coordinate;
	protected int moveSpeed;
	protected char direction;
	protected int stamina;
	protected int currentStamina;
	protected char owner;
	
	public char GetOwner() {
		return owner;
	}

	public int GetCurrentStamina() {
		return currentStamina;
	}

	public void SetCurrentStamina(int currentStamina) {
		this.currentStamina = currentStamina;
	}

	public int GetStamina() {
		return stamina;
	}

	public int GetMoveSpeed() {
		return moveSpeed;
	}
	
	public String GetID() {
		return ID;
	}
	
	public String GetType() {
		return type;
	}
	
	public int GetLife() {
		return life;
	}
	
	public int GetCurrentLife() {
		return currentLife;
	}
	
	public void SetCurrentLife(int currentLife) {
		this.currentLife = currentLife;
	}
	
	public int GetCost() {
		return cost;
	}
	
	public Coordinate GetCoordinate() {
		return coordinate;
	}
	
	public void SetCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}
	
	public char GetDirection() {
		return direction;
	}

	public void SetDirection(char direction) {
		this.direction = direction;
	}

	public abstract Object[][] Move(Object[][] map, String command);
}