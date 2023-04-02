package project.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import project.colors.ConsoleColors;
import project.exception.NoRecordFoundException;
import project.exception.SomethingWentWrongException;
import project.util.DBUtils;

public class AdminDAOImpl implements AdminDAO {

	public void viewUsersSeller() throws SomethingWentWrongException, NoRecordFoundException {
		Connection conn = null;
		try {
			conn = DBUtils.getConnectionTodatabase();
			String query = "SELECT  sellerId, sellerName, address, mobileNo, username from seller where is_deleted = 0";
			PreparedStatement ps = conn.prepareStatement(query);

			ResultSet rs = ps.executeQuery();
			if (DBUtils.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("	User Not Found");
			}

			while (rs.next()) {
				System.out.print("	User Id : " + rs.getInt(1));
				System.out.print(", User Name : " + rs.getString(2));
				System.out.print(", Address : " + rs.getString(3));
				System.out.print(", Mobile : " + rs.getString(4));
				System.out.print(", Username : " + rs.getString(5));
				System.out.print(", User Type : Seller");
				System.out.println();
			}

		} catch (ClassNotFoundException | SQLException ex) {
			throw new SomethingWentWrongException("User Not Found");
		} finally {
			try {
				DBUtils.closeConnection(conn);
			} catch (SQLException ex) {

			}
		}
	}

	@Override
	public void viewUsersBuyer() throws SomethingWentWrongException, NoRecordFoundException {
		Connection conn = null;
		try {
			conn = DBUtils.getConnectionTodatabase();
			String query = "SELECT * from buyer where is_deleted = 0";
			PreparedStatement ps = conn.prepareStatement(query);

			ResultSet rs = ps.executeQuery();
			if (DBUtils.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("	User Not Found");
			}

			while (rs.next()) {
				System.out.print("	User Id : " + rs.getInt(1));
				System.out.print(", User Name : " + rs.getString(2));
				System.out.print(", Address : " + rs.getString(3));
				System.out.print(", Mobile : " + rs.getString(4));
				System.out.print(", Username : " + rs.getString(5));
				System.out.print(", User Type : Buyer");
				System.out.println();
			}

		} catch (ClassNotFoundException | SQLException ex) {
			throw new SomethingWentWrongException("User Not Found");
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
			String query = "SELECT product_id, seller_id, p.name, price, quantity, c.name from product p join category c ON p.category_id = c.id where is_deleted = 0";
			PreparedStatement ps = conn.prepareStatement(query);

			ResultSet rs = ps.executeQuery();
			if (DBUtils.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("	Product Not Found");
			}

			while (rs.next()) {
				System.out.print("	Product Id : " + rs.getInt(1));
				System.out.print(", Seller Id : " + rs.getInt(2));
				System.out.print(", Name : " + rs.getString(3));
				System.out.print(", Price : " + rs.getDouble(4));
				System.out.print(", Quantity : " + rs.getInt(5));
				System.out.print(", Category : " + rs.getString(6));
				System.out.println();
			}

		} catch (ClassNotFoundException | SQLException ex) {
			throw new SomethingWentWrongException("Product Not Found");
		} finally {
			try {
				DBUtils.closeConnection(conn);
			} catch (SQLException ex) {

			}
		}
	}

	@Override
	public void viewProductByCategory(int ch) throws SomethingWentWrongException, NoRecordFoundException {
		Connection conn = null;
		try {
			conn = DBUtils.getConnectionTodatabase();
			String query = "SELECT product_id, seller_id, p.name, price, quantity, c.name from product p join category c ON p.category_id = c.id where c.id = ? AND is_deleted = 0";
			PreparedStatement ps = conn.prepareStatement(query);

			ps.setInt(1, ch);

			ResultSet rs = ps.executeQuery();
			if (DBUtils.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("	Product Not Found");
			}

			while (rs.next()) {
				System.out.print("	Product Id : " + rs.getInt(1));
				System.out.print(", Seller Id : " + rs.getInt(2));
				System.out.print(", Name : " + rs.getString(3));
				System.out.print(", Price : " + rs.getDouble(4));
				System.out.print(", Quantity : " + rs.getInt(5));
				System.out.print(", Category : " + rs.getString(6));
				System.out.println();
			}

		} catch (ClassNotFoundException | SQLException ex) {
			throw new SomethingWentWrongException("Product Not Found");
		} finally {
			try {
				DBUtils.closeConnection(conn);
			} catch (SQLException ex) {

			}
		}
	}

	@Override
	public void viewSoldProduct() throws SomethingWentWrongException, NoRecordFoundException {
		Connection conn = null;
		try {
			conn = DBUtils.getConnectionTodatabase();
			String query = "SELECT product_id, seller_id, p.name, price, quantity, c.name from product p join category c ON p.category_id = c.id where is_deleted = 0 AND sold_status = 1";
			PreparedStatement ps = conn.prepareStatement(query);

			ResultSet rs = ps.executeQuery();
			if (DBUtils.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("	Product Not Found");
			}

			while (rs.next()) {
				System.out.print("	Product Id : " + rs.getInt(1));
				System.out.print(", Seller Id : " + rs.getInt(2));
				System.out.print(", Name : " + rs.getString(3));
				System.out.print(", Price : " + rs.getDouble(4));
				System.out.print(", Quantity : " + rs.getInt(5));
				System.out.print(", Category : " + rs.getString(6));
				System.out.println();
			}

		} catch (ClassNotFoundException | SQLException ex) {
			throw new SomethingWentWrongException("Product Not Found");
		} finally {
			try {
				DBUtils.closeConnection(conn);
			} catch (SQLException ex) {

			}
		}
	}

	@Override
	public void viewSoldProductByCategory(int ch) throws SomethingWentWrongException, NoRecordFoundException {
		Connection conn = null;
		try {
			conn = DBUtils.getConnectionTodatabase();
			String query = "SELECT product_id, seller_id, p.name, price, quantity, c.name from product p join category c ON p.category_id = c.id where c.id = ? AND is_deleted = 0 AND sold_status = 1";
			PreparedStatement ps = conn.prepareStatement(query);

			ps.setInt(1, ch);

			ResultSet rs = ps.executeQuery();
			if (DBUtils.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("	Product Not Found");
			}

			while (rs.next()) {
				System.out.print("	Product Id : " + rs.getInt(1));
				System.out.print(", Seller Id : " + rs.getInt(2));
				System.out.print(", Name : " + rs.getString(3));
				System.out.print(", Price : " + rs.getDouble(4));
				System.out.print(", Quantity : " + rs.getInt(5));
				System.out.print(", Category : " + rs.getString(6));
				System.out.println();
			}

		} catch (ClassNotFoundException | SQLException ex) {
			throw new SomethingWentWrongException("Product Not Found");
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
			System.out.println(ex.getMessage());
		} finally {
			try {
				DBUtils.closeConnection(conn);
			} catch (SQLException ex) {

			}
		}
		return quantity;
	}

	@Override
	public void createAuction(int id, LocalDate date, LocalTime st, int duration)
			throws SomethingWentWrongException, NoRecordFoundException {
		Connection conn = null;
		try {
			conn = DBUtils.getConnectionTodatabase();
			String query = "SELECT seller_id, price from product where product_id = ? AND is_deleted = 0";
			PreparedStatement ps = conn.prepareStatement(query);

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			if (DBUtils.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("	Product Not Found");
			}

			int sellerId = 0;
			double price = 0;

			while (rs.next()) {
				sellerId = rs.getInt(1);
				price = rs.getDouble(2);
			}

			LocalTime end = st.plusHours(duration);
			DateTimeFormatter formater = DateTimeFormatter.ofPattern("HH:mm");

			LocalTime endTime = LocalTime.parse(formater.format(end));

			String query2 = "INSERT into auction (seller_id, product_id, price, date, start_time, end_time) VALUES (?,?,?,?,?,?)";
			PreparedStatement ps2 = conn.prepareStatement(query2);

			ps2.setInt(1, sellerId);
			ps2.setInt(2, id);
			ps2.setDouble(3, price);
			ps2.setDate(4, Date.valueOf(date));
			ps2.setTime(5, Time.valueOf(st));
			ps2.setTime(6, Time.valueOf(endTime));

			ps2.executeUpdate();

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
	public void viewAuctionHistory() throws SomethingWentWrongException, NoRecordFoundException {
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

				System.out.print("	Auction Id : " + rs.getInt(1));
				System.out.print(", Seller Id : " + rs.getInt(2));
				System.out.print(", Product Id : " + rs.getInt(3));
				System.out.print(", Price : " + rs.getDouble(4));

				LocalDate nowDate = LocalDate.now();
				LocalDate aucDate = rs.getDate(5).toLocalDate();

				System.out.print(", Date : " + aucDate);

				LocalTime nowTime = LocalTime.now();
				LocalTime aucStartTime = rs.getTime(6).toLocalTime();
				LocalTime aucEndTime = rs.getTime(7).toLocalTime();

				System.out.print(", Start Time : " + aucStartTime);
				System.out.print(", End Time : " + aucEndTime + ",");

				int buyerId = rs.getInt(8);

				if (buyerId == 0) {
					System.out.print(ConsoleColors.RED + " Not Purchased," + ConsoleColors.RESET);
				} else {
					System.out.print(ConsoleColors.GREEN + " Buyer Id : " + buyerId + "," + ConsoleColors.RESET);
				}

				if (aucDate.compareTo(nowDate) == 0) {
					if (nowTime.compareTo(aucStartTime) < 0 && nowTime.compareTo(aucEndTime) < 0) {
						System.out.print(ConsoleColors.YELLOW + " Not Started" + ConsoleColors.RESET);
					} else if (nowTime.compareTo(aucStartTime) >= 0 && nowTime.compareTo(aucEndTime) < 0) {
						System.out.print(ConsoleColors.GREEN + " Running" + ConsoleColors.RESET);
					} else {
						System.out.print(ConsoleColors.RED + " Endded" + ConsoleColors.RESET);
					}
				} else if (aucDate.compareTo(nowDate) > 0) {
					System.out.print(ConsoleColors.YELLOW + " Not Started" + ConsoleColors.RESET);
				} else {
					System.out.print(ConsoleColors.RED + " Endded" + ConsoleColors.RESET);
				}

				System.out.println();
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
			String query = "SELECT t.transaction_id, p.name, t.price, t.quantity, t.transaction_date, t.seller_id, t.buyer_id from transaction t join product p ON t.item_id = p.product_id where t.is_deleted = 0";
			PreparedStatement ps = conn.prepareStatement(query);

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
				System.out.print(", Buyer Id : " + rs.getInt(7));
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
	public void filterTransactionByDateRange(LocalDate startDate, LocalDate endDate)
			throws SomethingWentWrongException, NoRecordFoundException {
		Connection conn = null;
		try {
			conn = DBUtils.getConnectionTodatabase();
			String query = "SELECT t.transaction_id, p.name, t.price, t.quantity, t.transaction_date, t.seller_id, t.buyer_id from transaction t join product p ON t.item_id = p.product_id where t.transaction_date >= ? AND t.transaction_date <= ? AND t.is_deleted = 0";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setDate(1, Date.valueOf(startDate));
			ps.setDate(2, Date.valueOf(endDate));
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
				System.out.print(", Buyer Id : " + rs.getInt(7));
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
	public void filterTransactionAsc() throws SomethingWentWrongException, NoRecordFoundException {
		Connection conn = null;
		try {
			conn = DBUtils.getConnectionTodatabase();
			String query = "SELECT t.transaction_id, p.name, t.price, t.quantity, t.transaction_date, t.seller_id, t.buyer_id from transaction t join product p ON t.item_id = p.product_id where t.is_deleted = 0 order by t.price asc";
			PreparedStatement ps = conn.prepareStatement(query);

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
				System.out.print(", Buyer Id : " + rs.getInt(7));
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
	public void filterTransactionDesc() throws SomethingWentWrongException, NoRecordFoundException {
		Connection conn = null;
		try {
			conn = DBUtils.getConnectionTodatabase();
			String query = "SELECT t.transaction_id, p.name, t.price, t.quantity, t.transaction_date, t.seller_id, t.buyer_id from transaction t join product p ON t.item_id = p.product_id where t.is_deleted = 0 order by t.price desc";
			PreparedStatement ps = conn.prepareStatement(query);

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
				System.out.print(", Buyer Id : " + rs.getInt(7));
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
	public void viewTransactionById(int tid) throws SomethingWentWrongException, NoRecordFoundException {
		Connection conn = null;
		try {
			conn = DBUtils.getConnectionTodatabase();
			String query = "SELECT t.transaction_id, p.name, t.price, t.quantity, t.transaction_date, t.seller_id, t.buyer_id from transaction t join product p ON t.item_id = p.product_id where t.transaction_id = ? AND t.is_deleted = 0";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, tid);
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
				System.out.print(", Buyer Id : " + rs.getInt(7));
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
	public void viewRefundProduct() throws SomethingWentWrongException, NoRecordFoundException {
		Connection conn = null;
		try {
			conn = DBUtils.getConnectionTodatabase();
			String query = "SELECT t.transaction_id, p.name, t.price, t.quantity, t.transaction_date, t.seller_id from transaction t join product p ON t.item_id = p.product_id where t.is_deleted = 0 AND is_returned = 1";
			PreparedStatement ps = conn.prepareStatement(query);
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
