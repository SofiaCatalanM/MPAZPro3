package com.mpazpro3.app.data.database;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.mpazpro3.app.data.dao.ActividadDao;
import com.mpazpro3.app.data.dao.ActividadDao_Impl;
import com.mpazpro3.app.data.dao.AsignaturaDao;
import com.mpazpro3.app.data.dao.AsignaturaDao_Impl;
import com.mpazpro3.app.data.dao.ContenidoDao;
import com.mpazpro3.app.data.dao.ContenidoDao_Impl;
import com.mpazpro3.app.data.dao.EvaluacionDao;
import com.mpazpro3.app.data.dao.EvaluacionDao_Impl;
import com.mpazpro3.app.data.dao.ObjetivoDao;
import com.mpazpro3.app.data.dao.ObjetivoDao_Impl;
import com.mpazpro3.app.data.dao.RespuestaDao;
import com.mpazpro3.app.data.dao.RespuestaDao_Impl;
import com.mpazpro3.app.data.dao.UsuarioDao;
import com.mpazpro3.app.data.dao.UsuarioDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile UsuarioDao _usuarioDao;

  private volatile AsignaturaDao _asignaturaDao;

  private volatile ObjetivoDao _objetivoDao;

  private volatile ContenidoDao _contenidoDao;

  private volatile ActividadDao _actividadDao;

  private volatile RespuestaDao _respuestaDao;

  private volatile EvaluacionDao _evaluacionDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `usuarios` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `nombre` TEXT NOT NULL, `rol` TEXT NOT NULL, `curso` TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `asignaturas` (`codigo` TEXT NOT NULL, `nombre` TEXT NOT NULL, PRIMARY KEY(`codigo`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `objetivos_aprendizaje` (`codigo` TEXT NOT NULL, `descripcion` TEXT NOT NULL, `asignaturaCodigo` TEXT NOT NULL, `unidad` INTEGER NOT NULL, `ejeCurricular` TEXT NOT NULL, PRIMARY KEY(`codigo`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `contenidos` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `titulo` TEXT NOT NULL, `explicacion` TEXT NOT NULL, `ejemplo` TEXT NOT NULL, `objetivoCodigo` TEXT NOT NULL, `aprobadoUtp` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `actividades` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `pregunta` TEXT NOT NULL, `alternativaA` TEXT NOT NULL, `alternativaB` TEXT NOT NULL, `alternativaC` TEXT NOT NULL, `alternativaD` TEXT NOT NULL, `respuestaCorrecta` TEXT NOT NULL, `objetivoCodigo` TEXT NOT NULL, `dificultad` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `respuestas_estudiante` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `estudianteId` INTEGER NOT NULL, `actividadId` INTEGER NOT NULL, `respuestaSeleccionada` TEXT NOT NULL, `esCorrecta` INTEGER NOT NULL, `fecha` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `evaluaciones` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `estudianteId` INTEGER NOT NULL, `objetivoCodigo` TEXT NOT NULL, `puntaje` INTEGER NOT NULL, `total` INTEGER NOT NULL, `fecha` INTEGER NOT NULL, `intento` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '3cd5da4d89d94da4448bbf0004342993')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `usuarios`");
        db.execSQL("DROP TABLE IF EXISTS `asignaturas`");
        db.execSQL("DROP TABLE IF EXISTS `objetivos_aprendizaje`");
        db.execSQL("DROP TABLE IF EXISTS `contenidos`");
        db.execSQL("DROP TABLE IF EXISTS `actividades`");
        db.execSQL("DROP TABLE IF EXISTS `respuestas_estudiante`");
        db.execSQL("DROP TABLE IF EXISTS `evaluaciones`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsUsuarios = new HashMap<String, TableInfo.Column>(4);
        _columnsUsuarios.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsuarios.put("nombre", new TableInfo.Column("nombre", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsuarios.put("rol", new TableInfo.Column("rol", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsuarios.put("curso", new TableInfo.Column("curso", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUsuarios = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUsuarios = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUsuarios = new TableInfo("usuarios", _columnsUsuarios, _foreignKeysUsuarios, _indicesUsuarios);
        final TableInfo _existingUsuarios = TableInfo.read(db, "usuarios");
        if (!_infoUsuarios.equals(_existingUsuarios)) {
          return new RoomOpenHelper.ValidationResult(false, "usuarios(com.mpazpro3.app.data.entity.Usuario).\n"
                  + " Expected:\n" + _infoUsuarios + "\n"
                  + " Found:\n" + _existingUsuarios);
        }
        final HashMap<String, TableInfo.Column> _columnsAsignaturas = new HashMap<String, TableInfo.Column>(2);
        _columnsAsignaturas.put("codigo", new TableInfo.Column("codigo", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAsignaturas.put("nombre", new TableInfo.Column("nombre", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAsignaturas = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAsignaturas = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAsignaturas = new TableInfo("asignaturas", _columnsAsignaturas, _foreignKeysAsignaturas, _indicesAsignaturas);
        final TableInfo _existingAsignaturas = TableInfo.read(db, "asignaturas");
        if (!_infoAsignaturas.equals(_existingAsignaturas)) {
          return new RoomOpenHelper.ValidationResult(false, "asignaturas(com.mpazpro3.app.data.entity.Asignatura).\n"
                  + " Expected:\n" + _infoAsignaturas + "\n"
                  + " Found:\n" + _existingAsignaturas);
        }
        final HashMap<String, TableInfo.Column> _columnsObjetivosAprendizaje = new HashMap<String, TableInfo.Column>(5);
        _columnsObjetivosAprendizaje.put("codigo", new TableInfo.Column("codigo", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsObjetivosAprendizaje.put("descripcion", new TableInfo.Column("descripcion", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsObjetivosAprendizaje.put("asignaturaCodigo", new TableInfo.Column("asignaturaCodigo", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsObjetivosAprendizaje.put("unidad", new TableInfo.Column("unidad", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsObjetivosAprendizaje.put("ejeCurricular", new TableInfo.Column("ejeCurricular", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysObjetivosAprendizaje = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesObjetivosAprendizaje = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoObjetivosAprendizaje = new TableInfo("objetivos_aprendizaje", _columnsObjetivosAprendizaje, _foreignKeysObjetivosAprendizaje, _indicesObjetivosAprendizaje);
        final TableInfo _existingObjetivosAprendizaje = TableInfo.read(db, "objetivos_aprendizaje");
        if (!_infoObjetivosAprendizaje.equals(_existingObjetivosAprendizaje)) {
          return new RoomOpenHelper.ValidationResult(false, "objetivos_aprendizaje(com.mpazpro3.app.data.entity.ObjetivoAprendizaje).\n"
                  + " Expected:\n" + _infoObjetivosAprendizaje + "\n"
                  + " Found:\n" + _existingObjetivosAprendizaje);
        }
        final HashMap<String, TableInfo.Column> _columnsContenidos = new HashMap<String, TableInfo.Column>(6);
        _columnsContenidos.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContenidos.put("titulo", new TableInfo.Column("titulo", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContenidos.put("explicacion", new TableInfo.Column("explicacion", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContenidos.put("ejemplo", new TableInfo.Column("ejemplo", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContenidos.put("objetivoCodigo", new TableInfo.Column("objetivoCodigo", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContenidos.put("aprobadoUtp", new TableInfo.Column("aprobadoUtp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysContenidos = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesContenidos = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoContenidos = new TableInfo("contenidos", _columnsContenidos, _foreignKeysContenidos, _indicesContenidos);
        final TableInfo _existingContenidos = TableInfo.read(db, "contenidos");
        if (!_infoContenidos.equals(_existingContenidos)) {
          return new RoomOpenHelper.ValidationResult(false, "contenidos(com.mpazpro3.app.data.entity.Contenido).\n"
                  + " Expected:\n" + _infoContenidos + "\n"
                  + " Found:\n" + _existingContenidos);
        }
        final HashMap<String, TableInfo.Column> _columnsActividades = new HashMap<String, TableInfo.Column>(9);
        _columnsActividades.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsActividades.put("pregunta", new TableInfo.Column("pregunta", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsActividades.put("alternativaA", new TableInfo.Column("alternativaA", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsActividades.put("alternativaB", new TableInfo.Column("alternativaB", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsActividades.put("alternativaC", new TableInfo.Column("alternativaC", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsActividades.put("alternativaD", new TableInfo.Column("alternativaD", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsActividades.put("respuestaCorrecta", new TableInfo.Column("respuestaCorrecta", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsActividades.put("objetivoCodigo", new TableInfo.Column("objetivoCodigo", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsActividades.put("dificultad", new TableInfo.Column("dificultad", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysActividades = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesActividades = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoActividades = new TableInfo("actividades", _columnsActividades, _foreignKeysActividades, _indicesActividades);
        final TableInfo _existingActividades = TableInfo.read(db, "actividades");
        if (!_infoActividades.equals(_existingActividades)) {
          return new RoomOpenHelper.ValidationResult(false, "actividades(com.mpazpro3.app.data.entity.Actividad).\n"
                  + " Expected:\n" + _infoActividades + "\n"
                  + " Found:\n" + _existingActividades);
        }
        final HashMap<String, TableInfo.Column> _columnsRespuestasEstudiante = new HashMap<String, TableInfo.Column>(6);
        _columnsRespuestasEstudiante.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRespuestasEstudiante.put("estudianteId", new TableInfo.Column("estudianteId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRespuestasEstudiante.put("actividadId", new TableInfo.Column("actividadId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRespuestasEstudiante.put("respuestaSeleccionada", new TableInfo.Column("respuestaSeleccionada", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRespuestasEstudiante.put("esCorrecta", new TableInfo.Column("esCorrecta", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRespuestasEstudiante.put("fecha", new TableInfo.Column("fecha", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysRespuestasEstudiante = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesRespuestasEstudiante = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoRespuestasEstudiante = new TableInfo("respuestas_estudiante", _columnsRespuestasEstudiante, _foreignKeysRespuestasEstudiante, _indicesRespuestasEstudiante);
        final TableInfo _existingRespuestasEstudiante = TableInfo.read(db, "respuestas_estudiante");
        if (!_infoRespuestasEstudiante.equals(_existingRespuestasEstudiante)) {
          return new RoomOpenHelper.ValidationResult(false, "respuestas_estudiante(com.mpazpro3.app.data.entity.RespuestaEstudiante).\n"
                  + " Expected:\n" + _infoRespuestasEstudiante + "\n"
                  + " Found:\n" + _existingRespuestasEstudiante);
        }
        final HashMap<String, TableInfo.Column> _columnsEvaluaciones = new HashMap<String, TableInfo.Column>(7);
        _columnsEvaluaciones.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvaluaciones.put("estudianteId", new TableInfo.Column("estudianteId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvaluaciones.put("objetivoCodigo", new TableInfo.Column("objetivoCodigo", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvaluaciones.put("puntaje", new TableInfo.Column("puntaje", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvaluaciones.put("total", new TableInfo.Column("total", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvaluaciones.put("fecha", new TableInfo.Column("fecha", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvaluaciones.put("intento", new TableInfo.Column("intento", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysEvaluaciones = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesEvaluaciones = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoEvaluaciones = new TableInfo("evaluaciones", _columnsEvaluaciones, _foreignKeysEvaluaciones, _indicesEvaluaciones);
        final TableInfo _existingEvaluaciones = TableInfo.read(db, "evaluaciones");
        if (!_infoEvaluaciones.equals(_existingEvaluaciones)) {
          return new RoomOpenHelper.ValidationResult(false, "evaluaciones(com.mpazpro3.app.data.entity.Evaluacion).\n"
                  + " Expected:\n" + _infoEvaluaciones + "\n"
                  + " Found:\n" + _existingEvaluaciones);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "3cd5da4d89d94da4448bbf0004342993", "18bf091843f12f8ac93985f61df223f7");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "usuarios","asignaturas","objetivos_aprendizaje","contenidos","actividades","respuestas_estudiante","evaluaciones");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `usuarios`");
      _db.execSQL("DELETE FROM `asignaturas`");
      _db.execSQL("DELETE FROM `objetivos_aprendizaje`");
      _db.execSQL("DELETE FROM `contenidos`");
      _db.execSQL("DELETE FROM `actividades`");
      _db.execSQL("DELETE FROM `respuestas_estudiante`");
      _db.execSQL("DELETE FROM `evaluaciones`");
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
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(UsuarioDao.class, UsuarioDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(AsignaturaDao.class, AsignaturaDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ObjetivoDao.class, ObjetivoDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ContenidoDao.class, ContenidoDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ActividadDao.class, ActividadDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(RespuestaDao.class, RespuestaDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(EvaluacionDao.class, EvaluacionDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public UsuarioDao usuarioDao() {
    if (_usuarioDao != null) {
      return _usuarioDao;
    } else {
      synchronized(this) {
        if(_usuarioDao == null) {
          _usuarioDao = new UsuarioDao_Impl(this);
        }
        return _usuarioDao;
      }
    }
  }

  @Override
  public AsignaturaDao asignaturaDao() {
    if (_asignaturaDao != null) {
      return _asignaturaDao;
    } else {
      synchronized(this) {
        if(_asignaturaDao == null) {
          _asignaturaDao = new AsignaturaDao_Impl(this);
        }
        return _asignaturaDao;
      }
    }
  }

  @Override
  public ObjetivoDao objetivoDao() {
    if (_objetivoDao != null) {
      return _objetivoDao;
    } else {
      synchronized(this) {
        if(_objetivoDao == null) {
          _objetivoDao = new ObjetivoDao_Impl(this);
        }
        return _objetivoDao;
      }
    }
  }

  @Override
  public ContenidoDao contenidoDao() {
    if (_contenidoDao != null) {
      return _contenidoDao;
    } else {
      synchronized(this) {
        if(_contenidoDao == null) {
          _contenidoDao = new ContenidoDao_Impl(this);
        }
        return _contenidoDao;
      }
    }
  }

  @Override
  public ActividadDao actividadDao() {
    if (_actividadDao != null) {
      return _actividadDao;
    } else {
      synchronized(this) {
        if(_actividadDao == null) {
          _actividadDao = new ActividadDao_Impl(this);
        }
        return _actividadDao;
      }
    }
  }

  @Override
  public RespuestaDao respuestaDao() {
    if (_respuestaDao != null) {
      return _respuestaDao;
    } else {
      synchronized(this) {
        if(_respuestaDao == null) {
          _respuestaDao = new RespuestaDao_Impl(this);
        }
        return _respuestaDao;
      }
    }
  }

  @Override
  public EvaluacionDao evaluacionDao() {
    if (_evaluacionDao != null) {
      return _evaluacionDao;
    } else {
      synchronized(this) {
        if(_evaluacionDao == null) {
          _evaluacionDao = new EvaluacionDao_Impl(this);
        }
        return _evaluacionDao;
      }
    }
  }
}
