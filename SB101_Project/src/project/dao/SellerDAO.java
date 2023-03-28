package project.dao;

import project.dto.SellerDTO;
import project.exception.NoRecordFoundException;
import project.exception.SomethingWentWrongException;

public interface SellerDAO {
	public void login(String username, String password) throws SomethingWentWrongException, NoRecordFoundException;

	public void logout();

	public void registerSeller(SellerDTO obj) throws SomethingWentWrongException;
}
