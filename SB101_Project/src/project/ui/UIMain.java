package project.ui;

import java.util.Scanner;

import project.dao.SellerDAO;
import project.dao.SellerDAOImpl;
import project.dto.SellerDTO;
import project.dto.SellerDTOImpl;
import project.exception.SomethingWentWrongException;

public class UIMain {

	static void displayAdminMenu() {
		System.out.println("	0. Logout");
		System.out.println("	1. Add new Category");
		System.out.println("	2. View all products for a Category");
		System.out.println("	3. Add new Product");
		System.out.println("	4. View All Products");
		System.out.println("	5. View all Users");
		System.out.println("	6. View all Orders");
		System.out.print("	Enter your Selection : ");
	}

	static void adminMenu(Scanner sc) {
		int choice = 0;
		do {

			displayAdminMenu();

			choice = sc.nextInt();

			switch (choice) {
			case 1:
				System.out.println("	W1");
				break;
			case 2:
				System.out.println("	W1");
				break;
			case 3:
				System.out.println("	W2");
				break;
			case 0:
				System.out.println("	Thank you ");
				break;
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

	public static void main(String[] args) {
		System.out.println("	Welcome to Automated Auction System");
		Scanner sc = new Scanner(System.in);

		int choice = 0;

		do {

			System.out.println("	1. Login as Admin");
			System.out.println("	2. Login as Seller");
			System.out.println("	3. Login as Customer");
			System.out.println("	4. Register as Seller");
			System.out.println("	5. Register as Customer");
			System.out.println("	0. Exit");

			System.out.print("	Enter your Selection : ");
			choice = sc.nextInt();

			switch (choice) {
			case 1:
				adminLogin(sc);
				break;
			case 2:
				SellerUi.sellerLogin(sc);
				break;
			case 3:
//				buyerLogin(sc);
				break;
			case 4:
				SellerUi.registerSeller(sc);
				break;
			case 5:
				BuyerUi.registerBuyer(sc);
				break;
			case 0:
				System.out.println("	Thank you for using our Application");
				break;
			}

		} while (choice != 0);

	}
}
