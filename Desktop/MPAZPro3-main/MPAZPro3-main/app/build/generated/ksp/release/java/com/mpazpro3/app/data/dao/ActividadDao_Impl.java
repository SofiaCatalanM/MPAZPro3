package com.mpazpro3.app.data.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.mpazpro3.app.data.entity.Actividad;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class ActividadDao_Impl implements ActividadDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Actividad> __insertionAdapterOfActividad;

  public ActividadDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfActividad = new EntityInsertionAdapter<Actividad>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `actividades` (`id`,`pregunta`,`alternativaA`,`alternativaB`,`alternativaC`,`alternativaD`,`respuestaCorrecta`,`objetivoCodigo`,`dificultad`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Actividad entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getPregunta());
        statement.bindString(3, entity.getAlternativaA());
        statement.bindString(4, entity.getAlternativaB());
        statement.bindString(5, entity.getAlternativaC());
        statement.bindString(6, entity.getAlternativaD());
        statement.bindString(7, entity.getRespuestaCorrecta());
        statement.bindString(8, entity.getObjetivoCodigo());
        statement.bindString(9, entity.getDificultad());
      }
    };
  }

  @Override
  public Object insertar(final Actividad actividad, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfActividad.insert(actividad);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object obtenerPorObjetivo(final String objetivo,
      final Continuation<? super List<Actividad>> $completion) {
    final String _sql = "SELECT * FROM actividades WHERE objetivoCodigo = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, objetivo);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Actividad>>() {
      @Override
      @NonNull
      public List<Actividad> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPregunta = CursorUtil.getColumnIndexOrThrow(_cursor, "pregunta");
          final int _cursorIndexOfAlternativaA = CursorUtil.getColumnIndexOrThrow(_cursor, "alternativaA");
          final int _cursorIndexOfAlternativaB = CursorUtil.getColumnIndexOrThrow(_cursor, "alternativaB");
          final int _cursorIndexOfAlternativaC = CursorUtil.getColumnIndexOrThrow(_cursor, "alternativaC");
          final int _cursorIndexOfAlternativaD = CursorUtil.getColumnIndexOrThrow(_cursor, "alternativaD");
          final int _cursorIndexOfRespuestaCorrecta = CursorUtil.getColumnIndexOrThrow(_cursor, "respuestaCorrecta");
          final int _cursorIndexOfObjetivoCodigo = CursorUtil.getColumnIndexOrThrow(_cursor, "objetivoCodigo");
          final int _cursorIndexOfDificultad = CursorUtil.getColumnIndexOrThrow(_cursor, "dificultad");
          final List<Actividad> _result = new ArrayList<Actividad>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Actividad _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpPregunta;
            _tmpPregunta = _cursor.getString(_cursorIndexOfPregunta);
            final String _tmpAlternativaA;
            _tmpAlternativaA = _cursor.getString(_cursorIndexOfAlternativaA);
            final String _tmpAlternativaB;
            _tmpAlternativaB = _cursor.getString(_cursorIndexOfAlternativaB);
            final String _tmpAlternativaC;
            _tmpAlternativaC = _cursor.getString(_cursorIndexOfAlternativaC);
            final String _tmpAlternativaD;
            _tmpAlternativaD = _cursor.getString(_cursorIndexOfAlternativaD);
            final String _tmpRespuestaCorrecta;
            _tmpRespuestaCorrecta = _cursor.getString(_cursorIndexOfRespuestaCorrecta);
            final String _tmpObjetivoCodigo;
            _tmpObjetivoCodigo = _cursor.getString(_cursorIndexOfObjetivoCodigo);
            final String _tmpDificultad;
            _tmpDificultad = _cursor.getString(_cursorIndexOfDificultad);
            _item = new Actividad(_tmpId,_tmpPregunta,_tmpAlternativaA,_tmpAlternativaB,_tmpAlternativaC,_tmpAlternativaD,_tmpRespuestaCorrecta,_tmpObjetivoCodigo,_tmpDificultad);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object buscarPorId(final int id, final Continuation<? super Actividad> $completion) {
    final String _sql = "SELECT * FROM actividades WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Actividad>() {
      @Override
      @Nullable
      public Actividad call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPregunta = CursorUtil.getColumnIndexOrThrow(_cursor, "pregunta");
          final int _cursorIndexOfAlternativaA = CursorUtil.getColumnIndexOrThrow(_cursor, "alternativaA");
          final int _cursorIndexOfAlternativaB = CursorUtil.getColumnIndexOrThrow(_cursor, "alternativaB");
          final int _cursorIndexOfAlternativaC = CursorUtil.getColumnIndexOrThrow(_cursor, "alternativaC");
          final int _cursorIndexOfAlternativaD = CursorUtil.getColumnIndexOrThrow(_cursor, "alternativaD");
          final int _cursorIndexOfRespuestaCorrecta = CursorUtil.getColumnIndexOrThrow(_cursor, "respuestaCorrecta");
          final int _cursorIndexOfObjetivoCodigo = CursorUtil.getColumnIndexOrThrow(_cursor, "objetivoCodigo");
          final int _cursorIndexOfDificultad = CursorUtil.getColumnIndexOrThrow(_cursor, "dificultad");
          final Actividad _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpPregunta;
            _tmpPregunta = _cursor.getString(_cursorIndexOfPregunta);
            final String _tmpAlternativaA;
            _tmpAlternativaA = _cursor.getString(_cursorIndexOfAlternativaA);
            final String _tmpAlternativaB;
            _tmpAlternativaB = _cursor.getString(_cursorIndexOfAlternativaB);
            final String _tmpAlternativaC;
            _tmpAlternativaC = _cursor.getString(_cursorIndexOfAlternativaC);
            final String _tmpAlternativaD;
            _tmpAlternativaD = _cursor.getString(_cursorIndexOfAlternativaD);
            final String _tmpRespuestaCorrecta;
            _tmpRespuestaCorrecta = _cursor.getString(_cursorIndexOfRespuestaCorrecta);
            final String _tmpObjetivoCodigo;
            _tmpObjetivoCodigo = _cursor.getString(_cursorIndexOfObjetivoCodigo);
            final String _tmpDificultad;
            _tmpDificultad = _cursor.getString(_cursorIndexOfDificultad);
            _result = new Actividad(_tmpId,_tmpPregunta,_tmpAlternativaA,_tmpAlternativaB,_tmpAlternativaC,_tmpAlternativaD,_tmpRespuestaCorrecta,_tmpObjetivoCodigo,_tmpDificultad);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
