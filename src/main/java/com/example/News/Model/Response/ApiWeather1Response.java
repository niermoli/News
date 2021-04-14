package com.example.News.Model.Response;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiWeather1Response {

    @SerializedName("location")
    private Location location;

    @SerializedName("current")
    private Current current;
}
