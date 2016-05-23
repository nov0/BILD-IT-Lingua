package org.bildit.lingua.repository;

import java.io.Serializable;

import org.bildit.lingua.common.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 
 * @interface BaseRepository
 * 
 * @author Mladen Todorovic
 * 
 * */

@NoRepositoryBean
public interface BaseRepository <T extends BaseEntity, K extends Serializable> extends JpaRepository <T, K> {
	  
}
