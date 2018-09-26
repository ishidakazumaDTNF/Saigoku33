package local.hal.ma42.android.saigoku3350053;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 第1画面表示用アクティビティクラス。
 * 西国三十三所リスト表示する。
 *
 */
public class TempleListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temple_list);

        ListView lvTemple = findViewById(R.id.lvTemple);

        List<String> templeList = createTempleList();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(TempleListActivity.this, android.R.layout.simple_list_item_1, templeList);
        lvTemple.setAdapter(adapter);

        lvTemple.setOnItemClickListener(new ListItemClickListener());
    }

    /**
     * 西国三十三所リストを生成するメソッド。
     * @return 西国三十三所リストオブジェクト。
     */
    private List<String> createTempleList() {
        List<String> templeList = new ArrayList<>();
        templeList.add("第一番 青岸渡寺");
        templeList.add("第二番 金剛宝寺");
        templeList.add("第三番 粉河寺");
        templeList.add("第四番 施福寺");
        templeList.add("第五番 葛井寺");
        templeList.add("第六番 南法華寺");
        templeList.add("第七番 岡寺");
        templeList.add("第八番 長谷寺");
        templeList.add("第九番 南円堂");
        templeList.add("第十番 三室戸寺");
        templeList.add("第十一番 上醍醐");
        templeList.add("第十二番 正法寺");
        templeList.add("第十三番 石山寺");
        templeList.add("第十四番 三井寺");
        templeList.add("第十五番 今熊野観音寺");
        templeList.add("第十六番 清水寺");
        templeList.add("第十七番 六波羅蜜寺");
        templeList.add("第十八番 六角堂 頂法寺");
        templeList.add("第十九番 革堂 行願寺");
        templeList.add("第二十番 善峯寺");
        templeList.add("第二十一番 穴太寺");
        templeList.add("第二十二番 総持寺");
        templeList.add("第二十三番 勝尾寺");
        templeList.add("第二十四番 中山寺");
        templeList.add("第二十五番 播州清水寺");
        templeList.add("第二十六番 一乗寺");
        templeList.add("第二十七番 圓教寺");
        templeList.add("第二十八番 成相寺");
        templeList.add("第二十九番 松尾寺");
        templeList.add("第三十番 宝厳寺");
        templeList.add("第三十一番 長命寺");
        templeList.add("第三十二番 観音正寺");
        templeList.add("第三十三番 華厳寺");
        return templeList;
    }

    /**
     * リストが選択されたときの処理が記述されたメンバクラス。
     * 第2画面へ処理を移管する。
     */
    private class ListItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String templeName = (String) parent.getItemAtPosition(position);

            Intent intent = new Intent(TempleListActivity.this, TempleEditActivity.class);
            intent.putExtra("selectedTempleNo", position);
            intent.putExtra("selectedTempleName", templeName);
            startActivity(intent);
        }
    }
}
