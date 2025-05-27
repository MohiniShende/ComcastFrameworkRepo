package com.comcast.crm.generic.webDriverUtility;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	public int  getRandomNumber() {
		// create object for random class
		Random random= new Random();
		int randomnumber = random.nextInt(5000);// in this boundary it will pick one of the number
		return randomnumber;// this random number is requird for testscript developer
	}
	public String getSystemDateyyyyMMdd() { // if want currnt date no need to pass argument
		Date dateObj = new Date();
		// complete date information
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
		String date = sdf.format(dateObj);
	    return date;
	    
		
	}
	
	public String getRequiredDateyyyMMdd(int days) {// if want required date may be + - parametr is requird
		 Date dateobj = new Date();
		  // System.out.println(dateobj.toString());// it will capture today date and time
		    SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		    String satrtactDate = sim.format(dateobj);
		   
		    //SimpleDateFormat sim1 = new SimpleDateFormat("yyyy-mm-dd");
		   Calendar cal = sim.getCalendar();
		   //cal.add(Calendar.DAY_OF_MONTH,-30);//before 30 date 2025-04-13
		   cal.add(Calendar.DAY_OF_MONTH,days);// after adding 30 days
		   String reqDate = sim.format(cal.getTime());//before 30 date 2025-06-12
		return reqDate;
		  
	}
		
		
		
		
	}


