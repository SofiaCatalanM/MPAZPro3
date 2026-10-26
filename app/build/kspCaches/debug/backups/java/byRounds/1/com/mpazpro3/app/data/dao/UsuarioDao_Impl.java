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
import com.mpazpro3.app.data.entity.Usuario;
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
public final class UsuarioDao_Impl implements UsuarioDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Usuario> __insertionAdapterOfUsuario;

  public UsuarioDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUsuario = new EntityInsertionAdapter<Usuario>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `usuarios` (`id`,`nombre`,`rol`,`curso`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Usuario entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getNombre());
        statement.bindString(3, entity.getRol());
        if (entity.getCurso() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getCurso());
        }
      }
    };
  }

  @Override
  public Object insertar(final Usuario usuario, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfUsuario.insert(usuario);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object buscarPorId(final int id, final Continuation<? super Usuario> $completion) {
    final String _sql = "SELECT * FROM usuarios WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Usuario>() {
      @Override
      @Nullable
      public Usuario call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNombre = CursorUtil.getColumnIndexOrThrow(_cursor, "nombre");
          final int _cursorIndexOfRol = CursorUtil.getColumnIndexOrThrow(_cursor, "rol");
          final int _cursorIndexOfCurso = CursorUtil.getColumnIndexOrThrow(_cursor, "curso");
          final Usuario _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpNombre;
            _tmpNombre = _cursor.getString(_cursorIndexOfNombre);
            final String _tmpRol;
            _tmpRol = _cursor.getString(_cursorIndexOfRol);
            final String _tmpCurso;
            if (_cursor.isNull(_cursorIndexOfCurso)) {
              _tmpCurso = null;
            } else {
              _tmpCurso = _cursor.getString(_cursorIndexOfCurso);
            }
            _result = new Usuario(_tmpId,_tmpNombre,_tmpRol,_tmpCurso);
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

  @Override
  public Object buscarPorRol(final String rol,
      final Continuation<? super List<Usuario>> $completion) {
    final String _sql = "SELECT * FROM usuarios WHERE rol = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, rol);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Usuario>>() {
      @Override
      @NonNull
      public List<Usuario> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNombre = CursorUtil.getColumnIndexOrThrow(_cursor, "nombre");
          final int _cursorIndexOfRol = CursorUtil.getColumnIndexOrThrow(_cursor, "rol");
          final int _cursorIndexOfCurso = CursorUtil.getColumnIndexOrThrow(_cursor, "curso");
          final List<Usuario> _result = new ArrayList<Usuario>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Usuario _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpNombre;
            _tmpNombre = _cursor.getString(_cursorIndexOfNombre);
            final String _tmpRol;
            _tmpRol = _cursor.getString(_cursorIndexOfRol);
            final String _tmpCurso;
            if (_cursor.isNull(_cursorIndexOfCurso)) {
              _tmpCurso = null;
            } else {
              _tmpCurso = _cursor.getString(_cursorIndexOfCurso);
            }
            _item = new Usuario(_tmpId,_tmpNombre,_tmpRol,_tmpCurso);
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
  public Object obtenerTodos(final Continuation<? super List<Usuario>> $completion) {
    final String _sql = "SELECT * FROM usuarios";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Usuario>>() {
      @Override
      @NonNull
      public List<Usuario> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNombre = CursorUtil.getColumnIndexOrThrow(_cursor, "nombre");
          final int _cursorIndexOfRol = CursorUtil.getColumnIndexOrThrow(_cursor, "rol");
          final int _cursorIndexOfCurso = CursorUtil.getColumnIndexOrThrow(_cursor, "curso");
          final List<Usuario> _result = new ArrayList<Usuario>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Usuario _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpNombre;
            _tmpNombre = _cursor.getString(_cursorIndexOfNombre);
            final String _tmpRol;
            _tmpRol = _cursor.getString(_cursorIndexOfRol);
            final String _tmpCurso;
            if (_cursor.isNull(_cursorIndexOfCurso)) {
              _tmpCurso = null;
            } else {
              _tmpCurso = _cursor.getString(_cursorIndexOfCurso);
            }
            _item = new Usuario(_tmpId,_tmpNombre,_tmpRol,_tmpCurso);
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
