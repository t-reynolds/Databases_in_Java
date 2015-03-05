import java.util.ArrayList;
public class Queue<X>
{//This is the method that creates the queue class.
	//The queue uses an arrayList, as that is the easiest/most efficient structure to store values and access certain values
	private ArrayList<X> values;
	public Queue()
	{
		//This is the queue constructor class
		values = new ArrayList<X>();
	}
	public void enqueue(X x)
	{
		//This method adds a value to the array
		values.add(x);
	}
	public void dequeue()
	{
		//This method removes the first value added to the array
		values.remove(0);
	}
	public X peek()
	{
		//This method returns the first value stored in the array
		X y = values.get(0);
		return y;
	}
	public boolean isEmpty()
	{
		//this value checks if the queue is empty by checking if the arrays size is 0 and it returns a boolean
		boolean empty = true;
		if(values.size() == 0)
		{
			return empty;
		}
		else
		{
			empty = false;
			return empty;
		}
	}
	public int findSize()
	{
		//This method returns the size of the array
		int size = values.size();
		return size;
	}
	public static void main(String[] args)
	{
		Queue<String> queue = new Queue<String>();
		queue.enqueue("Tom");
		queue.enqueue("Tim");
		queue.enqueue("Terrence");
		queue.enqueue("Theresa");
		queue.dequeue();
		System.out.println(queue);
	}
}
