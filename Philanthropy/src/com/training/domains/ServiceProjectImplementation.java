/**
 * 
 */
package com.training.domains;

import java.util.*;

import com.training.enums.Available;
import com.training.utils.ProjectDAO;

/**
 * @author akaul5
 *
 */
public class ServiceProjectImplementation {
	
	private static List<ServiceProject> projList = new ArrayList<ServiceProject>();
	private static ProjectDAO projdao = new ProjectDAO();
	
	/**
	 * @return the projList
	 */
	public static List<ServiceProject> getProjList() {
		return projList;
	}

	/**
	 * @param projList the projList to set
	 */
	public static void setProjList(List<ServiceProject> projList) {
		/*Iterator<ServiceProject> itr = projList.iterator();
		while(itr.hasNext()){
			ServiceProject proj = itr.next();
			projdao.add(proj);
		}
		ServiceProjectImplementation.projList = projList;
		*/
	}

	public static void addProject(ServiceProject proj){
		projdao.add(proj);
		projList.add(proj);
	}
	
	public static void addProjectDetails(long projectID, long projectCost, double amountCollected, double pendingAmount, List<Donor> dnrList, Available avail){
		ServiceProject proj = new ServiceProject(projectID, projectCost, amountCollected, pendingAmount, dnrList, avail);
		projdao.add(proj);
		projList.add(proj);
	}
	
	public static void deleteProject(long projectId) {
		for(ServiceProject proj: projList){
			if(proj.getProjectID() == projectId){
				projList.remove(proj);
			}
		}
		projdao.delete(projectId);
	}
	
	public static List<ServiceProject> returnAvailableProjectList(){
		//List<ServiceProject> list = new ArrayList<ServiceProject>();
		return projdao.findAll();
		/*Iterator<ServiceProject> itr = projList.iterator();
		while(itr.hasNext()){
			ServiceProject proj = itr.next();
			if(proj.getAvail() == Available.AVAILABLE){
				list.add(proj);
			}
		}*/
	}
	
	public static void printDonors(ServiceProject proj){
		for(Donor d:proj.getDnrList())
			System.out.println(d);
	}
}

