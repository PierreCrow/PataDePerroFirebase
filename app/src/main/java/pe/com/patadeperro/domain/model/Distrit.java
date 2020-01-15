package pe.com.patadeperro.domain.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Distrit")
public class Distrit implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private String id;
    private String name;
    private String created_at;

    public Distrit(String name, String created_at) {
        // this.id = id;
        this.name = name;
        this.created_at = created_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

}
