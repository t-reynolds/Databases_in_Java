import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.List;

public class Graph<T>
{
	//This class contains the methods that constructs the Graph data structure.
	//A HashMap is used as the main data structure, because it is the most convenient data structure to store an two different objects, such as an object and an arrayList of objects
	//The arrayList is used to hold all of the edges of the stored object, as an object can have any amount of edges, up to n amount, where n is the total objects present.
	public HashMap<T, GraphNode<T>> graph;
	public Graph()
	{
		//Constructor class
		graph = new HashMap<T, GraphNode<T>>();
	}
	public void addNode(T t)
	{
		//This method adds node objects to the hashmap, with their stored edges, which there are currently nill of
		GraphNode<T> node = new GraphNode(t);
		graph.put(t, node);
	}
	public void addEdge(GraphNode<T> from, GraphNode<T> to)
	{//This method adds an edge from one node to another.
		//The if statements are used to check that both nodes actually exist.
		//We get the key object from the hash map, access its arrayList, and add the node object we would like to like our initial node to.
		if(graph.containsKey(from.t))
		{
			if(graph.containsKey(to.t))
			{
				graph.get(from.t).edges.add(to);
			}
		}
	}
	public void removeNode(GraphNode<T> node)
	{
		//This methods removes a value from the hashMap.
		if(graph.containsKey(node.t))
		{
			graph.remove(node.t);
		}
	}
	public void removeEdge(GraphNode<T> from, GraphNode<T> to)
	{
		//This method removes an edge.
		if(graph.containsKey(from.t))
		{
			graph.get(from.t).edges.remove(to.t);
			
		}
	}
	public Stack<GraphNode<T>> shortestPath(GraphNode<T> node)
	{
		//This method finds the shortest path. 
		//It is called when the node we are searching for is initially found within the BFT method.
		//It loops through the parent nodes stored into the input node, which is the found node.
		Stack<GraphNode<T>> stack = new Stack<GraphNode<T>>();

		while(node.prev != null)
		{
			stack.push(node);
			node = node.prev;
		}
		return stack;
	}	
	public GraphNode<T> get(T t)
	{
		return graph.get(t);
	}
	
	public ArrayList<GraphNode<T>> DFT(GraphNode<T> startNode)
	{//This is the method that carries out the Depth First traversal algorithm
		//The ArrayList accessed is used so that we track all nodes that have already been accessed
		//The stack is used because this method tracks one branch of nodes at a time
		//The branch is traversed until an end is hit, where there are no nodes available to be traversed that have not already been traversed
		//The stack then pops off nodes until it reaches a node where another branch/path is available, and it follows that path until it reaches the
		//same condition as before. Once all nodes have been accessed and the stack has popped out all values, we return the accessed arrayList
		 ArrayList<GraphNode<T>> accessed = new ArrayList<GraphNode<T>>();
		  Stack<GraphNode<T>> stack = new Stack<GraphNode<T>>();
		  ArrayList<GraphNode<T>> stack_list = new ArrayList<GraphNode<T>>();//This variable exists to track all nodes that have been stacked, so that the same branch is not taken twice

		  stack.push(startNode); //This adds the initial node to the stack pile
		  
		  while (!stack.isEmpty())//While stack still has values, loop
		  {
		    GraphNode<T> top = stack.peek();
		    stack.pop(); //Remove top node

		    	if(!accessed.contains(top))//If the top value has already been traversed, skip!
		    	{
		    		accessed.add(top);//add the top to the accessed list
		    		stack.push(top);//Add the new node to the top of the stack
		    		stack_list.add(top);//track the stacked nodes

		    		for(int i = 0; i < top.edges.size(); i++)
			     	 {
			      		GraphNode<T> edge = (GraphNode<T>)top.edges.get(i); //store each edge found in the top node into an edge variable
				      	if(!accessed.contains(edge) && !stack_list.contains(edge)) //If the edge has not yet been traversed, stack it
				      	{
				      		stack.push(edge); //the edge is stacked
				      		stack_list.add(edge); //track the stack
				      	}
			      	 }
		    	}

		  }
  
 		 return accessed; //return the traversed nodes.
	}
	public ArrayList<GraphNode<T>> BFT(GraphNode<T> startNode) 
	{
		//This is the method that contains the breadth first traversal algorithm
		//The ArrayList accessed is used to store all values that have been traversed by the BFT algorithm
		//The queue exists because the Breadth first algorithm traverses one degree of separation at a time
		//First it checks each value stored in the edge array of the startNode, and enqueue's each found value. 
		//Once all values stored in the edge of the initial node have been checked,
		//the algorithm then switches the analyzed node to the first node found in the initial nodes edge array list.
		//It checks this next node for any unaccessed values, and then moves on to the second node placed in the initial nodes array list.
		//The whole process relies on the first in/first out, which means we need to use a queue.
		  ArrayList<GraphNode<T>> accessed = new ArrayList<GraphNode<T>>();
		  
		  Queue<GraphNode<T>> queue = new Queue<GraphNode<T>>();
		  ArrayList<GraphNode<T>> queue_list = new ArrayList<GraphNode<T>>();
		  queue.enqueue(startNode);
		  startNode.prev = null;
		  
		  while (!queue.isEmpty())//As long as the queue is not empty, as long as we have not run out of unchecked values, continue looping
		  {
		    GraphNode<T> node = (GraphNode<T>)queue.peek();//We store the first node in the queue in the node variable.
		    queue.dequeue();				//Remove the first node from the queue

		    
	    	if(!accessed.contains(node))//As long as this node has not already been traversed
	    	{
	    		accessed.add(node);//Add node to the store of traversed nodes
	    		queue.enqueue(node);//queue the node up so that we check its edges for untraversed nodes
	    		queue_list.add(node);//Store the node in the list of nodes that have been queue'd
	    	}
		     
		      for(int i = 0; i < node.edges.size(); i++)
		      {
		      	GraphNode edge = (GraphNode<T>)node.edges.get(i);
		      	if(!accessed.contains(edge) && !queue_list.contains(edge))
		      	{//Read through the edges stored in the node, if they havent already been accessed or queue'd 
		      		edge.prev = node;		//Set the current node as the previous node of the edge for the shortest path method
		      		queue.enqueue(edge);	//Queue the new edge to check for untraversed nodes
		      		queue_list.add(edge);	//add the edge to the list of nodes that have been queue'd
		      	}
		      }
	    
	  }
  
 		 return accessed; //Returns all traversed nodes
	}
	public void BFTShortest(GraphNode<T> startNode, GraphNode<T> finishNode) 
	{
		//This is the method that contains the breadth first traversal algorithm
		//The ArrayList accessed is used to store all values that have been traversed by the BFT algorithm
		//The queue exists because the Breadth first algorithm traverses one degree of separation at a time
		//First it checks each value stored in the edge array of the startNode, and enqueue's each found value. 
		//Once all values stored in the edge of the initial node have been checked,
		//the algorithm then switches the analyzed node to the first node found in the initial nodes edge array list.
		//It checks this next node for any unaccessed values, and then moves on to the second node placed in the initial nodes array list.
		//The whole process relies on the first in/first out, which means we need to use a queue.
		  ArrayList<GraphNode<T>> accessed = new ArrayList<GraphNode<T>>();
		  
		  Queue<GraphNode<T>> queue = new Queue<GraphNode<T>>();
		  ArrayList<GraphNode<T>> queue_list = new ArrayList<GraphNode<T>>();
		  queue.enqueue(startNode);
		  startNode.prev = null;
		  
		  while (!queue.isEmpty())//As long as the queue is not empty, as long as we have not run out of unchecked values, continue looping
		  {
		    GraphNode<T> node = (GraphNode<T>)queue.peek();//We store the first node in the queue in the node variable.
		    queue.dequeue();				//Remove the first node from the queue

		    if(accessed.contains(finishNode))
		    {
		    	shortestPath(node)
		    }
		    else{

	    	if(!accessed.contains(node))//As long as this node has not already been traversed
	    	{
	    		accessed.add(node);//Add node to the store of traversed nodes
	    		queue.enqueue(node);//queue the node up so that we check its edges for untraversed nodes
	    		queue_list.add(node);//Store the node in the list of nodes that have been queue'd
	    	}
		     
		      for(int i = 0; i < node.edges.size(); i++)
		      {
		      	GraphNode edge = (GraphNode<T>)node.edges.get(i);
		      	if(!accessed.contains(edge) && !queue_list.contains(edge))
		      	{//Read through the edges stored in the node, if they havent already been accessed or queue'd 
		      		edge.prev = node;		//Set the current node as the previous node of the edge for the shortest path method
		      		queue.enqueue(edge);	//Queue the new edge to check for untraversed nodes
		      		queue_list.add(edge);	//add the edge to the list of nodes that have been queue'd
		      	}
		      }
		  }
	    
	  }
  
 		 return accessed; //Returns all traversed nodes
	}
	
	
	public void print_path(GraphNode<T> start)
	{
		ArrayList<GraphNode<T>> printmeDFT = DFT(start);

		System.out.println("DFT TRAVERSAL");
		for(GraphNode<T> print : printmeDFT)
		{
			System.out.println(print);
		}
		ArrayList<GraphNode<T>> printmeBFT = BFT(start);
		System.out.println("BFT TRAVERSAL");
		for(GraphNode<T> print : printmeBFT)
		{
			System.out.println(print);
		}

	}
	public static void main(String[] args)
	{
		//This method creates the graph and then calls a print method, that prints out all of the traversed nodes 
		//using the Depth First traversal algorithm and the Breadth First Traversal
		Graph<String> graph1 = new Graph<String>();
		graph1.addNode("A");
		graph1.addNode("B");
		graph1.addNode("C");
		graph1.addNode("D");
		graph1.addNode("E");
		
		graph1.addEdge(graph1.get("A"), graph1.get("C"));
		graph1.addEdge(graph1.get("A"), graph1.get("E"));
		graph1.addEdge(graph1.get("B"), graph1.get("A"));
		graph1.addEdge(graph1.get("B"), graph1.get("D"));
		graph1.addEdge(graph1.get("D"), graph1.get("C"));
		graph1.addEdge(graph1.get("D"), graph1.get("A"));
		graph1.addEdge(graph1.get("D"), graph1.get("B"));
		graph1.addEdge(graph1.get("E"), graph1.get("C"));
		graph1.addEdge(graph1.get("C"), graph1.get("E"));
		graph1.addEdge(graph1.get("C"), graph1.get("B"));

		graph1.print_path(graph1.get("A"));
	}
}

class GraphNode<T> 
{
	//This class is responsible for creating the nodes and edges
	//The node takes in some object
	//This object is used as the key in our HashMap that stores all of the GraphNodes
	//The edges are stored in an arrayList.
	//This makes it easy to store all of the attached edges to an object, since the
	//object often contains more than one edge

       T t;

       ArrayList<GraphNode<T>> edges;

       GraphNode<T> prev;

       public GraphNode(T t)
       {
       		this.t = t;
       		edges = new ArrayList<GraphNode<T>>();
       }
       public String toString()
       {
       return t + "";
       }
}
