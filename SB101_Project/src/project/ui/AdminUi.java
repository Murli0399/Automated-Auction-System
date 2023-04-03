package project.ui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
import project.colors.ConsoleColors;
import project.dao.AdminDAO;
import project.dao.AdminDAOImpl;
import project.exception.NoRecordFoundException;
import project.exception.SomethingWentWrongException;

public class AdminUi {
	static void displayAdminMenu() {
		System.out.println();
		System.out.println(ConsoleColors.YELLOW_BOLD+"		Admin Menu"+ConsoleColors.RESET);
		System.out.println("	1. View All User");
		System.out.println("	2. View All Product");
		System.out.println("	3. View Sold Product");
		System.out.println("	4. Create Auction");
		System.out.println("	5. View Auction History");
		System.out.println("	6. View Transaction");
		System.out.println("	7. View Transaction by Id");
		System.out.println("	8. View Refund Product");
		System.out.println("	0. Logout");
		System.out.println();
		System.out.print(ConsoleColors.CYAN + "		Enter your Selection : " + ConsoleColors.RESET);
	}

	static void viewRefundProduct() {
		AdminDAO dao = new AdminDAOImpl();
		try {
			dao.viewRefundProduct();
		} catch (SomethingWentWrongException | NoRecordFoundException e) {
			System.out.println(ConsoleColors.RED + "		Refund Product Not Available" + ConsoleColors.RESET);
		}
	}
	
	static void viewTransactionById(Scanner sc) {
		System.out.print(ConsoleColors.CYAN + "		Enter Transaction Id : " + ConsoleColors.RESET);
		int tid = sc.nextInt();
		AdminDAO dao = new AdminDAOImpl();

		try {
			dao.viewTransactionById(tid);
		} catch (SomethingWentWrongException | NoRecordFoundException e) {
			System.out
					.println(ConsoleColors.RED + "		Transaction Not Available of this ID" + ConsoleColors.RESET);
		}
	}

	static void viewTransaction(Scanner sc) {
		AdminDAO dao = new AdminDAOImpl();

		try {
			dao.viewTransaction();
			System.out.println(ConsoleColors.CYAN + "		Press 1 for filter by Date Range" + ConsoleColors.RESET);
			System.out.println(
					ConsoleColors.CYAN + "		Press 2 for filter Price in Ascending Order" + ConsoleColors.RESET);
			System.out.println(
					ConsoleColors.CYAN + "		Press 3 for filter Price in Descending Order" + ConsoleColors.RESET);
			System.out.println(ConsoleColors.CYAN + "		Press 0 for Exit");
			System.out.print(ConsoleColors.CYAN + "		Enter Selection : " + ConsoleColors.RESET);

			int x = sc.nextInt();
			if (x == 1) {
				System.out.print(ConsoleColors.CYAN + "		Enter Start Date (YYYY-MM-DD) : " + ConsoleColors.RESET);
				LocalDate startDate = LocalDate.parse(sc.next());
				System.out.print(ConsoleColors.CYAN + "		Enter End Date (YYYY-MM-DD) : " + ConsoleColors.RESET);
				LocalDate endDate = LocalDate.parse(sc.next());
				dao.filterTransactionByDateRange(startDate, endDate);
			} else if (x == 2) {
				dao.filterTransactionAsc();
				System.out.print(ConsoleColors.CYAN + "		Press 1 for Descending Order : " + ConsoleColors.RESET);
				int z = sc.nextInt();
				if (z == 1) {
					dao.filterTransactionDesc();
				}
			} else if (x == 3) {
				dao.filterTransactionDesc();
				System.out.print(ConsoleColors.CYAN + "		Press 1 for Ascending Order : " + ConsoleColors.RESET);
				int z = sc.nextInt();
				if (z == 1) {
					dao.filterTransactionAsc();
				}
			}

		} catch (SomethingWentWrongException | NoRecordFoundException e) {
			System.out.println(ConsoleColors.RED + "		Transaction Not Available" + ConsoleColors.RESET);
		}
	}

	static void viewAuctionHistory() {
		AdminDAO dao = new AdminDAOImpl();

		try {
			dao.viewAuctionHistory();
		} catch (SomethingWentWrongException | NoRecordFoundException e) {
			System.out.println(ConsoleColors.RED + "		Auction Not Available" + ConsoleColors.RESET);
		}
	}

	static void createAuction(Scanner sc) {
		System.out.print("	Enter Product Id : ");
		int id = sc.nextInt();
		AdminDAO dao = new AdminDAOImpl();
		int quantity = -1;
		try {
			quantity = dao.checkQuantity(id);
			if (quantity < 1) {
				System.out.println(
						ConsoleColors.RED + "		Unfortunately, the following item out of stock." + ConsoleColors.RESET);
			} else {
				System.out.print("	Enter Start Date (YYYY-MM-DD) : ");
				LocalDate date = LocalDate.parse(sc.next());
				System.out.print("	Enter Start Time (HH:mm) : ");
				LocalTime st = LocalTime.parse(sc.next());
				System.out.print("	Enter Duration of Auction in hours : ");
				int duration = sc.nextInt();

				try {
					dao.createAuction(id, date, st, duration);
					System.out.println(ConsoleColors.GREEN + "		Auction Created Successfull" + ConsoleColors.RESET);
				} catch (SomethingWentWrongException | NoRecordFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SomethingWentWrongException | NoRecordFoundException ex) {
			System.out.println(ConsoleColors.RED + "		Product Not Available" + ConsoleColors.RESET);
		}

	}

	static void viewSoldProduct(Scanner sc) {
		AdminDAO dao = new AdminDAOImpl();

		try {
			dao.viewSoldProduct();
			System.out.print(
					ConsoleColors.CYAN + "		Press 1 for search by Category else 0 : " + ConsoleColors.RESET);
			int x = sc.nextInt();
			if (x == 1) {
				System.out.print("	Enter Category Id : ");
				int ch = sc.nextInt();
				dao.viewSoldProductByCategory(ch);
			}
		} catch (SomethingWentWrongException | NoRecordFoundException e) {
			System.out.println(ConsoleColors.RED + "		Product Not Available" + ConsoleColors.RESET);
		}
	}

	static void viewProduct(Scanner sc) {
		AdminDAO dao = new AdminDAOImpl();

		try {
			dao.viewProduct();
			System.out.print(
					ConsoleColors.CYAN + "		Press 1 for search by Category else 0 : " + ConsoleColors.RESET);
			int x = sc.nextInt();
			if (x == 1) {
				System.out.print("	Enter Category Id : ");
				int ch = sc.nextInt();
				dao.viewProductByCategory(ch);
			}
		} catch (SomethingWentWrongException | NoRecordFoundException e) {
			System.out.println(ConsoleColors.RED + "		Product Not Available" + ConsoleColors.RESET);
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
		String choice = "0";
		do {

			displayAdminMenu();

			choice = sc.next();

			switch (choice) {
			case "1":
				viewUsers();
				break;
			case "2":
				viewProduct(sc);
				break;
			case "3":
				viewSoldProduct(sc);
				break;
			case "4":
				createAuction(sc);
				break;
			case "5":
				viewAuctionHistory();
				break;
			case "6":
				viewTransaction(sc);
				break;
			case "7":
				viewTransactionById(sc);
				break;
			case "8":
				viewRefundProduct();
				break;
			case "0":
				System.out.println(ConsoleColors.GREEN_BOLD + "		Logout Successful " + ConsoleColors.RESET);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			default:
				System.out.println(
						ConsoleColors.RED_BOLD + "		Invalid choice. Please try again." + ConsoleColors.RESET);
			}

		} while (!choice.equals("0"));
	}

	static void adminLogin(Scanner sc) {
		System.out.print("	Enter Username : ");
		String uname = sc.next();
		System.out.print("	Enter Password : ");
		String psw = sc.next();

		if (uname.equalsIgnoreCase("admin") && psw.equalsIgnoreCase("admin")) {
			System.out.println(ConsoleColors.GREEN_BOLD + "		Welcome Admin" + ConsoleColors.RESET);
			adminMenu(sc);
		} else {
			System.out.println(ConsoleColors.RED_BOLD + "		Invalid Username and Password" + ConsoleColors.RESET);
		}
	}
}
