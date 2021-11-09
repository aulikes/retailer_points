package com.springboot.retailer.app.retailer_points.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(exclude = {"purchaseDetail"})
@Table(name="ITEM")
public class Item {

    @Id
    @Column(name="ID")
	private Long id;

    @Column(name="NAME", nullable=false)
	private String name;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item", orphanRemoval = true)
	@JsonIgnoreProperties("item")
	private List<PurchaseDetail> purchaseDetail;

	public Item(Long id, String name) {
		this.id = id;
		this.name = name;
	}
}
