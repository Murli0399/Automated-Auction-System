package project.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import project.dto.BuyerDTO;
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
				System.out.print("Product Id = " + rs.getInt(1));
				System.out.print(" Seller Id = " + rs.getInt(2));
				System.out.print(" Product Name = " + rs.getString(3));
				System.out.print(" Price = " + rs.getDouble(4));
				System.out.print(" Quantity = " + rs.getInt(5));
				System.out.print(" Category = " + rs.getString(6));
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

	public void login(String username, String password) throws SomethingWentWrongException, NoRecordFoundException {
		Connection conn = null;
		try {
			conn = DBUtils.getConnectionTodatabase();
			String query = "SELECT buyerId FROM buyer WHERE username = ? AND password = ? AND is_deleted = 0";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (DBUtils.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("	Invalid username or password");
			}

			rs.next();
			StaticVar.LoggedInBuyerId = rs.getInt(1);
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
			System.out.println(ex.getMessage());
//			throw new SomethingWentWrongException("		Unable to Add Details");
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
			System.out.println(ex.getMessage());
//			throw new SomethingWentWrongException("		Unable to Add Details");
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
			System.out.println(ex.getMessage());
//			throw new SomethingWentWrongException("		Unable to Add Details");
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
	public void purchaseProduct(int id, int q) throws SomethingWentWrongException, NoRecordFoundException {
		Connection conn = null;
		try {
			conn = DBUtils.getConnectionTodatabase();
			String query = "SELECT seller_id, price, quantity from product where product_id = ? AND is_deleted = 0";
			PreparedStatement ps = conn.prepareStatement(query);

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			if (DBUtils.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("	Product Not Found");
			}

			int sellerId = 0;
			double price = 0;
			int quantity = 0;

			while (rs.next()) {
				sellerId = rs.getInt(1);
				price = rs.getDouble(2);
				quantity = rs.getInt(3);
			}

			quantity = quantity - q;

			String query1 = "UPDATE product SET quantity = ? where product_id = ?";
			PreparedStatement ps1 = conn.prepareStatement(query1);
			ps1.setInt(1, quantity);
			ps1.setInt(2, id);
			ps1.executeUpdate();

			price = (price * 110) / 100;

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
			System.out.println(ex.getMessage());
		} finally {
			try {
				DBUtils.closeConnection(conn);
			} catch (SQLException ex) {

			}
		}
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
				System.out.print("Transaction Id = " + rs.getInt(1));
				System.out.print(" Product Name = " + rs.getString(2));
				System.out.print(" Price = " + rs.getDouble(3));
				System.out.print(" Quantity = " + rs.getInt(4));
				System.out.print(" Transaction Date = " + rs.getDate(5));
				System.out.print(" Seller Id = " + rs.getInt(6));
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
}
