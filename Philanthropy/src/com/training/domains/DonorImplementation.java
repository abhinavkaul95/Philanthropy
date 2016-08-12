/**
 * 
 */
package com.training.domains;
import java.util.ArrayList;
import java.util.List;
import com.training.utils.DonorDAO;

/**
 * @author akaul5
 *
 */
public class DonorImplementation {
	private static List<Donor> donorList = new ArrayList<Donor>();
	private static DonorDAO dnrdao = new DonorDAO();
	public static boolean addDonor(Donor dnr) {
		boolean bool = false;
		bool = donorList.add(dnr);
		dnrdao.add(dnr);
		return bool;
	}

	public static void updateDonor(long dnrId, String name, String emailId, double amountDonated) {
		for(Donor dnr: donorList){
			if(dnr.getDnrId() == dnrId){
				dnr.setName(name);
				dnr.setEmailId(emailId);
				dnr.setAmountDonated(amountDonated);
			}
		}
		dnrdao.update(dnrId, name, emailId, ((Double)amountDonated).toString());
	}
	
	public static void deleteDonor(long dnrId) {
		for(Donor dnr: donorList){
			if(dnr.getDnrId() == dnrId){
				donorList.remove(dnr);
			}
		}
		dnrdao.delete(dnrId);
	}
	
	public static void printDonorList() {
		for(Donor d: donorList){
			System.out.println(d);
		}
		
	}


	public static List<Donor> getDonorList() {
		return donorList;
	}
}
