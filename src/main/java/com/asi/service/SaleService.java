package com.asi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asi.repository.SaleRepository;

@Service
public class SaleService {
	@Autowired
	SaleRepository saleRepository;
}
