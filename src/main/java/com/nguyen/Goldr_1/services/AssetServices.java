package com.nguyen.Goldr_1.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nguyen.Goldr_1.model.Asset;
import com.nguyen.Goldr_1.repository.AssetRepo;

@Service
public class AssetServices {
	@Autowired
	private AssetRepo assetRepo;

//	probably need to fix to return all assets of a user
	public List<Asset> getAllAssets() {
		List<Asset> assets = new ArrayList<Asset>();
		assetRepo.findAll().forEach(assets::add);
		return assets;
	}

	public Optional<Asset> getAssetById(Integer id) {
		return assetRepo.findById(id);
	}

	public void addAsset(Asset asset) {
		assetRepo.save(asset);
	}

	public void updateAsset(Integer id, Asset asset) {
		Optional<Asset> assetData = assetRepo.findById(id);

		if (assetData.isPresent()) {
			Asset _asset = assetData.get();
			if (!asset.getName().isEmpty()) {
				_asset.setName(asset.getName());
			}
			assetRepo.save(_asset);
		}
	}

	public void deleteAsset(Integer id) {
		assetRepo.deleteById(id);
	}
}
