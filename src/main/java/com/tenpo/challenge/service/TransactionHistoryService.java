package com.tenpo.challenge.service;

import com.tenpo.challenge.entity.model.TransactionHistory;

import java.util.List;

public interface TransactionHistoryService {

  void save(TransactionHistory transactionHistory);

  List<TransactionHistory> findAll(Integer page, Integer size, String sortBy);
}
