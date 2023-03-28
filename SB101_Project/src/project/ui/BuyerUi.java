package project.ui;

import java.util.Scanner;

import project.dao.BuyerDAO;
import project.dao.BuyerDAOImpl;
import project.dto.BuyerDTO;
import project.dto.BuyerDTOImpl;
import project.exception.NoRecordFoundException;
import project.exception.SomethingWentWrongException;

public class BuyerUi {
	static void registerBuyer(Scanner sc) {
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

		BuyerDTO dto = new BuyerDTOImpl(name, address, mobile, username, password);
		BuyerDAO dao = new BuyerDAOImpl();
		try {
			dao.registerBuyer(dto);
			System.out.println("	Registration Successfull");
		} catch (SomethingWentWrongException ex) {

		}

	}

	static void displayBuyerMenu() {
		System.out.println("	1. View All Products");
		System.out.println("	2. Purchase a Product");
		System.out.println("	3. View Order History");
		System.out.println("	4. Update My Name");
		System.out.println("	5. Update My Password");
		System.out.println("	6. Delete My Account");
		System.out.println("	0. Logout");
	}

	static void buyerLogin(Scanner sc) {
		if (!BuyerUi.login(sc))
			return;

		int choice = 0;
		do {
			displayBuyerMenu();
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
		BuyerDAO dao = new BuyerDAOImpl();
		try {
			dao.login(username, password);
		} catch (SomethingWentWrongException | NoRecordFoundException ex) {
			System.out.println(ex);
			return false;
		}
		return true;
	}

	static void logout() {
		BuyerDAO userDAO = new BuyerDAOImpl();
		userDAO.logout();
	}
}
