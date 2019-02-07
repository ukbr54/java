package com.fancyfrog.travelTickets.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.OptionalInt;
import java.util.stream.IntStream;

@Configuration
public class WebConfigurer {

    @Bean
    @Primary
    public ObjectMapper objectMapper(){
        return new CustomObjectMapper();
    }

    @Bean
    public HttpClient httpClient(){
        return HttpClients.createDefault();
    }

    @Bean
    public ClientHttpRequestFactory httpRequestFactory(){
        return new HttpComponentsClientHttpRequestFactory(httpClient());
    }

    @Bean
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate(httpRequestFactory());

        //Find the index of Jackson2HttpMessageConverter index.
        OptionalInt index = IntStream.range(0, restTemplate.getMessageConverters().size())
                .filter(i -> restTemplate.getMessageConverters().get(i) instanceof MappingJackson2HttpMessageConverter)
                .findFirst();

        //Replace the HttpMessageConverter with custom one to use CtsJacksonObjectMapper.
        if(index.isPresent()){
            restTemplate.getMessageConverters().set(index.getAsInt(),new MappingJackson2HttpMessageConverter(objectMapper()));
        }

        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

        return restTemplate;
    }
}
