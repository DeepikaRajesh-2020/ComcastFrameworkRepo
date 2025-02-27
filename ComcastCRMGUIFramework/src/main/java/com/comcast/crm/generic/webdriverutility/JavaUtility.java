package com.comcast.crm.generic.webdriverutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	public int getRandomNumber()
	{
		Random random=new Random();
		int randomNumber = random.nextInt(5000);
		return randomNumber;
	}
	public String getSystemDateYYYYMMDD()
	{
		Date dateObj=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(dateObj);
		return date;
	}
	
	public String getRequiredDateYYYYMMDD(int days)
	{
		 SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		    Calendar cal = Calendar.getInstance(); // Correct way to get a Calendar instance
		    cal.add(Calendar.DAY_OF_MONTH, days);
		    return sim.format(cal.getTime());
	}


}
