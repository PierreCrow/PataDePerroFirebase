package pe.com.patadeperro.data.datasource.db.store;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import pe.com.patadeperro.data.datasource.datastore.LostDataStore;
import pe.com.patadeperro.data.datasource.db.PdpDb;
import pe.com.patadeperro.data.datasource.db.dao.LostDAO;
import pe.com.patadeperro.data.datasource.db.model.DbLost;
import pe.com.patadeperro.data.mapper.LostDataMapper;
import pe.com.patadeperro.domain.model.Lost;
import pe.com.patadeperro.domain.repository.RepositoryCallback;

public class DbLostDataStore implements LostDataStore {

    LostDAO lostDAO;

    public DbLostDataStore(Context context) {
        lostDAO= PdpDb.getDatabase(context).lostDAO();
    }

    @Override
    public void createLost(Lost lost, RepositoryCallback repositoryCallback) {

        LostDataMapper lostDataMapper= new LostDataMapper();
        DbLost dbLost=lostDataMapper.transformToDb(lost);

//        dbLost.setId( 0 ); // o null?

        try {
            lost.setId(
                    lostDAO.InsertOnlyOne(dbLost).intValue()     // tipos... ver domain/model/lost
            );
            lost.dbIntCount += 1;
            repositoryCallback.onSuccess(lost);
        } catch (Exception e) {
            e.printStackTrace();
            repositoryCallback.onError(e);
        }

    }

    @Override
    public void createLostList(
            List<Lost> lostList,
            RepositoryCallback repositoryCallback) {

        LostDataMapper lostDataMapper= new LostDataMapper();
//        DbLost dbLost=lostDataMapper.transformToDb(lost);

        List<DbLost> dbLostList = new ArrayList<>();
        for (int i=0; i<lostList.size(); i++) {
            Lost wrkLost = lostList.get(i);
            DbLost wrkDbLost = lostDataMapper.transformToDb(wrkLost);
            dbLostList.add( wrkDbLost );
        }

//        dbLost.setId( 0 ); // o null?

        try {
            lostDAO.InsertMultiple(dbLostList);     // tipos... ver domain/model/lost
            repositoryCallback.onSuccess(lostList);
        } catch (Exception e) {
            e.printStackTrace();
            repositoryCallback.onError(e);
        }

    }

    @Override
    public void updateLost(Lost lost, RepositoryCallback repositoryCallback) {

        LostDataMapper lostDataMapper= new LostDataMapper();
        DbLost dbLost=lostDataMapper.transformToDb(lost);

        dbLost.setId( lost.getId() );

        try {
            lostDAO.updateById(
                    dbLost.getId().toString(),
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
            lost.dbIntCount += 1;
            repositoryCallback.onSuccess(lost);
        } catch (Exception e) {
            e.printStackTrace();
            repositoryCallback.onError(e);
        }

    }

    @Override
    public void deleteLost(Lost lost, RepositoryCallback repositoryCallback) {

        LostDataMapper lostDataMapper= new LostDataMapper();
        DbLost dbLost = lostDataMapper.transformToDb(lost);

        try {
            if (dbLost.getIdCloud()=="*ALL") {       // 2020-02-10 ecv
                lostDAO.deleteAll();
            } else {
                lostDAO.deleteById(dbLost.getIdCloud());
            }
            lost.dbIntCount += 1;
            repositoryCallback.onSuccess(lost);
        } catch (Exception e) {
            e.printStackTrace();
            repositoryCallback.onError(e);
        }
    }

    @Override
    public void lostsList(RepositoryCallback repositoryCallback) {

        LostDataMapper lostDataMapper= new LostDataMapper();
//        DbLost dbLost = lostDataMapper.transformToDb(lost);

        List<Lost> losts = new ArrayList<>();
        Lost lost;
        DbLost dbLost;

        try {
            List<DbLost> dbLosts = lostDAO.listAllQ();

            for (int i = 0; i < dbLosts.size(); i++) {
//                System.out.println(crunchifyList.get(i));
                dbLost = dbLosts.get(i);
                lost = lostDataMapper.transformFromDb(dbLost);
                losts.add(lost);
            }
            repositoryCallback.onSuccess(losts);

        } catch (Exception e) {
            e.printStackTrace();
            repositoryCallback.onError(e);
        }
    }


}
