package com.tenpo.challenge.entity.jpa;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "transaction_history")
public class TransactionHistoryJpaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String endpoint;

  private String status;

  private String response;

  @CreationTimestamp private LocalDateTime createdAt;
}
