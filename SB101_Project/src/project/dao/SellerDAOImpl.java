package project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
			String query = "SELECT sellerId FROM seller WHERE username = ? AND password = ? AND is_deleted = 0";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (DBUtils.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("	Invalid username or password");
			}

			rs.next();
			StaticVar.loggedInSellerId = rs.getInt(1);
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
	public void updateProduct(int pid, ProductDTO obj) throws SomethingWentWrongException {
		Connection conn = null;
		try {

			conn = DBUtils.getConnectionTodatabase();

			String query = "UPDATE Product SET name = ?, price = ?, quantity = ?, category_id = ? where product_id = ?";

			PreparedStatement ps = conn.prepareStatement(query);

			ps.setString(1, obj.getName());
			ps.setDouble(2, obj.getPrice());
			ps.setInt(3, obj.getQuantity());
			ps.setInt(4, obj.getCid());
			ps.setInt(5, pid);
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
	public void deleteProduct(int pid) throws SomethingWentWrongException {
		Connection conn = null;
		try {

			conn = DBUtils.getConnectionTodatabase();

			String query = "UPDATE Product SET is_deleted = 1 where product_id = ?";

			PreparedStatement ps = conn.prepareStatement(query);

			ps.setInt(1, pid);
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

			String query = "UPDATE seller SET is_deleted = 1 where sellerId = ?";

			PreparedStatement ps = conn.prepareStatement(query);

			ps.setInt(1, StaticVar.loggedInSellerId);
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
}
