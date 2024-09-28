package com.colombia.eps.holidays.infrastructure.input.rest;

import com.colombia.eps.holidays.application.dto.DatesResponse;
import com.colombia.eps.holidays.application.dto.HolidayRequest;
import com.colombia.eps.holidays.application.handler.IHolidaysHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/holidays")
@RequiredArgsConstructor
public class HolidaysController {

    private final IHolidaysHandler holidaysHandler;

    @Operation(summary = "get holidays dates for the city and the national holidays dates")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get holidays successfully", content = @Content),
            @ApiResponse(responseCode = "500", description = "Fail process for get holidays", content = @Content)
    })
    @GetMapping("/get-holidays")
    public List<DatesResponse> getHolidays(@RequestParam(name = "city") List<String> city){
        return holidaysHandler.getHolidays(city);
    }

    @Operation(summary = "create national holidays")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Create process successfully", content = @Content),
            @ApiResponse(responseCode = "500", description = "Fail process for create national holidays", content = @Content)
    })
    @GetMapping("/create-national-holidays")
    public String createNationalHolidays(){
        return holidaysHandler.createNationalHolidays();
    }

    @Operation(summary = "create local holidays")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Create process successfully", content = @Content),
            @ApiResponse(responseCode = "500", description = "Fail process for create national holidays", content = @Content)
    })
    @PostMapping("/create-local-holidays")
    public String createLocalHolidays(@RequestBody HolidayRequest holidayRequest){
        return holidaysHandler.createHoliday(holidayRequest);
    }
}
