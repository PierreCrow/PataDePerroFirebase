package pe.com.patadeperro.data.datasource.db.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import pe.com.patadeperro.data.datasource.db.model.DbLost;
import pe.com.patadeperro.domain.model.Lost;

@SuppressWarnings({"unchecked", "deprecation"})
public final class LostDAO_Impl implements LostDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<DbLost> __insertionAdapterOfDbLost;

  private final EntityInsertionAdapter<Lost> __insertionAdapterOfLost;

  private final EntityDeletionOrUpdateAdapter<DbLost> __deletionAdapterOfDbLost;

  private final EntityDeletionOrUpdateAdapter<DbLost> __updateAdapterOfDbLost;

  private final SharedSQLiteStatement __preparedStmtOfUpdateById;

  private final SharedSQLiteStatement __preparedStmtOfDeleteById;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public LostDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDbLost = new EntityInsertionAdapter<DbLost>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `DbLost` (`id`,`idCloud`,`petName`,`race`,`gender`,`color`,`age`,`contactPhoneNumber`,`contactName`,`description`,`reward`,`rewardAmount`,`country`,`state`,`city`,`urlImage`,`lat`,`lng`,`lostAddress`,`found`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DbLost value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
        if (value.getIdCloud() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getIdCloud());
        }
        if (value.getPetName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getPetName());
        }
        if (value.getRace() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getRace());
        }
        if (value.getGender() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getGender());
        }
        if (value.getColor() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getColor());
        }
        if (value.getAge() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getAge());
        }
        if (value.getContactPhoneNumber() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getContactPhoneNumber());
        }
        if (value.getContactName() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getContactName());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getDescription());
        }
        if (value.getReward() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getReward());
        }
        if (value.getRewardAmount() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getRewardAmount());
        }
        if (value.getCountry() == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindString(13, value.getCountry());
        }
        if (value.getState() == null) {
          stmt.bindNull(14);
        } else {
          stmt.bindString(14, value.getState());
        }
        if (value.getCity() == null) {
          stmt.bindNull(15);
        } else {
          stmt.bindString(15, value.getCity());
        }
        if (value.getUrlImage() == null) {
          stmt.bindNull(16);
        } else {
          stmt.bindString(16, value.getUrlImage());
        }
        if (value.getLat() == null) {
          stmt.bindNull(17);
        } else {
          stmt.bindString(17, value.getLat());
        }
        if (value.getLng() == null) {
          stmt.bindNull(18);
        } else {
          stmt.bindString(18, value.getLng());
        }
        if (value.getLostAddress() == null) {
          stmt.bindNull(19);
        } else {
          stmt.bindString(19, value.getLostAddress());
        }
        if (value.getFound() == null) {
          stmt.bindNull(20);
        } else {
          stmt.bindString(20, value.getFound());
        }
      }
    };
    this.__insertionAdapterOfLost = new EntityInsertionAdapter<Lost>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Lost` (`id`,`idCloud`,`petName`,`race`,`gender`,`color`,`age`,`contactPhoneNumber`,`contactName`,`description`,`reward`,`rewardAmount`,`country`,`state`,`city`,`urlImage`,`lat`,`lng`,`lostAddress`,`found`,`cloudIntCount`,`dbIntCount`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Lost value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
        if (value.getIdCloud() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getIdCloud());
        }
        if (value.getPetName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getPetName());
        }
        if (value.getRace() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getRace());
        }
        if (value.getGender() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getGender());
        }
        if (value.getColor() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getColor());
        }
        if (value.getAge() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getAge());
        }
        if (value.getContactPhoneNumber() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getContactPhoneNumber());
        }
        if (value.getContactName() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getContactName());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getDescription());
        }
        if (value.getReward() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getReward());
        }
        if (value.getRewardAmount() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getRewardAmount());
        }
        if (value.getCountry() == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindString(13, value.getCountry());
        }
        if (value.getState() == null) {
          stmt.bindNull(14);
        } else {
          stmt.bindString(14, value.getState());
        }
        if (value.getCity() == null) {
          stmt.bindNull(15);
        } else {
          stmt.bindString(15, value.getCity());
        }
        if (value.getUrlImage() == null) {
          stmt.bindNull(16);
        } else {
          stmt.bindString(16, value.getUrlImage());
        }
        if (value.getLat() == null) {
          stmt.bindNull(17);
        } else {
          stmt.bindString(17, value.getLat());
        }
        if (value.getLng() == null) {
          stmt.bindNull(18);
        } else {
          stmt.bindString(18, value.getLng());
        }
        if (value.getLostAddress() == null) {
          stmt.bindNull(19);
        } else {
          stmt.bindString(19, value.getLostAddress());
        }
        if (value.getFound() == null) {
          stmt.bindNull(20);
        } else {
          stmt.bindString(20, value.getFound());
        }
        stmt.bindLong(21, value.cloudIntCount);
        stmt.bindLong(22, value.dbIntCount);
      }
    };
    this.__deletionAdapterOfDbLost = new EntityDeletionOrUpdateAdapter<DbLost>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `DbLost` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DbLost value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
      }
    };
    this.__updateAdapterOfDbLost = new EntityDeletionOrUpdateAdapter<DbLost>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `DbLost` SET `id` = ?,`idCloud` = ?,`petName` = ?,`race` = ?,`gender` = ?,`color` = ?,`age` = ?,`contactPhoneNumber` = ?,`contactName` = ?,`description` = ?,`reward` = ?,`rewardAmount` = ?,`country` = ?,`state` = ?,`city` = ?,`urlImage` = ?,`lat` = ?,`lng` = ?,`lostAddress` = ?,`found` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DbLost value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
        if (value.getIdCloud() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getIdCloud());
        }
        if (value.getPetName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getPetName());
        }
        if (value.getRace() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getRace());
        }
        if (value.getGender() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getGender());
        }
        if (value.getColor() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getColor());
        }
        if (value.getAge() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getAge());
        }
        if (value.getContactPhoneNumber() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getContactPhoneNumber());
        }
        if (value.getContactName() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getContactName());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getDescription());
        }
        if (value.getReward() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getReward());
        }
        if (value.getRewardAmount() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getRewardAmount());
        }
        if (value.getCountry() == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindString(13, value.getCountry());
        }
        if (value.getState() == null) {
          stmt.bindNull(14);
        } else {
          stmt.bindString(14, value.getState());
        }
        if (value.getCity() == null) {
          stmt.bindNull(15);
        } else {
          stmt.bindString(15, value.getCity());
        }
        if (value.getUrlImage() == null) {
          stmt.bindNull(16);
        } else {
          stmt.bindString(16, value.getUrlImage());
        }
        if (value.getLat() == null) {
          stmt.bindNull(17);
        } else {
          stmt.bindString(17, value.getLat());
        }
        if (value.getLng() == null) {
          stmt.bindNull(18);
        } else {
          stmt.bindString(18, value.getLng());
        }
        if (value.getLostAddress() == null) {
          stmt.bindNull(19);
        } else {
          stmt.bindString(19, value.getLostAddress());
        }
        if (value.getFound() == null) {
          stmt.bindNull(20);
        } else {
          stmt.bindString(20, value.getFound());
        }
        if (value.getId() == null) {
          stmt.bindNull(21);
        } else {
          stmt.bindLong(21, value.getId());
        }
      }
    };
    this.__preparedStmtOfUpdateById = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE DbLost SET idCloud = ?, petName = ?, race = ?, gender = ?, color = ?, age = ?, contactPhoneNumber = ?, contactName = ?, description = ?, reward = ?, rewardAmount = ?, country = ?, state = ?, city = ?, urlImage = ?, lat = ?, lng = ?, lostAddress = ?, found = ? WHERE id = ? ";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE from DbLost WHERE idCloud = ? ";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE from DbLost";
        return _query;
      }
    };
  }

  @Override
  public Long InsertOnlyOne(final DbLost dbLost) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfDbLost.insertAndReturnId(dbLost);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void InsertMultiple(final List<DbLost> lostList) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfDbLost.insert(lostList);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void InsertMultiple(final ArrayList<Lost> losts) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfLost.insert(losts);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void Delete(final DbLost dbLost) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfDbLost.handle(dbLost);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void Update(final DbLost dbLost) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfDbLost.handle(dbLost);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateById(final String id, final String idCloud, final String petName,
      final String race, final String gender, final String color, final String age,
      final String contactPhoneNumber, final String contactName, final String description,
      final String reward, final String rewardAmount, final String country, final String state,
      final String city, final String urlImage, final String lat, final String lng,
      final String lostAddress, final String found) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateById.acquire();
    int _argIndex = 1;
    if (idCloud == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, idCloud);
    }
    _argIndex = 2;
    if (petName == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, petName);
    }
    _argIndex = 3;
    if (race == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, race);
    }
    _argIndex = 4;
    if (gender == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, gender);
    }
    _argIndex = 5;
    if (color == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, color);
    }
    _argIndex = 6;
    if (age == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, age);
    }
    _argIndex = 7;
    if (contactPhoneNumber == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, contactPhoneNumber);
    }
    _argIndex = 8;
    if (contactName == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, contactName);
    }
    _argIndex = 9;
    if (description == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, description);
    }
    _argIndex = 10;
    if (reward == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, reward);
    }
    _argIndex = 11;
    if (rewardAmount == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, rewardAmount);
    }
    _argIndex = 12;
    if (country == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, country);
    }
    _argIndex = 13;
    if (state == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, state);
    }
    _argIndex = 14;
    if (city == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, city);
    }
    _argIndex = 15;
    if (urlImage == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, urlImage);
    }
    _argIndex = 16;
    if (lat == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, lat);
    }
    _argIndex = 17;
    if (lng == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, lng);
    }
    _argIndex = 18;
    if (lostAddress == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, lostAddress);
    }
    _argIndex = 19;
    if (found == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, found);
    }
    _argIndex = 20;
    if (id == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, id);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateById.release(_stmt);
    }
  }

  @Override
  public void deleteById(final String idCloud) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteById.acquire();
    int _argIndex = 1;
    if (idCloud == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, idCloud);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteById.release(_stmt);
    }
  }

  @Override
  public void deleteAll() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public List<DbLost> listAllQ() {
    final String _sql = "SELECT * FROM DbLost";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfIdCloud = CursorUtil.getColumnIndexOrThrow(_cursor, "idCloud");
      final int _cursorIndexOfPetName = CursorUtil.getColumnIndexOrThrow(_cursor, "petName");
      final int _cursorIndexOfRace = CursorUtil.getColumnIndexOrThrow(_cursor, "race");
      final int _cursorIndexOfGender = CursorUtil.getColumnIndexOrThrow(_cursor, "gender");
      final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "color");
      final int _cursorIndexOfAge = CursorUtil.getColumnIndexOrThrow(_cursor, "age");
      final int _cursorIndexOfContactPhoneNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "contactPhoneNumber");
      final int _cursorIndexOfContactName = CursorUtil.getColumnIndexOrThrow(_cursor, "contactName");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final int _cursorIndexOfReward = CursorUtil.getColumnIndexOrThrow(_cursor, "reward");
      final int _cursorIndexOfRewardAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "rewardAmount");
      final int _cursorIndexOfCountry = CursorUtil.getColumnIndexOrThrow(_cursor, "country");
      final int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "state");
      final int _cursorIndexOfCity = CursorUtil.getColumnIndexOrThrow(_cursor, "city");
      final int _cursorIndexOfUrlImage = CursorUtil.getColumnIndexOrThrow(_cursor, "urlImage");
      final int _cursorIndexOfLat = CursorUtil.getColumnIndexOrThrow(_cursor, "lat");
      final int _cursorIndexOfLng = CursorUtil.getColumnIndexOrThrow(_cursor, "lng");
      final int _cursorIndexOfLostAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "lostAddress");
      final int _cursorIndexOfFound = CursorUtil.getColumnIndexOrThrow(_cursor, "found");
      final List<DbLost> _result = new ArrayList<DbLost>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final DbLost _item;
        final String _tmpIdCloud;
        _tmpIdCloud = _cursor.getString(_cursorIndexOfIdCloud);
        final String _tmpPetName;
        _tmpPetName = _cursor.getString(_cursorIndexOfPetName);
        final String _tmpRace;
        _tmpRace = _cursor.getString(_cursorIndexOfRace);
        final String _tmpGender;
        _tmpGender = _cursor.getString(_cursorIndexOfGender);
        final String _tmpColor;
        _tmpColor = _cursor.getString(_cursorIndexOfColor);
        final String _tmpAge;
        _tmpAge = _cursor.getString(_cursorIndexOfAge);
        final String _tmpContactPhoneNumber;
        _tmpContactPhoneNumber = _cursor.getString(_cursorIndexOfContactPhoneNumber);
        final String _tmpContactName;
        _tmpContactName = _cursor.getString(_cursorIndexOfContactName);
        final String _tmpDescription;
        _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        final String _tmpReward;
        _tmpReward = _cursor.getString(_cursorIndexOfReward);
        final String _tmpRewardAmount;
        _tmpRewardAmount = _cursor.getString(_cursorIndexOfRewardAmount);
        final String _tmpCountry;
        _tmpCountry = _cursor.getString(_cursorIndexOfCountry);
        final String _tmpState;
        _tmpState = _cursor.getString(_cursorIndexOfState);
        final String _tmpCity;
        _tmpCity = _cursor.getString(_cursorIndexOfCity);
        final String _tmpUrlImage;
        _tmpUrlImage = _cursor.getString(_cursorIndexOfUrlImage);
        final String _tmpLat;
        _tmpLat = _cursor.getString(_cursorIndexOfLat);
        final String _tmpLng;
        _tmpLng = _cursor.getString(_cursorIndexOfLng);
        final String _tmpLostAddress;
        _tmpLostAddress = _cursor.getString(_cursorIndexOfLostAddress);
        final String _tmpFound;
        _tmpFound = _cursor.getString(_cursorIndexOfFound);
        _item = new DbLost(_tmpIdCloud,_tmpPetName,_tmpRace,_tmpGender,_tmpColor,_tmpAge,_tmpContactPhoneNumber,_tmpContactName,_tmpDescription,_tmpReward,_tmpRewardAmount,_tmpCountry,_tmpState,_tmpCity,_tmpUrlImage,_tmpLat,_tmpLng,_tmpLostAddress,_tmpFound);
        final Integer _tmpId;
        if (_cursor.isNull(_cursorIndexOfId)) {
          _tmpId = null;
        } else {
          _tmpId = _cursor.getInt(_cursorIndexOfId);
        }
        _item.setId(_tmpId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
