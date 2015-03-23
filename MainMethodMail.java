import java.util.*;
import java.io.IOException;

public class MainMethodMail ////////////////////////////////////////////////////////////////////////////////////////////
{																				//This MainMethodMail class contains every call for each individual created method
																				//The reason I did it this way is that it helps to keep the different methods organized
	 public static void main(String[] args)										
	 {																			//The alphaValues ArrayList is the universal ArrayList from which all values are drawn
	    	ArrayList<Values> alphaValues = MailReaderArray.main_database();	//It is created from calling the main_database method, which reads the Mail_List.txt 
	    	boolean running = true;		
	    	Scanner sc = new Scanner(System.in);										//file into the alphaValues array.
	    	while(running)      //----------------------------------------------This while loop keeps the program in a constant running state, refreshing the program
	    	{																	//so that the program does not exit as soon as the user has put in a single input
		    								
		    	System.out.print("1.Alphabetize the list and print \n" + 
		    						"2.Add a donor\n" +
		    						"3.Remove a donor \n" +
		    						"4.Find all individuals who donated more than 'X' amount\n" +    //-----Here I create a GUI of sorts. 
		    						"5.Find all Families who donated more than 'X' amount\n"+		 // The user reads the options, and is then told to
		    						"6.Print the top ten individual who donated the most\n"+		// select whatever process he desires.
		    						"7.Search for all donors within a given zipcode\n"+
		    						"8.Search for all donors within a given state\n"+
		    						"9.Search for data on donors within a given zipcode\n"+
		    						"10.Search for data on donors within a given state\n"+
		    						"11.Top ten families who donated the most\n"+
		    						"12.Time taken to run processes\n" +
		    						"13. Exit Program\n"
		    											);
				System.out.println("\nEnter in the number of the proccess you would like to execute: ");
				/*try{System.out.println(System.in.available());}
				catch(IOException e){System.out.println(e);}<----------------- Used this function to resolve issue described below next to the addDonor*/

				int command = sc.nextInt();

				if(command == 1)											//Through a series of if and else if statements I created a 
				{															//system in which the program asks what input the user chose,
					EditArray.alphabetizer(alphaValues);					//and then selects to run only the chosen processes.
					for(Values alpha: alphaValues)
					{
						System.out.println(alpha);
					}
					//This first if statement calls the alphabetizing method if the user inputs 1
				}
				else if(command == 2)                                            
				{																
					//This calls the addDonor function, if the user inputs 2															
					EditArray.addDonor(alphaValues);							
					EditArray.alphabetizer(alphaValues);						
					for(Values beta: alphaValues)								
					{															
						System.out.println(beta);								
					}															
				}																
				else if(command ==3)
				{
					EditArray.removeDonor(alphaValues);                        
					EditArray.alphabetizer(alphaValues);
					//This calls the remove donor function, if the user inputs 3
					/*for(Values gamma: alphaValues)
					{
						System.out.println(gamma);
					}*/
					//MailReaderArray.file_writer_false(alphaValues);
				}
				else if(command == 4)
				{
					//If the user enters 4 they call the method responsible for finding all individuals who contributed more than the user inputted amount
					ArrayList<Values> indivLargerThanXArray = new ArrayList<Values>(alphaValues); //This method prompts the user to select any int, and
					EditArray.indivLargerThanX(indivLargerThanXArray);                            //the method outputs all donors who's contributions were larger than the selected int.
				}
				else if(command == 5)
				{
					//If the user enters 5, the method responsible for finding all families that donated more than a specified amount is called.
					ArrayList<Values> greaterThanXFamilyArray = new ArrayList<Values>(alphaValues);//Same concept as above, except we only look at aggregate families.
					EditArray.greaterThanXFamily(greaterThanXFamilyArray);		
				}
				else if(command == 6)
				{//If the user enters 6, the method that prints the top ten contributors is called. 
					ArrayList<Values> topTenDonorsArray = new ArrayList<Values>(alphaValues);//Prints out the top ten donors who contributed the most.
					EditArray.topTenDonors(topTenDonorsArray);			}
				else if(command == 7)
				{//If the user enters 7, the method that finds all donors within a certain zipcode is called
					ArrayList<Values> zipCodeSearchArray = new ArrayList<Values>(alphaValues);//Prints out all donors found in a certain zipcode
					EditArray.zipCodeSearch(zipCodeSearchArray);
				}
				else if(command == 8)
				{
					//if the user enters 8, the method that finds all donors within a certain state is called.
					ArrayList<Values> stateSearchArray = new ArrayList<Values>(alphaValues);//Prints out all donors found in a certain state
					EditArray.stateSearch(stateSearchArray);
				}
				else if(command == 9)
				{
					//If the user enters 9, all data on the specified zip code is printed out. 
					ArrayList<Values> dataCollectionZipArray = new ArrayList<Values>(alphaValues);//Prints out the average, total donors, total contribution, 
					EditArray.dataCollectionZip(dataCollectionZipArray);						//and median of the donations in a certain zip
				}
				else if(command == 10)
					//If the user enters 10, all data on the specified state is printed out.
				{
					ArrayList<Values> dataCollectionStateArray = new ArrayList<Values>(alphaValues);//Same as above, except applied to those found in a certain state
					EditArray.dataCollectionState(dataCollectionStateArray);
				}
				else if(command ==11)
				{
					//if the user enters 11, the top ten contributing families are printed out.
					ArrayList<Values> topTenFamiliesArray = new ArrayList<Values>(alphaValues);//Prints out the top ten families who donated the most.
					EditArray.topTenFamilies(topTenFamiliesArray);
				}
				else if(command ==12)
				{
					//If the user enters 12, the runtime diagnositcs for each method that has been run is printed out.
					System.out.println(EditArray.diagnostics);
				}
				else if(command ==13)//This allows the User to quit the program and end the loop, if they enter 13.
				{
					running = false;
				}

		}
		sc.close();
	}
}
