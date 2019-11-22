package com.app.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Trade;

public interface TradeRepository extends JpaRepository<Trade, Long>{

	List<Trade> findByUserId(long userId, Pageable pageable);

}
