public class Values {
	public String firstName;
    public String lastName; 
    public String address;
    public String city;
    public String state;
    public int zipcode;
    public int contribution;
    public String date;
    //This file simply holds all of the variables I wanted to use in order to organize all of the data.
    //It creates an object type that I can then store into an ArrayList or as an individual value.
    // It also converts it into a neat, printable form. The %s statements define spacing, the ---- act as separators.
     public String toString()
    {
        return String.format( "%s -- %15s -- %30s -- %15s -- %3s -- %6d -- %4d -- %9s",
                              firstName, lastName, address, city, state, zipcode, contribution, date );
    }
}
