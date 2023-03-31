package project.ui;

import java.util.Scanner;

import project.colors.ConsoleColors;

public class UIMain {

	static void mainMenu() {
		System.out.println("	1. Login as Admin");
		System.out.println("	2. Login as Seller");
		System.out.println("	3. Login as Customer");
		System.out.println("	4. Register as Seller");
		System.out.println("	5. Register as Customer");
		System.out.println("	0. Exit");
		System.out.println();
		System.out.print(ConsoleColors.CYAN + "		Enter Selection : " + ConsoleColors.RESET);
	}

	public static void main(String[] args) {
		System.out.println();
		System.out.println(ConsoleColors.BLUE_BOLD + "		Welcome to Automated Auction System" + ConsoleColors.RESET);
		System.out.println();
		Scanner sc = new Scanner(System.in);

		int choice = 0;

		do {

			mainMenu();
			choice = sc.nextInt();

			switch (choice) {
			case 1:
				AdminUi.adminLogin(sc);
				break;
			case 2:
				SellerUi.sellerLogin(sc);
				break;
			case 3:
				BuyerUi.buyerLogin(sc);
				break;
			case 4:
				SellerUi.registerSeller(sc);
				break;
			case 5:
				BuyerUi.registerBuyer(sc);
				break;
			case 0:
				System.out.println(
						ConsoleColors.GREEN_BOLD + "		Thank you for using our Application" + ConsoleColors.RESET);
				break;
			default:
				System.out.println(ConsoleColors.RED + "		Invalid choice. Please try again." + ConsoleColors.RESET);
			}

		} while (choice != 0);

		sc.close();

	}
}
