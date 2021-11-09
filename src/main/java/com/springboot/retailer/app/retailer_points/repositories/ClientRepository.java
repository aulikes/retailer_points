package com.springboot.retailer.app.retailer_points.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.retailer.app.retailer_points.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	
	@Query("select distinct u from Client u, Purchase p where u.id = p.client "
			+ "and (to_char(p.dateTime, 'YYYY-MM') between to_char(add_months(sysdate, -?1), 'YYYY-MM') and to_char(add_months(sysdate, -?2), 'YYYY-MM')) order by u.id")
    List<Client> findClientsByPurchaseDate(int minusInitMonth, int minusFinalMonth);
}
