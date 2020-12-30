package Gamers;

import Buildings.Base;
import Units.*;

public abstract class Gamer {
	
	Villager[] villagers;
	Archer[] archers;
	Foot[] foots;
	Base base;
	int vault;
	
	public Villager[] GetVillagers() {
		return villagers;
	}
	public void SetVillagers(Villager[] villagers) {
		this.villagers = villagers;
	}
	public Archer[] GetArchers() {
		return archers;
	}
	public void SetArchers(Archer[] archers) {
		this.archers = archers;
	}
	public Foot[] GetFoots() {
		return foots;
	}
	public void SetFoots(Foot[] foots) {
		this.foots = foots;
	}
	public Base GetBase() {
		return base;
	}
	public void SetBase(Base base) {
		this.base = base;
	}
	
	public int GetVault() {
		return vault;
	}
	
	public void SetVault(int vault) {
		this.vault = vault;
	}
	
	public abstract Object[][] InitializeGamer(Object[][] map);
}
