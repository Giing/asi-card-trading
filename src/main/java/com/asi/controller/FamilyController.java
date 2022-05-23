package com.asi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.asi.service.FamilyService;

@RestController
public class FamilyController {
	@Autowired
	FamilyService familyService;
}
