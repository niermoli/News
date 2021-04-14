package com.example.News.Model.Response;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Precipitation {

    @SerializedName("value")
    private String value;

    @SerializedName("units")
    private String units;
}
