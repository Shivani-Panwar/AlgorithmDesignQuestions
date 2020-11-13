package socratesCow;

import java.util.HashMap;
import java.util.Map;

public class Meadow {
	private  String name;
	private  int minTime;
	private Boolean hasCow;
	private  Map<Meadow,Integer> neighborMeadow; //To show neighbor meadow and the time to react there
	
	public Meadow(String name, int minTime) {
		this.name=name;
		this.minTime=minTime;
		this.neighborMeadow = new HashMap<>();
		this.hasCow = name.charAt(0) >= 'A' && name.charAt(0) <= 'Z';
	}

	public String getName() {
		return name;
	}

	public Boolean hasCow() {
		return hasCow;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMinTime() {
		return minTime;
	}

	public void setMinTime(int minTime) {
		this.minTime = minTime;
	}

	public Map<Meadow, Integer> getNeighborMeadow() {
		return neighborMeadow;
	}

	public void setNeighborMeadow(Map<Meadow, Integer> neighborMeadow) {
		this.neighborMeadow = neighborMeadow;
	}
}
