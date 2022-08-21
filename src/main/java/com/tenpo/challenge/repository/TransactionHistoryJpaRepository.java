package com.tenpo.challenge.repository;

import com.tenpo.challenge.entity.jpa.TransactionHistoryJpaEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TransactionHistoryJpaRepository
    extends PagingAndSortingRepository<TransactionHistoryJpaEntity, Long> {}
