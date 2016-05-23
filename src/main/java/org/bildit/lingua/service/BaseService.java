package org.bildit.lingua.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface BaseService<T, K> {

	List<T> getAll();
	
}
