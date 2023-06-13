package com.ll.beansight.boundedContext.search.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

@Builder
public class CafeInfoResponse{
    @JsonProperty("id")
    private Long id;

    @JsonProperty("place_name")
    private String placeName;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("address_name")
    private String addressName;

    @JsonProperty("road_address_name")
    private String roadAddressName;

    @JsonProperty("x")
    private double longitude;

    @JsonProperty("y")
    private double latitude;

    @JsonProperty("place_url")
    private String placeUrl;

    @JsonProperty("distance")
    private double distance;

    @JsonProperty("cafe_tag")
    private List<String> cafeTag;
}
