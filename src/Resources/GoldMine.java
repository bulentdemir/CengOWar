package Resources;

import java.util.Random;

import Auxiliary.Coordinate;

public class GoldMine{

	protected Coordinate coordinate;
	protected int quantity;
	protected int currentQuantity;
	
	public GoldMine(Coordinate coord)
	{
		Random rand = new Random();
		this.quantity = ((rand.nextInt((1000 - 500) + 1) + 500) / 100) * 100;
		this.coordinate = coord;
		this.currentQuantity = quantity;
	}

	public Coordinate GetCoordinate() {
		return coordinate;
	}

	public int GetQuantity() {
		return quantity;
	}

	public int GetCurrentQuantity() {
		return currentQuantity;
	}

	public void SetCurrentQuantity(int currentQuantity) {
		this.currentQuantity = currentQuantity;
	}
	
}
