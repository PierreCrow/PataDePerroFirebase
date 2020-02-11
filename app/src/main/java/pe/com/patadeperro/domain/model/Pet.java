package pe.com.patadeperro.domain.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


@Entity(tableName = "Pet")
public class Pet implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @SerializedName("id")
    private Integer id;
    @SerializedName("idCloud")
    private String idCloud;
    @SerializedName("idUser")
    private String idUser;
    @SerializedName("name")
    private String name;
    @SerializedName("race")
    private String race;
    @SerializedName("gender")
    private String gender;
    @SerializedName("age")
    private String age;
    @SerializedName("color")
    private String color;
    @SerializedName("qrCode")
    private String qrCode;

    public int cloudIntCount;
    public int dbIntCount;

    public Pet(
            Integer id,
            String idCloud,
            String idUser,
            String name,
            String race,
            String gender,
            String age,
            String color,
            String qrCode) {

        this.id = id;
        this.idCloud = idCloud;
        this.idUser = idUser;
        this.name = name;
        this.race = race;
        this.gender = gender;
        this.age = age;
        this.color = color;
        this.qrCode = qrCode;

        this.dbIntCount = 0;
        this.cloudIntCount = 0;

//        this.id = 0;    // 2020-02-05 probemos

    }

    public Pet() {

        this.id = 0;
        this.idCloud = "";
        this.idUser = "";
        this.name = "";
        this.race = "";
        this.gender = "";
        this.age = "";
        this.color = "";
        this.qrCode = "";

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

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
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
