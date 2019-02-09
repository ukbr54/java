package com.fancyfrog.travelTickets.repositories;

import com.fancyfrog.travelTickets.model.ticket3.CountryDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface CountryDetailsRepository extends CrudRepository<CountryDetails,String>,QueryByExampleExecutor<CountryDetails> {
}
