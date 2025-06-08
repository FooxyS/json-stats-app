package com.example;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

public class DataAdapter extends TypeAdapter<Data> {
    @Override
    public void write(JsonWriter out, Data value) throws IOException {
        out.beginObject();

        if (value.host() != null) {
            out.name("host").value(value.host());
        } else {
            out.name("host").nullValue();
        }

        if (value.ups_adv_battery_run_time_remaining() != null) {
            out.name("ups_adv_battery_run_time_remaining").value(value.ups_adv_battery_run_time_remaining());
        } else {
            out.name("ups_adv_battery_run_time_remaining").nullValue();
        }

        if (value.ups_adv_output_voltage() != null) {
            out.name("ups_adv_output_voltage").value(value.ups_adv_output_voltage());
        } else {
            out.name("ups_adv_output_voltage").nullValue();
        }

        out.endObject();
    }

    @Override
    public Data read(JsonReader in) throws IOException {
        String host = null;
        Integer runTime = null;
        Integer voltage = null;

        in.beginObject();

        while (in.hasNext()) {
            String name = in.nextName();
            switch (name) {
                case "host" -> {
                    if (in.peek()==JsonToken.NULL) {
                        in.nextNull();
                        host = null;
                    } else if (in.peek()==JsonToken.STRING) {
                        host = in.nextString();
                    }
                }
                case "ups_adv_battery_run_time_remaining" -> {
                    if (in.peek()==JsonToken.NULL) {
                        in.nextNull();
                        runTime = null;
                    } else if (in.peek()==JsonToken.NUMBER) {
                        runTime = in.nextInt();
                    }
                }
                case "ups_adv_output_voltage" -> {
                    if (in.peek()==JsonToken.NULL) {
                        in.nextNull();
                        voltage = null;
                    } else if (in.peek()==JsonToken.NUMBER) {
                        voltage = in.nextInt();
                    } 
                }
                default -> {
                    in.skipValue();
                }
            }
        }

        in.endObject();
        return new Data(runTime, voltage, host);
    }
}
