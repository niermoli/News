package com.example.News.Model.Response;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiWeather2Response {

    @SerializedName("lat")
    private Double lat;

    @SerializedName("lon")
    private Double lon;

    @SerializedName("precipitation")
    private Precipitation precipitation;

    @SerializedName("observation_time")
    private ObservationTime observationTime;
    
}
