package Buildings;

import Auxiliary.Coordinate;
import javafx.scene.paint.Color;

public class Base extends Building{

	public Base(char owner, Coordinate coordinate)
	{
		this.owner = owner;
		DP = 1000;
		currentDP = DP;
		this.coordinate = coordinate;
		if(owner == 'P')
		{
			name = "[P]";
		}
		else if(owner == 'C')
		{
			name = "[C]";
		}
		
		if(owner == 'P')
	    {
	    	border.setStroke(Color.DARKBLUE);	    	
	    }
	    else
	    {
	    	border.setStroke(Color.DARKRED);
	    }
        border.setFill(null);
		border.setStrokeWidth(2);
        lblOwner.setText(""+this.owner);
        getChildren().addAll(border, lblOwner);
	}
	
}
