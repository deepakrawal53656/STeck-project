package com.app.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.dao.TradeRepository;
import com.app.model.Trade;
import com.app.request.TradeRequest;
import com.app.service.TradeService;

@Service
public class TradeServiceServiceImpl implements TradeService{

	@Autowired
	TradeRepository tradeRepository;
	
	@Override
	public Trade insert(TradeRequest tradeRequest, long userId) {
			
		Trade trade = new Trade(); 
		trade.setCurrencyPair(tradeRequest.getCurrencyPair());
		trade.setCustomerName(tradeRequest.getCustomerName());
		trade.setRate(tradeRequest.getRate());
		trade.setAmount( (tradeRequest.getAmount() * tradeRequest.getRate()) );
		trade.setUserId(userId);
		
		return tradeRepository.save(trade);
	}

	@Override
	public List<Trade> getTradeList(int pageNumber, int pageSize, long userId) {
		
		Pageable pageable = new PageRequest(pageNumber-1, pageSize);
		
		return tradeRepository.findByUserId(userId, pageable);
		
	}

	
	
}
