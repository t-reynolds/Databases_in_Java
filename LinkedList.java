//Sites I looked at to aquire the knowledge/context required to write this class:
//http://codereview.stackexchange.com/questions/31341/java-code-for-linked-list
//https://www.youtube.com/watch?v=fblR50vqbDQ
//http://cslibrary.stanford.edu/103/LinkedListBasics.pdf
//http://www.java-tips.org/java-se-tips/java.lang/linked-list-implementation-in-java.html
 //http://www.cs.cmu.edu/~adamchik/15-121/lectures/Linked%20Lists/linked%20lists.html
//http://crunchify.com/how-to-implement-a-linkedlist-class-from-scratch-in-java/

public class LinkedListAlias {
   
     ValueNode head; //Initialize head variable
     int sizeCounter; //Initialize size counter variable
 
    public LinkedListAlias() 
    //This constructs the linked list class, currently an empty set
    {

        head = new ValueNode(null);
        sizeCounter = 0;
    }

    public void addPosition(Values value, int index)
    //This method adds a value at the user inputted location
    {
        ValueNode valueTemp = new ValueNode(value);  //Stores the input into a variable
        ValueNode currentNode = head;            //Creates iterator variable

        for (int i = 1; currentNode.getNextValue() != null && i < index; i++)//As long as the i is less than index, and the next value is not null, iterate
        {
            currentNode = currentNode.getNextValue();
        }
        valueTemp.setNextValue(currentNode.getNextValue());//Now that the index has been hit, link the input to the next index value
        currentNode.setNextValue(valueTemp);               //and link the current node iterator to the input, effectively sandwiching the input
        sizeCounter++;                                   //Increment the size counter since there is now an additional value                             
    }
    public void add(Values value)
    //This adds values to the end of the linked list
    {
        ValueNode valueTemp = new ValueNode(value); //Template value stores the user inputted value
        ValueNode currentNode = head;               //Creates a variable we will use to iterate through list til end
        if(currentNode.getNextValue() == null)
        {
            currentNode.setNextValue(valueTemp);
            sizeCounter++;
            return;
        }
        while (currentNode.getNextValue() != null) //While the next node is not a null value, we iterate through the linked list
        {
            currentNode = currentNode.getNextValue();
        }
        currentNode.setNextValue(valueTemp); //Now that the next node is null, we set the null node equal to the user inputted value
        sizeCounter++;//increment the size counter varaible, as now the list contains one more value
    }
    
    public Values get(int index)
    //This method returns the value at the inputted index
    {
        if (index == 0) //if the input is 0, return null, because the first value is the head, which is always defined as null.
        {
            return null;
        }
        ValueNode currentNode = head.getNextValue(); //Sets iterator variable
        for (int i = 1; i < index; i++)//iterates through linked variables until we hit the index
        {
            if (currentNode.getNextValue() == null)
            {
                return null;
            }
            currentNode = currentNode.getNextValue();
        }
        return currentNode.getValue();//Returns the asked for value
    }
 
    public boolean remove(int index)
    //This method removes the desired value through an index input
    {
        ValueNode currentNode = head;   //Sets up iterator variable
         sizeCounter--; //Decrease the counter because now there is one less value then before.
        for (int i = 1; i < index; i++) //Begin iterating
        {
            if (currentNode.getNextValue() == null)//if the next stored value is null, we exit the loop and return false because there is no removable value
            {
                return false;
            }
            currentNode = currentNode.getNextValue(); 
        }
        currentNode.setNextValue(currentNode.getNextValue().getNextValue()); //This links the value linked to the deleted value to the value the deleted value was linked to.
       
        return true;
    }

    public int size()
    //Method that tracks the size counter
    {
        return sizeCounter;
    }   
}
