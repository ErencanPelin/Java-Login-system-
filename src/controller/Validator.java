package controller;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.LinkedList;

public class Validator
{
    private LinkedList<String> errors;
    
    private final String namePattern = "^[^0-9]+$"; //carrot indicates beginning, $ indicates end of line
    private final String emailPattern = "^[^0-9]+$"; //carrot indicates beginning, $ indicates end of line
    private final String phonePattern = "^[0-9]{8}$"; //carrot indicates beginning, $ indicates end of line
    private final String TFNPattern = "^[0-9]{3}-[0-9]{3}$"; //{3} means 3 repetitions
    private final String paidHoursPattern = "^[0-9]+$";
    private final String hourlyRatePattern = "^[0-9]+.[0-9]+$";
    private final String jobTypePattern = "^[^0-9]+$";
    //more patterns added here
    
    public Validator()
    {
        errors = new LinkedList();
    }
    
    private boolean Validate(String pattern, String input) //will take an input and a pattern and will return true if they match
    {
        Pattern regex = Pattern.compile(pattern);
        Matcher match = regex.matcher(input);
        return match.matches();
    }
    
    public void GenerateErrors(String name, String email, String address, String phone, String TFN, String paidHours, String hourlyRate, String jobType) //called every time we press a button
            //it should compare all text fields and add the error messages up
    {
        errors.clear();
        
        if (!Validate(namePattern, name)) errors.add("Incorrect name pattern!");
        if (!Validate(emailPattern, email)) errors.add("Incorrect email pattern!");
   //     if (!Validate(addressPattern, address)) errors.add("Incorrect address pattern!");
        if (!Validate(phonePattern, phone)) errors.add("Incorrect phone pattern!");
        if (!Validate(TFNPattern, TFN)) errors.add("Incorrect TFN pattern!");
        if (!Validate(paidHoursPattern, paidHours)) errors.add("Incorrect Paid Hours pattern!");
        if (!Validate(hourlyRatePattern, hourlyRate)) errors.add("Incorrect Hourly Rate pattern! Should be <##.##>");
        if (!Validate(jobTypePattern, jobType)) errors.add("Incorrect job type pattern!");
    }
    
    public LinkedList<String> Errors() //getter
    { 
        return this.errors; 
    }
}
