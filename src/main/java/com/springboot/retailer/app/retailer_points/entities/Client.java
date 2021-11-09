package com.springboot.retailer.app.retailer_points.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;


@Entity
@Data
//@EqualsAndHashCode(exclude = {"purchases"})
@Table(name="CLIENT")
public class Client {
    @Id
    @Column(name="ID")
    private Long id;

    @Column(name="FIRSTNAME", nullable = false)
    private String firstName;

    @Column(name="LASTNAME", nullable = false)
    private String lastName;
    
    @Transient
	private List<Purchase> purchases;

    public Client(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Client() {
    }
}
