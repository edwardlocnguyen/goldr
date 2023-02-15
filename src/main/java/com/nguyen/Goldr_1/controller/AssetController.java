package com.nguyen.Goldr_1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nguyen.Goldr_1.model.Asset;
import com.nguyen.Goldr_1.services.AssetServices;

//@RestController
@Controller
@RequestMapping("/users/{userId}/assets")
public class AssetController {

	private static final String ASSET_FORM = "assetForm";

	@Autowired
	private AssetServices assetServices;

//	@GetMapping
//	public List<Asset> getAllAssets() {
//		return assetServices.getAllAssets();
//	}
//
//	@GetMapping("/{id}")
//	public Optional<Asset> getAssetById(@PathVariable("id") Integer id) {
//		return assetServices.getAssetById(id);
//	}

	@GetMapping("/form")
	public String getAssetForm(Model model) {
		model.addAttribute("asset", new Asset());
		return ASSET_FORM;
	}

	@PostMapping("/post")
	public String createAsset(@RequestParam("userId") Integer userId, @ModelAttribute("asset") Asset asset) {
		assetServices.addAsset(userId, asset);
		return "redirect:/users/user-asset/" + userId;
	}

//	@PostMapping
//	public String createAsset(@RequestBody Asset asset) {
//		assetServices.addAsset(asset);
//		return "assetForm";
//	}

	@PutMapping("/{id}")
	public void updateAsset(@PathVariable("id") Integer id, @RequestBody Asset asset) {
		assetServices.updateAsset(id, asset);
	}

	@DeleteMapping("/{id}")
	public String deleteAsset(@RequestParam("userId") Integer userId, @PathVariable("id") Integer id) {
		assetServices.deleteAsset(id);
		return "redirect:/users/" + userId + "/assets-amounts";
	}

}
