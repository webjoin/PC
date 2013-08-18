package com.pc.pconsumption.util;

public abstract class AbstractDAOUtil {
	
	private boolean isNew = true;

	public boolean isNew() {
		return isNew;
	}

	public synchronized void setNew(boolean isNew) {
		this.isNew = isNew;
	}
	
}
