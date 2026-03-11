package com.transaction.entity;

import java.time.LocalDateTime;

import com.transaction.TransactionStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
@Builder
@Table(name = "transactions")
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String fromAccount;
	private String toAccount;
	private Double amount;
	private LocalDateTime transactionTime;
	@Enumerated(EnumType.STRING)
	private TransactionStatus status;

}
