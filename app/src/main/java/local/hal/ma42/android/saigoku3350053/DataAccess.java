package local.hal.ma42.android.saigoku3350053;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

/**
 * MA42 Androidデータベース接続
 *
 * データベース上のデータを取得するクラス。
 *
 */
public class DataAccess {
    /**
     * 主キーによる内容検索メソッド。
     * @param db SQLiteDatabaseオブジェクト。
     * @param id 主キー値。
     * @return 主キーに対応するhonzonカラムの値。
     */
    public static String findhonzonByPK(SQLiteDatabase db, int id) {
        String sql = "SELECT honzon FROM temples WHERE _id = " + id;
        Cursor cursor = db.rawQuery(sql, null);
        String result = "";
        while (cursor.moveToNext()) {
            int idxContent = cursor.getColumnIndex("honzon");
            result = cursor.getString(idxContent);
        }
        return result ;
    }
    /**
     * 主キーによる内容検索メソッド。
     * @param db SQLiteDatabaseオブジェクト。
     * @param id 主キー値。
     * @return 主キーに対応するshushiカラムの値。
     */
    public static String findshushiByPK(SQLiteDatabase db, int id) {
        String sql = "SELECT shushi FROM temples WHERE _id = " + id;
        Cursor cursor = db.rawQuery(sql, null);
        String result = "";
        while (cursor.moveToNext()) {
            int idxContent = cursor.getColumnIndex("shushi");
            result = cursor.getString(idxContent);
        }
        return result ;
    }
    /**
     * 主キーによる内容検索メソッド。
     * @param db SQLiteDatabaseオブジェクト。
     * @param id 主キー値。
     * @return 主キーに対応するaddressカラムの値。
     */
    public static String findaddressByPK(SQLiteDatabase db, int id) {
        String sql = "SELECT address FROM temples WHERE _id = " + id;
        Cursor cursor = db.rawQuery(sql, null);
        String result = "";
        while (cursor.moveToNext()) {
            int idxContent = cursor.getColumnIndex("address");
            result = cursor.getString(idxContent);
        }
        return result ;
    }
    /**
     * 主キーによる内容検索メソッド。
     * @param db SQLiteDatabaseオブジェクト。
     * @param id 主キー値。
     * @return 主キーに対応するurlカラムの値。
     */
    public static String findurlByPK(SQLiteDatabase db, int id) {
        String sql = "SELECT url FROM temples WHERE _id = " + id;
        Cursor cursor = db.rawQuery(sql, null);
        String result = "";
        while (cursor.moveToNext()) {
            int idxContent = cursor.getColumnIndex("url");
            result = cursor.getString(idxContent);
        }
        return result ;
    }
    /**
     * 主キーによる内容検索メソッド。
     * @param db SQLiteDatabaseオブジェクト。
     * @param id 主キー値。
     * @return 主キーに対応するnoteカラムの値。
     */
    public static String findnoteByPK(SQLiteDatabase db, int id) {
        String sql = "SELECT note FROM temples WHERE _id = " + id;
        Cursor cursor = db.rawQuery(sql, null);
        String result = "";
        while (cursor.moveToNext()) {
            int idxContent = cursor.getColumnIndex("note");
            result = cursor.getString(idxContent);
        }
        return result ;
    }

    /**
     * 主キーによるレコード存在チェックメソッド。
     * @param db SQLiteDatabaseオブジェクト。
     * @param id 主キー値。
     * @return 主キーに対応するcontentカラム値。
     */
    public static boolean findRowByPK(SQLiteDatabase db, int id) {
        String sql = "SELECT COUNT(*) AS count FROM temples WHERE _id = " + id;
        Cursor cursor = db.rawQuery(sql, null);
        boolean result = false;
        if (cursor.moveToFirst()) {
            int idxCount = cursor.getColumnIndex("count");
            int count = cursor.getInt(idxCount);
            if (count >= 1) {
                result = true;
            }
        }
        return result;
    }

    /**
     * 更新するメソッド。
     * @param db SQLiteDatabaseオブジェクト。
     * @param id 主キー値。
     * @param name 寺院名。
     * @param honzon 本尊名。
     * @param shushi 宗旨。
     * @param address 所在地。
     * @param url URL。
     * @param note 感想。
     * @return 更新件数。
     */
    public static int update(SQLiteDatabase db, int id, String name, String honzon, String shushi, String address, String url, String note) {
        String sql = "UPDATE temples SET name = ?, honzon = ?, shushi = ?, address = ?, url = ?, note = ? WHERE _id = ?";
        SQLiteStatement stmt  = db.compileStatement(sql);

        stmt.bindString(1, name);
        stmt.bindString(2, honzon);
        stmt.bindString(3, shushi);
        stmt.bindString(4, address);
        stmt.bindString(5, url);
        stmt.bindString(6, note);
        stmt.bindLong(7, id);

        int result = stmt.executeUpdateDelete();
        return result;
    }

    /**
     * 新規登録するメソッド。
     * @param db SQLiteDatabaseオブジェクト。
     * @param id 主キー値。
     * @param name 寺院名。
     * @param honzon 本尊名。
     * @param shushi 宗旨。
     * @param address 所在地。
     * @param url URL。
     * @param note 感想。
     * @return 登録したレコードの主キー値。
     */
    public static long insert(SQLiteDatabase db, int id, String name, String honzon, String shushi, String address, String url, String note) {
        String sql = "INSERT INTO temples (_id, name, honzon, shushi, address, url, note) VALUES (?, ?, ?, ?, ?, ?, ?)";
        SQLiteStatement stmt = db.compileStatement(sql);
        stmt.bindLong(1, id);
        stmt.bindString(2, name);
        stmt.bindString(3, honzon);
        stmt.bindString(4, shushi);
        stmt.bindString(5, address);
        stmt.bindString(6, url);
        stmt.bindString(7, note);
        long insertedId = stmt.executeInsert();
        return insertedId;
    }
}
