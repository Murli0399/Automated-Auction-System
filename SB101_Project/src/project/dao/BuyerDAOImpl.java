package project.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import project.colors.ConsoleColors;
import project.dto.BuyerDTO;
import project.dto.ProductDTO;
import project.dto.ProductDTOImpl;
import project.exception.NoRecordFoundException;
import project.exception.SomethingWentWrongException;
import project.util.DBUtils;

public class BuyerDAOImpl implements BuyerDAO {

	public void viewProduct() throws SomethingWentWrongException, NoRecordFoundException {
		Connection conn = null;
		try {
			conn = DBUtils.getConnectionTodatabase();
			String query = "SELECT product_id, seller_id, p.name, price, quantity, c.name from product p join category c ON p.category_id = c.id where is_deleted = 0";
			PreparedStatement ps = conn.prepareStatement(query);

			ResultSet rs = ps.executeQuery();
			if (DBUtils.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("	Product Not Found");
			}

			while (rs.next()) {
				System.out.print("	Product Id : " + rs.getInt(1));
				System.out.print(", Seller Id : " + rs.getInt(2));
				System.out.print(", Product Name : " + rs.getString(3));
				System.out.print(", Price : " + rs.getDouble(4));
				System.out.print(", Quantity : " + rs.getInt(5));
				System.out.print(", Category : " + rs.getString(6));
				System.out.println();
			}

		} catch (ClassNotFoundException | SQLException ex) {
			throw new SomethingWentWrongException("Unable to find product");
		} finally {
			try {
				DBUtils.closeConnection(conn);
			} catch (SQLException ex) {

			}
		}
	}

	public void login(String username, String password) throws SomethingWentWrongException, NoRecordFoundException {
		Connection conn = null;
		try {
			conn = DBUtils.getConnectionTodatabase();
			String query = "SELECT buyerId, buyerName FROM buyer WHERE username = ? AND password = ? AND is_deleted = 0";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (DBUtils.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("	Invalid username or password");
			}

			rs.next();
			StaticVar.LoggedInBuyerId = rs.getInt(1);
			StaticVar.LoggedInBuyerName = rs.getString(2);
		} catch (ClassNotFoundException | SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
			try {
				DBUtils.closeConnection(conn);
			} catch (SQLException ex) {

			}
		}
	}

	public void logout() {
		StaticVar.LoggedInBuyerId = 0;
	}

	@Override
	public void registerBuyer(BuyerDTO obj) throws SomethingWentWrongException {
		Connection conn = null;
		try {

			conn = DBUtils.getConnectionTodatabase();

			String query = "INSERT INTO buyer(buyerName, address, mobileNo, username, password) VALUES(?,?,?,?,?)";

			PreparedStatement ps = conn.prepareStatement(query);

			ps.setString(1, obj.getName());
			ps.setString(2, obj.getAddress());
			ps.setString(3, obj.getMobile());
			ps.setString(4, obj.getUsername());
			ps.setString(5, obj.getPassword());
			ps.executeUpdate();

		} catch (ClassNotFoundException | SQLException ex) {
			throw new SomethingWentWrongException("		Unable to Add Details");
		} finally {
			try {
				DBUtils.closeConnection(conn);
			} catch (SQLException ex) {

			}
		}

	}

	@Override
	public void updatePersonal(BuyerDTO obj) throws SomethingWentWrongException {
		Connection conn = null;
		try {

			conn = DBUtils.getConnectionTodatabase();

			String query = "UPDATE buyer SET buyerName = ?, address = ?, mobileNo = ?, username = ?, password = ? where buyerId = ?";

			PreparedStatement ps = conn.prepareStatement(query);

			ps.setString(1, obj.getName());
			ps.setString(2, obj.getAddress());
			ps.setString(3, obj.getMobile());
			ps.setString(4, obj.getUsername());
			ps.setString(5, obj.getPassword());
			ps.setInt(6, StaticVar.LoggedInBuyerId);
			ps.executeUpdate();

		} catch (ClassNotFoundException | SQLException ex) {
			throw new SomethingWentWrongException("		Unable to Update Details");
		} finally {
			try {
				DBUtils.closeConnection(conn);
			} catch (SQLException ex) {

			}
		}
	}

	@Override
	public void deleteAccount() throws SomethingWentWrongException {
		Connection conn = null;
		try {

			conn = DBUtils.getConnectionTodatabase();

			String query = "UPDATE buyer SET is_deleted = 1 where buyerId = ?";

			PreparedStatement ps = conn.prepareStatement(query);

			ps.setInt(1, StaticVar.LoggedInBuyerId);
			ps.executeUpdate();

		} catch (ClassNotFoundException | SQLException ex) {
			throw new SomethingWentWrongException("Account Not Deleted");
		} finally {
			try {
				DBUtils.closeConnection(conn);
			} catch (SQLException ex) {

			}
		}
	}

	@Override
	public int checkQuantity(int id) throws SomethingWentWrongException, NoRecordFoundException {
		Connection conn = null;
		int quantity = 0;
		try {
			conn = DBUtils.getConnectionTodatabase();
			String query = "SELECT quantity from product where product_id = ? AND is_deleted = 0";
			PreparedStatement ps = conn.prepareStatement(query);

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			if (DBUtils.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("	Product Not Found");
			}

			while (rs.next()) {
				quantity = rs.getInt(1);
			}

		} catch (ClassNotFoundException | SQLException ex) {
			throw new SomethingWentWrongException("Quantity not found");
		} finally {
			try {
				DBUtils.closeConnection(conn);
			} catch (SQLException ex) {

			}
		}
		return quantity;
	}

	@Override
	public List<ProductDTO> purchaseProduct(int id, int q) throws SomethingWentWrongException, NoRecordFoundException {
		Connection conn = null;
		List<ProductDTO> list;
		try {
			conn = DBUtils.getConnectionTodatabase();
			String query = "SELECT seller_id, price, quantity, category_id, name from product where product_id = ? AND is_deleted = 0";
			PreparedStatement ps = conn.prepareStatement(query);

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			if (DBUtils.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("	Product Not Found");
			}

			int sellerId = 0;
			double price = 0;
			int quantity = 0;
			int categoryId = 0;
			String name = null;
			while (rs.next()) {
				sellerId = rs.getInt(1);
				price = rs.getDouble(2);
				quantity = rs.getInt(3);
				categoryId = rs.getInt(4);
				name = rs.getString(5);
			}

			quantity = quantity - q;

			String query1 = "UPDATE product SET quantity = ?, sold_status = 1 where product_id = ?";
			PreparedStatement ps1 = conn.prepareStatement(query1);
			ps1.setInt(1, quantity);
			ps1.setInt(2, id);
			ps1.executeUpdate();
			list = new ArrayList<>();
			price = price * q;
			int percent = 0;
			if (categoryId == 101) {
				percent = 5;
			} else if (categoryId == 102) {
				percent = 7;
			} else if (categoryId == 103) {
				percent = 9;
			} else if (categoryId == 102) {
				percent = 12;
			}
			ProductDTO dto = new ProductDTOImpl(name, price, percent);
			list.add(dto);
			String query2 = "INSERT into transaction (seller_id, buyer_id, item_id, price, quantity, transaction_date) VALUES (?,?,?,?,?,?)";
			PreparedStatement ps2 = conn.prepareStatement(query2);
			ps2.setInt(1, sellerId);
			ps2.setInt(2, StaticVar.LoggedInBuyerId);
			ps2.setInt(3, id);
			ps2.setDouble(4, price);
			ps2.setInt(5, q);
			ps2.setDate(6, Date.valueOf(LocalDate.now()));

			ps2.executeUpdate();

		} catch (ClassNotFoundException | SQLException ex) {
			throw new SomethingWentWrongException("Product not Purchased");
		} finally {
			try {
				DBUtils.closeConnection(conn);
			} catch (SQLException ex) {

			}
		}
		return list;
	}

	@Override
	public void viewHistory() throws SomethingWentWrongException, NoRecordFoundException {
		Connection conn = null;
		try {
			conn = DBUtils.getConnectionTodatabase();
			String query = "SELECT t.transaction_id, p.name, t.price, t.quantity, t.transaction_date, t.seller_id from transaction t join product p ON t.item_id = p.product_id where buyer_id = ? AND t.is_deleted = 0";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, StaticVar.LoggedInBuyerId);
			ResultSet rs = ps.executeQuery();
			if (DBUtils.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("	Product Not Found");
			}

			while (rs.next()) {
				System.out.print("	Transaction Id : " + rs.getInt(1));
				System.out.print(", Product Name : " + rs.getString(2));
				System.out.print(", Price : " + rs.getDouble(3));
				System.out.print(", Quantity : " + rs.getInt(4));
				System.out.print(", Transaction Date : " + rs.getDate(5));
				System.out.print(", Seller Id : " + rs.getInt(6));
				System.out.println();
			}

		} catch (ClassNotFoundException | SQLException ex) {
			throw new SomethingWentWrongException("View History");
		} finally {
			try {
				DBUtils.closeConnection(conn);
			} catch (SQLException ex) {

			}
		}
	}

	@Override
	public void viewAuction() throws SomethingWentWrongException, NoRecordFoundException {
		Connection conn = null;
		try {
			conn = DBUtils.getConnectionTodatabase();
			String query = "SELECT auction_id, seller_id, product_id, price, date, start_time, end_time, buyer_id from auction where is_deleted = 0";
			PreparedStatement ps = conn.prepareStatement(query);

			ResultSet rs = ps.executeQuery();
			if (DBUtils.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("	Auction Not Avilable");
			}

			while (rs.next()) {

				int auctionID = rs.getInt(1);
				int sellerId = rs.getInt(2);
				int productId = rs.getInt(3);
				double price = rs.getDouble(4);

				LocalDate nowDate = LocalDate.now();
				LocalDate aucDate = rs.getDate(5).toLocalDate();

				LocalTime nowTime = LocalTime.now();
				LocalTime aucStartTime = rs.getTime(6).toLocalTime();
				LocalTime aucEndTime = rs.getTime(7).toLocalTime();

				int buyerId = rs.getInt(8);

				if (aucDate.compareTo(nowDate) == 0) {
					if (nowTime.compareTo(aucStartTime) < 0 && nowTime.compareTo(aucEndTime) < 0) {
						System.out.print("	Auction Id : " + auctionID);
						System.out.print(", Seller Id : " + sellerId);
						System.out.print(", Product Id : " + productId);
						System.out.print(", Price : " + price);
						System.out.print(", Date : " + aucDate);
						System.out.print(", Start Time : " + aucStartTime);
						System.out.print(", End Time : " + aucEndTime);
						if (buyerId == 0) {
							System.out.print(ConsoleColors.RED + ", Not Purchased" + ConsoleColors.RESET);
						} else {
							System.out.print(ConsoleColors.GREEN + ", Buyer Id = " + buyerId + ConsoleColors.RESET);
						}
						System.out.println(ConsoleColors.YELLOW + ", Not Started" + ConsoleColors.RESET);
					} else if (nowTime.compareTo(aucStartTime) >= 0 && nowTime.compareTo(aucEndTime) < 0) {
						System.out.print("	Auction Id : " + auctionID);
						System.out.print(", Seller Id : " + sellerId);
						System.out.print(", Product Id : " + productId);
						System.out.print(", Price : " + price);
						System.out.print(", Date : " + aucDate);
						System.out.print(", Start Time : " + aucStartTime);
						System.out.print(", End Time : " + aucEndTime);
						if (buyerId == 0) {
							System.out.print(ConsoleColors.RED + ", Not Purchased" + ConsoleColors.RESET);
						} else {
							System.out.print(ConsoleColors.GREEN + ", Buyer Id = " + buyerId + ConsoleColors.RESET);
						}
						System.out.println(ConsoleColors.GREEN + ", Running" + ConsoleColors.RESET);
					}
				} else if (aucDate.compareTo(nowDate) > 0) {
					System.out.print("	Auction Id : " + auctionID);
					System.out.print(", Seller Id : " + sellerId);
					System.out.print(", Product Id : " + productId);
					System.out.print(", Price : " + price);
					System.out.print(", Date : " + aucDate);
					System.out.print(", Start Time : " + aucStartTime);
					System.out.print(", End Time : " + aucEndTime);
					if (buyerId == 0) {
						System.out.print(ConsoleColors.RED + ", Not Purchased" + ConsoleColors.RESET);
					} else {
						System.out.print(ConsoleColors.GREEN + ", Buyer Id = " + buyerId + ConsoleColors.RESET);
					}
					System.out.println(ConsoleColors.YELLOW + ", Not Started" + ConsoleColors.RESET);
				}
			}

		} catch (ClassNotFoundException | SQLException ex) {
			throw new SomethingWentWrongException("Auction Not Available");
		} finally {
			try {
				DBUtils.closeConnection(conn);
			} catch (SQLException ex) {

			}
		}
	}

	@Override
	public void createBid() throws SomethingWentWrongException, NoRecordFoundException {
		Connection conn = null;
		try {
			conn = DBUtils.getConnectionTodatabase();
			String query = "SELECT auction_id, seller_id, product_id, price, date, start_time, end_time, buyer_id from auction where is_deleted = 0";
			PreparedStatement ps = conn.prepareStatement(query);

			ResultSet rs = ps.executeQuery();
			if (DBUtils.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("	Auction Not Avilable");
			}

			while (rs.next()) {

				int auctionID = rs.getInt(1);
				int sellerId = rs.getInt(2);
				int productId = rs.getInt(3);
				double price = rs.getDouble(4);

				LocalDate nowDate = LocalDate.now();
				LocalDate aucDate = rs.getDate(5).toLocalDate();

				LocalTime nowTime = LocalTime.now();
				LocalTime aucStartTime = rs.getTime(6).toLocalTime();
				LocalTime aucEndTime = rs.getTime(7).toLocalTime();

				int buyerId = rs.getInt(8);

				if (aucDate.compareTo(nowDate) == 0) {
					if (nowTime.compareTo(aucStartTime) >= 0 && nowTime.compareTo(aucEndTime) < 0) {
						System.out.print("	Auction Id : " + auctionID);
						System.out.print(", Seller Id : " + sellerId);
						System.out.print(", Product Id : " + productId);
						System.out.print(", Price : " + price);
						System.out.print(", Date : " + aucDate);
						System.out.print(", Start Time : " + aucStartTime);
						System.out.print(", End Time : " + aucEndTime);
						if (buyerId == 0) {
							System.out.print(ConsoleColors.RED + " Not Purchased" + ConsoleColors.RESET);
						} else {
							System.out.print(ConsoleColors.GREEN + " Buyer Id = " + buyerId + ConsoleColors.RESET);
						}
						System.out.print(ConsoleColors.GREEN + " Running" + ConsoleColors.RESET);
					}
				}

			}

		} catch (ClassNotFoundException | SQLException ex) {
			throw new SomethingWentWrongException("Bid Not Created");
		} finally {
			try {
				DBUtils.closeConnection(conn);
			} catch (SQLException ex) {

			}
		}
	}

	@Override
	public void updateBid(Scanner sc) throws SomethingWentWrongException, NoRecordFoundException {
		Connection conn = null;
		System.out.print("	Enter Auction Id : ");
		int aid = sc.nextInt();
		try {
			conn = DBUtils.getConnectionTodatabase();
			String query = "SELECT price, buyer_id from auction where auction_id = ? AND is_deleted = 0";
			PreparedStatement ps = conn.prepareStatement(query);

			ps.setInt(1, aid);

			ResultSet rs = ps.executeQuery();
			if (DBUtils.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException(ConsoleColors.RED + "	Auction Not Avilable" + ConsoleColors.RESET);
			}

			double price = 0;
			int buyerId = 0;
			while (rs.next()) {
				price = rs.getDouble(1);
				buyerId = rs.getInt(2);

			}
			if (StaticVar.LoggedInBuyerId == buyerId) {
				System.out.println(ConsoleColors.RED_BOLD + "		You already have latest Bid" + ConsoleColors.RESET);
				return;
			}
			System.out.print("	Enter Bid Price : ");
			double newPrice = sc.nextDouble();

			if (price >= newPrice) {
				System.out.println(
						ConsoleColors.RED_BOLD + "		Price is less then current price" + ConsoleColors.RESET);
				return;
			}

			String query1 = "UPDATE auction SET price = ?, buyer_id = ? where auction_id = ?";
			PreparedStatement ps1 = conn.prepareStatement(query1);
			ps1.setDouble(1, newPrice);
			ps1.setInt(2, StaticVar.LoggedInBuyerId);
			ps1.setInt(3, aid);

			if (ps1.executeUpdate() > 0) {
				System.out.println(ConsoleColors.GREEN + "		Bid created successful" + ConsoleColors.RESET);
			} else {
				System.out.println(ConsoleColors.RED_BOLD + "		Bid not created" + ConsoleColors.RESET);
			}

		} catch (ClassNotFoundException | SQLException ex) {
			throw new SomethingWentWrongException("Bid Not Created");
		} finally {
			try {
				DBUtils.closeConnection(conn);
			} catch (SQLException ex) {

			}
		}
	}

	@Override
	public void auctionHistory() throws SomethingWentWrongException, NoRecordFoundException {
		Connection conn = null;
		try {
			conn = DBUtils.getConnectionTodatabase();
			String query = "SELECT auction_id, seller_id, product_id, price, date from auction where buyer_id = ? AND is_deleted = 0";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, StaticVar.LoggedInBuyerId);
			ResultSet rs = ps.executeQuery();
			if (DBUtils.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("	Auction Not Avilable");
			}

			while (rs.next()) {
				int auctionID = rs.getInt(1);
				int sellerID = rs.getInt(2);
				int productId = rs.getInt(3);
				double price = rs.getDouble(4);

				LocalDate aucDate = rs.getDate(5).toLocalDate();

				String query1 = "SELECT sellerName from seller where sellerId = ?";
				PreparedStatement ps1 = conn.prepareStatement(query1);
				ps1.setInt(1, sellerID);
				ResultSet rs1 = ps1.executeQuery();
				if (DBUtils.isResultSetEmpty(rs1)) {
					throw new NoRecordFoundException("	Auction Not Avilable");
				}
				String sellerName = null;
				while (rs1.next()) {
					sellerName = rs1.getString(1);
				}

				String query2 = "SELECT p.name, c.name from product p join category c ON p.category_id = c.id where product_id = ?";
				PreparedStatement ps2 = conn.prepareStatement(query2);
				ps2.setInt(1, productId);
				ResultSet rs2 = ps2.executeQuery();
				if (DBUtils.isResultSetEmpty(rs2)) {
					throw new NoRecordFoundException("	Auction Not Avilable");
				}

				String itemName = null;
				String category = null;
				while (rs2.next()) {
					itemName = rs2.getString(1);
					category = rs2.getString(2);
				}

				System.out.print("	Auction Id : " + auctionID);
				System.out.print(", Seller Name : " + sellerName);
				System.out.print(", Product Name : " + itemName);
				System.out.print(", Category : " + category);
				System.out.print(", Price : " + price);
				System.out.print(", Date : " + aucDate);

			}

		} catch (ClassNotFoundException | SQLException ex) {
			throw new SomethingWentWrongException("Auction History Not Available");
		} finally {
			try {
				DBUtils.closeConnection(conn);
			} catch (SQLException ex) {

			}
		}
	}

	@Override
	public void forgatPassword(Scanner sc) throws SomethingWentWrongException, NoRecordFoundException {
		Connection conn = null;
		System.out.print(ConsoleColors.CYAN + "		Enter UserName : " + ConsoleColors.RESET);
		String username = sc.next();

		System.out.print(ConsoleColors.CYAN + "		Enter Mobile Number : " + ConsoleColors.RESET);
		String mobile = sc.next();
		try {
			conn = DBUtils.getConnectionTodatabase();
			String query = "SELECT buyerId FROM buyer WHERE username = ? AND mobileNo = ? AND is_deleted = 0";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, mobile);
			ResultSet rs = ps.executeQuery();
			if (DBUtils.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("	Invalid username");
			}

			rs.next();
			int buyerId = rs.getInt(1);

			System.out.print(ConsoleColors.CYAN + "		Enter New Password : " + ConsoleColors.RESET);
			String psw = sc.next();

			System.out.print(ConsoleColors.CYAN + "		Confirm Password : " + ConsoleColors.RESET);
			String newPsw = sc.next();

			if (!psw.equals(newPsw)) {
				System.out.println(
						ConsoleColors.RED_BOLD + "		Password and Confirm Password Not Match" + ConsoleColors.RESET);
			} else {
				Random random = new Random();
				String otp = String.format("%04d", random.nextInt(10000));
				System.out.print(ConsoleColors.CYAN + "		OTP is sent to Your Mobile : " + ConsoleColors.YELLOW);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.print(otp);
				System.out.println("		" + ConsoleColors.RESET);
				System.out.print(ConsoleColors.CYAN + "		Enter Your OTP : " + ConsoleColors.RESET);
				String newOtp = sc.next();
				int i = 0;
				while (!otp.equals(newOtp) && i < 2) {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(ConsoleColors.RED + "		Sorry! Invalid OTP" + ConsoleColors.RESET);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.print(ConsoleColors.CYAN + "		Please Enter Correct OTP : " + ConsoleColors.RESET);
					newOtp = sc.next();
					i++;
				}
				if (!otp.equals(newOtp)) {
					System.out.print(ConsoleColors.RED + "		OTP Dose Not Match" + ConsoleColors.RESET);
				} else {
					String query1 = "Update buyer SET password = ? WHERE buyerId = ?";
					PreparedStatement ps1 = conn.prepareStatement(query1);
					ps1.setString(1, psw);
					ps1.setInt(2, buyerId);
					int x = ps1.executeUpdate();
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (x > 0) {
						System.out.println(
								ConsoleColors.GREEN + "		Password Updated Successful" + ConsoleColors.RESET);
					} else {
						System.out.print(ConsoleColors.RED + "		Password Not Changed" + ConsoleColors.RESET);
					}
				}
			}

		} catch (ClassNotFoundException | SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
			try {
				DBUtils.closeConnection(conn);
			} catch (SQLException ex) {

			}
		}
	}

	@Override
	public void refundProduct(Scanner sc) throws SomethingWentWrongException, NoRecordFoundException {
		Connection conn = null;
		try {
			conn = DBUtils.getConnectionTodatabase();

			LocalDate date = LocalDate.now();
			LocalDate newDate = date.minusDays(5);

			String query = "SELECT t.transaction_id, p.name, t.price, t.quantity, t.transaction_date, t.seller_id from transaction t join product p ON t.item_id = p.product_id where buyer_id = ? AND t.transaction_date >= ? AND t.is_deleted = 0 AND t.is_returned = 0";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, StaticVar.LoggedInBuyerId);
			ps.setDate(2, Date.valueOf(newDate));
			ResultSet rs = ps.executeQuery();
			if (DBUtils.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("	Product Not Found");
			}

			while (rs.next()) {
				System.out.print("	Transaction Id : " + rs.getInt(1));
				System.out.print(", Product Name : " + rs.getString(2));
				System.out.print(", Price : " + rs.getDouble(3));
				System.out.print(", Quantity : " + rs.getInt(4));
				System.out.print(", Transaction Date : " + rs.getDate(5));
				System.out.print(", Seller Id : " + rs.getInt(6));
				System.out.println();
			}

			System.out.print(ConsoleColors.CYAN + "		Enter Transaction Id : " + ConsoleColors.RESET);
			int tid = sc.nextInt();
			String query1 = "SELECT quantity, item_id from transaction where buyer_id = ? AND  transaction_id = ? AND is_deleted = 0";
			PreparedStatement ps1 = conn.prepareStatement(query1);
			ps1.setInt(1, StaticVar.LoggedInBuyerId);
			ps1.setInt(2, tid);
			ResultSet rs1 = ps1.executeQuery();
			if (DBUtils.isResultSetEmpty(rs1)) {
				throw new NoRecordFoundException("	Product Not Found");
			}
			rs1.next();
			int quantity = rs1.getInt(1);
			int pid = rs1.getInt(1);

			String query2 = "UPDATE transaction SET is_returned = 1 where transaction_id = ? AND is_deleted = 0";
			PreparedStatement ps2 = conn.prepareStatement(query2);
			ps2.setInt(1, tid);
			ps2.executeUpdate();

			String query3 = "SELECT quantity from product where product_id = ?";
			PreparedStatement ps3 = conn.prepareStatement(query3);
			ps3.setInt(1, pid);
			ResultSet rs3 = ps3.executeQuery();
			if (DBUtils.isResultSetEmpty(rs3)) {
				throw new NoRecordFoundException("	Product Not Found");
			}
			rs3.next();
			int newQ = rs3.getInt(1);

			try {
				Thread.sleep(1000);
				System.out.print(ConsoleColors.CYAN + "		Processing.");
				Thread.sleep(1000);
				System.out.print(".");
				Thread.sleep(1000);
				System.out.print(".");
				Thread.sleep(1000);
				System.out.print(".");
				Thread.sleep(1000);
				System.out.println("." + ConsoleColors.RESET);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			quantity = quantity + newQ;

			String query4 = "UPDATE product SET quantity = ? where product_id = ?";
			PreparedStatement ps4 = conn.prepareStatement(query4);
			ps4.setInt(1, quantity);
			ps4.setInt(2, pid);
			if (ps4.executeUpdate() > 0) {
				System.out.println(ConsoleColors.GREEN_BOLD + "		Product Refund Successful" + ConsoleColors.RESET);
			}

		} catch (ClassNotFoundException | SQLException ex) {
			throw new SomethingWentWrongException("View History");
		} finally {
			try {
				DBUtils.closeConnection(conn);
			} catch (SQLException ex) {

			}
		}
	}

	@Override
	public void viewRefundHistory() throws SomethingWentWrongException, NoRecordFoundException {
		Connection conn = null;
		try {
			conn = DBUtils.getConnectionTodatabase();
			String query = "SELECT t.transaction_id, p.name, t.price, t.quantity, t.transaction_date, t.seller_id from transaction t join product p ON t.item_id = p.product_id where buyer_id = ? AND t.is_deleted = 0 AND is_returned = 1";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, StaticVar.LoggedInBuyerId);
			ResultSet rs = ps.executeQuery();
			if (DBUtils.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("	Product Not Found");
			}

			while (rs.next()) {
				System.out.print("	Transaction Id : " + rs.getInt(1));
				System.out.print(", Product Name : " + rs.getString(2));
				System.out.print(", Price : " + rs.getDouble(3));
				System.out.print(", Quantity : " + rs.getInt(4));
				System.out.print(", Transaction Date : " + rs.getDate(5));
				System.out.print(", Seller Id : " + rs.getInt(6));
				System.out.println();
			}

		} catch (ClassNotFoundException | SQLException ex) {
			throw new SomethingWentWrongException("View History");
		} finally {
			try {
				DBUtils.closeConnection(conn);
			} catch (SQLException ex) {

			}
		}
	}
}
