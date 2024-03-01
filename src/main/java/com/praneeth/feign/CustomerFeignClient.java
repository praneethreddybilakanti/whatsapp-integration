package com.praneeth.feign;

import com.praneeth.config.FeignConfig;
import com.praneeth.dto.response.external.CustomerBasicInfo;
import com.praneeth.model.DartContact;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The interface Customer feign client.
 */
@FeignClient(name = "customer-service", url = "${customer-service}", configuration = FeignConfig.class)
public interface CustomerFeignClient {
  /**
   * Gets basic customer details.
   *
   * @param offset
   *     the offset
   * @param page
   *     the page
   *
   * @return the basic customer details
   */
  @GetMapping(value = "/customer/basic-info")
  ResponseEntity<List<CustomerBasicInfo>> getBasicCustomerDetails(
      @RequestParam(defaultValue = "10", required = false) Integer offset, @RequestParam(defaultValue = "1",
      required = false) Integer page);


  /**
   * Gets basic customer details 1.
   *
   * @return the basic customer details 1
   */
  @GetMapping(value = "/customer/basic-info")
  ResponseEntity<List<CustomerBasicInfo>> getBasicCustomerDetails1();

  /**
   * Gets all customers.
   *
   * @return the all customers
   */
  @GetMapping
  List<DartContact> getAllCustomers();
}
