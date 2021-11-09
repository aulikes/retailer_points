package com.springboot.retailer.app.retailer_points.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(exclude = { "client", "purchaseDetails" })
@Table(name = "PURCHASE")
public class Purchase {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "purchSeq")
	@SequenceGenerator(name = "purchSeq", sequenceName = "SEQ_PURCHASE ", allocationSize = 1)
	private Long id;

//    @JsonSerialize(using = CalenderSerializer.class)
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Paris")
	@Column(name = "DATE_TIME", nullable = false)
	private Date dateTime;

	@Column(name = "POINT", nullable = false)
	private Long point;

	@ManyToOne
	@JoinColumn(name = "CLIENT", insertable = true, updatable = false, nullable = false)
	@JsonIgnoreProperties("purchases")
	private Client client;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "purchase", orphanRemoval = true)
	@JsonIgnoreProperties("purchase")
	private List<PurchaseDetail> purchaseDetails;

	public Purchase() {
	}

	public Purchase(Long id, Date dateTime, Long point, Client client) {
		this.id = id;
		this.dateTime = dateTime;
		this.point = point;
		this.client = client;
	}

	public Purchase(Date dateTime, Long point, Client client, List<PurchaseDetail> purchaseDetails) {
		this.dateTime = dateTime;
		this.point = point;
		this.client = client;
		this.purchaseDetails = purchaseDetails;
	}

}
