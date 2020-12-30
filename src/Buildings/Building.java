package Buildings;

import Auxiliary.Coordinate;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

public abstract class Building extends StackPane{

	protected Rectangle border = new Rectangle(35,35);
	protected Label lblOwner = new Label();
			
	protected int DP;
	protected int currentDP;
	protected String name;
	protected char owner;
	protected Coordinate coordinate;
	
	public int GetCurrentDP() {
		return currentDP;
	}
	
	public void SetCurrentDP(int currentDP) {	//Maybe villagers (constructors (need interface)) can repair. Repair point.
		if(currentDP > DP)
		{
			this.currentDP = DP;
		}
		else
		{
			this.currentDP = currentDP;
		}
	}
	
	public int GSetDP() {
		return DP;
	}
	
	public char GetOwner() {
		return owner;
	}

	public Coordinate GetCoordinate() {
		return coordinate;
	}

	public void SetCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public String GetName() {
		return name;
	}
	
	
	
	
	
}
