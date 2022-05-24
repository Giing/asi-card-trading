package com.asi.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.asi.dto.SaleDto;
import com.asi.dto.SaleTransactionDto;
import com.asi.model.Sale;
import com.asi.service.SaleService;

@RestController
public class SaleController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	SaleService saleService;

	private SaleDto convertToDto(Sale sale) {
		SaleDto saleDto = modelMapper.map(sale, SaleDto.class);
		saleDto.setCard(sale.getCardInstance().getCardInstance());
		saleDto.setIdCard(sale.getCardInstance().getIdInstance());
	    return saleDto;
	}
	
//	private Sale convertToEntity(SaleDto saleDto) throws ParseException {
//		Sale sale = modelMapper.map(saleDto, Sale.class);
//	    return sale;
//	}
	
	//TODO enlever user le param user dans les routes quand fct getcurrentUser dispo
	// error code html
	
	@RequestMapping(method=RequestMethod.POST,value="/api/sales/sell")
	public int sellCard(@RequestBody SaleTransactionDto saleDto) {
		System.out.println(saleDto.getIdSale() + " " + saleDto.getIdUser() + " " + saleDto.getPriceSale());
		int code = saleService.sell(saleDto.getIdUser(), saleDto.getIdCard(), saleDto.getPriceSale());
		return code;
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/api/sales/buy")
	public int buyCard(@RequestBody SaleTransactionDto saleDto) {
		int code = saleService.buy(saleDto.getIdSale(), saleDto.getIdUser());
		System.out.println(saleDto.getIdSale() + " " + saleDto.getIdUser());
		return code;
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/api/sales")
	public List<SaleDto> get() {
		return saleService.getAllSales()
				.stream()
				.map(s -> convertToDto(s))
				.collect(Collectors.toList());
	}
}
