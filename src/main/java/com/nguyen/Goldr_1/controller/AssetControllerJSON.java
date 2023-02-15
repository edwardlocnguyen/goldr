package com.nguyen.Goldr_1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nguyen.Goldr_1.services.AssetServices;

@RestController
@RequestMapping("/users/{id}/api")
public class AssetControllerJSON {

	@Autowired
	private AssetServices assetServices;
	
//	@GetMapping("/assets")
	
}
