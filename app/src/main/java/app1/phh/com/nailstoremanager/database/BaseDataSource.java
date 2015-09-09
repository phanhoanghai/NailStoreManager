package app1.phh.com.nailstoremanager.database;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.math.BigDecimal;

import app1.phh.com.nailstoremanager.Common.Constants;

/**
 * Created by HaiPhan on 9/7/2015.
 */
public class BaseDataSource {
    protected SQLiteDatabase database;
    protected SqlHelper dbHelper;

    boolean isOpenInternal = false;

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public boolean isOpen() {
        return (database != null);
    }

    public void close() {
        if (database != null && database.inTransaction()) {
            database.setTransactionSuccessful();
            database.endTransaction();
        }
        dbHelper.close();
        isOpenInternal = false;
    }

    protected void closeInternal() {
        if (isOpenInternal) {
            dbHelper.close();
            isOpenInternal = false;
        }
    }

    protected String getString(Object o) {
        if (o == null) {
            return Constants.BLANK;
        }
        if (o.getClass().equals(BigDecimal.class)) {
            return ((BigDecimal) o).toPlainString();
        } else {
            return String.valueOf(o);
        }
    }

    protected Boolean getBoolean(Cursor c, int index) {
        String str = c.getString(index);
        if (str != null && !str.equals(Constants.BLANK)
                && !str.equals(Constants.NULL_DATA_BASE)) {
            return Boolean.valueOf(str);
        } else {
            return null;
        }
    }

    protected void openInternal() {
        if (database == null) {
            database = dbHelper.getWritableDatabase();
            isOpenInternal = true;
        }
    }
}
