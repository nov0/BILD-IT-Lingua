package org.bildit.lingua.service;

import java.io.Serializable;
import java.util.List;

import org.bildit.lingua.common.BaseEntity;
import org.bildit.lingua.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class BaseServiceImpl<T extends BaseEntity, K extends Serializable> implements BaseService<T, K> {

	@Autowired
	private BaseRepository <T, K> baseRepository;

	public BaseServiceImpl(BaseRepository<T, K> baseRepository) {
		this.baseRepository = baseRepository;
	}

	@Override
	public List<T> getAll() {
		
		return baseRepository.findAll();
	}
	
	
	


}
