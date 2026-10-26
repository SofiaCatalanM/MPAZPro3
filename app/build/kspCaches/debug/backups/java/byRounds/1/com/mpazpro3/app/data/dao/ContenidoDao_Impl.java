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
import com.mpazpro3.app.data.entity.Contenido;
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
public final class ContenidoDao_Impl implements ContenidoDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Contenido> __insertionAdapterOfContenido;

  public ContenidoDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfContenido = new EntityInsertionAdapter<Contenido>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `contenidos` (`id`,`titulo`,`explicacion`,`ejemplo`,`objetivoCodigo`,`aprobadoUtp`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Contenido entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getTitulo());
        statement.bindString(3, entity.getExplicacion());
        statement.bindString(4, entity.getEjemplo());
        statement.bindString(5, entity.getObjetivoCodigo());
        final int _tmp = entity.getAprobadoUtp() ? 1 : 0;
        statement.bindLong(6, _tmp);
      }
    };
  }

  @Override
  public Object insertar(final Contenido contenido, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfContenido.insert(contenido);
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
      final Continuation<? super List<Contenido>> $completion) {
    final String _sql = "SELECT * FROM contenidos WHERE objetivoCodigo = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, objetivo);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Contenido>>() {
      @Override
      @NonNull
      public List<Contenido> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitulo = CursorUtil.getColumnIndexOrThrow(_cursor, "titulo");
          final int _cursorIndexOfExplicacion = CursorUtil.getColumnIndexOrThrow(_cursor, "explicacion");
          final int _cursorIndexOfEjemplo = CursorUtil.getColumnIndexOrThrow(_cursor, "ejemplo");
          final int _cursorIndexOfObjetivoCodigo = CursorUtil.getColumnIndexOrThrow(_cursor, "objetivoCodigo");
          final int _cursorIndexOfAprobadoUtp = CursorUtil.getColumnIndexOrThrow(_cursor, "aprobadoUtp");
          final List<Contenido> _result = new ArrayList<Contenido>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Contenido _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpTitulo;
            _tmpTitulo = _cursor.getString(_cursorIndexOfTitulo);
            final String _tmpExplicacion;
            _tmpExplicacion = _cursor.getString(_cursorIndexOfExplicacion);
            final String _tmpEjemplo;
            _tmpEjemplo = _cursor.getString(_cursorIndexOfEjemplo);
            final String _tmpObjetivoCodigo;
            _tmpObjetivoCodigo = _cursor.getString(_cursorIndexOfObjetivoCodigo);
            final boolean _tmpAprobadoUtp;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfAprobadoUtp);
            _tmpAprobadoUtp = _tmp != 0;
            _item = new Contenido(_tmpId,_tmpTitulo,_tmpExplicacion,_tmpEjemplo,_tmpObjetivoCodigo,_tmpAprobadoUtp);
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
  public Object obtenerAprobados(final Continuation<? super List<Contenido>> $completion) {
    final String _sql = "SELECT * FROM contenidos WHERE aprobadoUtp = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Contenido>>() {
      @Override
      @NonNull
      public List<Contenido> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitulo = CursorUtil.getColumnIndexOrThrow(_cursor, "titulo");
          final int _cursorIndexOfExplicacion = CursorUtil.getColumnIndexOrThrow(_cursor, "explicacion");
          final int _cursorIndexOfEjemplo = CursorUtil.getColumnIndexOrThrow(_cursor, "ejemplo");
          final int _cursorIndexOfObjetivoCodigo = CursorUtil.getColumnIndexOrThrow(_cursor, "objetivoCodigo");
          final int _cursorIndexOfAprobadoUtp = CursorUtil.getColumnIndexOrThrow(_cursor, "aprobadoUtp");
          final List<Contenido> _result = new ArrayList<Contenido>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Contenido _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpTitulo;
            _tmpTitulo = _cursor.getString(_cursorIndexOfTitulo);
            final String _tmpExplicacion;
            _tmpExplicacion = _cursor.getString(_cursorIndexOfExplicacion);
            final String _tmpEjemplo;
            _tmpEjemplo = _cursor.getString(_cursorIndexOfEjemplo);
            final String _tmpObjetivoCodigo;
            _tmpObjetivoCodigo = _cursor.getString(_cursorIndexOfObjetivoCodigo);
            final boolean _tmpAprobadoUtp;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfAprobadoUtp);
            _tmpAprobadoUtp = _tmp != 0;
            _item = new Contenido(_tmpId,_tmpTitulo,_tmpExplicacion,_tmpEjemplo,_tmpObjetivoCodigo,_tmpAprobadoUtp);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
