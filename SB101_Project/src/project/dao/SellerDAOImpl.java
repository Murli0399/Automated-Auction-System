package project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

import project.colors.ConsoleColors;
import project.dto.ProductDTO;
import project.dto.SellerDTO;
import project.exception.NoRecordFoundException;
import project.exception.SomethingWentWrongException;
import project.util.DBUtils;

public class SellerDAOImpl implements SellerDAO {
	public void login(String username, String password) throws SomethingWentWrongException, NoRecordFoundException {
		Connection conn = null;
		try {
			conn = DBUtils.getConnectionTodatabase();
			String query = "SELECT sellerId, sellerName FROM seller WHERE username = ? AND password = ? AND is_deleted = 0";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (DBUtils.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("	Invalid username or password");
			}

			rs.next();
			StaticVar.loggedInSellerId = rs.getInt(1);
			StaticVar.LoggedInSellerName = rs.getString(2);
		} catch (ClassNotFoundException | SQLException ex) {
//			System.out.println(ex.getMessage());
		} finally {
			try {
				DBUtils.closeConnection(conn);
			} catch (SQLException ex) {

			}
		}
	}

	public void logout() {
		StaticVar.loggedInSellerId = 0;
	}

	@Override
	public void registerSeller(SellerDTO obj) throws SomethingWentWrongException {
		Connection conn = null;
		try {

			conn = DBUtils.getConnectionTodatabase();

			String query = "INSERT INTO seller(sellerName, address, mobileNo, username, password) VALUES(?,?,?,?,?)";

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
	public void viewProduct() throws SomethingWentWrongException, NoRecordFoundException {
		Connection conn = null;
		try {
			conn = DBUtils.getConnectionTodatabase();
			String query = "SELECT product_id, seller_id, p.name, price, quantity, c.name from product p join category c ON p.category_id = c.id where seller_id = ? AND is_deleted = 0";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, StaticVar.loggedInSellerId);
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

	@Override
	public void addProduct(ProductDTO obj) throws SomethingWentWrongException {
		Connection conn = null;
		try {

			conn = DBUtils.getConnectionTodatabase();

			String query = "insert into Product (seller_id, name, price, quantity, category_id) VALUES (?,?,?,?,?)";

			PreparedStatement ps = conn.prepareStatement(query);

			ps.setInt(1, StaticVar.loggedInSellerId);
			ps.setString(2, obj.getName());
			ps.setDouble(3, obj.getPrice());
			ps.setInt(4, obj.getQuantity());
			ps.setInt(5, obj.getCid());
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
	public void updateProduct(int pid, ProductDTO obj) throws SomethingWentWrongException {
		Connection conn = null;
		try {

			conn = DBUtils.getConnectionTodatabase();

			String query = "UPDATE Product SET name = ?, price = ?, quantity = ? where product_id = ?";

			PreparedStatement ps = conn.prepareStatement(query);

			ps.setString(1, obj.getName());
			ps.setDouble(2, obj.getPrice());
			ps.setInt(3, obj.getQuantity());
			ps.setInt(4, pid);
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
	public void deleteProduct(int pid) throws SomethingWentWrongException {
		Connection conn = null;
		try {

			conn = DBUtils.getConnectionTodatabase();

			String query = "UPDATE Product SET is_deleted = 1 where product_id = ?";

			PreparedStatement ps = conn.prepareStatement(query);

			ps.setInt(1, pid);
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
	public void updatePersonal(SellerDTO obj) throws SomethingWentWrongException {
		Connection conn = null;
		try {

			conn = DBUtils.getConnectionTodatabase();

			String query = "UPDATE seller SET sellerName = ?, address = ?, mobileNo = ?, username = ?, password = ? where sellerId = ?";

			PreparedStatement ps = conn.prepareStatement(query);

			ps.setString(1, obj.getName());
			ps.setString(2, obj.getAddress());
			ps.setString(3, obj.getMobile());
			ps.setString(4, obj.getUsername());
			ps.setString(5, obj.getPassword());
			ps.setInt(6, StaticVar.loggedInSellerId);
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
	public void deleteAccount() throws SomethingWentWrongException {
		Connection conn = null;
		try {

			conn = DBUtils.getConnectionTodatabase();

			String query = "UPDATE seller SET is_deleted = 1 where sellerId = ?";

			PreparedStatement ps = conn.prepareStatement(query);

			ps.setInt(1, StaticVar.loggedInSellerId);
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
	public void forgatPassword(Scanner sc) throws SomethingWentWrongException, NoRecordFoundException {
		Connection conn = null;
		System.out.print(ConsoleColors.CYAN + "		Enter UserName : " + ConsoleColors.RESET);
		String username = sc.next();

		System.out.print(ConsoleColors.CYAN + "		Enter Mobile Number : " + ConsoleColors.RESET);
		String mobile = sc.next();
		try {
			conn = DBUtils.getConnectionTodatabase();
			String query = "SELECT sellerId FROM seller WHERE username = ? AND mobileNo = ? AND is_deleted = 0";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, mobile);
			ResultSet rs = ps.executeQuery();
			if (DBUtils.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("	Invalid username");
			}

			rs.next();
			int sellerId = rs.getInt(1);

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
					String query1 = "Update seller SET password = ? WHERE sellerId = ?";
					PreparedStatement ps1 = conn.prepareStatement(query1);
					ps1.setString(1, psw);
					ps1.setInt(2, sellerId);
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
	public void viewTransaction() throws SomethingWentWrongException, NoRecordFoundException {
		Connection conn = null;
		try {
			conn = DBUtils.getConnectionTodatabase();
			String query = "SELECT t.transaction_id, p.name, t.price, t.quantity, t.transaction_date, t.buyer_id from transaction t join product p ON t.item_id = p.product_id where seller_id = ? AND t.is_deleted = 0";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, StaticVar.loggedInSellerId);
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
