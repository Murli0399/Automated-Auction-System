package project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import project.dto.BuyerDTO;
import project.exception.NoRecordFoundException;
import project.exception.SomethingWentWrongException;
import project.util.DBUtils;

public class BuyerDAOImpl implements BuyerDAO {

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
}
