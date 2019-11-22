package com.app.service;

import java.util.List;

import com.app.model.Trade;
import com.app.request.TradeRequest;

public interface TradeService {
	
	
	public Trade insert(TradeRequest mirgateRequest, long userId);

	List<Trade> getTradeList(int pageNumber, int pageSize, long userId);
	
}
