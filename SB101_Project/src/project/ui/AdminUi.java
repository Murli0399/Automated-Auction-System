package project.ui;

import java.util.Scanner;

import project.dao.AdminDAO;
import project.dao.AdminDAOImpl;
import project.exception.NoRecordFoundException;
import project.exception.SomethingWentWrongException;

public class AdminUi {
	static void displayAdminMenu() {
		System.out.println("	1. View All User");
		System.out.println("	2. View All Product");
		System.out.println("	3. View Sold Product");
		System.out.println("	4. View Transaction");
		System.out.println("	5. View Transaction by Id");
		System.out.println("	6. View Refund Product");
		System.out.println("	0. Logout");
		System.out.println();
		System.out.print("	Enter your Selection : ");
	}

	static void viewSoldProduct(Scanner sc) {
		AdminDAO dao = new AdminDAOImpl();

		try {
			dao.viewSoldProduct();
			System.out.print("Press 1 for search by Category : ");
			int x = sc.nextInt();
			if (x == 1) {
				System.out.print("Enter Category Id : ");
				int ch = sc.nextInt();
				dao.viewSoldProductByCategory(ch);
			}
		} catch (SomethingWentWrongException | NoRecordFoundException e) {
			System.out.println("Product Not Available");
		}
	}

	static void viewProduct(Scanner sc) {
		AdminDAO dao = new AdminDAOImpl();

		try {
			dao.viewProduct();
			System.out.print("Press 1 for search by Category : ");
			int x = sc.nextInt();
			if (x == 1) {
				System.out.print("Enter Category Id : ");
				int ch = sc.nextInt();
				dao.viewProductByCategory(ch);
			}
		} catch (SomethingWentWrongException | NoRecordFoundException e) {
			System.out.println("Product Not Available");
		}
	}

	static void viewUsers() {
		AdminDAO dao = new AdminDAOImpl();

		try {
			dao.viewUsersSeller();
			dao.viewUsersBuyer();
		} catch (SomethingWentWrongException | NoRecordFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	static void adminMenu(Scanner sc) {
		int choice = 0;
		do {

			displayAdminMenu();

			choice = sc.nextInt();

			switch (choice) {
			case 1:
				viewUsers();
				break;
			case 2:
				viewProduct(sc);
				break;
			case 3:
				viewSoldProduct(sc);
				break;
			case 0:
				System.out.println("	Thank you ");
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}

		} while (choice != 0);
	}

	static void adminLogin(Scanner sc) {
		System.out.print("	Enter Username : ");
		String uname = sc.next();
		System.out.print("	Enter Password : ");
		String psw = sc.next();

		if (uname.equalsIgnoreCase("admin") && psw.equalsIgnoreCase("admin")) {
			System.out.println("	Welcome to Our System");
			adminMenu(sc);
		} else {
			System.out.println("	Invalid Username and Password");
		}
	}
}
