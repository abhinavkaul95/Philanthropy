package com.training.sql;

import com.training.domains.Donor;
import com.training.utils.DonorDAO;
import com.training.utils.ProjectDAO;

public class SQLTest {
	public static void main(String[] ar) {
		
		//System.out.println(MySQLConnection.getMyOracleConnection());
		DonorDAO dnrdao = new DonorDAO();
		ProjectDAO prodao = new ProjectDAO();
		int rowAdded;
		Donor dnr;
		int key = 4;
		switch(key){
		case 1:
			rowAdded = dnrdao.add(new Donor(521452, "New Donor 1", "newdonor1@gmail.com", 4545));
			System.out.println(rowAdded);
			break;
			
		case 2:
			rowAdded = dnrdao.update(521452, "New Donor 2", "newdonor2@gmail.com", "4515");
			System.out.println(rowAdded);
			break;
			
		case 3:
			dnr = dnrdao.find(521452);
			System.out.println(dnr);
			break;
		case 4:
			System.out.println("Case 4 started");
			//System.out.println(prodao.findAll(6351112));
			//List<ServiceProject> list = prodao.findAll(6351112);
			//for(ServiceProject p: list)
		//	System.out.println(p.getDnrList());
			//System.out.println(prodao.find(45645425));
			System.out.println(prodao.findAll());
			System.out.println("Case 4 ended");
			break;
		}
	}
}
