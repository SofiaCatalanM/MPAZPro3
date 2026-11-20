package com.mpazpro3.app.data.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.mpazpro3.app.data.entity.RespuestaEstudiante;
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
public final class RespuestaDao_Impl implements RespuestaDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<RespuestaEstudiante> __insertionAdapterOfRespuestaEstudiante;

  public RespuestaDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfRespuestaEstudiante = new EntityInsertionAdapter<RespuestaEstudiante>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `respuestas_estudiante` (`id`,`estudianteId`,`actividadId`,`respuestaSeleccionada`,`esCorrecta`,`fecha`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final RespuestaEstudiante entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getEstudianteId());
        statement.bindLong(3, entity.getActividadId());
        statement.bindString(4, entity.getRespuestaSeleccionada());
        final int _tmp = entity.getEsCorrecta() ? 1 : 0;
        statement.bindLong(5, _tmp);
        statement.bindLong(6, entity.getFecha());
      }
    };
  }

  @Override
  public Object insertar(final RespuestaEstudiante respuesta,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfRespuestaEstudiante.insert(respuesta);
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
      final Continuation<? super List<RespuestaEstudiante>> $completion) {
    final String _sql = "SELECT * FROM respuestas_estudiante WHERE estudianteId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, estudianteId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<RespuestaEstudiante>>() {
      @Override
      @NonNull
      public List<RespuestaEstudiante> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfEstudianteId = CursorUtil.getColumnIndexOrThrow(_cursor, "estudianteId");
          final int _cursorIndexOfActividadId = CursorUtil.getColumnIndexOrThrow(_cursor, "actividadId");
          final int _cursorIndexOfRespuestaSeleccionada = CursorUtil.getColumnIndexOrThrow(_cursor, "respuestaSeleccionada");
          final int _cursorIndexOfEsCorrecta = CursorUtil.getColumnIndexOrThrow(_cursor, "esCorrecta");
          final int _cursorIndexOfFecha = CursorUtil.getColumnIndexOrThrow(_cursor, "fecha");
          final List<RespuestaEstudiante> _result = new ArrayList<RespuestaEstudiante>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final RespuestaEstudiante _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpEstudianteId;
            _tmpEstudianteId = _cursor.getInt(_cursorIndexOfEstudianteId);
            final int _tmpActividadId;
            _tmpActividadId = _cursor.getInt(_cursorIndexOfActividadId);
            final String _tmpRespuestaSeleccionada;
            _tmpRespuestaSeleccionada = _cursor.getString(_cursorIndexOfRespuestaSeleccionada);
            final boolean _tmpEsCorrecta;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfEsCorrecta);
            _tmpEsCorrecta = _tmp != 0;
            final long _tmpFecha;
            _tmpFecha = _cursor.getLong(_cursorIndexOfFecha);
            _item = new RespuestaEstudiante(_tmpId,_tmpEstudianteId,_tmpActividadId,_tmpRespuestaSeleccionada,_tmpEsCorrecta,_tmpFecha);
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
  public Object contarCorrectas(final int estudianteId,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM respuestas_estudiante WHERE estudianteId = ? AND esCorrecta = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, estudianteId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
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
  public Object contarTotal(final int estudianteId,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM respuestas_estudiante WHERE estudianteId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, estudianteId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
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
