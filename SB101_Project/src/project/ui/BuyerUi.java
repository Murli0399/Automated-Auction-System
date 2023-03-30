package project.ui;

import java.util.Scanner;

import project.colors.ConsoleColors;
import project.dao.BuyerDAO;
import project.dao.BuyerDAOImpl;
import project.dto.BuyerDTO;
import project.dto.BuyerDTOImpl;
import project.exception.NoRecordFoundException;
import project.exception.SomethingWentWrongException;

public class BuyerUi {
	static void displayBuyerMenu() {
		System.out.println("	1. View All Products");
		System.out.println("	2. Purchase a Product");
		System.out.println("	3. View Purchase History");
		System.out.println("	4. Update Personal Detail");
		System.out.println("	5. Delete Account");
		System.out.println("	0. Logout");
	}

	static void viewHistory() {
		BuyerDAO dao = new BuyerDAOImpl();
		try {
			dao.viewHistory();
		} catch (SomethingWentWrongException | NoRecordFoundException ex) {

		}
	}

	static void purchaseProduct(Scanner sc) {

		System.out.print("Enter Product Id : ");
		int id = sc.nextInt();

		System.out.print("Enter Quantity : ");
		int q = sc.nextInt();

		BuyerDAO dao = new BuyerDAOImpl();
		int quantity = -1;
		try {
			quantity = dao.checkQuantity(id);
		} catch (SomethingWentWrongException | NoRecordFoundException ex) {
			System.out.println(ex.getMessage());
		}

		int temp = 0;

		if (quantity == 0) {
			System.out.println("Unfortunately, the following items from your order are out of stock.");
		} else if (quantity < q) {
			System.out.println("Only " + quantity + " Product available in the stock.");
			System.out.print("Press 1 for continue this quantity else type 0 for Exit : ");
			temp = sc.nextInt();
		} else {
			temp = 1;
		}

		if (temp == 1) {
			try {
				dao.purchaseProduct(id, q);
				System.out.println("Your Order has been received");

				try {
					Thread.sleep(5000);
					System.out.println("CONGRATULATION! Your order has been placed.");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				System.out.println();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} catch (SomethingWentWrongException | NoRecordFoundException ex) {
				System.out.println(ex.getMessage());
			}
		}

	}

	static void viewProduct() {

		BuyerDAO dao = new BuyerDAOImpl();
		try {
			dao.viewProduct();
		} catch (SomethingWentWrongException | NoRecordFoundException ex) {

		}

	}

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

	static void updatePersonal(Scanner sc) {
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
			dao.updatePersonal(dto);
			System.out.println(ConsoleColors.GREEN + "	Details Updated Successfull" + ConsoleColors.RESET);
		} catch (SomethingWentWrongException ex) {

		}
	}

	static void deleteAccount() {
		BuyerDAO dao = new BuyerDAOImpl();
		try {
			dao.deleteAccount();
			System.out.println(ConsoleColors.GREEN + "	Account Deleted Successfull" + ConsoleColors.RESET);
		} catch (SomethingWentWrongException ex) {

		}
	}

	static void buyerLogin(Scanner sc) {
		if (!login(sc))
			return;

		int choice = 0;
		do {
			displayBuyerMenu();
			System.out.print("	Enter selection : ");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				viewProduct();
				break;
			case 2:
				purchaseProduct(sc);
				break;
			case 3:
				viewHistory();
				break;
			case 4:
				updatePersonal(sc);
				break;
			case 5:
				deleteAccount();
				choice = 0;
				break;
			case 0:
				logout();
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
