package com.example;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class Main {
    public static void main(String[] args) {
        InputStream input = Main.class.getResourceAsStream("/"+args[1]);

        InputStreamReader reader = new InputStreamReader(input);

        Gson gson = new GsonBuilder()
        .registerTypeAdapter(Data.class, new DataAdapter())
        .create();

        Type listType = new TypeToken<List<Data>>(){}.getType();
        List<Data> list = gson.fromJson(reader, listType);

        switch (args[0]) {
            case "avg" -> {
                System.out.println(avg(list));
            }
            case "max" -> {
                System.out.println(max(list));
            }
            case "values" -> {
                System.out.println(values(list));
            }
            default -> {
                System.out.println("Введена неправильная команда");
            }
        }
    }

    public static double avg(List<Data> list) {
        double sum = 0;
        int counter = 0;
        
        for (Data data : list) {
            if (data.ups_adv_battery_run_time_remaining()!=null) {
                sum += data.ups_adv_battery_run_time_remaining();
                counter++;
            }
        }

        return counter == 0 ? 0.0 : sum/counter;
    }

    public static int max(List<Data> list) {
        int max = 0;

        for (Data data : list) {
            if (data.ups_adv_output_voltage() != null && data.ups_adv_output_voltage() > max) {
                max = data.ups_adv_output_voltage();
            }
        }

        return max;
    }

    public static Set<String> values(List<Data> list) {
        Set<String> hosts = new HashSet<>();

        for (Data data : list) {
            if (data.host() != null) {
                hosts.add(data.host());
            }
        }

        return hosts;
    }
}