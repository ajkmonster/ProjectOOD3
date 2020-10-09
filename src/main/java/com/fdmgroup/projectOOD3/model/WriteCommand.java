package com.fdmgroup.projectOOD3.model;

import com.fdmgroup.projectOOD3.exceptions.CustomException;

public interface WriteCommand {
	void writeUser(User user) throws CustomException;
}
