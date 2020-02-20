package pe.com.patadeperro.data.mapper;

import pe.com.patadeperro.data.datasource.cloud.model.CloudLost;
import pe.com.patadeperro.data.datasource.db.model.DbLost;
import pe.com.patadeperro.domain.model.Lost;

public class LostDataMapper {

    public LostDataMapper(){
    }

    public CloudLost transformToCloud(Lost lost)
    {
        CloudLost cloudLost= new CloudLost(
                lost.getId().toString(),
                lost.getIdCloud(),
                lost.getPetName(),
                lost.getRace(),
                lost.getGender(),
                lost.getColor(),
                lost.getAge(),
                lost.getContactPhoneNumber(),
                lost.getContactName(),
                lost.getDescription(),
                lost.getReward(),
                lost.getRewardAmount(),
                lost.getCountry(),
                lost.getState(),
                lost.getCity(),
                lost.getUrlImage(),
                lost.getLat(),
                lost.getLng(),
                lost.getLostAddress(),
                lost.getFound()

        );
        return cloudLost;
    }

    public Lost transformFromCloud(CloudLost cloudLost)
    {
        String wkStringId = cloudLost.getId();
        if (wkStringId==null)   wkStringId="0";
        if (wkStringId=="")     wkStringId="0";
        ;

        Lost lost= new Lost(
                Integer.parseInt(wkStringId),
                cloudLost.getIdCloud(),
                cloudLost.getPetName(),
                cloudLost.getRace(),
                cloudLost.getGender(),
                cloudLost.getColor(),
                cloudLost.getAge(),
                cloudLost.getContactPhoneNumber(),
                cloudLost.getContactName(),
                cloudLost.getDescription(),
                cloudLost.getReward(),
                cloudLost.getRewardAmount(),
                cloudLost.getCountry(),
                cloudLost.getState(),
                cloudLost.getCity(),
                cloudLost.getUrlImage(),
                cloudLost.getLat(),
                cloudLost.getLng(),
                cloudLost.getLostAddress(),
                cloudLost.getFound()

                );
        return lost;
    }

    public DbLost transformToDb(Lost lost)
    {
        DbLost dbLost= new DbLost(
//                lost.getId(),
                lost.getIdCloud(),
                lost.getPetName(),
                lost.getRace(),
                lost.getGender(),
                lost.getColor(),
                lost.getAge(),
                lost.getContactPhoneNumber(),
                lost.getContactName(),
                lost.getDescription(),
                lost.getReward(),
                lost.getRewardAmount(),
                lost.getCountry(),
                lost.getState(),
                lost.getCity(),
                lost.getUrlImage(),
                lost.getLat(),
                lost.getLng(),
                lost.getLostAddress(),
                lost.getFound()

                );
        return dbLost;
    }

    public Lost transformFromDb(DbLost dbLost)
    {
        Lost lost= new Lost(
                dbLost.getId(),
                dbLost.getIdCloud(),
                dbLost.getPetName(),
                dbLost.getRace(),
                dbLost.getGender(),
                dbLost.getColor(),
                dbLost.getAge(),
                dbLost.getContactPhoneNumber(),
                dbLost.getContactName(),
                dbLost.getDescription(),
                dbLost.getReward(),
                dbLost.getRewardAmount(),
                dbLost.getCountry(),
                dbLost.getState(),
                dbLost.getCity(),
                dbLost.getUrlImage(),
                dbLost.getLat(),
                dbLost.getLng(),
                dbLost.getLostAddress(),
                dbLost.getFound()

                );
        return lost;
    }

}
