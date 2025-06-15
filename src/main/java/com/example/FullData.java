package com.example;

import com.google.gson.annotations.SerializedName;

public record FullData(
    Integer ups_adv_output_load,
    Integer ups_adv_battery_temperature,
    @SerializedName("@timestamp") String timestamp,
    String host,
    Integer ups_adv_battery_run_time_remaining,
    Integer ups_adv_output_voltage
) {
    
}
