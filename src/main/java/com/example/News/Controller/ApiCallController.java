package com.example.News.Controller;

import com.example.News.Model.Response.ApiWeather1Response;
import com.example.News.Service.ApiCallService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequestMapping("/api")
@RestController
public class ApiCallController {

    @Autowired
    ApiCallService apiCallService;

    @GetMapping
    @Operation(summary = "Weather Api endpoints")
    public Object callApi() {
        try {
            ApiWeather1Response apiWeather1Response = apiCallService.callApi1();
            if (apiWeather1Response.getLocation() == null){
                return apiCallService.callApi2();
            }
            return apiWeather1Response;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
