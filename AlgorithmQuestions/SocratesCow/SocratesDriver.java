package socratesCow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;


public class SocratesDriver {
	public static void main(String args[]) throws IOException {
		
		BufferedReader b=new BufferedReader(new InputStreamReader(System.in));
		String p=b.readLine();
		int paths=Integer.valueOf(p);
		
		long val=System.currentTimeMillis();
		MeadowGraph mg = new MeadowGraph();
		for(int i=0;i<paths;i++) {
			String inp=b.readLine();
			String[] pathDetails=inp.split(" ");
			//System.out.println(pathDetails[1]+"->"+pathDetails[0]+"="+Integer.valueOf(pathDetails[2]));
			mg.addPaths(pathDetails[1], pathDetails[0], Integer.valueOf(pathDetails[2]));
		}
		mg.printGraph();
		
		System.out.println("Running time actual "+((System.currentTimeMillis()-val))/1000f+" seconds"); 

		}
	
}
