package pe.com.patadeperro.domain.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "Usuario")
public class Usuario implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @SerializedName("id")
    private Integer id;
    @SerializedName("idCloud")
    private String idCloud;
    @SerializedName("uid")
    private String uid;
    @SerializedName("name")
    private String name;
    @SerializedName("phoneNumber")
    private String phoneNumber;
    @SerializedName("email")
    private String email;
    @SerializedName("lat")
    private Double lat;
    @SerializedName("lng")
    private Double lng;
    @SerializedName("logged")
    public boolean logged;
    @SerializedName("active")
    public boolean active;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("notifications")
    public boolean notifications;

    public int cloudIntCount;
    public int dbIntCount;

   public Usuario(
            Integer id,
            String idCloud,
            String uid,
            String name,
            String phoneNumber,
            String email,
            Double lat,
            Double lng,
            boolean logged,
            boolean active,
            String created_at,
            boolean notifications) {

        this.id = id;
        this.idCloud = idCloud;
        this.uid = uid;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.lat = lat;
        this.lng = lng;
        this.logged = logged;
        this.active = active;
        this.created_at = created_at;
        this.notifications = notifications;

        this.dbIntCount = 0;
        this.cloudIntCount = 0;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdCloud() {
        return idCloud;
    }

    public void setIdCloud(String idCloud) {
        this.idCloud = idCloud;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public boolean isNotifications() {
        return notifications;
    }

    public void setNotifications(boolean notifications) {
        this.notifications = notifications;
    }

    public int getCloudIntCount() {
        return cloudIntCount;
    }

    public void setCloudIntCount(int cloudIntCount) {
        this.cloudIntCount = cloudIntCount;
    }

    public int getDbIntCount() {
        return dbIntCount;
    }

    public void setDbIntCount(int dbIntCount) {
        this.dbIntCount = dbIntCount;
    }
}
