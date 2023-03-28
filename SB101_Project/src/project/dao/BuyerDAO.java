package project.dao;

import project.dto.BuyerDTO;
import project.exception.NoRecordFoundException;
import project.exception.SomethingWentWrongException;

public interface BuyerDAO {
	public void login(String username, String password) throws SomethingWentWrongException, NoRecordFoundException;

	public void logout();

	public void registerBuyer(BuyerDTO obj) throws SomethingWentWrongException;
}
