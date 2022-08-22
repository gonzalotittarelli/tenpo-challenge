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

  @Column(nullable = false)
  private String endpoint;

  @Column(nullable = false)
  private String status;

  @CreationTimestamp
  @Column(nullable = false)
  private LocalDateTime createdAt;
}
