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
import com.mpazpro3.app.data.entity.Evaluacion;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
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
public final class EvaluacionDao_Impl implements EvaluacionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Evaluacion> __insertionAdapterOfEvaluacion;

  public EvaluacionDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfEvaluacion = new EntityInsertionAdapter<Evaluacion>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `evaluaciones` (`id`,`estudianteId`,`objetivoCodigo`,`puntaje`,`total`,`fecha`,`intento`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Evaluacion entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getEstudianteId());
        statement.bindString(3, entity.getObjetivoCodigo());
        statement.bindLong(4, entity.getPuntaje());
        statement.bindLong(5, entity.getTotal());
        statement.bindLong(6, entity.getFecha());
        statement.bindLong(7, entity.getIntento());
      }
    };
  }

  @Override
  public Object insertar(final Evaluacion evaluacion,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfEvaluacion.insert(evaluacion);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object obtenerPorEstudiante(final int estudianteId,
      final Continuation<? super List<Evaluacion>> $completion) {
    final String _sql = "SELECT * FROM evaluaciones WHERE estudianteId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, estudianteId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Evaluacion>>() {
      @Override
      @NonNull
      public List<Evaluacion> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfEstudianteId = CursorUtil.getColumnIndexOrThrow(_cursor, "estudianteId");
          final int _cursorIndexOfObjetivoCodigo = CursorUtil.getColumnIndexOrThrow(_cursor, "objetivoCodigo");
          final int _cursorIndexOfPuntaje = CursorUtil.getColumnIndexOrThrow(_cursor, "puntaje");
          final int _cursorIndexOfTotal = CursorUtil.getColumnIndexOrThrow(_cursor, "total");
          final int _cursorIndexOfFecha = CursorUtil.getColumnIndexOrThrow(_cursor, "fecha");
          final int _cursorIndexOfIntento = CursorUtil.getColumnIndexOrThrow(_cursor, "intento");
          final List<Evaluacion> _result = new ArrayList<Evaluacion>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Evaluacion _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpEstudianteId;
            _tmpEstudianteId = _cursor.getInt(_cursorIndexOfEstudianteId);
            final String _tmpObjetivoCodigo;
            _tmpObjetivoCodigo = _cursor.getString(_cursorIndexOfObjetivoCodigo);
            final int _tmpPuntaje;
            _tmpPuntaje = _cursor.getInt(_cursorIndexOfPuntaje);
            final int _tmpTotal;
            _tmpTotal = _cursor.getInt(_cursorIndexOfTotal);
            final long _tmpFecha;
            _tmpFecha = _cursor.getLong(_cursorIndexOfFecha);
            final int _tmpIntento;
            _tmpIntento = _cursor.getInt(_cursorIndexOfIntento);
            _item = new Evaluacion(_tmpId,_tmpEstudianteId,_tmpObjetivoCodigo,_tmpPuntaje,_tmpTotal,_tmpFecha,_tmpIntento);
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
  public Object obtenerUltimoIntento(final int estudianteId, final String objetivo,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT MAX(intento) FROM evaluaciones WHERE estudianteId = ? AND objetivoCodigo = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, estudianteId);
    _argIndex = 2;
    _statement.bindString(_argIndex, objetivo);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @Nullable
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
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
