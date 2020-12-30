package application;
	
import java.util.Random;

import Auxiliary.Coordinate;
import Buildings.Building;
import Gamers.Computer;
import Gamers.Gamer;
import Gamers.Player;
import Resources.GoldMine;
import Units.Human;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Test extends Application {
	
	Scene scene;
	Object[][] map = new Object[20][20];
	Player player = new Player();
	Computer computer = new Computer(1);
	
	private GridPane CenterScreen()
	{
		GridPane centerScreen = new GridPane();
		centerScreen.setPrefSize(740, 740);
		centerScreen.setAlignment(Pos.TOP_CENTER);
		centerScreen.setPadding(new Insets(10));
		centerScreen.setHgap(1);
		centerScreen.setVgap(1);
				
		for(int i = 0; i < map.length; i++)
		{
			for(int j = 0; j < map[0].length; j++)
			{
				if(map[i][j] == null)
				{
					Rectangle border = new Rectangle(35,35);
					border.setStroke(Color.BLACK);
					border.setStrokeWidth(2);
					border.setFill(null);
					centerScreen.add(border, j , i);					
				}
				else if(map[i][j] instanceof GoldMine)
				{
					StackPane goldMine = new StackPane();
					Rectangle border = new Rectangle(35,35);
					Label lblQuantity = new Label();
					border.setStroke(Color.GOLD);
					border.setStrokeWidth(2);
			        border.setFill(null);
			        lblQuantity.setText(""+((GoldMine)map[i][j]).GetCurrentQuantity());
			        goldMine.getChildren().addAll(border, lblQuantity);
			        centerScreen.add(goldMine, j,i);
				}
				else if(map[i][j] instanceof Human)
				{	
					centerScreen.add((Human)map[i][j], j, i);					
				}
				else if(map[i][j] instanceof Building)
				{	
					centerScreen.add((Building)map[i][j], j, i);					
				}
			}
		}
		
		return centerScreen;
	}
	
	private HBox TopScreen()
	{
		HBox topScreen = new HBox();
		topScreen.setPadding(new Insets(10, 10, 10, 10));
		topScreen.setSpacing(10);
		
		Label lblPlayer = new Label("Player's: ");
		lblPlayer.setStyle("-fx-font-weight: bold");
		Label lblVault = new Label("Vault = " + player.GetVault());
		Label lblBase = new Label("Base = " + player.GetBase().GetCurrentDP());
		
		Label lblComputer = new Label("Computer's: ");
		lblComputer.setStyle("-fx-font-weight: bold");
		Label lblCVault = new Label("Vault = " + computer.GetVault());
		Label lblCBase = new Label("Base = " + computer.GetBase().GetCurrentDP());
		
		topScreen.getChildren().addAll(lblPlayer, lblVault, lblBase, lblComputer, lblCVault, lblCBase);
		return topScreen;
	}
	
	private VBox RightScreen()
	{
		VBox rightScreen = new VBox();
		rightScreen.setPadding(new Insets(10));
		rightScreen.setStyle("-fx-border-color: black;");
		rightScreen.setSpacing(10);
		rightScreen.setAlignment(Pos.TOP_CENTER);
		ComboBox<String> cbHumans = new ComboBox<String>();
		cbHumans.setMaxSize(170, 30);
		cbHumans.setMinSize(170, 30);
		cbHumans.setPromptText("Select unit");
		
		for(int i = 0; i < player.GetVillagers().length; i++)
		{
			if(player.GetVillagers()[i] != null)
			{
				cbHumans.getItems().add(player.GetVillagers()[i].GetID());
			}
			if(player.GetFoots()[i] != null)
			{
				cbHumans.getItems().add(player.GetFoots()[i].GetID());
			}
			if(player.GetArchers()[i] != null)
			{
				cbHumans.getItems().add(player.GetArchers()[i].GetID());
			}
		}

		GridPane direction = new GridPane();
		direction.setHgap(3);
		direction.setVgap(3);
		
		Image imgUp= new Image(getClass().getResourceAsStream("icons/up.png"));
		Image imgDown = new Image(getClass().getResourceAsStream("icons/down.png"));
		Image imgLeft = new Image(getClass().getResourceAsStream("icons/left.png"));
		Image imgRight = new Image(getClass().getResourceAsStream("icons/right.png"));
		Image imgAction = new Image(getClass().getResourceAsStream("icons/action.png"));
		
		Button btnUp = new Button("", new ImageView(imgUp));
		btnUp.setMinSize(52, 52);
		btnUp.setMaxSize(52, 52);
		direction.add(btnUp, 1, 1);
		
		btnUp.setOnAction(e -> 
		{
			if(cbHumans.getValue() != null)
			{
				String ID = cbHumans.getValue();
				if(ID.substring(0, 1).equals("V"))
				{
					int index = player.FindUnit(player.GetVillagers(), ID);
					map = player.GetVillagers()[index].Move(map, "up");
				}
				else if(ID.substring(0, 1).equals("F"))
				{
					int index = player.FindUnit(player.GetFoots(), ID);
					map = player.GetFoots()[index].Move(map, "up");
				}
				else if(ID.substring(0, 1).equals("A"))
				{
					int index = player.FindUnit(player.GetArchers(), ID);
					map = player.GetArchers()[index].Move(map, "up");
				}
			}
			scene.setRoot(CreateContent());
		});
		
		Button btnDown = new Button("", new ImageView(imgDown));
		btnDown.setMinSize(52, 52);
		btnDown.setMaxSize(52, 52);
		direction.add(btnDown, 1, 3);
		
		btnDown.setOnAction(e ->
		{
			if(cbHumans.getValue() != null)
			{
				String ID = cbHumans.getValue();
				if(ID.substring(0, 1).equals("V"))
				{
					int index = player.FindUnit(player.GetVillagers(), ID);
					map = player.GetVillagers()[index].Move(map, "down");
				}
				else if(ID.substring(0, 1).equals("F"))
				{
					int index = player.FindUnit(player.GetFoots(), ID);
					map = player.GetFoots()[index].Move(map, "down");
				}
				else if(ID.substring(0, 1).equals("A"))
				{
					int index = player.FindUnit(player.GetArchers(), ID);
					map = player.GetArchers()[index].Move(map, "down");
				}
			}
			scene.setRoot(CreateContent());
		});
		
		Button btnLeft = new Button("", new ImageView(imgLeft));
		btnLeft.setMinSize(52, 52);
		btnLeft.setMaxSize(52, 52);
		direction.add(btnLeft, 0, 2);
		
		btnLeft.setOnAction(e ->
		{
			if(cbHumans.getValue() != null)
			{
				String ID = cbHumans.getValue();
				if(ID.substring(0, 1).equals("V"))
				{
					int index = player.FindUnit(player.GetVillagers(), ID);
					map = player.GetVillagers()[index].Move(map, "left");
				}
				else if(ID.substring(0, 1).equals("F"))
				{
					int index = player.FindUnit(player.GetFoots(), ID);
					map = player.GetFoots()[index].Move(map, "left");
				}
				else if(ID.substring(0, 1).equals("A"))
				{
					int index = player.FindUnit(player.GetArchers(), ID);
					map = player.GetArchers()[index].Move(map, "left");
				}
			}
			scene.setRoot(CreateContent());
		});
		
		Button btnRight = new Button("", new ImageView(imgRight));
		btnRight.setMinSize(52, 52);
		btnRight.setMaxSize(52, 52);
		direction.add(btnRight, 2, 2);
		
		btnRight.setOnAction(e ->
		{
			if(cbHumans.getValue() != null)
			{
				String ID = cbHumans.getValue();
				if(ID.substring(0, 1).equals("V"))
				{
					int index = player.FindUnit(player.GetVillagers(), ID);
					map = player.GetVillagers()[index].Move(map, "right");
				}
				else if(ID.substring(0, 1).equals("F"))
				{
					int index = player.FindUnit(player.GetFoots(), ID);
					map = player.GetFoots()[index].Move(map, "right");
				}
				else if(ID.substring(0, 1).equals("A"))
				{
					int index = player.FindUnit(player.GetArchers(), ID);
					map = player.GetArchers()[index].Move(map, "right");
				}
			}
			scene.setRoot(CreateContent());
		});
		
		Button btnAction = new Button("", new ImageView(imgAction));
		btnAction.setMinSize(52, 52);
		btnAction.setMaxSize(52, 52);
		direction.add(btnAction, 1, 2);
		
		btnAction.setOnAction(e ->
		{
			if(cbHumans.getValue() != null)
			{
				String ID = cbHumans.getValue();
				if(ID.substring(0, 1).equals("V"))
				{
					int index = player.FindUnit(player.GetVillagers(), ID);
					map = player.GetVillagers()[index].Collect(map, player);
				}
				else if(ID.substring(0, 1).equals("F"))
				{
					int index = player.FindUnit(player.GetFoots(), ID);
					map = player.GetFoots()[index].Attack(map);
				}
				else if(ID.substring(0, 1).equals("A"))
				{
					int index = player.FindUnit(player.GetArchers(), ID);
					map = player.GetArchers()[index].Attack(map);
				}
			}
			scene.setRoot(CreateContent());
		});
		
		Button btnEndTurn = new Button("End Turn");
		
		btnEndTurn.setOnAction(e -> 
		{
			
			ResetStamina(player);
			computer.RandomCommand(map);
			ResetStamina(computer);
			scene.setRoot(CreateContent());
		});
		
		rightScreen.getChildren().addAll(cbHumans, direction, CreateUnit(), btnEndTurn, ShowInfo());
		
		return rightScreen;
	}
	
	private GridPane CreateUnit()
	{
		GridPane createUnit = new GridPane();
		createUnit.setAlignment(Pos.CENTER_LEFT);
		createUnit.setPadding(new Insets(10));
		createUnit.setHgap(50);
		createUnit.setVgap(10);
		
		Label lblVillager = new Label("Villager = ");
		Button btnVilPlus = new Button("+");
		
		btnVilPlus.setOnAction(e -> {
			player.CreateVillager(map);
			scene.setRoot(CreateContent());
		});
		
		Label lblFoot = new Label("Foot      = ");
		Button btnFooPlus = new Button("+");
		
		btnFooPlus.setOnAction(e ->
		{
			player.CreateFoot(map);
			scene.setRoot(CreateContent());
		});
		
		Label lblArcher = new Label("Archer   = ");
		Button btnArcPlus = new Button("+");
		
		btnArcPlus.setOnAction(e ->
		{
			player.CreateArcher(map);
			scene.setRoot(CreateContent());
		});
		
		createUnit.add(lblVillager, 0,0);
		createUnit.add(btnVilPlus, 1,0);
		createUnit.add(lblFoot, 0,1);
		createUnit.add(btnFooPlus, 1,1);
		createUnit.add(lblArcher, 0,2);
		createUnit.add(btnArcPlus, 1,2);
				
		return createUnit;
	}
	
	private VBox ShowInfo()
	{
		VBox showInfo = new VBox();
		showInfo.setAlignment(Pos.CENTER_LEFT);
		showInfo.setPadding(new Insets(10));
		
		Label lblInfoName = new Label("Player Information");
		lblInfoName.setStyle("-fx-font-weight: bold");
		showInfo.getChildren().add(lblInfoName);
		
		Label[] lblVilInfo = new Label[4];
		Label[] lblFooInfo = new Label[4];
		Label[] lblArcInfo = new Label[4];
		
		for(int i = 0; i < player.GetVillagers().length; i++)
		{
			if(player.GetVillagers()[i] != null)
			{
				lblVilInfo[i] = new Label(player.GetVillagers()[i].GetID()+"'s Life: "+
			player.GetVillagers()[i].GetCurrentLife()+" Stamina: "+player.GetVillagers()[i].GetCurrentStamina());
				showInfo.getChildren().add(lblVilInfo[i]);
			}
			if(player.GetFoots()[i] != null)
			{
				lblFooInfo[i] = new Label(player.GetFoots()[i].GetID()+"'s Life: "+
			player.GetFoots()[i].GetCurrentLife()+" Stamina: "+player.GetFoots()[i].GetCurrentStamina());
				showInfo.getChildren().add(lblFooInfo[i]);
			}
			if(player.GetArchers()[i] != null)
			{
				lblArcInfo[i] = new Label(player.GetArchers()[i].GetID()+"'s Life: "+
			player.GetArchers()[i].GetCurrentLife()+" Stamina: "+player.GetArchers()[i].GetCurrentStamina());
				showInfo.getChildren().add(lblArcInfo[i]);
			}
		}
		
		Label lblInfoName2 = new Label("Computer Information");
		lblInfoName2.setStyle("-fx-font-weight: bold");
		showInfo.getChildren().add(lblInfoName2);
		
		Label[] lblCVilInfo = new Label[4];
		Label[] lblCFooInfo = new Label[4];
		Label[] lblCArcInfo = new Label[4];
		
		for(int i = 0; i < computer.GetVillagers().length; i++)
		{
			if(computer.GetVillagers()[i] != null)
			{
				lblCVilInfo[i] = new Label(computer.GetVillagers()[i].GetID()+"'s Life: "+
						computer.GetVillagers()[i].GetCurrentLife()+" Stamina: "+computer.GetVillagers()[i].GetCurrentStamina());
				showInfo.getChildren().add(lblCVilInfo[i]);
			}
			if(computer.GetFoots()[i] != null)
			{
				lblCFooInfo[i] = new Label(computer.GetFoots()[i].GetID()+"'s Life: "+
						computer.GetFoots()[i].GetCurrentLife()+" Stamina: "+computer.GetFoots()[i].GetCurrentStamina());
				showInfo.getChildren().add(lblCFooInfo[i]);
			}
			if(computer.GetArchers()[i] != null)
			{
				lblCArcInfo[i] = new Label(computer.GetArchers()[i].GetID()+"'s Life: "+
						computer.GetArchers()[i].GetCurrentLife()+" Stamina: "+computer.GetArchers()[i].GetCurrentStamina());
				showInfo.getChildren().add(lblCArcInfo[i]);
			}
		}
		
		return showInfo;
	}
	
	private HBox BottomScreen()
	{
		HBox bottomScreen = new HBox();
		bottomScreen.setPadding(new Insets(10, 10, 10, 10));
		bottomScreen.setSpacing(10);
		bottomScreen.setStyle("-fx-border-color: black;");
		
		Label lblAuthor = new Label("Copyright © 2018. All rights belong to the makers of Ceng - O - War.");
		
		
		bottomScreen.getChildren().addAll(lblAuthor);
		
		return bottomScreen;
	}
	
	private Parent CreateContent()
	{
		BorderPane mainScreen = new BorderPane();
		mainScreen.setPrefSize(1000, 800);
		
		mainScreen.setTop(TopScreen());
		mainScreen.setRight(RightScreen());
		mainScreen.setCenter(CenterScreen());
		mainScreen.setBottom(BottomScreen());
		return mainScreen;
	}
	
	public void start(Stage stage) throws Exception {
		
		Initialize();
		scene = new Scene(CreateContent());
		
		
		stage.setTitle("Ceng - O - War");
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private void Initialize()
	{
		CreateMap();
		player.InitializeGamer(map);		
		computer.InitializeGamer(map);
	}
	
	private void CreateMap()
	{
		Random rand = new Random();
		int i = 0;
		
		while(i < 25)
		{
			int randomX = rand.nextInt((map.length - 0)) + 0;
		    int randomY = rand.nextInt((map[0].length - 0)) + 0;
		    if(map[randomX][randomY] == null)
		    {
		    	map[randomX][randomY] = new GoldMine(new Coordinate(randomX, randomY));
		    	i++;
		    }
		}
	}
	
	private void ResetStamina(Gamer player)
	{
		for(int i = 0; i < player.GetVillagers().length; i++)
		{
			if(player.GetVillagers()[i] != null)
			{
				player.GetVillagers()[i].SetCurrentStamina(player.GetVillagers()[i].GetStamina());
			}
			if(player.GetFoots()[i] != null)
			{
				player.GetFoots()[i].SetCurrentStamina(player.GetFoots()[i].GetStamina());
			}
			if(player.GetArchers()[i] != null)
			{
				player.GetArchers()[i].SetCurrentStamina(player.GetArchers()[i].GetStamina());
			}
		}
	}
	
}
