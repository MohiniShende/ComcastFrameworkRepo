package com.comcast.crm.generic.fileUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class fileUtility {
	// i will not use main method because it is reusable method
	public String getDataFromPropertiesFile(String key) throws Throwable {
		FileInputStream fis= new FileInputStream("./configAppData/commondata.properties");
		Properties  pobj = new Properties();
		pobj.load(fis);
		String data=pobj.getProperty(key);
		return data;
		
	}

}
