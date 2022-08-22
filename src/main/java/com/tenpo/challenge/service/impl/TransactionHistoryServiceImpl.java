package com.tenpo.challenge.service.impl;

import com.tenpo.challenge.adapter.mapper.jpa.TransactionHistoryJpaMapper;
import com.tenpo.challenge.adapter.mapper.model.TransactionHistoryMapper;
import com.tenpo.challenge.entity.jpa.TransactionHistoryJpaEntity;
import com.tenpo.challenge.entity.model.TransactionHistory;
import com.tenpo.challenge.repository.TransactionHistoryJpaRepository;
import com.tenpo.challenge.service.TransactionHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionHistoryServiceImpl implements TransactionHistoryService {

  @Autowired private final TransactionHistoryJpaRepository transactionHistoryJpaRepository;

  @Override
  @Transactional
  public void save(TransactionHistory transactionHistory) {
    transactionHistoryJpaRepository.save(TransactionHistoryMapper.map(transactionHistory));
  }

  @Override
  public List<TransactionHistory> findAll(Integer page, Integer size, String sortBy) {
    Pageable paging = PageRequest.of(page, size, Sort.by(sortBy));
    Page<TransactionHistoryJpaEntity> pageResult = transactionHistoryJpaRepository.findAll(paging);
    return pageResult.hasContent()
        ? pageResult.getContent().stream().map(TransactionHistoryJpaMapper::map).toList()
        : List.of();
  }
}
