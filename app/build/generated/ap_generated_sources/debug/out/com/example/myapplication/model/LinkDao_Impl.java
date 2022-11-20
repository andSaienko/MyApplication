package com.example.myapplication.model;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class LinkDao_Impl implements LinkDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Link> __insertionAdapterOfLink;

  private final EntityDeletionOrUpdateAdapter<Link> __deletionAdapterOfLink;

  private final EntityDeletionOrUpdateAdapter<Link> __updateAdapterOfLink;

  public LinkDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfLink = new EntityInsertionAdapter<Link>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `links_table` (`id`,`link_name`) VALUES (nullif(?, 0),?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Link value) {
        stmt.bindLong(1, value.getId());
        if (value.getLink() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getLink());
        }
      }
    };
    this.__deletionAdapterOfLink = new EntityDeletionOrUpdateAdapter<Link>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `links_table` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Link value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfLink = new EntityDeletionOrUpdateAdapter<Link>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `links_table` SET `id` = ?,`link_name` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Link value) {
        stmt.bindLong(1, value.getId());
        if (value.getLink() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getLink());
        }
        stmt.bindLong(3, value.getId());
      }
    };
  }

  @Override
  public void insert(final Link... links) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfLink.insert(links);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Link link) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfLink.handle(link);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Link... links) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfLink.handleMultiple(links);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<Link>> getAllLinks() {
    final String _sql = "SELECT * FROM links_table";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"links_table"}, false, new Callable<List<Link>>() {
      @Override
      public List<Link> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfLink = CursorUtil.getColumnIndexOrThrow(_cursor, "link_name");
          final List<Link> _result = new ArrayList<Link>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Link _item;
            final String _tmpLink;
            if (_cursor.isNull(_cursorIndexOfLink)) {
              _tmpLink = null;
            } else {
              _tmpLink = _cursor.getString(_cursorIndexOfLink);
            }
            _item = new Link(_tmpLink);
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item.setId(_tmpId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
