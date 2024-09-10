package com.colombia.eps.holidays.infrastructure.output.feing.client;

import com.colombia.eps.holidays.infrastructure.output.feing.entity.ResponseHolidaysApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "ApiHolidaysClient", url = "${holidays-api.service.url}")
public interface IApiHolidaysClient {
    @GetMapping
    List<ResponseHolidaysApi> getHolidays(@RequestParam String country, @RequestParam String year);
}
