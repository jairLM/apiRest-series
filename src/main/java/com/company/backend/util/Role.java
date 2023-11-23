package com.company.backend.util;


import java.util.Arrays;
import java.util.List;

public enum Role {

	CUSTOMER(Arrays.asList(Permission.READ_ALL_SERIES)),
	ADMINISTRATOR(Arrays.asList(Permission.SAVE_ONE_SERIE,Permission.READ_ALL_SERIES));
	
	private List<Permission> permissions;

		
	private Role(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
	
	
	
	
}
