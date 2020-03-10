package pe.com.patadeperro.data.datasource.db;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import pe.com.patadeperro.data.datasource.db.dao.AbuseDAO;
import pe.com.patadeperro.data.datasource.db.dao.AbuseDAO_Impl;
import pe.com.patadeperro.data.datasource.db.dao.LostDAO;
import pe.com.patadeperro.data.datasource.db.dao.LostDAO_Impl;
import pe.com.patadeperro.data.datasource.db.dao.PetDAO;
import pe.com.patadeperro.data.datasource.db.dao.PetDAO_Impl;
import pe.com.patadeperro.data.datasource.db.dao.UsuarioDAO;
import pe.com.patadeperro.data.datasource.db.dao.UsuarioDAO_Impl;

@SuppressWarnings({"unchecked", "deprecation"})
public final class PdpDb_Impl extends PdpDb {
  private volatile LostDAO _lostDAO;

  private volatile AbuseDAO _abuseDAO;

  private volatile PetDAO _petDAO;

  private volatile UsuarioDAO _usuarioDAO;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(3) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `DbUsuario` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `idCloud` TEXT, `uid` TEXT, `name` TEXT, `phoneNumber` TEXT, `email` TEXT, `lat` REAL, `lng` REAL, `logged` INTEGER NOT NULL, `active` INTEGER NOT NULL, `created_at` TEXT, `notifications` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `DbPet` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `idCloud` TEXT, `idUser` TEXT, `name` TEXT, `race` TEXT, `gender` TEXT, `age` TEXT, `color` TEXT, `qrCode` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `DbLost` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `idCloud` TEXT, `petName` TEXT, `race` TEXT, `gender` TEXT, `color` TEXT, `age` TEXT, `contactPhoneNumber` TEXT, `contactName` TEXT, `description` TEXT, `reward` TEXT, `rewardAmount` TEXT, `country` TEXT, `state` TEXT, `city` TEXT, `urlImage` TEXT, `lat` TEXT, `lng` TEXT, `lostAddress` TEXT, `found` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Usuario` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `idCloud` TEXT, `uid` TEXT, `name` TEXT, `phoneNumber` TEXT, `email` TEXT, `lat` REAL, `lng` REAL, `logged` INTEGER NOT NULL, `active` INTEGER NOT NULL, `created_at` TEXT, `notifications` INTEGER NOT NULL, `cloudIntCount` INTEGER NOT NULL, `dbIntCount` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Pet` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `idCloud` TEXT, `idUser` TEXT, `name` TEXT, `race` TEXT, `gender` TEXT, `age` TEXT, `color` TEXT, `qrCode` TEXT, `cloudIntCount` INTEGER NOT NULL, `dbIntCount` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Lost` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `idCloud` TEXT, `petName` TEXT, `race` TEXT, `gender` TEXT, `color` TEXT, `age` TEXT, `contactPhoneNumber` TEXT, `contactName` TEXT, `description` TEXT, `reward` TEXT, `rewardAmount` TEXT, `country` TEXT, `state` TEXT, `city` TEXT, `urlImage` TEXT, `lat` TEXT, `lng` TEXT, `lostAddress` TEXT, `found` TEXT, `cloudIntCount` INTEGER NOT NULL, `dbIntCount` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Abuse` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `idCloud` TEXT, `petName` TEXT, `race` TEXT, `gender` TEXT, `color` TEXT, `age` TEXT, `contactPhoneNumber` TEXT, `contactName` TEXT, `description` TEXT, `country` TEXT, `state` TEXT, `city` TEXT, `urlImage` TEXT, `lat` TEXT, `lng` TEXT, `abuseAddress` TEXT, `complaint` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '8327c16ff9284843160784c48bbe459c')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `DbUsuario`");
        _db.execSQL("DROP TABLE IF EXISTS `DbPet`");
        _db.execSQL("DROP TABLE IF EXISTS `DbLost`");
        _db.execSQL("DROP TABLE IF EXISTS `Usuario`");
        _db.execSQL("DROP TABLE IF EXISTS `Pet`");
        _db.execSQL("DROP TABLE IF EXISTS `Lost`");
        _db.execSQL("DROP TABLE IF EXISTS `Abuse`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsDbUsuario = new HashMap<String, TableInfo.Column>(12);
        _columnsDbUsuario.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDbUsuario.put("idCloud", new TableInfo.Column("idCloud", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDbUsuario.put("uid", new TableInfo.Column("uid", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDbUsuario.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDbUsuario.put("phoneNumber", new TableInfo.Column("phoneNumber", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDbUsuario.put("email", new TableInfo.Column("email", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDbUsuario.put("lat", new TableInfo.Column("lat", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDbUsuario.put("lng", new TableInfo.Column("lng", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDbUsuario.put("logged", new TableInfo.Column("logged", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDbUsuario.put("active", new TableInfo.Column("active", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDbUsuario.put("created_at", new TableInfo.Column("created_at", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDbUsuario.put("notifications", new TableInfo.Column("notifications", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDbUsuario = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDbUsuario = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDbUsuario = new TableInfo("DbUsuario", _columnsDbUsuario, _foreignKeysDbUsuario, _indicesDbUsuario);
        final TableInfo _existingDbUsuario = TableInfo.read(_db, "DbUsuario");
        if (! _infoDbUsuario.equals(_existingDbUsuario)) {
          return new RoomOpenHelper.ValidationResult(false, "DbUsuario(pe.com.patadeperro.data.datasource.db.model.DbUsuario).\n"
                  + " Expected:\n" + _infoDbUsuario + "\n"
                  + " Found:\n" + _existingDbUsuario);
        }
        final HashMap<String, TableInfo.Column> _columnsDbPet = new HashMap<String, TableInfo.Column>(9);
        _columnsDbPet.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDbPet.put("idCloud", new TableInfo.Column("idCloud", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDbPet.put("idUser", new TableInfo.Column("idUser", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDbPet.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDbPet.put("race", new TableInfo.Column("race", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDbPet.put("gender", new TableInfo.Column("gender", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDbPet.put("age", new TableInfo.Column("age", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDbPet.put("color", new TableInfo.Column("color", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDbPet.put("qrCode", new TableInfo.Column("qrCode", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDbPet = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDbPet = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDbPet = new TableInfo("DbPet", _columnsDbPet, _foreignKeysDbPet, _indicesDbPet);
        final TableInfo _existingDbPet = TableInfo.read(_db, "DbPet");
        if (! _infoDbPet.equals(_existingDbPet)) {
          return new RoomOpenHelper.ValidationResult(false, "DbPet(pe.com.patadeperro.data.datasource.db.model.DbPet).\n"
                  + " Expected:\n" + _infoDbPet + "\n"
                  + " Found:\n" + _existingDbPet);
        }
        final HashMap<String, TableInfo.Column> _columnsDbLost = new HashMap<String, TableInfo.Column>(20);
        _columnsDbLost.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDbLost.put("idCloud", new TableInfo.Column("idCloud", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDbLost.put("petName", new TableInfo.Column("petName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDbLost.put("race", new TableInfo.Column("race", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDbLost.put("gender", new TableInfo.Column("gender", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDbLost.put("color", new TableInfo.Column("color", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDbLost.put("age", new TableInfo.Column("age", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDbLost.put("contactPhoneNumber", new TableInfo.Column("contactPhoneNumber", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDbLost.put("contactName", new TableInfo.Column("contactName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDbLost.put("description", new TableInfo.Column("description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDbLost.put("reward", new TableInfo.Column("reward", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDbLost.put("rewardAmount", new TableInfo.Column("rewardAmount", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDbLost.put("country", new TableInfo.Column("country", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDbLost.put("state", new TableInfo.Column("state", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDbLost.put("city", new TableInfo.Column("city", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDbLost.put("urlImage", new TableInfo.Column("urlImage", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDbLost.put("lat", new TableInfo.Column("lat", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDbLost.put("lng", new TableInfo.Column("lng", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDbLost.put("lostAddress", new TableInfo.Column("lostAddress", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDbLost.put("found", new TableInfo.Column("found", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDbLost = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDbLost = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDbLost = new TableInfo("DbLost", _columnsDbLost, _foreignKeysDbLost, _indicesDbLost);
        final TableInfo _existingDbLost = TableInfo.read(_db, "DbLost");
        if (! _infoDbLost.equals(_existingDbLost)) {
          return new RoomOpenHelper.ValidationResult(false, "DbLost(pe.com.patadeperro.data.datasource.db.model.DbLost).\n"
                  + " Expected:\n" + _infoDbLost + "\n"
                  + " Found:\n" + _existingDbLost);
        }
        final HashMap<String, TableInfo.Column> _columnsUsuario = new HashMap<String, TableInfo.Column>(14);
        _columnsUsuario.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsuario.put("idCloud", new TableInfo.Column("idCloud", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsuario.put("uid", new TableInfo.Column("uid", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsuario.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsuario.put("phoneNumber", new TableInfo.Column("phoneNumber", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsuario.put("email", new TableInfo.Column("email", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsuario.put("lat", new TableInfo.Column("lat", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsuario.put("lng", new TableInfo.Column("lng", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsuario.put("logged", new TableInfo.Column("logged", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsuario.put("active", new TableInfo.Column("active", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsuario.put("created_at", new TableInfo.Column("created_at", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsuario.put("notifications", new TableInfo.Column("notifications", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsuario.put("cloudIntCount", new TableInfo.Column("cloudIntCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsuario.put("dbIntCount", new TableInfo.Column("dbIntCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUsuario = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUsuario = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUsuario = new TableInfo("Usuario", _columnsUsuario, _foreignKeysUsuario, _indicesUsuario);
        final TableInfo _existingUsuario = TableInfo.read(_db, "Usuario");
        if (! _infoUsuario.equals(_existingUsuario)) {
          return new RoomOpenHelper.ValidationResult(false, "Usuario(pe.com.patadeperro.domain.model.Usuario).\n"
                  + " Expected:\n" + _infoUsuario + "\n"
                  + " Found:\n" + _existingUsuario);
        }
        final HashMap<String, TableInfo.Column> _columnsPet = new HashMap<String, TableInfo.Column>(11);
        _columnsPet.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPet.put("idCloud", new TableInfo.Column("idCloud", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPet.put("idUser", new TableInfo.Column("idUser", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPet.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPet.put("race", new TableInfo.Column("race", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPet.put("gender", new TableInfo.Column("gender", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPet.put("age", new TableInfo.Column("age", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPet.put("color", new TableInfo.Column("color", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPet.put("qrCode", new TableInfo.Column("qrCode", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPet.put("cloudIntCount", new TableInfo.Column("cloudIntCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPet.put("dbIntCount", new TableInfo.Column("dbIntCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPet = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPet = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPet = new TableInfo("Pet", _columnsPet, _foreignKeysPet, _indicesPet);
        final TableInfo _existingPet = TableInfo.read(_db, "Pet");
        if (! _infoPet.equals(_existingPet)) {
          return new RoomOpenHelper.ValidationResult(false, "Pet(pe.com.patadeperro.domain.model.Pet).\n"
                  + " Expected:\n" + _infoPet + "\n"
                  + " Found:\n" + _existingPet);
        }
        final HashMap<String, TableInfo.Column> _columnsLost = new HashMap<String, TableInfo.Column>(22);
        _columnsLost.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLost.put("idCloud", new TableInfo.Column("idCloud", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLost.put("petName", new TableInfo.Column("petName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLost.put("race", new TableInfo.Column("race", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLost.put("gender", new TableInfo.Column("gender", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLost.put("color", new TableInfo.Column("color", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLost.put("age", new TableInfo.Column("age", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLost.put("contactPhoneNumber", new TableInfo.Column("contactPhoneNumber", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLost.put("contactName", new TableInfo.Column("contactName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLost.put("description", new TableInfo.Column("description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLost.put("reward", new TableInfo.Column("reward", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLost.put("rewardAmount", new TableInfo.Column("rewardAmount", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLost.put("country", new TableInfo.Column("country", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLost.put("state", new TableInfo.Column("state", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLost.put("city", new TableInfo.Column("city", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLost.put("urlImage", new TableInfo.Column("urlImage", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLost.put("lat", new TableInfo.Column("lat", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLost.put("lng", new TableInfo.Column("lng", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLost.put("lostAddress", new TableInfo.Column("lostAddress", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLost.put("found", new TableInfo.Column("found", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLost.put("cloudIntCount", new TableInfo.Column("cloudIntCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLost.put("dbIntCount", new TableInfo.Column("dbIntCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysLost = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesLost = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoLost = new TableInfo("Lost", _columnsLost, _foreignKeysLost, _indicesLost);
        final TableInfo _existingLost = TableInfo.read(_db, "Lost");
        if (! _infoLost.equals(_existingLost)) {
          return new RoomOpenHelper.ValidationResult(false, "Lost(pe.com.patadeperro.domain.model.Lost).\n"
                  + " Expected:\n" + _infoLost + "\n"
                  + " Found:\n" + _existingLost);
        }
        final HashMap<String, TableInfo.Column> _columnsAbuse = new HashMap<String, TableInfo.Column>(18);
        _columnsAbuse.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAbuse.put("idCloud", new TableInfo.Column("idCloud", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAbuse.put("petName", new TableInfo.Column("petName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAbuse.put("race", new TableInfo.Column("race", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAbuse.put("gender", new TableInfo.Column("gender", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAbuse.put("color", new TableInfo.Column("color", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAbuse.put("age", new TableInfo.Column("age", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAbuse.put("contactPhoneNumber", new TableInfo.Column("contactPhoneNumber", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAbuse.put("contactName", new TableInfo.Column("contactName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAbuse.put("description", new TableInfo.Column("description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAbuse.put("country", new TableInfo.Column("country", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAbuse.put("state", new TableInfo.Column("state", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAbuse.put("city", new TableInfo.Column("city", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAbuse.put("urlImage", new TableInfo.Column("urlImage", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAbuse.put("lat", new TableInfo.Column("lat", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAbuse.put("lng", new TableInfo.Column("lng", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAbuse.put("abuseAddress", new TableInfo.Column("abuseAddress", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAbuse.put("complaint", new TableInfo.Column("complaint", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAbuse = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAbuse = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAbuse = new TableInfo("Abuse", _columnsAbuse, _foreignKeysAbuse, _indicesAbuse);
        final TableInfo _existingAbuse = TableInfo.read(_db, "Abuse");
        if (! _infoAbuse.equals(_existingAbuse)) {
          return new RoomOpenHelper.ValidationResult(false, "Abuse(pe.com.patadeperro.domain.model.Abuse).\n"
                  + " Expected:\n" + _infoAbuse + "\n"
                  + " Found:\n" + _existingAbuse);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "8327c16ff9284843160784c48bbe459c", "893efd41ea2cd39defe943c79db3d6c7");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "DbUsuario","DbPet","DbLost","Usuario","Pet","Lost","Abuse");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `DbUsuario`");
      _db.execSQL("DELETE FROM `DbPet`");
      _db.execSQL("DELETE FROM `DbLost`");
      _db.execSQL("DELETE FROM `Usuario`");
      _db.execSQL("DELETE FROM `Pet`");
      _db.execSQL("DELETE FROM `Lost`");
      _db.execSQL("DELETE FROM `Abuse`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public LostDAO lostDAO() {
    if (_lostDAO != null) {
      return _lostDAO;
    } else {
      synchronized(this) {
        if(_lostDAO == null) {
          _lostDAO = new LostDAO_Impl(this);
        }
        return _lostDAO;
      }
    }
  }

  @Override
  public AbuseDAO abuseDAO() {
    if (_abuseDAO != null) {
      return _abuseDAO;
    } else {
      synchronized(this) {
        if(_abuseDAO == null) {
          _abuseDAO = new AbuseDAO_Impl(this);
        }
        return _abuseDAO;
      }
    }
  }

  @Override
  public PetDAO petDAO() {
    if (_petDAO != null) {
      return _petDAO;
    } else {
      synchronized(this) {
        if(_petDAO == null) {
          _petDAO = new PetDAO_Impl(this);
        }
        return _petDAO;
      }
    }
  }

  @Override
  public UsuarioDAO usuarioDAO() {
    if (_usuarioDAO != null) {
      return _usuarioDAO;
    } else {
      synchronized(this) {
        if(_usuarioDAO == null) {
          _usuarioDAO = new UsuarioDAO_Impl(this);
        }
        return _usuarioDAO;
      }
    }
  }
}
