package hr.java.tasks.weather.weatherin2.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MainDTO {
    @JsonProperty
    private Double temp;
    @JsonProperty
    private Double humidity;

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }
}
