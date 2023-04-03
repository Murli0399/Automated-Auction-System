package project.ui;

import java.util.Scanner;

import project.colors.ConsoleColors;

public class UIMain {

	static void buyerOperations(Scanner sc) {
		
		String choice = "0";
		do {
			System.out.println();
			System.out.println(ConsoleColors.YELLOW_BOLD+"		Buyer Operation Menu"+ConsoleColors.RESET);
			System.out.println("	1. Login");
			System.out.println("	2. Create Account");
			System.out.println("	3. Forgat Password");
			System.out.println("	0. Go to Main Menu");
			System.out.print(ConsoleColors.CYAN + "		Enter Selection : " + ConsoleColors.RESET);
			
			choice = sc.next();

			switch (choice) {
			case "1":
				BuyerUi.buyerLogin(sc);
				break;
			case "2":
				BuyerUi.registerBuyer(sc);
				break;
			case "3":
				BuyerUi.forgatPassword(sc);
				break;
			case "0":
				System.out.println(ConsoleColors.GREEN_BOLD + "		Thank you" + ConsoleColors.RESET);
				break;
			default:
				System.out
						.println(ConsoleColors.RED + "		Invalid choice. Please try again." + ConsoleColors.RESET);
			}

		} while (!choice.equals("0"));
	}

	static void sellerOperations(Scanner sc) {
		
		String choice = "0";
		do {
			System.out.println();
			System.out.println(ConsoleColors.YELLOW_BOLD+"		Seller Operation Menu"+ConsoleColors.RESET);
			System.out.println("	1. Login");
			System.out.println("	2. Create Account");
			System.out.println("	3. Forgat Password");
			System.out.println("	0. Go to Main Menu");
			System.out.print(ConsoleColors.CYAN + "		Enter Selection : " + ConsoleColors.RESET);

			choice = sc.next();

			switch (choice) {
			case "1":
				SellerUi.sellerLogin(sc);
				break;
			case "2":
				SellerUi.registerSeller(sc);
				break;
			case "3":
				SellerUi.forgatPassword(sc);
				break;
			case "0":
				System.out.println(ConsoleColors.GREEN_BOLD + "		Thank you" + ConsoleColors.RESET);
				break;
			default:
				System.out
						.println(ConsoleColors.RED + "		Invalid choice. Please try again." + ConsoleColors.RESET);
			}

		} while (!choice.equals("0"));

	}

	static void mainMenu() {
		System.out.println();
		System.out.println(ConsoleColors.YELLOW_BOLD+"		Main Menu"+ConsoleColors.RESET);
		System.out.println("	1. Login as Admin");
		System.out.println("	2. Seller Operations");
		System.out.println("	3. Buyer Operations");
		System.out.println("	0. Exit");
		System.out.println();
		System.out.print(ConsoleColors.CYAN + "		Enter Selection : " + ConsoleColors.RESET);
	}

	public static void main(String[] args) {
		System.out.println();
		System.out.println(ConsoleColors.BLUE_BOLD + "		Welcome to Automated Auction System" + ConsoleColors.RESET);
		
		Scanner sc = new Scanner(System.in);

		String choice = "0";

		do {

			mainMenu();
			choice = sc.next();

			switch (choice) {
			case "1":
				AdminUi.adminLogin(sc);
				break;
			case "2":
				sellerOperations(sc);
				break;
			case "3":
				buyerOperations(sc);
				break;
			case "0":
				System.out.println(
						ConsoleColors.GREEN_BOLD + "		Thank you for using our Application" + ConsoleColors.RESET);
				break;
			default:
				System.out
						.println(ConsoleColors.RED + "		Invalid choice. Please try again." + ConsoleColors.RESET);
			}

		} while (!choice.equals("0"));

		sc.close();

	}
}
