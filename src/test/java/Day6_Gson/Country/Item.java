
package Day6_Gson.Country;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Item {

    @SerializedName("country_id")
    @Expose
    private String countryId;
    @SerializedName("country_name")
    @Expose
    private String countryName;
    @SerializedName("region_id")
    @Expose
    private Integer regionId;
    @SerializedName("links")
    @Expose
    private List<Link> links = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Item() {
    }

    /**
     * 
     * @param regionId
     * @param links
     * @param countryName
     * @param countryId
     */
    public Item(String countryId, String countryName, Integer regionId, List<Link> links) {
        super();
        this.countryId = countryId;
        this.countryName = countryName;
        this.regionId = regionId;
        this.links = links;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("countryId", countryId).append("countryName", countryName).append("regionId", regionId).append("links", links).toString();
    }

}
