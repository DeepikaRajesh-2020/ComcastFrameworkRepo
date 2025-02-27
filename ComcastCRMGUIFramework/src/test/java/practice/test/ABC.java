package practice.test;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass1;

@Listeners(com.comcast.crm.listenerutility.ListenerImp.class)
public class ABC extends BaseClass1{
	@Test
	public void m1()
	{
		System.out.println("hi");
	}
	@Test
	public void m2()
	{
		System.out.println("hello");
	}


}
