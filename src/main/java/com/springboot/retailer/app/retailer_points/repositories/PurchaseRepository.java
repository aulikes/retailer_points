package com.springboot.retailer.app.retailer_points.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.retailer.app.retailer_points.entities.Client;
import com.springboot.retailer.app.retailer_points.entities.Purchase;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

	@Query(value = "select p.client, to_char(p.date_time, 'YYYY-MM') as date_temp, sum(p.point) as point from Purchase p "
			+ "    where ((to_char(p.date_time, 'YYYY-MM') < to_char(sysdate, 'YYYY-MM') and to_char(p.date_time, 'YYYY-MM') >= to_char(add_months(sysdate, -3), 'YYYY-MM')))"
			+ "group by p.client, to_char(p.date_time, 'YYYY-MM')", nativeQuery = true)
	List<Object> findPurchaseByDate();	
	
	@Query("select p from Purchase p where "
			+ "(to_char(p.dateTime, 'YYYY-MM') between to_char(add_months(sysdate, -?1), 'YYYY-MM') and to_char(add_months(sysdate, -?2), 'YYYY-MM')) order by p.client")
    List<Purchase> findPurByDateMon(int minusInitMonth, int minusFinalMonth);
	
	@Query("select p from Purchase p where "
			+ "(to_char(p.dateTime, 'YYYY-MM') between to_char(add_months(sysdate, -?1), 'YYYY-MM') and to_char(add_months(sysdate, -?2), 'YYYY-MM'))"
			+ "and p.client = ?3 order by p.client")
    List<Purchase> findPurByDateMonCli(int minusInitMonth, int minusFinalMonth, Client client);
}
