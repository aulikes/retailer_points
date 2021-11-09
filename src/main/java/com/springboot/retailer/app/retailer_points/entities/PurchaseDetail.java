package com.springboot.retailer.app.retailer_points.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EqualsAndHashCode(exclude = { "purchase", "item" })
@Table(name = "PURCHASE_DETAIL")
public class PurchaseDetail {

	@EmbeddedId
	PurchaseDetailKey id;

	@ManyToOne
	@MapsId("idPurchase")
	@JoinColumn(name = "PURCHASE", insertable=true, updatable=false, nullable = false)
	@JsonIgnoreProperties("purchaseDetails")
	private Purchase purchase;

	@ManyToOne
	@MapsId("idItem")
	@JoinColumn(name = "ITEM", insertable=true, updatable=false, nullable = false)
	@JsonIgnoreProperties("purchaseDetail")
	private Item item;

	@Column(name = "VALUEUNIT")
	private Double value;

	@Column(name = "CANT")
	private Integer cant;

	public PurchaseDetail(Purchase purchase, Item item, Double value, Integer cant) {
		this.id = new PurchaseDetailKey(purchase.getId(), item.getId());
		this.purchase = purchase;
		this.item = item;
		this.value = value;
		this.cant = cant;
	}
}
