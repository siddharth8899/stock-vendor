package com.internship.quantitycontroller.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.internship.quantitycontroller.entity.SmsPojo;
import com.internship.quantitycontroller.entity.Stock;
import com.internship.quantitycontroller.entity.Vendor;
import com.internship.quantitycontroller.entity.WrapperPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.ws.rs.core.MediaType;


@RestController
@Component

public class QuantityController {
    @Autowired
    RestTemplate restTemplate;
//    @Autowired
//    WebClient.Builder webClientBuilder;

//    @GetMapping("/")
//    public List<Stock_id_qty> checkQty(){
//        List<Stock_id_qty> stock = restTemplate.getForObject("http://localhost:2000/stocks/qty", List.class);
//        List<Stock_id_qty> result = new ArrayList<>();
//        for(Stock_id_qty s : stock){
//            if(s.getStockQty() < 10)
//            {
//                result.add(s);
//            }
//        }
//        return result;
//    }

//    @GetMapping("/")
//    public List<Stock_id_qty> checkQty() throws JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
//        JsonNode stocks = restTemplate.getForObject("http://localhost:2000/stocks/qty", JsonNode.class);
//        List<Stock_id_qty> stockList = mapper.convertValue(stocks, new TypeReference<List<Stock_id_qty>>() {});
//        List<Stock_id_qty> result = new ArrayList<>();
//        for(Stock_id_qty s : stockList){
//            if(s.getStockQty() < 10)
//            {
//                result.add(s);
//            }
//        }
//        return result;
//    }
//
//        @GetMapping("/")
//    public List<Stock_id_qty> checkQty(){
//            ObjectMapper mapper=new ObjectMapper();
//        List<Stock_id_qty> stocks = restTemplate.getForObject("http://STOCK-SERVICE/stocks/qty", List.class);
//        List<Stock_id_qty> stocks = webClientBuilder.build()
//                    .get()
//                    .uri("http://STOCK-SERVICE/stocks/qty")
//                    .retrieve()
//                    .bodyToMono(List.class)
//                    .block();

        //iska bina nhii chalega, HashMap error again
//        List<Stock_id_qty> stockList = mapper.convertValue(stocks, new TypeReference<List<Stock_id_qty>>() {});
//        List<Stock_id_qty> result = new ArrayList<>();
//        for(Stock_id_qty s : stockList){
//            if(s.getStockQty() < 10)
//            {
//                result.add(s);
//
//            }
//        }
//        return result;
//    }
    /*
    check threshold everyday
    if threshold is less
    place a purchase order when threshold is less to the vendor
     */

    @Scheduled(fixedRate = 2000)
    //@GetMapping("/qtyController")
   // @JsonIgnoreProperties
    public List<WrapperPojo> checkQty() throws JsonProcessingException, URISyntaxException {
        ObjectMapper mapper=new ObjectMapper();
//        List<Stock> wps = restTemplate.getForObject("http://STOCK-SERVICE/stocks/allStocks", List.class);
//        List<Stock> stockList = mapper.convertValue(wps, new TypeReference<List<Stock>>() {});
        List<WrapperPojo> wps = restTemplate.getForObject("http://STOCK-SERVICE/stocks/", List.class);
        List<WrapperPojo> stockList = mapper.convertValue(wps, new TypeReference<List<WrapperPojo>>() {});
        List<WrapperPojo> result = new ArrayList<>();
        for(WrapperPojo stock : stockList){
            Stock s = stock.getStock();
            if(s.getStockQty() < 50)
            {
                result.add(stock);
                //sendSms(s.getVendorId());
            }
        }
        System.out.println(result);
        return result;
    }
//    public void sendSms(Long vendorId) throws JsonProcessingException, URISyntaxException {
//        // ObjectMapper mapper=new ObjectMapper();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(org.springframework.http.MediaType.valueOf(MediaType.APPLICATION_JSON));
//        HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
//       // ResponseEntity<String> response = restTemplate.put(url, entity);
//        URI uri = new URI("http://SMS-SERVICE/sms");
//        //Vendor vendor = restTemplate.getForObject("http://VENDOR-SERVICE/" + vendorId, Vendor.class);
//        //String to = vendor.getVendorPhoneNumber();
//        String to = "+919999632030";
//        String msg = "hello";
//        SmsPojo smsBody = new SmsPojo(to,msg);
//        HttpEntity<SmsPojo> request = HttpEntity<>(new SmsPojo(smsBody), headers);
//        //String JsonSms = mapper.writeValueAsString(smsBody);
//        //System.out.print(JsonSms);
//        //restTemplate.postForObject(uri, request, SmsPojo.class, to, msg);
//        restTemplate.put(uri, entity);
//        //restTemplate.postForObject("http://SMS-SERVICE/sms", to, msg);
//    }

    public void sendSms(Long vendorId) throws URISyntaxException {
        //Vendor vendor = restTemplate.getForObject("http://VENDOR-SERVICE/" + vendorId, Vendor.class);
        //String to = vendor.getVendorPhoneNumber();
        String to = "+919999632030";
        String msg = "hello";
//        final String baseUrl = "http://SMS-SERVICE/sms";
//        URI uri = new URI(baseUrl);
        SmsPojo sms = new SmsPojo(to, msg);
        ResponseEntity<String> result = restTemplate.postForEntity("http://SMS-SERVICE/sms", sms, String.class);
    }
}
