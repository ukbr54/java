package com.fancyfrog.travelTickets.repositories;

import com.fancyfrog.travelTickets.model.ticket3.CityDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface CityDetailsRepository extends CrudRepository<CityDetails,String>,QueryByExampleExecutor<CityDetails> {

}
