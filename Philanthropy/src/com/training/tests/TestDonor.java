package com.training.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.training.domains.*;


public class TestDonor {
	@Test
	public void testNormal(){
		Donor d = new Donor(61564132, "vndjhj", "email@gmail.com", 50000);
		ServiceProject proj = new ServiceProject(543212, 4000000, 62123, 26332);
		long amount = 151;
		d.donate(amount, proj);
	}
	
	//Donating normal
	//Donating 
}
