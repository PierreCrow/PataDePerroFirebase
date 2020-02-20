package pe.com.patadeperro.data.datasource.cloud.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "CloudLost")
public class CloudLost {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @SerializedName("id")
    private String id;
    private String idCloud;
    private String petName;
    private String race;
    private String gender;
    private String color;
    private String age;
    private String contactPhoneNumber;
    private String contactName;
    private String description;
    private String reward;
    private String rewardAmount;
    private String country;
    private String state;
    private String city;
    private String urlImage;
    private String lat;
    private String lng;
    private String lostAddress;
    private String found;

    @Ignore
    public CloudLost(
            String petName,
            String contactPhoneNumber,
            String contactName) {
        this.petName = petName;
        this.contactPhoneNumber = contactPhoneNumber;
        this.contactName = contactName;
    }

    @Ignore
    public CloudLost() {
        this.id = "0";
        this.idCloud = "";
        this.petName = "";
        this.race = "";
        this.gender = "";
        this.color = "";
        this.age = "";
        this.contactPhoneNumber = "";
        this.contactName = "";
        this.description = "";
        this.reward = "";
        this.rewardAmount = "";
        this.country = "";
        this.state = "";
        this.city = "";
        this.urlImage = "";
        this.lat = "";
        this.lng = "";
        this.lostAddress = "";
        this.found = "";
    }

    public CloudLost(
            String id,
            String idCloud,
            String petName,
            String race,
            String gender,
            String color,
            String age,
            String contactPhoneNumber,
            String contactName,
            String description,
            String reward,
            String rewardAmount,
            String country,
            String state,
            String city,
            String urlImage,
            String lat,
            String lng,
            String lostAddress,
            String found)
    {
        this.id = id;
        this.idCloud = idCloud;
        this.petName = petName;
        this.race = race;
        this.gender = gender;
        this.color = color;
        this.age = age;
        this.contactPhoneNumber = contactPhoneNumber;
        this.contactName = contactName;
        this.description = description;
        this.reward = reward;
        this.rewardAmount = rewardAmount;
        this.country = country;
        this.state = state;
        this.city = city;
        this.urlImage = urlImage;
        this.lat = lat;
        this.lng = lng;
        this.lostAddress = lostAddress;
        this.found = found;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdCloud() {
        return idCloud;
    }

    public void setIdCloud(String idCloud) {
        this.idCloud = idCloud;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public String getRewardAmount() {
        return rewardAmount;
    }

    public void setRewardAmount(String rewardAmount) {
        this.rewardAmount = rewardAmount;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLostAddress() {
        return lostAddress;
    }

    public void setLostAddress(String lostAddress) {
        this.lostAddress = lostAddress;
    }

    public String getFound() {
        return found;
    }

    public void setFound(String found) {
        this.found = found;
    }

}
