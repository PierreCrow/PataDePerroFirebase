package pe.com.patadeperro.data.datasource.db.dao;

import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import pe.com.patadeperro.domain.model.Abuse;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AbuseDAO_Impl implements AbuseDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Abuse> __insertionAdapterOfAbuse;

  private final EntityDeletionOrUpdateAdapter<Abuse> __deletionAdapterOfAbuse;

  private final EntityDeletionOrUpdateAdapter<Abuse> __updateAdapterOfAbuse;

  public AbuseDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAbuse = new EntityInsertionAdapter<Abuse>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Abuse` (`id`,`idCloud`,`petName`,`race`,`gender`,`color`,`age`,`contactPhoneNumber`,`contactName`,`description`,`country`,`state`,`city`,`urlImage`,`lat`,`lng`,`abuseAddress`,`complaint`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Abuse value) {
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
        if (value.getCountry() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getCountry());
        }
        if (value.getState() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getState());
        }
        if (value.getCity() == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindString(13, value.getCity());
        }
        if (value.getUrlImage() == null) {
          stmt.bindNull(14);
        } else {
          stmt.bindString(14, value.getUrlImage());
        }
        if (value.getLat() == null) {
          stmt.bindNull(15);
        } else {
          stmt.bindString(15, value.getLat());
        }
        if (value.getLng() == null) {
          stmt.bindNull(16);
        } else {
          stmt.bindString(16, value.getLng());
        }
        if (value.getAbuseAddress() == null) {
          stmt.bindNull(17);
        } else {
          stmt.bindString(17, value.getAbuseAddress());
        }
        if (value.getComplaint() == null) {
          stmt.bindNull(18);
        } else {
          stmt.bindString(18, value.getComplaint());
        }
      }
    };
    this.__deletionAdapterOfAbuse = new EntityDeletionOrUpdateAdapter<Abuse>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Abuse` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Abuse value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
      }
    };
    this.__updateAdapterOfAbuse = new EntityDeletionOrUpdateAdapter<Abuse>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Abuse` SET `id` = ?,`idCloud` = ?,`petName` = ?,`race` = ?,`gender` = ?,`color` = ?,`age` = ?,`contactPhoneNumber` = ?,`contactName` = ?,`description` = ?,`country` = ?,`state` = ?,`city` = ?,`urlImage` = ?,`lat` = ?,`lng` = ?,`abuseAddress` = ?,`complaint` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Abuse value) {
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
        if (value.getCountry() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getCountry());
        }
        if (value.getState() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getState());
        }
        if (value.getCity() == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindString(13, value.getCity());
        }
        if (value.getUrlImage() == null) {
          stmt.bindNull(14);
        } else {
          stmt.bindString(14, value.getUrlImage());
        }
        if (value.getLat() == null) {
          stmt.bindNull(15);
        } else {
          stmt.bindString(15, value.getLat());
        }
        if (value.getLng() == null) {
          stmt.bindNull(16);
        } else {
          stmt.bindString(16, value.getLng());
        }
        if (value.getAbuseAddress() == null) {
          stmt.bindNull(17);
        } else {
          stmt.bindString(17, value.getAbuseAddress());
        }
        if (value.getComplaint() == null) {
          stmt.bindNull(18);
        } else {
          stmt.bindString(18, value.getComplaint());
        }
        if (value.getId() == null) {
          stmt.bindNull(19);
        } else {
          stmt.bindLong(19, value.getId());
        }
      }
    };
  }

  @Override
  public void InsertOnlyOne(final Abuse abuse) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfAbuse.insert(abuse);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void InsertMultiple(final ArrayList<Abuse> abuses) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfAbuse.insert(abuses);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void Delete(final Abuse abuse) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfAbuse.handle(abuse);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void Update(final Abuse abuse) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfAbuse.handle(abuse);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }
}
