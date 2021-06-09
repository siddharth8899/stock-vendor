package com.internship.stock.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.internship.stock.enitity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Service
public class StockService {
    @Autowired
    StockRepository repo;
//    @PersistenceContext
//    private EntityManager entityManager;
    @Autowired
    RestTemplate restTemplate;
//    @Autowired
//    WebClient.Builder webClientBuilder;

//    @OneToMany
//    @JsonIgnore
//    @JoinColumn(name = "vendorId", referencedColumnName = "vendorId")
//    @Fetch(FetchMode.JOIN)

    public Stock addStock(Stock stock) {
        return repo.save(stock);
    }


    public WrapperPojo getStock(Long stockId) throws IOException {
        //ObjectMapper mapper = new ObjectMapper();
        Stock s =  repo.findByStockId(stockId);
        WrapperPojo wp = new WrapperPojo();
        Long vendorId = s.getVendorId();
        Vendor v = restTemplate.getForObject("http://VENDOR-SERVICE/vendors/"+ vendorId, Vendor.class);
        wp.setStock(s);
        wp.setVendor(v);
        return wp;
//        WrapperPojo wp = new WrapperPojo();
//        wp.setStock(stock);
//        //List<Long> vendorId = stock.getVendorId();
//        List<Long> vendorId = mapper.readValue((JsonParser) stock.getVendorId(), List.class);
//        List<Vendor> vendors = new ArrayList<>();
//        System.out.print(vendorId);
//        for(Long i : vendorId){
//            Vendor vendor = restTemplate.getForObject("http://localhost:3000/vendors/"+i, Vendor.class);
//            //Vendor vendor = mapper.readValue("http://VENDOR-SERVICE/vendors/"+i, Vendor.class);
//           // Vendor vendorV = mapper.convertValue();
//            System.out.print(vendor);
//            vendors.add(vendor);
//        }
//        wp.setVendor(vendors);
    }

    public List<Stock_id_qty> getStockQty() {
        List<Stock> list = repo.findAll();
        List<Stock_id_qty> stockQtys = new ArrayList<>();
        Long stockId;
        String stockName;
        Long stockQty;
        for(Stock s : list)
        {
            stockId= s.getStockId();
            stockName=s.getStockName();
            stockQty=s.getStockQty();
            Stock_id_qty stock = new Stock_id_qty(stockId, stockName, stockQty);
            stockQtys.add(stock);
        }
        return stockQtys;
    }

    public List<WrapperPojo> getStocks() throws IOException {
        List<Stock> stockList =  repo.findAll();
        List<WrapperPojo> result= new ArrayList<>();
        for(Stock s : stockList){
            //WrapperPojo wp =  getStock(s.getStockId());
            WrapperPojo wp = new WrapperPojo();
            Long vendorId = s.getVendorId();
            Vendor v = restTemplate.getForObject("http://VENDOR-SERVICE/vendors/"+ vendorId, Vendor.class);
            wp.setVendor(v);
            wp.setStock(s);
            result.add(wp);
        }
        return result;
    }

    public Stock deleteStock(Long stockId) {
        Stock s = repo.findByStockId(stockId);
        repo.delete(s);
        return s;
    }

    public Stock deleteStockByQty(Long stockId, Long qty) {
        Stock s = repo.findByStockId(stockId);
        Long q = s.getStockQty();
        q = q - qty;
        s.setStockQty(q);
        repo.save(s);
        return s;
    }

    public List<Stock> getAllStocks() {
        List<Stock> stockList = repo.findAll();
        return stockList;
    }


    public List<WrapperPojo> checkQty() throws IOException, URISyntaxException {
        ObjectMapper mapper = new ObjectMapper();
        List<WrapperPojo> wps = getStocks();
        List<WrapperPojo> stockList = mapper.convertValue(wps, new TypeReference<List<WrapperPojo>>() {});
        List<WrapperPojo> result=new ArrayList<>();
        for(WrapperPojo stock : stockList){
            Stock s = stock.getStock();
            if(s.getStockQty() < 1500)
            {
                result.add(stock);
                //sendSms(s.getVendorId());
                //sendEmail(stock);
            }
        }
        System.out.println(result);
        return result;
    }

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

    public void sendEmail(WrapperPojo stock){
        ResponseEntity<String> result = restTemplate.postForEntity("http://EMAIL-SERVICE/email/", stock, String.class);
    }
}
