
package pl.sda.intermediate12.weather.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Clouds {

    @SerializedName("all")
    @Expose
    public Integer all;

}
