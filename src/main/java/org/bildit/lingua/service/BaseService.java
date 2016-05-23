package org.bildit.lingua.service;

import java.util.List;


public interface BaseService<T, K> {

	List<T> getAll();
	T getOne(K id);
	
}
