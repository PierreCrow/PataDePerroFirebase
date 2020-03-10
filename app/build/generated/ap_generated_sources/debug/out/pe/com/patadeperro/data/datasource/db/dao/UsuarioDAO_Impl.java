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
import java.lang.Double;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import pe.com.patadeperro.data.datasource.db.model.DbUsuario;
import pe.com.patadeperro.domain.model.Usuario;

@SuppressWarnings({"unchecked", "deprecation"})
public final class UsuarioDAO_Impl implements UsuarioDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<DbUsuario> __insertionAdapterOfDbUsuario;

  private final EntityInsertionAdapter<Usuario> __insertionAdapterOfUsuario;

  private final EntityDeletionOrUpdateAdapter<DbUsuario> __deletionAdapterOfDbUsuario;

  private final EntityDeletionOrUpdateAdapter<DbUsuario> __updateAdapterOfDbUsuario;

  private final SharedSQLiteStatement __preparedStmtOfUpdateById;

  private final SharedSQLiteStatement __preparedStmtOfDeleteById;

  public UsuarioDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDbUsuario = new EntityInsertionAdapter<DbUsuario>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `DbUsuario` (`id`,`idCloud`,`uid`,`name`,`phoneNumber`,`email`,`lat`,`lng`,`logged`,`active`,`created_at`,`notifications`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DbUsuario value) {
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
        if (value.getUid() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getUid());
        }
        if (value.getName() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getName());
        }
        if (value.getPhoneNumber() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getPhoneNumber());
        }
        if (value.getEmail() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getEmail());
        }
        if (value.getLat() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindDouble(7, value.getLat());
        }
        if (value.getLng() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindDouble(8, value.getLng());
        }
        final int _tmp;
        _tmp = value.logged ? 1 : 0;
        stmt.bindLong(9, _tmp);
        final int _tmp_1;
        _tmp_1 = value.active ? 1 : 0;
        stmt.bindLong(10, _tmp_1);
        if (value.getCreated_at() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getCreated_at());
        }
        final int _tmp_2;
        _tmp_2 = value.notifications ? 1 : 0;
        stmt.bindLong(12, _tmp_2);
      }
    };
    this.__insertionAdapterOfUsuario = new EntityInsertionAdapter<Usuario>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Usuario` (`id`,`idCloud`,`uid`,`name`,`phoneNumber`,`email`,`lat`,`lng`,`logged`,`active`,`created_at`,`notifications`,`cloudIntCount`,`dbIntCount`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Usuario value) {
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
        if (value.getUid() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getUid());
        }
        if (value.getName() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getName());
        }
        if (value.getPhoneNumber() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getPhoneNumber());
        }
        if (value.getEmail() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getEmail());
        }
        if (value.getLat() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindDouble(7, value.getLat());
        }
        if (value.getLng() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindDouble(8, value.getLng());
        }
        final int _tmp;
        _tmp = value.logged ? 1 : 0;
        stmt.bindLong(9, _tmp);
        final int _tmp_1;
        _tmp_1 = value.active ? 1 : 0;
        stmt.bindLong(10, _tmp_1);
        if (value.getCreated_at() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getCreated_at());
        }
        final int _tmp_2;
        _tmp_2 = value.notifications ? 1 : 0;
        stmt.bindLong(12, _tmp_2);
        stmt.bindLong(13, value.cloudIntCount);
        stmt.bindLong(14, value.dbIntCount);
      }
    };
    this.__deletionAdapterOfDbUsuario = new EntityDeletionOrUpdateAdapter<DbUsuario>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `DbUsuario` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DbUsuario value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
      }
    };
    this.__updateAdapterOfDbUsuario = new EntityDeletionOrUpdateAdapter<DbUsuario>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `DbUsuario` SET `id` = ?,`idCloud` = ?,`uid` = ?,`name` = ?,`phoneNumber` = ?,`email` = ?,`lat` = ?,`lng` = ?,`logged` = ?,`active` = ?,`created_at` = ?,`notifications` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DbUsuario value) {
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
        if (value.getUid() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getUid());
        }
        if (value.getName() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getName());
        }
        if (value.getPhoneNumber() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getPhoneNumber());
        }
        if (value.getEmail() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getEmail());
        }
        if (value.getLat() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindDouble(7, value.getLat());
        }
        if (value.getLng() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindDouble(8, value.getLng());
        }
        final int _tmp;
        _tmp = value.logged ? 1 : 0;
        stmt.bindLong(9, _tmp);
        final int _tmp_1;
        _tmp_1 = value.active ? 1 : 0;
        stmt.bindLong(10, _tmp_1);
        if (value.getCreated_at() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getCreated_at());
        }
        final int _tmp_2;
        _tmp_2 = value.notifications ? 1 : 0;
        stmt.bindLong(12, _tmp_2);
        if (value.getId() == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindLong(13, value.getId());
        }
      }
    };
    this.__preparedStmtOfUpdateById = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE DbUsuario SET name = ?, phoneNumber = ?, email = ?, lat = ?, lng = ?, logged = ?, active = ?, created_at = ?, notifications = ? WHERE idCloud = ? and id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE from DbUsuario WHERE idCloud = ? ";
        return _query;
      }
    };
  }

  @Override
  public long InsertOnlyOne(final DbUsuario usuario) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfDbUsuario.insertAndReturnId(usuario);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void InsertMultiple(final ArrayList<Usuario> usuarios) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfUsuario.insert(usuarios);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void Delete(final DbUsuario usuario) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfDbUsuario.handle(usuario);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void Update(final DbUsuario usuario) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfDbUsuario.handle(usuario);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateById(final String id, final String idCloud, final String name,
      final String phoneNumber, final String email, final String lat, final String lng,
      final String logged, final String active, final String created_at,
      final String notifications) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateById.acquire();
    int _argIndex = 1;
    if (name == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, name);
    }
    _argIndex = 2;
    if (phoneNumber == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, phoneNumber);
    }
    _argIndex = 3;
    if (email == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, email);
    }
    _argIndex = 4;
    if (lat == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, lat);
    }
    _argIndex = 5;
    if (lng == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, lng);
    }
    _argIndex = 6;
    if (logged == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, logged);
    }
    _argIndex = 7;
    if (active == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, active);
    }
    _argIndex = 8;
    if (created_at == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, created_at);
    }
    _argIndex = 9;
    if (notifications == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, notifications);
    }
    _argIndex = 10;
    if (idCloud == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, idCloud);
    }
    _argIndex = 11;
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
  public List<DbUsuario> listAllQ(final String sWhere) {
    final String _sql = "SELECT * FROM DbUsuario WHERE ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (sWhere == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, sWhere);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfIdCloud = CursorUtil.getColumnIndexOrThrow(_cursor, "idCloud");
      final int _cursorIndexOfUid = CursorUtil.getColumnIndexOrThrow(_cursor, "uid");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfPhoneNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "phoneNumber");
      final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
      final int _cursorIndexOfLat = CursorUtil.getColumnIndexOrThrow(_cursor, "lat");
      final int _cursorIndexOfLng = CursorUtil.getColumnIndexOrThrow(_cursor, "lng");
      final int _cursorIndexOfLogged = CursorUtil.getColumnIndexOrThrow(_cursor, "logged");
      final int _cursorIndexOfActive = CursorUtil.getColumnIndexOrThrow(_cursor, "active");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
      final int _cursorIndexOfNotifications = CursorUtil.getColumnIndexOrThrow(_cursor, "notifications");
      final List<DbUsuario> _result = new ArrayList<DbUsuario>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final DbUsuario _item;
        final String _tmpIdCloud;
        _tmpIdCloud = _cursor.getString(_cursorIndexOfIdCloud);
        final String _tmpUid;
        _tmpUid = _cursor.getString(_cursorIndexOfUid);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        final String _tmpPhoneNumber;
        _tmpPhoneNumber = _cursor.getString(_cursorIndexOfPhoneNumber);
        final String _tmpEmail;
        _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
        final Double _tmpLat;
        if (_cursor.isNull(_cursorIndexOfLat)) {
          _tmpLat = null;
        } else {
          _tmpLat = _cursor.getDouble(_cursorIndexOfLat);
        }
        final Double _tmpLng;
        if (_cursor.isNull(_cursorIndexOfLng)) {
          _tmpLng = null;
        } else {
          _tmpLng = _cursor.getDouble(_cursorIndexOfLng);
        }
        final boolean _tmpLogged;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfLogged);
        _tmpLogged = _tmp != 0;
        final boolean _tmpActive;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfActive);
        _tmpActive = _tmp_1 != 0;
        final String _tmpCreated_at;
        _tmpCreated_at = _cursor.getString(_cursorIndexOfCreatedAt);
        final boolean _tmpNotifications;
        final int _tmp_2;
        _tmp_2 = _cursor.getInt(_cursorIndexOfNotifications);
        _tmpNotifications = _tmp_2 != 0;
        _item = new DbUsuario(_tmpIdCloud,_tmpUid,_tmpName,_tmpPhoneNumber,_tmpEmail,_tmpLat,_tmpLng,_tmpLogged,_tmpActive,_tmpCreated_at,_tmpNotifications);
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
