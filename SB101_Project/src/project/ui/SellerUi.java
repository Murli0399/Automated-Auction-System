package project.ui;

import java.util.Scanner;

import project.colors.ConsoleColors;
import project.dao.SellerDAO;
import project.dao.SellerDAOImpl;
import project.dao.StaticVar;
import project.dto.ProductDTO;
import project.dto.ProductDTOImpl;
import project.dto.SellerDTO;
import project.dto.SellerDTOImpl;
import project.exception.NoRecordFoundException;
import project.exception.SomethingWentWrongException;

public class SellerUi {

	static void displaySellerMenu() {
		System.out.println();
		System.out.println(ConsoleColors.YELLOW_BOLD+"		Seller Menu"+ConsoleColors.RESET);
		System.out.println("	1. View All Products");
		System.out.println("	2. Add Product");
		System.out.println("	3. Update Product");
		System.out.println("	4. Delete Product");
		System.out.println("	5. View Transactions");
		System.out.println("	6. Update Your Profile");
		System.out.println("	7. Delete Account");
		System.out.println("	0. Logout");

		System.out.print(ConsoleColors.CYAN + "		Enter Selection : " + ConsoleColors.RESET);
	}

	static void forgatPassword(Scanner sc) {

		SellerDAO dao = new SellerDAOImpl();
		try {
			dao.forgatPassword(sc);
		} catch (SomethingWentWrongException | NoRecordFoundException ex) {
			System.out.println(ConsoleColors.RED_BOLD + "		UserName Not Found" + ConsoleColors.RESET);
		}

	}

	static void addProduct(Scanner sc) {
		sc.nextLine();
		System.out.print("	Enter Product Name : ");
		String name = sc.nextLine();
		System.out.print("	Enter Product Price : ");
		String price = sc.nextLine();
		System.out.print("	Enter Product Quantity : ");
		String quantity = sc.nextLine();
		System.out.print("	Enter Category Id : ");
		int cid = sc.nextInt();

		ProductDTO dto = new ProductDTOImpl(name, Double.parseDouble(price), Integer.parseInt(quantity), cid);
		SellerDAO dao = new SellerDAOImpl();
		try {
			dao.addProduct(dto);
			System.out.println(ConsoleColors.GREEN_BOLD + "		Product Added Successful" + ConsoleColors.RESET);
		} catch (SomethingWentWrongException ex) {

		}

	}

	static void updateProduct(Scanner sc) {
		System.out.print("	Enter Product Id : ");
		int pid = sc.nextInt();
		sc.nextLine();
		System.out.print("	Enter Product Name : ");
		String name = sc.nextLine();
		System.out.print("	Enter Product Price : ");
		String price = sc.nextLine();
		System.out.print("	Enter Product Quantity : ");
		String quantity = sc.nextLine();

		ProductDTO dto = new ProductDTOImpl(name, Double.parseDouble(price), Integer.parseInt(quantity));
		SellerDAO dao = new SellerDAOImpl();
		try {
			dao.updateProduct(pid, dto);
			System.out.println(ConsoleColors.GREEN + "		Product Updated Successful" + ConsoleColors.RESET);
		} catch (SomethingWentWrongException ex) {

		}
	}

	static void deleteProduct(Scanner sc) {
		System.out.print("	Enter Product Id : ");
		int pid = sc.nextInt();
		SellerDAO dao = new SellerDAOImpl();
		try {
			dao.deleteProduct(pid);
			System.out.println(ConsoleColors.GREEN_BOLD + "		Product Deleted Successful" + ConsoleColors.RESET);
		} catch (SomethingWentWrongException ex) {

		}
	}

	static void viewProduct() {

		SellerDAO dao = new SellerDAOImpl();
		try {
			dao.viewProduct();
		} catch (SomethingWentWrongException | NoRecordFoundException ex) {
			System.out.println(ConsoleColors.RED_BOLD + "		You Not Have Any Product" + ConsoleColors.RESET);
		}

	}

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
			System.out.println(ConsoleColors.GREEN_BOLD + "		Registration Successfull" + ConsoleColors.RESET);
		} catch (SomethingWentWrongException ex) {
			System.out.println(ConsoleColors.RED_BOLD + "		Username Already Exists" + ConsoleColors.RESET);
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

		SellerDTO dto = new SellerDTOImpl(name, address, mobile, username, password);
		SellerDAO dao = new SellerDAOImpl();
		try {
			dao.updatePersonal(dto);
			System.out.println(ConsoleColors.GREEN_BOLD + "		You Have Successfully Updated Your Profile"
					+ ConsoleColors.RESET);
		} catch (SomethingWentWrongException ex) {

		}
	}

	static void deleteAccount() {
		SellerDAO dao = new SellerDAOImpl();
		try {
			dao.deleteAccount();
			System.out.println(
					ConsoleColors.GREEN + "		Your Account Has Been Successfully Deleted" + ConsoleColors.RESET);
		} catch (SomethingWentWrongException ex) {

		}
	}

	static void viewTransaction() {
		SellerDAO dao = new SellerDAOImpl();
		try {
			dao.viewTransaction();
		} catch (SomethingWentWrongException | NoRecordFoundException ex) {
			System.out.println(ConsoleColors.RED_BOLD + "		You Not Have Any Sold Product" + ConsoleColors.RESET);
		}
	}
	
	static void sellerLogin(Scanner sc) {
		if (!SellerUi.login(sc))
			return;

		String choice = "0";
		do {
			displaySellerMenu();
			choice = sc.next();
			switch (choice) {
			case "1":
				viewProduct();
				break;
			case "2":
				addProduct(sc);
				break;
			case "3":
				updateProduct(sc);
				break;
			case "4":
				deleteProduct(sc);
				break;
			case "5":
				viewTransaction();
				break;
			case "6":
				updatePersonal(sc);
				break;
			case "7":
				deleteAccount();
				choice = "0";
				break;

			case "0":
				logout();
				System.out.print(ConsoleColors.GREEN + "		Logout Successful" + ConsoleColors.RESET);
				break;
			default:
				System.out
						.println(ConsoleColors.RED + "		Invalid choice. Please try again." + ConsoleColors.RESET);
			}
		} while (!choice.equals("0"));
	}

	static boolean login(Scanner sc) {
		System.out.print("	Enter username : ");
		String username = sc.next();
		System.out.print("	Enter password : ");
		String password = sc.next();
		SellerDAO dao = new SellerDAOImpl();
		try {
			dao.login(username, password);
			System.out.println(
					ConsoleColors.GREEN_BOLD + "		Welcome " + StaticVar.LoggedInSellerName + ConsoleColors.RESET);
		} catch (SomethingWentWrongException | NoRecordFoundException ex) {
			System.out.println(ConsoleColors.RED + "		Invalid username or password" + ConsoleColors.RESET);
			return false;
		}
		return true;
	}

	static void logout() {
		SellerDAO userDAO = new SellerDAOImpl();
		userDAO.logout();
	}
}
