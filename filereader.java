import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class MailReaderArray {
	
	public static ArrayList<Values> get_Names (Scanner fs) 
	{														//This method retrieves the data from the txt file that I am reading in.
		ArrayList<Values> mailList = new ArrayList<Values>();
		fs.useDelimiter(",|\\n");                           //It creates a mailList ArrayList, which then, through a delimiter
		                                                    //that parses out commas and new lines to define a new value
		while(fs.hasNext())
		{                                                   //is filled with the 7 objects created in the Values class file.
			Values v = new Values();
			v.firstName = fs.next();
			v.lastName = fs.next();
			v.address = fs.next();
			v.city = fs.next();
			v.state = fs.next();
			v.zipcode = fs.nextInt();
			v.contribution = fs.nextInt();
			v.date = fs.next();

			mailList.add(v);                              //At the end of the method, we add the Values v object to the array, and then return the array value
		}												//Which in turn is fed into the main method class as alphaValues
		fs.close();
		
		return mailList;
	}
	
	
	public static Scanner open_file(String file_name)
	{
		File f = new File(file_name);							//This method sets the file we want to read up for scanning in the main_database method
		try{													//It is not original, I retrieved it from my CS1 class files from the past year
			return new Scanner(f);								//which were shown to me by Matthew Whitehead
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error: " + e);
			System.exit(-1);
		}
		return(null);
	}
	public static String user_input;                           //This creates the user_input instance variable, allowing it to be defined in one method
																//and used in another.
	public static ArrayList<Values> main_database()
	{							//------------------------------------This main_database method is the "brain" of this class
		Scanner sc = new Scanner(System.in);						//It is the method that calls all other methods, and feeds into each method the 
		System.out.println("Enter in the file you would like to read: ");//required values. It is also called from the MainMethodMail class, as the first
		user_input = sc.nextLine();										//process of the program. The method prompts the user for a file input, which it
																		//then runs through a method to prep the input into a value that can then be read
		Scanner fs = open_file(user_input);								//by a scanner object. 
		ArrayList<Values> value = get_Names(fs);
		return value;
	}
	
}
