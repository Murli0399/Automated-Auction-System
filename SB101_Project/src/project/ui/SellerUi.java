package project.ui;

import java.util.Scanner;

import project.dao.SellerDAO;
import project.dao.SellerDAOImpl;
import project.dto.SellerDTO;
import project.dto.SellerDTOImpl;
import project.exception.NoRecordFoundException;
import project.exception.SomethingWentWrongException;

public class SellerUi {
	static void registerSeller(Scanner sc) {
		sc.nextLine();
		System.out.print("	Enter Your Name : ");
		String name = sc.nextLine();
		System.out.print("	Enter Your Address : ");
		String address = sc.nextLine();
		System.out.print("	Enter Your Mobile Number : ");
		String mobile = sc.nextLine();
		System.out.print("	Enter Your UserName : ");
		String username = sc.nextLine();
		System.out.print("	Enter Your Password : ");
		String password = sc.nextLine();

		SellerDTO dto = new SellerDTOImpl(name, address, mobile, username, password);
		SellerDAO dao = new SellerDAOImpl();
		try {
			dao.registerSeller(dto);
			System.out.println("	Registration Successfull");
		} catch (SomethingWentWrongException ex) {

		}

	}

	static void displaySellerMenu() {
		System.out.println("	1. View All Products");
		System.out.println("	2. Purchase a Product");
		System.out.println("	3. View Order History");
		System.out.println("	4. Update My Name");
		System.out.println("	5. Update My Password");
		System.out.println("	6. Delete My Account");
		System.out.println("	0. Logout");
	}

	static void sellerLogin(Scanner sc) {
		if (!SellerUi.login(sc))
			return;

		int choice = 0;
		do {
			displaySellerMenu();
			System.out.print("	Enter selection : ");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				// productUI.viewAllProducts();
				break;

			case 0:
				SellerUi.logout();
				break;
			default:
				System.out.println("	Invalid Selection, try again");
			}
		} while (choice != 0);
	}

	static boolean login(Scanner sc) {
		System.out.print("	Enter username : ");
		String username = sc.next();
		System.out.print("	Enter password : ");
		String password = sc.next();
		SellerDAO dao = new SellerDAOImpl();
		try {
			dao.login(username, password);
		} catch (SomethingWentWrongException | NoRecordFoundException ex) {
			System.out.println(ex);
			return false;
		}
		return true;
	}

	static void logout() {
		SellerDAO userDAO = new SellerDAOImpl();
		userDAO.logout();
	}
}
