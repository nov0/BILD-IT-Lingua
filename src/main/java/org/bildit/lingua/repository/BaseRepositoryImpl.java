package org.bildit.lingua.repository;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.bildit.lingua.common.BaseEntity;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

/**
 * 
 * @abstract class BaseRepositoryImpl
 * 
 * @author Mladen Todorovic
 * 
 * */

public abstract class BaseRepositoryImpl <T extends BaseEntity, K extends Serializable> extends SimpleJpaRepository <T, K>
	implements BaseRepository <T, K> {

	public BaseRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
		super(domainClass, entityManager);
	}
	
}