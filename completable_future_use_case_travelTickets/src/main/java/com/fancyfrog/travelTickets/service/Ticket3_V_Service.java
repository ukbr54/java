package com.fancyfrog.travelTickets.service;

import com.fancyfrog.travelTickets.common.CsvService;
import com.fancyfrog.travelTickets.model.ticket3.CityDetails;
import com.fancyfrog.travelTickets.model.ticket3.CountryDetails;
import com.fancyfrog.travelTickets.model.ticket3.TravelTickets;
import com.fancyfrog.travelTickets.repositories.CityDetailsRepository;
import com.fancyfrog.travelTickets.repositories.CountryDetailsRepository;
import com.fancyfrog.travelTickets.repositories.TravelTicketsRepository;
import com.fancyfrog.travelTickets.vo.ticket3_V.TicketVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class Ticket3_V_Service {

    private @Autowired CsvService csvService;
    private @Autowired TravelTicketsRepository travelTicketsRepository;
    private @Autowired CityDetailsRepository cityDetailsRepository;
    private @Autowired CountryDetailsRepository countryDetailsRepository;

    public void dumpCsvFileIntoRedisServer(String fileName){
        List<TicketVO> ticketData = csvService.readCsvFile(TicketVO.class, fileName);
        Map<String, List<TicketVO>> groupByCountry = ticketData.stream().collect(Collectors.groupingBy(TicketVO::getCountry));
        for(Map.Entry<String,List<TicketVO>> map : groupByCountry.entrySet()){
            List<CityDetails> savedCities = new ArrayList<>();
            Map<String, List<TicketVO>> groupByCity = map.getValue().stream().collect(Collectors.groupingBy(TicketVO::getCity));

            for(Map.Entry<String,List<TicketVO>> secondMap : groupByCity.entrySet()){
                List<TravelTickets> tickets = secondMap.getValue().stream().map(TravelTickets::new).collect(Collectors.toList());
                List<TravelTickets> savedTickets =
                        tickets.stream().map(ticket -> travelTicketsRepository.save(ticket)).collect(Collectors.toList());

                if(secondMap.getKey().matches("[a-zA-Z]+")){
                    CityDetails cityDetails = new CityDetails();
                    cityDetails.setCity(secondMap.getKey());
                    cityDetails.setContinent(secondMap.getValue().stream().findFirst().get().getContinent());
                    cityDetails.setRegion(secondMap.getValue().stream().findFirst().get().getRegion());
                    cityDetails.setIATACode(secondMap.getValue().stream().findFirst().get().getIATACode());
                    cityDetails.setTickets(savedTickets);
                    cityDetailsRepository.save(cityDetails);
                    savedCities.add(cityDetails);
                }
            }

            CountryDetails countryDetails = new CountryDetails();
            countryDetails.setCountry(map.getKey());
            countryDetails.setCities(savedCities);
            countryDetailsRepository.save(countryDetails);
        }
    }
}
