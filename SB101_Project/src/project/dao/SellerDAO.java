package project.dao;

import java.util.Scanner;

import project.dto.ProductDTO;
import project.dto.SellerDTO;
import project.exception.NoRecordFoundException;
import project.exception.SomethingWentWrongException;

public interface SellerDAO {
	public void login(String username, String password) throws SomethingWentWrongException, NoRecordFoundException;

	public void logout();

	public void registerSeller(SellerDTO obj) throws SomethingWentWrongException;

	public void updatePersonal(SellerDTO obj) throws SomethingWentWrongException;

	public void viewProduct() throws SomethingWentWrongException, NoRecordFoundException;

	public void addProduct(ProductDTO obj) throws SomethingWentWrongException;

	public void updateProduct(int pid, ProductDTO obj) throws SomethingWentWrongException;

	public void deleteProduct(int pid) throws SomethingWentWrongException;

	public void deleteAccount() throws SomethingWentWrongException;

	public void forgatPassword(Scanner sc) throws SomethingWentWrongException, NoRecordFoundException;

	public void viewTransaction() throws SomethingWentWrongException, NoRecordFoundException;
}
