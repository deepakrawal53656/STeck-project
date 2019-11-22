package com.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.config.Config;
import com.app.model.Trade;
import com.app.request.TradeRequest;
import com.app.response.DataResponse;
import com.app.response.RestResponse;
import com.app.service.TradeService;


@RestController
@RequestMapping("/api/v1/")
public class TradeController {
		
	@Autowired
	TradeService tradeService;
	
	public static final Logger logger = LoggerFactory.getLogger(TradeController.class);

	
	@PostMapping(value = "/trade/", produces = "application/json")
	public RestResponse insert(HttpServletRequest req,
			@RequestHeader("Authorization") String auth,
			@Valid  @RequestBody TradeRequest tradeRequest , BindingResult br) {
		
		if (br.hasErrors()) {
			return new DataResponse(400, br.getAllErrors().get(0).getDefaultMessage(), null);
		}
		
		try {
			long userId = Integer.parseInt(req.getAttribute("id").toString());		
			Trade trade = tradeService.insert(tradeRequest, userId);
			
			String message = "Trade for "+tradeRequest.getCurrencyPair()+" has been booked with rate "+tradeRequest.getRate()+". The amount of Rs "+trade.getAmount()+" "
					+ " will be transferred in 2 working days to "+tradeRequest.getCustomerName()+"";
			
			
			logger.info("Trade inserted");
			return new DataResponse(201, message, trade);
		}catch (Exception e) {
			logger.error("Error:- {}",e);
			return new DataResponse(500, e.getMessage(), tradeRequest);
		}
		
	}
	
	@GetMapping(value = "/currencies/", produces = "application/json")
	public RestResponse currencyList(
			HttpServletRequest req,
			@RequestHeader("Authorization") String auth) {
		
		try {
			return new DataResponse(200, "Success", Config.currencyList);
		}catch (Exception e) {
			logger.error("Error:- {}",e);
			return new DataResponse(500, e.getMessage(), null);
		}
	}
	
	@GetMapping(value = "/trades/", produces = "application/json")
	public RestResponse tradeList(
			HttpServletRequest req,
			@RequestHeader("Authorization") String auth,
			@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize) {
		
		try {
			long userId = Integer.parseInt(req.getAttribute("id").toString());
			Object tradeList = tradeService.getTradeList(pageNumber, pageSize, userId);
			
			logger.info("Trade list");
			return new DataResponse(200, "Success", tradeList);
		}catch (Exception e) {
			logger.error("Error:- {}",e);
			return new DataResponse(500, e.getMessage(), null);
		}
		
	}
	
}
