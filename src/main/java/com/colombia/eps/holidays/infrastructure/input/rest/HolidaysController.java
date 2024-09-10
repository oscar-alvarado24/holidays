package com.colombia.eps.holidays.infrastructure.input.rest;

import com.colombia.eps.holidays.application.dto.DatesResponse;
import com.colombia.eps.holidays.application.dto.HolidayRequest;
import com.colombia.eps.holidays.application.handler.IHolidaysHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/holidays/{city}")
    public DatesResponse getHolidays(@PathVariable(name = "city") String city){
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
