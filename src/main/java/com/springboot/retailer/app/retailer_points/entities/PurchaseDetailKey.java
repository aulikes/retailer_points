package com.springboot.retailer.app.retailer_points.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDetailKey implements Serializable {
	private static final long serialVersionUID = -3820002697351327623L;

	@Column(name = "PURCHASE")
	private Long idPurchase;

	@Column(name = "ITEM")
	private Long idItem;


	@Override
	public String toString() {
		return "PurchaseDetailID [Purchase=" + idPurchase + ", Item=" + idItem + "]";
	}
}
