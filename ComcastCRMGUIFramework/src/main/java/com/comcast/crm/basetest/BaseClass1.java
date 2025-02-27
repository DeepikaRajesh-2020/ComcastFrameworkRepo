package com.comcast.crm.basetest;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseClass1 {
	
	@BeforeSuite
	public void congifBS()
	{
		System.out.println("BS");
	}
	@BeforeClass
	public void congifBC()
	{
		System.out.println("BC");
	}
	@BeforeMethod
	public void congifBM()
	{
		System.out.println("BM");
	}
	
	@AfterMethod
	public void congifAM()
	{
		System.out.println("AM");
	}
	@AfterClass
	public void congifAC()
	{
		System.out.println("AC");
	}
	@AfterSuite
	public void congifAS()
	{
		System.out.println("AS");
	}

}
