package com.cms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cms.entity.AddContent;

@Repository
public interface ContentRepository extends JpaRepository<AddContent, Long> {
    
	   List<AddContent> findBypId(Long pId);
}