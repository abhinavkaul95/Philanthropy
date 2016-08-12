package com.training.ifaces;

import java.util.List;

public interface InterfaceMyDAO<T> {
	public int add(T t);
	public int update(long dnrId, String... strings);
	public T find(long dnrId);
	public int delete(long dnrId);
	public List<T> findAll(long dnrId);
	int updateAmount(long projId, double amount);
	public void close();
}
