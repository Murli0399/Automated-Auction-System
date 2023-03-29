package project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
				System.out.print("User Id = " + rs.getInt(1));
				System.out.print(" User Name = " + rs.getString(2));
				System.out.print(" Address = " + rs.getString(3));
				System.out.print(" Mobile = " + rs.getString(4));
				System.out.print(" Username = " + rs.getString(5));
				System.out.print(" User Type = Seller");
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
				System.out.print("User Id = " + rs.getInt(1));
				System.out.print(" User Name = " + rs.getString(2));
				System.out.print(" Address = " + rs.getString(3));
				System.out.print(" Mobile = " + rs.getString(4));
				System.out.print(" Username = " + rs.getString(5));
				System.out.print(" User Type = Buyer");
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

}
