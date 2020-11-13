package socratesCow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.plaf.synth.SynthSeparatorUI;

public class MeadowGraph {
	private Map<String, Meadow> meadows = new HashMap<>();
	
	//Add new neighbors when creating the graph
	public void makeNeighbors(Meadow sMeadow, Meadow dMeadow,int time) {
		if(!sMeadow.hasCow() && !dMeadow.hasCow())
		{
			addNeighborOf(sMeadow, dMeadow, time);
			addNeighborOf(dMeadow, sMeadow, time);
		}
		else if(!sMeadow.hasCow() && dMeadow.hasCow())
			addNeighborOf(sMeadow, dMeadow, time);
		else if(!dMeadow.hasCow() && sMeadow.hasCow())
			addNeighborOf(dMeadow, sMeadow, time);
	}

	private void addNeighborOf(Meadow sMeadow, Meadow dMeadow, int time) {
		Map<Meadow,Integer> neighborOfSource = sMeadow.getNeighborMeadow();
		neighborOfSource.put(dMeadow, time);
		sMeadow.setNeighborMeadow(neighborOfSource);
	}

	//to add new edges to the graph
	public void addPaths(String source, String destination, int time) {
		Meadow sMed=null;
		Meadow dMed=null;

		if(meadows.containsKey(source))
		{
			System.out.println("Got meadow: "+source);
			sMed = meadows.get(source);
		}
		else sMed = new Meadow(source, Integer.MAX_VALUE);

		if(meadows.containsKey(destination))
		{
			System.out.println("Got meadow: "+destination);
			dMed = meadows.get(destination);
		}
		else dMed = new Meadow(destination, Integer.MAX_VALUE);

		makeNeighbors(sMed,dMed,time);

		meadows.put(source, sMed);
		meadows.put(destination, dMed);
	}

	public Boolean hasMeadow(String name)
	{
		return meadows.containsKey(name);
	}
	
	public Meadow getMeadow(String name) {
		if(meadows.containsKey(name))
			return meadows.get(name);
		return null;
	}
	
	public void printGraph() {
		for(Entry<String, Meadow> source :meadows.entrySet())
		{
			System.out.print(source.getKey()+" : ");
			Map<Meadow,Integer> neighbours = source.getValue().getNeighborMeadow();
			for(Entry<Meadow,Integer> e:neighbours.entrySet()) {
				System.out.print("(" + e.getKey().getName() + "=" + e.getValue() + ")   ");
			}
			System.out.println();
		}
		}

		
	}

