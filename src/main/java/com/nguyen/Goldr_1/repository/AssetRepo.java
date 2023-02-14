package com.nguyen.Goldr_1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nguyen.Goldr_1.model.Asset;

@Repository
public interface AssetRepo extends JpaRepository<Asset, Integer> {
	List<Asset> findByUserId(Integer userId);
}
