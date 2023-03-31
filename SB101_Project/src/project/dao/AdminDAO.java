package project.dao;

import java.time.LocalDate;
import java.time.LocalTime;

import project.exception.NoRecordFoundException;
import project.exception.SomethingWentWrongException;

public interface AdminDAO {
	public void viewUsersSeller() throws SomethingWentWrongException, NoRecordFoundException;

	public void viewUsersBuyer() throws SomethingWentWrongException, NoRecordFoundException;

	public void viewProduct() throws SomethingWentWrongException, NoRecordFoundException;

	public void viewAuctionHistory() throws SomethingWentWrongException, NoRecordFoundException;

	public void viewSoldProduct() throws SomethingWentWrongException, NoRecordFoundException;

	public void viewProductByCategory(int ch) throws SomethingWentWrongException, NoRecordFoundException;

	public void viewSoldProductByCategory(int ch) throws SomethingWentWrongException, NoRecordFoundException;

	public int checkQuantity(int id) throws SomethingWentWrongException, NoRecordFoundException;

	public void createAuction(int id, LocalDate date, LocalTime st, int du)
			throws SomethingWentWrongException, NoRecordFoundException;

}
