package pe.com.patadeperro.data.datasource.db.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;


@Entity(tableName = "DbPet")
public class DbPet {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @SerializedName("id")
    private Integer id;
    private String idCloud;
    private String idUser;
    private String name;
    private String race;
    private String gender;
    private String age;
    private String color;
    private String qrCode;

    public DbPet(
           // String id,
            String idCloud,
            String idUser,
            String name,
            String race,
            String gender,
            String age,
            String color,
            String qrCode) {

    //    this.id = id;
        this.idCloud = idCloud;
        this.idUser = idUser;
        this.name = name;
        this.race = race;
        this.gender = gender;
        this.gender = age;
        this.color = color;
        this.qrCode = qrCode;

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

}
