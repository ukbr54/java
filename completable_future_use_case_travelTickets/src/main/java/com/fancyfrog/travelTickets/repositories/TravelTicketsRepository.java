package com.fancyfrog.travelTickets.repositories;

import com.fancyfrog.travelTickets.model.ticket3.TravelTickets;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface TravelTicketsRepository extends CrudRepository<TravelTickets,String>,QueryByExampleExecutor<String> {

}
