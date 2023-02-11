package com.nguyen.Goldr_1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nguyen.Goldr_1.model.Asset;
import com.nguyen.Goldr_1.services.AssetServices;

@RestController
@RequestMapping("/assets")
public class AssetController {
	
	@Autowired
	private AssetServices assetServices;

	@GetMapping
	public List<Asset> getAllAssets() {
		return assetServices.getAllAssets();
	}

	@GetMapping("/{id}")
	public Optional<Asset> getAssetById(@PathVariable("id") Integer id) {
		return assetServices.getAssetById(id);
	}

	@PostMapping
	public void createAccount(@RequestBody Asset asset) {
		assetServices.addAsset(asset);
	}

	@PutMapping("/{id}")
	public void updateAsset(@PathVariable("id") Integer id, @RequestBody Asset asset) {
		assetServices.updateAsset(id, asset);
	}

	@DeleteMapping("/{id}")
	public void deleteAsset(@PathVariable("id") Integer id) {
		assetServices.deleteAsset(id);
	}
	
}
