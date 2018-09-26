package local.hal.ma42.android.saigoku3350053;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 *
 * 第2画面表示用アクティビティクラス。
 * 寺院編集画面を表示する。
 *
 */
public class TempleEditActivity extends AppCompatActivity {
    /**
     * 寺院リスト画面で選択されたリストの行番号。
     */
    private int _selectedTempleNo = 0;
    /**
     * 寺院リスト画面で選択された寺院名。
     */
    private  String _selectedTempleName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temple_edit);

        Intent intent = getIntent();
        _selectedTempleNo = intent.getIntExtra("selectedTempleNo", 0);
        _selectedTempleName = intent.getStringExtra("selectedTempleName");

        String honzon = "";
        String shushi = "";
        String access = "";
        String url = "";
        String note = "";

        DatabaseHelper helper = new DatabaseHelper(TempleEditActivity.this);
        SQLiteDatabase db = helper.getWritableDatabase();
        try {
            honzon = DataAccess.findhonzonByPK(db, _selectedTempleNo);
            shushi = DataAccess.findshushiByPK(db, _selectedTempleNo);
            access = DataAccess.findaddressByPK(db, _selectedTempleNo);
            url = DataAccess.findurlByPK(db, _selectedTempleNo);
            note = DataAccess.findnoteByPK(db, _selectedTempleNo);
        }
        catch (Exception ex) {
            Log.e("TempleMemo", ex.toString());
        }
        finally {
            db.close();
        }
        EditText etHonzon = findViewById(R.id.etHonzon);
        etHonzon.setText(honzon);

        EditText etShushi = findViewById(R.id.etShushi);
        etShushi.setText(shushi);

        EditText etAddress = findViewById(R.id.etAddress);
        etAddress.setText(access);

        EditText etUrl = findViewById(R.id.etUrl);
        etUrl.setText(url);

        EditText etNote = findViewById(R.id.etNote);
        etNote.setText(note);


        TextView tvTemp = findViewById(R.id.tvTemp);
        tvTemp.setText(_selectedTempleName);

        Button btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new ButtonClickListener());
    }

    /**
     * ボタンがクリックされたときの処理が記述されたメンバクラス。
     * DBにデータを保存する。
     */
    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            EditText etHonzon = findViewById(R.id.etHonzon);
            String honzon = etHonzon.getText().toString();

            EditText etShushi = findViewById(R.id.etShushi);
            String shushi = etShushi.getText().toString();

            EditText etAddress = findViewById(R.id.etAddress);
            String address = etAddress.getText().toString();

            EditText etUrl = findViewById(R.id.etUrl);
            String url = etUrl.getText().toString();

            EditText etNote = findViewById(R.id.etNote);
            String note = etNote.getText().toString();

            DatabaseHelper helper = new DatabaseHelper(TempleEditActivity.this);
            SQLiteDatabase db = helper.getWritableDatabase();
            try {
                boolean exist = DataAccess.findRowByPK(db, _selectedTempleNo);
                if (exist) {
                    DataAccess.update(db, _selectedTempleNo, _selectedTempleName, honzon, shushi, address, url, note);
                }
                else {
                    DataAccess.insert(db, _selectedTempleNo, _selectedTempleName, honzon, shushi, address, url, note);
                }
            }
            catch (Exception ex) {
                Log.e("TempleMemo", ex.toString());
            }
            finally {
                db.close();
            }

            finish();
        }
    }
}
