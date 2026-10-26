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
import com.mpazpro3.app.data.entity.ObjetivoAprendizaje;
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
public final class ObjetivoDao_Impl implements ObjetivoDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ObjetivoAprendizaje> __insertionAdapterOfObjetivoAprendizaje;

  public ObjetivoDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfObjetivoAprendizaje = new EntityInsertionAdapter<ObjetivoAprendizaje>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `objetivos_aprendizaje` (`codigo`,`descripcion`,`asignaturaCodigo`,`unidad`,`ejeCurricular`) VALUES (?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ObjetivoAprendizaje entity) {
        statement.bindString(1, entity.getCodigo());
        statement.bindString(2, entity.getDescripcion());
        statement.bindString(3, entity.getAsignaturaCodigo());
        statement.bindLong(4, entity.getUnidad());
        statement.bindString(5, entity.getEjeCurricular());
      }
    };
  }

  @Override
  public Object insertar(final ObjetivoAprendizaje objetivo,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfObjetivoAprendizaje.insert(objetivo);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object obtenerPorAsignatura(final String asignatura,
      final Continuation<? super List<ObjetivoAprendizaje>> $completion) {
    final String _sql = "SELECT * FROM objetivos_aprendizaje WHERE asignaturaCodigo = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, asignatura);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<ObjetivoAprendizaje>>() {
      @Override
      @NonNull
      public List<ObjetivoAprendizaje> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfCodigo = CursorUtil.getColumnIndexOrThrow(_cursor, "codigo");
          final int _cursorIndexOfDescripcion = CursorUtil.getColumnIndexOrThrow(_cursor, "descripcion");
          final int _cursorIndexOfAsignaturaCodigo = CursorUtil.getColumnIndexOrThrow(_cursor, "asignaturaCodigo");
          final int _cursorIndexOfUnidad = CursorUtil.getColumnIndexOrThrow(_cursor, "unidad");
          final int _cursorIndexOfEjeCurricular = CursorUtil.getColumnIndexOrThrow(_cursor, "ejeCurricular");
          final List<ObjetivoAprendizaje> _result = new ArrayList<ObjetivoAprendizaje>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ObjetivoAprendizaje _item;
            final String _tmpCodigo;
            _tmpCodigo = _cursor.getString(_cursorIndexOfCodigo);
            final String _tmpDescripcion;
            _tmpDescripcion = _cursor.getString(_cursorIndexOfDescripcion);
            final String _tmpAsignaturaCodigo;
            _tmpAsignaturaCodigo = _cursor.getString(_cursorIndexOfAsignaturaCodigo);
            final int _tmpUnidad;
            _tmpUnidad = _cursor.getInt(_cursorIndexOfUnidad);
            final String _tmpEjeCurricular;
            _tmpEjeCurricular = _cursor.getString(_cursorIndexOfEjeCurricular);
            _item = new ObjetivoAprendizaje(_tmpCodigo,_tmpDescripcion,_tmpAsignaturaCodigo,_tmpUnidad,_tmpEjeCurricular);
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
  public Object buscarPorCodigo(final String codigo,
      final Continuation<? super ObjetivoAprendizaje> $completion) {
    final String _sql = "SELECT * FROM objetivos_aprendizaje WHERE codigo = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, codigo);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<ObjetivoAprendizaje>() {
      @Override
      @Nullable
      public ObjetivoAprendizaje call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfCodigo = CursorUtil.getColumnIndexOrThrow(_cursor, "codigo");
          final int _cursorIndexOfDescripcion = CursorUtil.getColumnIndexOrThrow(_cursor, "descripcion");
          final int _cursorIndexOfAsignaturaCodigo = CursorUtil.getColumnIndexOrThrow(_cursor, "asignaturaCodigo");
          final int _cursorIndexOfUnidad = CursorUtil.getColumnIndexOrThrow(_cursor, "unidad");
          final int _cursorIndexOfEjeCurricular = CursorUtil.getColumnIndexOrThrow(_cursor, "ejeCurricular");
          final ObjetivoAprendizaje _result;
          if (_cursor.moveToFirst()) {
            final String _tmpCodigo;
            _tmpCodigo = _cursor.getString(_cursorIndexOfCodigo);
            final String _tmpDescripcion;
            _tmpDescripcion = _cursor.getString(_cursorIndexOfDescripcion);
            final String _tmpAsignaturaCodigo;
            _tmpAsignaturaCodigo = _cursor.getString(_cursorIndexOfAsignaturaCodigo);
            final int _tmpUnidad;
            _tmpUnidad = _cursor.getInt(_cursorIndexOfUnidad);
            final String _tmpEjeCurricular;
            _tmpEjeCurricular = _cursor.getString(_cursorIndexOfEjeCurricular);
            _result = new ObjetivoAprendizaje(_tmpCodigo,_tmpDescripcion,_tmpAsignaturaCodigo,_tmpUnidad,_tmpEjeCurricular);
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
