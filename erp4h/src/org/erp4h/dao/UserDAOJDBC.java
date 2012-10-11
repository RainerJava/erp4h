package org.erp4h.dao;

import java.util.List;

import org.erp4h.system.dto.UserDTO;

public class UserDAOJDBC implements UserDAO{

	@Override
	public UserDTO find(Long id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO find(String email, String password) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDTO> list() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(UserDTO user) throws IllegalArgumentException,
			DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(UserDTO user) throws IllegalArgumentException,
			DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(UserDTO user) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean existEmail(String email) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void changePassword(UserDTO user) throws DAOException {
		// TODO Auto-generated method stub
		
	}

}
