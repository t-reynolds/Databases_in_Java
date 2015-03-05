import java.util.*;    
import java.lang.System;	
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
							  //This class file contains all of the innerworkings of the program
public class EditArray		//I call this class from the main method class for each and every process that occurs
{
/////////////////////Collect Mean, Median, and Total Values of Contributions///////////////////////////////////////
		public static String diagnostics = "Program Diagnostics: \n"; //This is the timer for all programs. I set a start at each
//All diagnostics functions are found from stack overflow online//////method and then at the end I store the time into this variable.
		public static void dataCollectionState(ArrayList<Values> alphaValues)
		{
			//This method is responsible for collecting all data on the donors found in the user inputted State
			//It collects the average, total donors found in the State, total amount of contribution, and the 
			//median donation value.
			//The method does this by looping through the master ArrayList and storing each donor that lives in the user inputted State into a new array
			//If the inputted value does not match any values stored, the Program prints out that there were no donors found within that State
			//The average is found by loopng through the new array and adding each stored contribution value up, and then dividing it by the total amount of
			//values stored in the new array.
			//The median is found by ordering the new array, and pointing to the middle value if the total amount of donors is odd, or averaging the two
			//middle values if the total amount of donors from the state is an even number.
			//The amount of donors in the state is found by tracking the size of the new array.

			 long start = System.nanoTime();									//Timer is kept in nanoseconds, as it is more efficient than millis apparently.

			ArrayList<Values> aliasArray = new ArrayList<Values>(alphaValues);      //The aliasArray here is created so that I can edit all the values within
			ArrayList<Values> donorsFromState = new ArrayList<Values>();			//alphaValues, without editing the alphaValues array itself.
			Scanner fs = new Scanner(System.in);									//It is a clone array.
			System.out.println("Enter in a Two letter State: ");
			String x = fs.nextLine();												//Here I store the users input into a String x, which is referenced later

			double totalContribution = 0;
			double averageAmount;													//I initialize all of the variables I want to keep track of outside the for loop here
			int median;																//If I did not do this, the values of these variables would disappear at the end of the loop
			int i;
			int check = 0;

			for( int j = 0; j < aliasArray.size(); j++)
			{
				if(aliasArray.get(j).state.equals(x))							//Here I ask if the x string is equal to the current state being iterated through by the loop
				{
					check++;													//The check variable is served to generate an answer if the entered state is not present.
					donorsFromState.add(aliasArray.get(j));
				}
				else if(j == aliasArray.size() - 1 && check == 0)
				{
					System.out.println("No donors found with that State");
				}
			}
			for(int k = 1; k < donorsFromState.size(); k++)
	   		{
	   			for(int j = 0; j < donorsFromState.size(); j++)
	   			{
	   				if(donorsFromState.get(j).contribution - donorsFromState.get(k).contribution > 0)//This is a swap algorithm that I came up with on my own, though I very much realize 
	   				{																				//It is not an original thought, and I am sure I've seen it done before somewhere.
	   					Values swap = donorsFromState.get(k);										
	   					donorsFromState.set(k, donorsFromState.get(j));
	   					donorsFromState.set(j, swap);
	   				}
	   			}
	   		}

			for(i = 0; i < donorsFromState.size(); i++)                            //This loop counts the total contribution. Fairly simple.
			{
				totalContribution = donorsFromState.get(i).contribution + totalContribution;
			}
			averageAmount = totalContribution / (i);                          //This is the reason I initialized i outside of the for loop.
																			//It keeps track of the number of contributions.
			System.out.println("Total of " + i + " donors from zipcode area.\n \n" 
								 + "Total Contribution in Zipcode: " + totalContribution + "$\n \n"
								 + "Average Donation size from given zipcode: " + averageAmount + "$\n");

			if(i % 2== 0)															//This implementation to find the median value was slightly tricky, simply because
			{																		//if the number of stored values was even, you treat it as an odd number since it starts 
				int evenMedian = donorsFromState.get((i + 1)/2).contribution + donorsFromState.get((i-1)/2).contribution;//at value zero, and vice versa if it IS odd.
				System.out.println("Median donation is: " + evenMedian / 2 + "$");
			}
			else if(i % 2 == 1)
			{
				System.out.println("Median donation is: " + donorsFromState.get(i/2).contribution + "$");
				System.out.println(donorsFromState.get(1));
			}
			long time = System.nanoTime() - start;
			diagnostics += "Time(ns) taken to collect data on given State: " + time + "\n";		//The += operand is used because without it, you only store the most recent timer value	
		}																						//This way, we can see the timed value of all processes run by the user
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		public static void dataCollectionZip(ArrayList<Values> alphaValues)
		{
			//This method is responsible for collecting all data on the donors found in the user inputted zipcode
			//It collects the average, total donors found in the zipcode, total amount of contribution, and the 
			//median donation value.
			//The method does this by looping through the master ArrayList and storing each donor that lives in the user inputted zipcode into a new array
			//If the inputted value does not match any values stored, the Program prints out that there were no donors found within that zipcode
			//The average is found by loopng through the new array and adding each stored contribution value up, and then dividing it by the total amount of
			//values stored in the new array.
			//The median is found by ordering the new array, and pointing to the middle value if the total amount of donors is odd, or averaging the two
			//middle values if the total amount of donors from the zipcode is an even number.
			//The amount of donors in the zipcode is found by tracking the size of the new array.

			 long start = System.nanoTime();

			ArrayList<Values> aliasArray = new ArrayList<Values>(alphaValues);
			ArrayList<Values> donorsFromZip = new ArrayList<Values>();
			Scanner fs = new Scanner(System.in);
			System.out.println("Enter in a Zipcode: ");
			int x = fs.nextInt();

			double totalContribution = 0;
			double averageAmount;
			int median;
			int i;
			int check = 0;

															//This method is exactly identical to the above method, except for the initial input is a zipcode rather than a state.
			for( int j = 0; j < aliasArray.size(); j++)    //Storing the array as a collection of objects makes this change very easy to deal with.
			{												
				if(aliasArray.get(j).zipcode == x)
				{
					check++;
					donorsFromZip.add(aliasArray.get(j));
				}
				else if(j == aliasArray.size() - 1 && check == 0)
				{
					System.out.println("No donors found with that zipcode");
				}
			}

			for(int k = 1; k < donorsFromZip.size(); k++)
	   		{
	   			for(int j = 0; j < donorsFromZip.size(); j++)
	   			{
	   				if(donorsFromZip.get(j).contribution - donorsFromZip.get(k).contribution > 0)
	   				{
	   					Values swap = donorsFromZip.get(k);
	   					donorsFromZip.set(k, donorsFromZip.get(j));
	   					donorsFromZip.set(j, swap);
	   				}
	   			}
	   		}

			for(i = 0; i < donorsFromZip.size(); i++)
			{
				totalContribution = donorsFromZip.get(i).contribution + totalContribution;
			}
			averageAmount = totalContribution / (i);
			System.out.println("Total of " + i + " donors from zipcode area.\n \n" 
								 + "Total Contribution in Zipcode: " + totalContribution + "$\n \n"
								 + "Average Donation size from given zipcode: " + averageAmount + "$\n");

			if(i % 2== 0)
			{
				int evenMedian = donorsFromZip.get((i + 1)/2).contribution + donorsFromZip.get((i-1)/2).contribution;
				System.out.println("Median donation is: " + evenMedian / 2 + "$");
			}
			else if(i % 2 == 1)
			{
				System.out.println("Median donation is: " + donorsFromZip.get(i/2).contribution + "$");
				System.out.println(donorsFromZip.get(1));
			}
			long time = System.nanoTime() - start;
			diagnostics += "Time(ns) taken to collect data on given Zip Code: " + time+ "\n";
		}
///////////////////////////State and Zip Code Search Methods///////////////////////////////////////////
		public static void stateSearch(ArrayList<Values> alphaValues)
		{
			//This method asks the user for a two-letter abbreviation of a State, and the method then
			//loops through the master array list, and anytime the loop comes across a value
			//that matches the user-input it prints the donor out.
			//If the user inputs an invalid state name, the program will tell the user and then 
			//asks the user for another command.
			long start = System.nanoTime();
			ArrayList<Values> aliasArray = new ArrayList<Values>(alphaValues);//For this method I simply iterated through the aliasArray and printed out
																			// all the matching objects.
			Scanner fs = new Scanner(System.in);
			System.out.println("Enter in State abbreviation in capital letters: ");
			String x = fs.next();
			int check = 0;
			for( int j = 0; j < aliasArray.size(); j++)
			{
				if(aliasArray.get(j).state.equals(x))
				{
					check++;
					System.out.println(aliasArray.get(j));				
				}
				else if(j == aliasArray.size() - 1 && check == 0)
				{
					System.out.println("No donors found with that State");
				}
			}
			long time = System.nanoTime() - start;
			diagnostics += "Time(ns) taken to search for donors in given State: " + time+ "\n";
		}
////////////////////////////////////////////////////////////////////////////////////////////////////////
		public static void zipCodeSearch(ArrayList<Values> alphaValues)
		{
			//This method asks the user for a zipcode, and the method then
			//loops through the master array list, and anytime the loop comes across a value
			//that matches the user-input it prints the donor out.
			//The variable check checks whether or not a donor has been found, and 
			//if it comes out to be a value of 0 by the time the loop has iterated through every single stored value
			//the program tells the user the zipcode does not exist within the stored value.
			long start = System.nanoTime();
			ArrayList<Values> aliasArray = new ArrayList<Values>(alphaValues);//Same exact method as above, copy pasted, just using zipcode instead of state objects

			Scanner fs = new Scanner(System.in);
			System.out.println("Enter in a Zipcode: ");
			int x = fs.nextInt();
			int check = 0;
			for(int i = 0; i < aliasArray.size(); i++)
			{
				if(aliasArray.get(i).zipcode == x)
				{
					check++;
					System.out.println(aliasArray.get(i));
				}
				else if(i == aliasArray.size() - 1 && check == 0)
				{
					System.out.println("No donors found with that zipcode");
				}
			}
			long time = System.nanoTime() - start;
			diagnostics += "Time(ns) taken to search for donors in given Zip Code: " + time;
		}
//////////////////////////////Find Top Ten Methods////////////////////////////////////////////////////////
		public static void topTenFamilies(ArrayList<Values> alphaValues)
		{
			//This method is responsible for printing out the top ten families who contributed the most
			//The first function it carries out is storing each family member into the first family member the function encounters.
			//It does this by looping through the alias array that has all of the stored donors inside of it.
			//The alias array is necessary because without it, the master arrayList would have values removed from it,
			//which would ruin the rest of the methods since the master arrayList would no longer contain all needed values.
			//Once the families have all been aggregated, I loop through the new alias ArrayList
			//and add each maximum contribution value the program finds to a new array. Once the array has reached a size of 10,
			//the loop ends and I then print out the newly built array. 
			long start = System.nanoTime();

			ArrayList<Values> aliasArray = new ArrayList<Values>(alphaValues);
			ArrayList<Values> topTen = new ArrayList<Values>();

			for(int i = 0; i < aliasArray.size(); i++) //This loop  filters out all extraneous values, I.E. individual family members, and stores all family members into one single family member
			{
				int contrb = 0;
				for(int j = 1; j < aliasArray.size(); j++)
				{
					if(aliasArray.get(j).lastName.equals(aliasArray.get(i).lastName) && i != j)
					{
						contrb = aliasArray.get(j).contribution + aliasArray.get(i).contribution;
						aliasArray.get(i).contribution = contrb;
						aliasArray.remove(j);
					}
				}					
			}			
			while(topTen.size() < 10)            //This loop took longer than it should have to figure out.
			{												//The while loop continues until the topTen is of the correct size(10)
				Values greatest = aliasArray.get(0);		//A value is added to the array when it is deemed as the maximum value 
				for(int i = 1; i < aliasArray.size(); i++)  //in the array aliasArray, and it is then removed once the for loop is exited.
				{
					if(aliasArray.get(i).contribution > greatest.contribution)
					{
						greatest = aliasArray.get(i);
					}
				}
				topTen.add(greatest);
				aliasArray.remove(greatest);
			}
			for(int i = 0; i < topTen.size(); i++)
			{													
				String familyName = topTen.get(i).lastName;
				int contributions = topTen.get(i).contribution;
				System.out.println("Family " + familyName + " contributed: " + contributions + "$");
			}
			long time = System.nanoTime() - start;
			diagnostics += "Time(ns) taken to collect top ten family donors: " + time + "\n";
		}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
		public static void topTenDonors(ArrayList<Values> alphaValues) 
		{
			//This method iterates through the alias master array list, and upon finding a maximum value, the program
			//removes it from the alias master array list, and then adds it to a topTen array.
			//Once the topTen array has reached size 10 the loop exits and the method prints out the topTen array.
			long start = System.nanoTime();

			ArrayList<Values> aliasArray = new ArrayList<Values>(alphaValues);
			ArrayList<Values> topTen = new ArrayList<Values>(); 				//Same method as above except without parsing out extraneous family members
																				//  Works with individuals
			
			while(topTen.size() < 10)
			{
				Values greatest = aliasArray.get(0);
				for(int i = 1; i < aliasArray.size(); i++)
				{
					if(aliasArray.get(i).contribution > greatest.contribution)
					{
						greatest = aliasArray.get(i);
					}
				}
				topTen.add(greatest);
				aliasArray.remove(greatest);
			}
			
			for(Values gamma: topTen)
			{
				System.out.println(gamma);
			}
			long time = System.nanoTime() - start;
			diagnostics += "Time(ns) taken to collect top ten individual donors: " + time + "\n";
		}
//////////////////////////////Find Greater Than X Methods//////////////////////////////////////////////
		public static void greaterThanXFamily(ArrayList<Values> alphaValues)      
		{
			//This method is responsible for finding all families that donated more than the user inputted amount.
			//The first function it carries out is storing each family member into the first family member the function encounters.
			//It does this by looping through the alias array that has all of the stored donors inside of it.
			//When this method is called, the method asks the user for some integer value.
			//Once the program has the integer value, it simply reads through the array that has all of the families stored
			//and adds all values stored that are greater than x into the greaterThanX array.
			//The next step is simply to print out the newly constructed array.
			long start = System.nanoTime();

			ArrayList<Values> aliasArray = new ArrayList<Values>(alphaValues);       //This method prints out only those values 
			ArrayList<Values> greaterThanX = new ArrayList<Values>();				//that are greater than the user input of x
																					//I again parse out all extraneous family members by 
			for(int i = 0; i < aliasArray.size(); i++)								//storing all of their contribution values into one family member
			{
				int contrb = 0;
				for(int j = 1; j < aliasArray.size(); j++)
				{
					if(aliasArray.get(j).lastName.equals(aliasArray.get(i).lastName) && i != j)
					{
						contrb = aliasArray.get(j).contribution + aliasArray.get(i).contribution;
						aliasArray.get(i).contribution = contrb;
						aliasArray.remove(j);
					}
				}					
			}			
			Scanner fs = new Scanner(System.in);
			System.out.println("Enter in a contribution amount: ");
			int x = fs.nextInt();

			for(int i = 0; i < aliasArray.size(); i++)
			{
				if(aliasArray.get(i).contribution > x)
				{
					greaterThanX.add(aliasArray.get(i));
				}
			}
			alphabetizer(greaterThanX);
			for(int i = 0; i < greaterThanX.size(); i++)
			{
				String familyName = greaterThanX.get(i).lastName;
				int contributions = greaterThanX.get(i).contribution;
				System.out.println("Family " + familyName + " contributed: " + contributions + "$");
			}
			long time = System.nanoTime() - start;
			diagnostics += "Time(ns) taken to collect families who donated more than given x value: " + time + "\n";

		}
////////////////////////////////////////////////////////////////////////////////////////////////////
		public static void indivLargerThanX(ArrayList<Values> alphaValues)
		{
			//This method is responsible for printing out all donors who donated more than the user inputted amount.
			//The method loops through our alphaValues and asks if each contribution amount is greater than the x input.
			//If it is, it stores the value in the array greaterThanX.
			//After that is done, the method alphabetizes the greaterThanX array and prints out the new array.

			long start = System.nanoTime(); 

			Scanner fs = new Scanner(System.in);
			ArrayList<Values> greaterThanX = new ArrayList<Values>(); // Same method as above, with individuals instead of whole families

			System.out.println("Enter in a contribution amount: ");
			int x = fs.nextInt();

			for(int i = 0; i < alphaValues.size(); i++)
			{
				if(alphaValues.get(i).contribution > x)
				{
					greaterThanX.add(alphaValues.get(i));
				}
			}
			for(int i = 1; i < greaterThanX.size(); i++)
	   		{
	   			for(int j = 0; j < greaterThanX.size(); j++)
	   			{
	   				if(greaterThanX.get(j).contribution - greaterThanX.get(i).contribution > 0)
	   				{
	   					Values swap = greaterThanX.get(i);
	   					greaterThanX.set(i, greaterThanX.get(j));
	   					greaterThanX.set(j, swap);
	   				}
	   			}
	   		}
			System.out.println("Donors who contributed more than(Written least to greatest): " + x);
			for(Values beta: greaterThanX)
	   		{
	   			System.out.println(beta);
	   		}
	   		long time = System.nanoTime() - start;
			diagnostics += "Time(ns) taken to collect individuals who donated more than given x: " + time + "\n";
		}

//////////////////////////////Alphabetizer///////////////////////////////////////////////////////
		public static void alphabetizer(ArrayList<Values> alphaValues)
		{
			//This method alphabetizes the master ArrayList using a swapping algorithim. 
			//The method checks if one neighboring value is smaller than the other, and if the later value is smaller, it swaps it with the later value.
			//This same value is then compared with the next one, and if it is still larger it is swapped again, until it is next to a value larger than itself or there are 
			//no further values to compare it to.
			long start = System.nanoTime();
			for(int i = 1; i < alphaValues.size(); i++)               //This was the very first method I wrote, and by far the most difficult, as it was in this one that
	   		{														// I initially wrote the swapping algorithm. It took me honestly probably around 3-4 hours fraught with
	   			for(int j = 0; j < alphaValues.size(); j++)			//re-writing the whole method countless times. Initially I wrote it to alphabetize first names, but then 
	   			{													//I decided to change it to sort last names.
	   				if(Character.getNumericValue(alphaValues.get(j).lastName.charAt(0)) - Character.getNumericValue(alphaValues.get(i).lastName.charAt(0)) > 0)
	   				{
	   					Values swap = alphaValues.get(i);			//This sets the higher value equal to the object swap, to store in order for the swap
	   					alphaValues.set(i, alphaValues.get(j));     //This sets the value at alphaValue(i) to the value stored at alphaValue(j), effectively erasing the alphaValue(i) original value
	   					alphaValues.set(j, swap);					//This sets the value of alphaValue(j) to the value stored in swap, which is an archived value of what alphaValue(i) was, completing the swap
	   				}
	   				else if(Character.getNumericValue(alphaValues.get(j).lastName.charAt(0)) - Character.getNumericValue(alphaValues.get(i).lastName.charAt(0)) == 0
	   						&&Character.getNumericValue(alphaValues.get(j).lastName.charAt(1)) - Character.getNumericValue(alphaValues.get(i).lastName.charAt(1)) > 0)
	   				{
	   					Values _swap = alphaValues.get(i);                           //Higher accuracy sorting^
	   					alphaValues.set(i, alphaValues.get(j));
	   					alphaValues.set(j, _swap);
	   				}
	   				else if(Character.getNumericValue(alphaValues.get(j).lastName.charAt(0)) - Character.getNumericValue(alphaValues.get(i).lastName.charAt(0)) == 0
	   						&&Character.getNumericValue(alphaValues.get(j).lastName.charAt(1)) - Character.getNumericValue(alphaValues.get(i).lastName.charAt(1)) == 0
	   						&&Character.getNumericValue(alphaValues.get(j).lastName.charAt(2)) - Character.getNumericValue(alphaValues.get(i).lastName.charAt(2)) > 0)
	   				{
	   					Values _swap = alphaValues.get(i);                  //Raise accuracy
	   					alphaValues.set(i, alphaValues.get(j));
	   					alphaValues.set(j, _swap);
	   				}
	   			}
	   		}
	   		
	   		long time = System.nanoTime() - start;
			diagnostics += "Time(ns) taken to alphabetize: " + time + "\n";
		}
///////////////////Remove and Add donor methods/////////////////////////////////////////////////
		public static void addDonor(ArrayList<Values> alphaValues)
		{
			//This method is responsible for adding a donor to the master ArrayList.
			//The actual process is the exact same one used to read in the original mailing list file.
			//With the exception of course that each object is created through the users input.
			//Once the user inputs each required piece of information the method adds it to the master arraylist.
			long start = System.nanoTime();

			Scanner fs = new Scanner(System.in);                              //This method is essentially a rebuilt version of the proccess described in
			Values v = new Values();										// MainMethodMail.get_name
			//fs.useDelimiter("\\n");
			System.out.println("Enter Donor First Name: ");					//We build an object v, and at the end add it to the array alphaValues
			v.firstName = fs.next();										
			System.out.println("Enter Donor Last Name: ");
			v.lastName = fs.next();
			System.out.println("Enter Donor Address: ");
			v.address = fs.nextLine(); 
			v.address = fs.nextLine();
			System.out.println("Enter Donor City: ");
			v.city = fs.next();
			System.out.println("Enter Donor 2-letter State(i.e. FL for Florida): ");
			v.state = fs.next();
			System.out.println("Enter Donor zipcode: ");
			v.zipcode = fs.nextInt();
			System.out.println("Enter Donor Contribution dollar amount: ");
			v.contribution = fs.nextInt();
			System.out.println("Enter date of donation(mm/dd/yyyy): ");   
			v.date = fs.nextLine();           //Took me awhile to figure out this weird java quirk with fs.nextLine(); requiring a repetition. Very frustrating but so gratifying to figure out.
			v.date = fs.nextLine();
			alphaValues.add(v);
			

			long time = System.nanoTime() - start;
			diagnostics += "Time(ns) taken to add a donor: " + time + "\n";
		}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		public static void removeDonor(ArrayList<Values> alphaValues)
		{
			//This method is responsible for removing a donor from the list.
			//It asks the user for a first and last name. It then loops through the master array, and asks if each object if the first and last name stored within the object
			//matches the user input. If it does, it removes the donor. If it doesn't, the system will print out that the donor does not exist
			//And the program will re-loop.
			long start = System.nanoTime();

			Scanner fs = new Scanner(System.in);
			Values v = new Values();
			System.out.println("Type the first name of the donor you wish to remove: ");
			v.firstName = fs.next();
			String removeFirst = v.firstName;
			System.out.println("Type the last name of the donor you wish to remove: ");
			v.lastName = fs.next();                                                        //This method removes donors by creating two variables and then searching through
			String removeLast = v.lastName;													// the array to find an object that has both identical variables
			boolean validName = false;
			int i = 0;
			while(!validName)
			{
				if(removeFirst.equals(alphaValues.get(i).firstName) && removeLast.equals(alphaValues.get(i).lastName))
				{
					alphaValues.remove(i);
					validName = true;
				}
				else if(i == alphaValues.size() -1 && validName == false )
				{
						System.out.println("Not a valid name, please try again");
						removeDonor(alphaValues);
				}
				i++;
			}
			/*try{
				File f = new File(MailReaderArray.user_input);
				System.out.println(f);
                FileWriter f_writer = new FileWriter(f, false); // false to overwrite
                for(Values p : alphaValues) 
                {
                    f_writer.write( p.firstName + "," +                      //I tried very, very hard to get this method to work(same as method in MailReaderArray class)
                                    p.lastName + "," +						// but sadly no luck. It wipes the txt file clean every time
                                    p.address + "," +						//and I do not know enough about the method to fix this issue.
                                    p.city + "," +
                                    p.state + "," +
                                    p.zipcode + "," +
                                    p.contribution + "," +
                                    p.date + "\n" );
                }
                f_writer.close();  
            }
                catch (IOException e)
            {
                System.err.printf( "Cannot write '%s'. Exiting.", MailReaderArray.user_input);
                System.exit( -1 );
            }*/
			long time = System.nanoTime() - start;
			diagnostics += "Time(ns) taken to collect data on given State: " + time + "\n";
		}
///////////////////////////////////////End of EditArray class////////////////////////////////////////////
}
