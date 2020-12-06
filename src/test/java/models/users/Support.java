
package models.users;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Support {

    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("text")
    @Expose
    public String text;

}
