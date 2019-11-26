package finalexam;
import algs4.*;
/* 
* <Tsvety Sotonov>
*<City Distances>
*/

public class CityDistances 
{
	public static void main(String[] args) 
	{
		StdIn.fromFile("data/fecities.txt"); //List of midwestern cities
				
	    BinarySearchST<String, Integer> cities = new BinarySearchST<String, Integer>();
	    int number_cities = 0;
	    
	    while(!StdIn.isEmpty()) 
	    {
	    	String[] names = StdIn.readAllStrings();
	    
	    	for (int i = 0; i < names.length; i++) 
	    	{
	          cities.put(names[i], i);  
	    	}
	    	number_cities = names.length;
	    	
	    }
	    		
	    //Create graph and read in connection  
	    Graph cityGraph = new Graph(number_cities);
	    StdIn.fromFile("data/feconnections.txt");
	    while(StdIn.hasNextLine()) 
	    {
	    	String line = StdIn.readLine(); 
	    	String[] fields = line.split("\\s+");
	        String cityName = fields[0];
	        String cityConnection = fields[1];
	        int city = cities.get(cityName);
	        int connect = cities.get(cityConnection);
	        cityGraph.addEdge(city, connect);  
	    	
	    }
	    
	    //Creates BFS 
	    int Chicago = cities.get("Chicago");
	    BreadthFirstPaths chi = new BreadthFirstPaths(cityGraph, Chicago);
	    
	    //Prints headers 
	    String[] major_citys = {"Chicago", "KansasCity", "Milwaukee", "Minneapolis", "SaintLouis"};
	       for(String header: major_citys) {
	           StdOut.print("\t\t" + header + " ");
	       }
	    
	   //Prints shortest distance from major city to the city in the outer loop 
	  
	   String[] connect = {"Alton","Appleton", "Carbondale","Carlinville","Centralia","Champaign-Urbana","Chicago","Galesburg",
	   "JeffersonCity","Joliet","Kankakee", "KansasCity", "Kewanee","LaCrosse","LaPlata","Mattoon","Milwaukee","Minneapolis",
	   "Naperville","Oshkosh","Pontiac","Portage","Quincy","RedWing","SaintLouis","Springfield","Wausau","Wittenburg"};
	   
	   StdOut.println();
	   for(String city : connect) 
	   {
		   StdOut.printf("%-20s"+" ", city);
           for(String route: major_citys) 
           {
               int distance = chi.distTo(cities.get(city)) + chi.distTo(cities.get(route));
               StdOut.printf("%-18d\t", distance);
           }
           StdOut.println();
	   }
	    
	}
		
}
