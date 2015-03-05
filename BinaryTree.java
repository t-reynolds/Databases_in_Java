import java.util.ArrayList;
public class BinaryTreeAlias {

	Node root;
	int size = 0;

	public void add(double key_f, double key_l, double key_z,double key_s, double key_c, Values value)
	{
		//This method is responsible for adding nodes to the binary search tree.
		//If the root is null, we add the new node to the root value.
		//else, we go through a loop that will not add a node until it finds the appropriate null
		//child placement. It then sets the null child equal to the newNode variable.
		//The boolean variable success is then set to true, which exits the loop.
		//The parent variable is needed to crawl through the binary search tree
		//and compare the desired key of the parent to the newNode.
		//If the newNode variables key is larger, we crawl to the right
		//else, we crawl to the left.
		//If a null value is found, as I said, we add the newNode to it and exit the crawling loop.
		
		Node parent = root;								
		Node newNode = new Node(key_f, key_l, key_z, key_s, key_c, value);
		boolean success = false;

		if(root == null)
		{
			root = newNode;
			size++;
			return;
		}
		else
		{
			while(!success)
			{//These if and else if statements add the newNode to a null spot.
				 if(key_l <= parent.key_l && parent.leftChild == null)
				{
					parent.leftChild = newNode;
					size++;
					success = true;
				}
				else if(key_l > parent.key_l && parent.rightChild == null)
				{
					parent.rightChild = newNode;
					size++;
					success = true;
				}
				else//This else statement keeps the loop crawling fora null value
				{
					if(parent.key_l < key_l)
					{
						parent = parent.rightChild;
					}
					else if(parent.key_l >= key_l)
					{
						parent = parent.leftChild;
					}
				}
			}
		}
	}
	public void addByContributors(Node focusNode)
	{
		//This is the method I tried to use in order to create a tree organized by the contribution size, so that finding the top donors was much easier
		//However, my while loop was continually infinite, and I could not figure out why since it is the exact same method as my add method above,
		//which works perfectly. The only difference between this method and the above method is this one takes in a whole node, the other constructs a node,
		//and this one organizes based off of the contribution key value, while the other one organizes based off the last name key value.
		Node parent = root;
		boolean success = false;

		if(root == null)
		{
			root = focusNode;
			size++;
			return;
		}
		else
		{
			while(!success)
			{
				 if(focusNode.key_c <= parent.key_c && parent.leftChild == null)
				{
					parent.leftChild = focusNode;
					size++;
					success = true;
				}
				else if(focusNode.key_c >= parent.key_l && parent.rightChild == null)
				{
					parent.rightChild = focusNode;
					size++;
					success = true;
				}
				else
				{
					if(parent.key_c <= focusNode.key_c)
					{
						parent = parent.rightChild;
						System.out.println("INFINITE HERE TOO! :D if statement " + focusNode);
					}
					else if(parent.key_c > focusNode.key_c)
					{
						parent = parent.leftChild;
					System.out.println("INFINITE HERE TOO! :D else if statement " + focusNode);

					}
				}
			}
		}
	}
	

	public static BinaryTreeAlias arrayToTree(ArrayList<Values> v, BinaryTreeAlias betaValues)
	{ //This is a method used to put an array into a BST structure, I use a loop that then creates the necessary key and value object for each node and value stored in the array
		//It is required since I use a binary search tree for some functions, and an array for others.
		//The method feeds the master values into the desired tree.
		for(int i = 0; i < v.size(); i++)
		{
			double key_f = (double)(Character.getNumericValue(v.get(i).firstName.charAt(0))) + (double)(Character.getNumericValue(v.get(i).firstName.charAt(1))) / 100
					+ (double) (Character.getNumericValue(v.get(i).firstName.charAt(2))) / 10000;
			double key_l = (double)(Character.getNumericValue(v.get(i).lastName.charAt(0))) + (double)(Character.getNumericValue(v.get(i).lastName.charAt(1))) / 100
					+ (double)(Character.getNumericValue(v.get(i).lastName.charAt(2))) / 10000;
			double key_z = v.get(i).zipcode;
			double key_s = (double)(Character.getNumericValue(v.get(i).lastName.charAt(0))) + (double)(Character.getNumericValue(v.get(i).lastName.charAt(1))) / 100;
			double key_c = v.get(i).contribution;
			betaValues.add(key_f, key_l, key_z, key_s, key_c, v.get(i));
		}
		return betaValues;
	}
	public static ArrayList<Values> treeToArray(ArrayList<Values> alphaValues, Node focusNode)
	{//This method is similar to the above method, but because we are working with a BST first, the only way I could think to 
		// add the values to the array was through the recursive method I used in order to print out the BST in order
		//The method feeds the master values of the tree into the array.
		//It is immediately used to feed the tree that takes in the mailing list into an array, since most of the methods use the array structure.
		if (focusNode != null) 
		{
			treeToArray(alphaValues, focusNode.leftChild);
			alphaValues.add(focusNode.value);
			treeToArray(alphaValues, focusNode.rightChild);
			
		}
		return alphaValues;
	}
	public static BinaryTreeAlias treeToTreeContributors(BinaryTreeAlias betaValues, Node focusNode)
	{
		//This method is not used, as it was conceptualized to attempt to create a tree that was
		//responsible for sorting by the contribution amount, but the addByContributors method is broken and 
		//I do not know how to fix it within the time frame.
		if (focusNode != null) 
		{
			System.out.println("TreeToTree function has begun!");
			treeToTreeContributors(betaValues, focusNode.leftChild);
			betaValues.addByContributors(focusNode);
			treeToTreeContributors(betaValues,focusNode.rightChild);
		}
		return betaValues;
	}
	public static void inOrderTraverseTree(Node focusNode) 
	{
//This method was very cool to write, as it is the first time where recursion was the only answer I could see
//The method calls itself until it hits a null value, and then it slowly works back through each call and prints out the values input
//Starting with all of the left children, from the bottom and up.
//Once all of the left children of every node has been printed, it prints the root, and then prints all of the right children.
		if (focusNode != null) 
		{
			inOrderTraverseTree(focusNode.leftChild);
			System.out.println(focusNode.value);
			inOrderTraverseTree(focusNode.rightChild);
		}
	}
	public static void reverseOrderTraverseTree(Node focusNode) 
	{
		//This method reverses the order so the nodes are printed out with the maximum value acessed first.
		//It was created with the intention of conceptualizing how to deal with the greaterThanX cases or top ten cases.
		if (focusNode != null) 
		{
			reverseOrderTraverseTree(focusNode.rightChild);
			System.out.println(focusNode.value);
			reverseOrderTraverseTree(focusNode.leftChild);
		}
	}
	public boolean remove(double key_f, double key_l) 
	{
		//This method is the method called whenever a donor must be removed. 
		//This website is responsible for teaching me to code it
		//http://www.newthinktank.com/2013/03/binary-trees-in-java-2/
		//The method first finds the appropriate node to be removed, and then makes sure to point out whether 
		//or not it is a leftChild.
		//Once the node has been found,
		//We check a series of cases; If its the root, if its a leaf, if it has one child attached, or if it has two children.
		//The leaf is the simplest case, it can simply be removed without being replaced.
		//The single attached child case is the next easiest. You simply move the single child to the removed parent node place.
		//The two children case is the most complex, as it requires you to replace the parent node with the smallest right child of the removed node, or the largest leftChild.
		//This is because those two values are the next smallest or largest value, therefore all other children stay in the same place, since there is no difference
		//as far as the children are concerned, between the smallest right child and the parent.

		Node focusNode = root;//Crawler variable
		Node parent = root;

		boolean isItALeftChild = true;                                       //This method is responsible for carrying out the remove node function

		while (focusNode.key_l != key_l && focusNode.key_f != key_f) 		//Here we create a loop that runs until the focusNode key for the first
		{																	//And last name match that of the input node. Unless the focusNode runs to the 
			parent = focusNode;												//end of the tree and has searched all possible values
			if (key_l <= focusNode.key_l) 									//It will then return a false value, indicating there was no match, and no possible value to remove
			{
				isItALeftChild = true;
				focusNode = focusNode.leftChild;

			} 
			else 
			{
				isItALeftChild = false;
				focusNode = focusNode.rightChild;
			}


			if (focusNode == null)
			{
				return false;
			}

		}
		System.out.println(focusNode.value.firstName + " " + focusNode.value.lastName + " has been removed."); //Here, the program states it has found the value to be removed, and is able to complete the operation

		if (focusNode.leftChild == null && focusNode.rightChild == null)  //This if check looks for whether or not the found node is a leaf
		{

			if (focusNode == root)										//If the node is a root and contains no children, set the root to null
			{
				size--;
				root = null;
			}
			else if (isItALeftChild)									//If the left child boolean check is true, delete the left child
			{
				size--;
				parent.leftChild = null;
			}
			else														//If the left child boolean check is false, delete the right child
			{
				size--;
				parent.rightChild = null;
			}

		}


		else if (focusNode.rightChild == null) 							//We cover the case of whether or not the node contains two children or just one
		{																//This else if statement is specifically about what to do if the right child is null

			if (focusNode == root)										//If our node is the root, we set the root equal to the left child
			{													
				root = focusNode.leftChild;
			}

			else if (isItALeftChild)									//If its a left child, we set our parent node's left child, which is the parent of our found node,
			{															//equal to the found nodes left child, since the found node must be removed and there is no right child attached to it
				parent.leftChild = focusNode.leftChild;
			}


			else														//This covers the right child case of the found node.
			{
				parent.rightChild = focusNode.leftChild;
			}

		}

		else if (focusNode.leftChild == null) 							//Same as above, except for if the leftChild of the found node is null
		{

			if (focusNode == root)
			{
				root = focusNode.rightChild;
			}

			else if (isItALeftChild)
			{
				parent.leftChild = focusNode.rightChild;
			}


			else
			{
				parent.rightChild = focusNode.rightChild;
			}

		}


		else 														//Here we deal with the most complicated case, that requires the replacement method to be run
		{															//The node to be removed has two children, so neither one can simply be switched into the found node spot

			Node replacement = getReplacementNode(focusNode);

			if (focusNode == root)//Root case
			{
				root = replacement;
			}
			else if (isItALeftChild) //left child case
			{
				parent.leftChild = replacement;
			}
			else //Right child case
			{
				parent.rightChild = replacement;
				replacement.leftChild = focusNode.leftChild;
			}
		}

		return true;

	}

	public Node getReplacementNode(Node replacedNode)
	 {
	 	//This method finds the replacement node
	 	//It searches for the smallest right child in this case to replace the found node.

		Node replacementParent = replacedNode;				//This is the method responsible for finding the replacements for the final remove-node case 
		Node replacement = replacedNode;					//

		Node focusNode = replacedNode.rightChild;


		while (focusNode != null) {									//This loop crawls down the binary search tree, first moving to the right child, and then

			replacementParent = replacement;						//skirting over to the far left child, effectively finding the smallest value larger than the
																	// node we want to replace
			replacement = focusNode;

			focusNode = focusNode.leftChild;

		}



		if (replacement != replacedNode.rightChild) {				//If the node we want to replace is not the right child, which it should not be based 
																	//on the parameters we had set earlier
			replacementParent.leftChild = replacement.rightChild;	//set the rightChild of our found replacement node as position the found replacement node is currently holding
			replacement.rightChild = replacedNode.rightChild;		// And set the rightChild of our replacement node = to the right child of the node we want to replace

		}

		return replacement;

	}
}

class Node {

	double key_f;										//This is the node constructor class
	double key_l;										//I created the keys that I thought would potentially be useful for the methods
	double key_z;										//I only use the key_f and key_l keys to help sort/remove nodes, these stand for first name and last name
	double key_s;										
	double key_c;	


	Values value;

	Node leftChild;										//These construct the leftChild and rightChild variables, unique to every node
	Node rightChild;

	Node(double key_f,double key_l,double key_z,double key_s,double key_c, Values value) {

		this.key_f = key_f;                               //This inputs the values into the keys and the Value object
		this.key_l = key_l;
		this.key_z = key_z;
		this.key_s = key_s;
		this.key_c = key_c;
		this.value = value;

	}
	public String toString() {
										//This allows the nodes to be printed as strings and not just memory addresses
		return value + "";


	}



}
