package project.dao;

import java.util.Scanner;

import project.dto.BuyerDTO;
import project.exception.NoRecordFoundException;
import project.exception.SomethingWentWrongException;

public interface BuyerDAO {
	public void login(String username, String password) throws SomethingWentWrongException, NoRecordFoundException;

	public void logout();

	public void registerBuyer(BuyerDTO obj) throws SomethingWentWrongException;

	public void updatePersonal(BuyerDTO obj) throws SomethingWentWrongException;

	public void viewProduct() throws SomethingWentWrongException, NoRecordFoundException;

	public void viewHistory() throws SomethingWentWrongException, NoRecordFoundException;

	public int checkQuantity(int id) throws SomethingWentWrongException, NoRecordFoundException;

	public void purchaseProduct(int id, int q) throws SomethingWentWrongException, NoRecordFoundException;

	void deleteAccount() throws SomethingWentWrongException;

	public void viewAuction() throws SomethingWentWrongException, NoRecordFoundException;

	public void createBid() throws SomethingWentWrongException, NoRecordFoundException;

	public void auctionHistory() throws SomethingWentWrongException, NoRecordFoundException;

	public void updateBid(Scanner sc) throws SomethingWentWrongException, NoRecordFoundException;

}
