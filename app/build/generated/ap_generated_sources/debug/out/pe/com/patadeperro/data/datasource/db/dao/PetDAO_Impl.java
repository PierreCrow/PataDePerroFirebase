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
import pe.com.patadeperro.data.datasource.db.model.DbPet;
import pe.com.patadeperro.domain.model.Pet;

@SuppressWarnings({"unchecked", "deprecation"})
public final class PetDAO_Impl implements PetDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<DbPet> __insertionAdapterOfDbPet;

  private final EntityInsertionAdapter<Pet> __insertionAdapterOfPet;

  private final EntityDeletionOrUpdateAdapter<DbPet> __deletionAdapterOfDbPet;

  private final EntityDeletionOrUpdateAdapter<DbPet> __updateAdapterOfDbPet;

  private final SharedSQLiteStatement __preparedStmtOfUpdateById;

  private final SharedSQLiteStatement __preparedStmtOfDeleteById;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public PetDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDbPet = new EntityInsertionAdapter<DbPet>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `DbPet` (`id`,`idCloud`,`idUser`,`name`,`race`,`gender`,`age`,`color`,`qrCode`) VALUES (?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DbPet value) {
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
        if (value.getIdUser() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getIdUser());
        }
        if (value.getName() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getName());
        }
        if (value.getRace() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getRace());
        }
        if (value.getGender() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getGender());
        }
        if (value.getAge() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getAge());
        }
        if (value.getColor() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getColor());
        }
        if (value.getQrCode() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getQrCode());
        }
      }
    };
    this.__insertionAdapterOfPet = new EntityInsertionAdapter<Pet>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Pet` (`id`,`idCloud`,`idUser`,`name`,`race`,`gender`,`age`,`color`,`qrCode`,`cloudIntCount`,`dbIntCount`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Pet value) {
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
        if (value.getIdUser() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getIdUser());
        }
        if (value.getName() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getName());
        }
        if (value.getRace() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getRace());
        }
        if (value.getGender() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getGender());
        }
        if (value.getAge() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getAge());
        }
        if (value.getColor() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getColor());
        }
        if (value.getQrCode() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getQrCode());
        }
        stmt.bindLong(10, value.cloudIntCount);
        stmt.bindLong(11, value.dbIntCount);
      }
    };
    this.__deletionAdapterOfDbPet = new EntityDeletionOrUpdateAdapter<DbPet>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `DbPet` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DbPet value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
      }
    };
    this.__updateAdapterOfDbPet = new EntityDeletionOrUpdateAdapter<DbPet>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `DbPet` SET `id` = ?,`idCloud` = ?,`idUser` = ?,`name` = ?,`race` = ?,`gender` = ?,`age` = ?,`color` = ?,`qrCode` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DbPet value) {
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
        if (value.getIdUser() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getIdUser());
        }
        if (value.getName() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getName());
        }
        if (value.getRace() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getRace());
        }
        if (value.getGender() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getGender());
        }
        if (value.getAge() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getAge());
        }
        if (value.getColor() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getColor());
        }
        if (value.getQrCode() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getQrCode());
        }
        if (value.getId() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindLong(10, value.getId());
        }
      }
    };
    this.__preparedStmtOfUpdateById = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE DbPet SET idCloud = ?, idUser = ?, name = ?, race = ?, gender = ?, age = ?, color = ?, qrCode = ? WHERE id = ? ";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE from DbPet WHERE idCloud = ? ";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE from DbPet";
        return _query;
      }
    };
  }

  @Override
  public Long InsertOnlyOne(final DbPet dbPet) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfDbPet.insertAndReturnId(dbPet);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void InsertMultiple(final List<DbPet> petList) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfDbPet.insert(petList);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void InsertMultiple(final ArrayList<Pet> pets) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfPet.insert(pets);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void Delete(final DbPet dbPet) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfDbPet.handle(dbPet);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void Update(final DbPet dbPet) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfDbPet.handle(dbPet);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateById(final String id, final String idCloud, final String idUser,
      final String name, final String race, final String gender, final String age,
      final String color, final String qrCode) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateById.acquire();
    int _argIndex = 1;
    if (idCloud == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, idCloud);
    }
    _argIndex = 2;
    if (idUser == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, idUser);
    }
    _argIndex = 3;
    if (name == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, name);
    }
    _argIndex = 4;
    if (race == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, race);
    }
    _argIndex = 5;
    if (gender == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, gender);
    }
    _argIndex = 6;
    if (age == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, age);
    }
    _argIndex = 7;
    if (color == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, color);
    }
    _argIndex = 8;
    if (qrCode == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, qrCode);
    }
    _argIndex = 9;
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
  public List<DbPet> listAllQ() {
    final String _sql = "SELECT * FROM DbPet";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfIdCloud = CursorUtil.getColumnIndexOrThrow(_cursor, "idCloud");
      final int _cursorIndexOfIdUser = CursorUtil.getColumnIndexOrThrow(_cursor, "idUser");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfRace = CursorUtil.getColumnIndexOrThrow(_cursor, "race");
      final int _cursorIndexOfGender = CursorUtil.getColumnIndexOrThrow(_cursor, "gender");
      final int _cursorIndexOfAge = CursorUtil.getColumnIndexOrThrow(_cursor, "age");
      final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "color");
      final int _cursorIndexOfQrCode = CursorUtil.getColumnIndexOrThrow(_cursor, "qrCode");
      final List<DbPet> _result = new ArrayList<DbPet>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final DbPet _item;
        final String _tmpIdCloud;
        _tmpIdCloud = _cursor.getString(_cursorIndexOfIdCloud);
        final String _tmpIdUser;
        _tmpIdUser = _cursor.getString(_cursorIndexOfIdUser);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        final String _tmpRace;
        _tmpRace = _cursor.getString(_cursorIndexOfRace);
        final String _tmpGender;
        _tmpGender = _cursor.getString(_cursorIndexOfGender);
        final String _tmpAge;
        _tmpAge = _cursor.getString(_cursorIndexOfAge);
        final String _tmpColor;
        _tmpColor = _cursor.getString(_cursorIndexOfColor);
        final String _tmpQrCode;
        _tmpQrCode = _cursor.getString(_cursorIndexOfQrCode);
        _item = new DbPet(_tmpIdCloud,_tmpIdUser,_tmpName,_tmpRace,_tmpGender,_tmpAge,_tmpColor,_tmpQrCode);
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
