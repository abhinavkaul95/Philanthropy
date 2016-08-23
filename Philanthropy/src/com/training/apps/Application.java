/**
 * 
 */

package com.training.apps;

import java.util.List;
import java.util.Scanner;

import com.training.domains.Donor;
import com.training.domains.DonorImplementation;
import com.training.domains.ServiceProject;
import com.training.domains.ServiceProjectImplementation;
import com.training.utils.DonorDAO;
import com.training.utils.ProjectDAO;

/**
 * Application to clients.
 * @author akaul5
 *
 */

public class Application {
  /** Main.
  * @param args cmd line arguments
  */
  public static void main(String[] args) {
    final DonorDAO dnrdao = new DonorDAO();
    final ProjectDAO projdao = new ProjectDAO();
    final Scanner scan = new Scanner(System.in);
    System.out.println("Select an option");
    String choice;
    final int ch = scan.nextInt();
    switch (ch) {
      // Testing purposes
      case 0:
        break;

      case 1:
        choice = "y";
        while (choice.equals("y")) {
          System.out.println("Add a donor:");
          final long dnrId = scan.nextLong();
          final String name = scan.next();
          final String emailId = scan.next();
          final Donor dnr = new Donor(dnrId, name, emailId, 1);
          DonorImplementation.addDonor(dnr);
          System.out.println("Do you want to add some more donors:");
          choice = scan.next();
        }
        break;

      case 2:
        choice = "y";
        while (choice.equals("y")) {
          System.out.println("Edit a donor:");
          System.out.println("Enter the donor ID:");
          long dnrId = scan.nextLong();
          System.out.println("Enter the details:");
          String name = scan.next();
          String emailId = scan.next();
          double amountDonated = scan.nextDouble();
          DonorImplementation.updateDonor(dnrId, name, emailId, amountDonated);
          System.out.println("Do you want to edit some more donors:");
          choice = scan.next();
        }
        break;

      case 3:
        choice = "y";
        while (choice.equals("y")) {
          System.out.println("Delete a donor:");
          long dnrId = scan.nextLong();
          DonorImplementation.deleteDonor(dnrId);
          System.out.println("Do you want to delete some more donors:");
          choice = scan.next();
        }
        break;

      case 4:
        choice = "y";
        while (choice.equals("y")) {
          System.out.println("Add a project:");
          final long projectId = scan.nextLong();
          final long projectCost = scan.nextLong();
          final long amountCollected = scan.nextLong();
          final long pendingAmount = scan.nextLong();
          ServiceProject project = new ServiceProject(projectId, projectCost,
              amountCollected, pendingAmount);
          ServiceProjectImplementation.addProject(project);
          System.out.println("Do you want to add some more projects:");
          choice = scan.next();
        }
        break;
  /*
  case 5:
choice = "y";
while(choice.equals("y")){
System.out.println("Add a project details");
long projectID = scan.nextLong();
long projectCost = scan.nextLong();
long amountCollected = scan.nextLong();
long pendingAmount = scan.nextLong();
ServiceProject project = new ServiceProject(projectID, projectCost,
amountCollected, pendingAmount, new ArrayList<Donor>(), Available.AVAILABLE);
ServiceProjectImplementation.addProject(project);
System.out.println("Do you want to add some more projects:");
choice = scan.next();
}
break;
*/
      case 6:
        choice = "y";
        while (choice.equals("y")) {
          System.out.println("Delete a project");
          long projectId = scan.nextLong();
          ServiceProjectImplementation.deleteProject(projectId);
          System.out.println("Do you want to delete some more projects:");
          choice = scan.next();
        }
        break;

      case 7:
        System.out.println("List of all projects and donors");
        List<ServiceProject> projList = projdao.findAll();
        for (ServiceProject p: projList) {
          System.out.println(p);
        }
        break;

      case 8:
        System.out.println("List of all the available projects and donors");
        List<ServiceProject> list = ServiceProjectImplementation.returnAvailableProjectList();
        for (ServiceProject p: list) {
          System.out.println(p);
        }
        break;

      case 9:
        System.out.println("Donate:");
        System.out.println("Your ID:");
        long dnrId = scan.nextLong();
        System.out.println("Project ID of the project you want to donate in:");
        long projectId = scan.nextLong();
        System.out.println("Amount you want to pay:");
        double amount = scan.nextDouble();
        ServiceProject proj = projdao.find(projectId);
        dnrdao.find(dnrId).donate(amount, proj);
        System.out.println(amount + " donated to " + proj);
        break;

      case 10:
        System.out.println("Project ID of the project whose donors you want to see:");
        long projId = scan.nextLong();
        ServiceProject project = projdao.find(projId);
        ServiceProjectImplementation.printDonors(project);
        break;

      default:
        System.out.println("Invalid Choice!");
        break;
    }
    System.out.println("Case " + ch + " ended");
    scan.close();
    dnrdao.close();
    projdao.close();
  }
}
