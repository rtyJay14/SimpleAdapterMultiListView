package training.listview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ListViewActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {

	EditText et;
	Button b;
	ListView lv1, lv2;
	TextView tv;
	
	ArrayList<String> arrList = new ArrayList<String>();
	ArrayAdapter<String> arrAdapter;
	
	List<HashMap<String, String>> mylist;
	HashMap<String, String> map;
	SimpleAdapter multiVerser;
	
	String multi = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view);

		et = (EditText)findViewById(R.id.editText1);
		tv = (TextView)findViewById(R.id.textView1);
		
		b = (Button)findViewById(R.id.button1);
		b.setOnClickListener(this);
		
		// ListView 1
		
		arrAdapter = new ArrayAdapter<String>(this, 
				android.R.layout.simple_list_item_1, arrList);
		
		lv1 = (ListView)findViewById(R.id.listView1);
		lv1.setOnItemClickListener(this);
		lv1.setAdapter(arrAdapter);
		
		// ListView 2

		lv2 = (ListView)findViewById(R.id.listView2);
		lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				mylist.remove(position);
				multiVerser.notifyDataSetChanged();
			}
		});
		
		mylist = new ArrayList<HashMap<String, String>>();
		map = new HashMap<String, String>();

		multiVerser = new SimpleAdapter(this, mylist, R.layout.row,
	            new String[] {"1st", "2nd", "3rd"}, 
	            new int[] {R.id.FIRST_CELL, R.id.SECOND_CELL, R.id.THIRD_CELL});
		
		lv2.setAdapter(multiVerser);
	}

	public String getMulti() {
		return multi;
	}

	public String setMulti(String multi) {
		return this.multi = multi;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		arrList.remove(position);
		arrAdapter.notifyDataSetChanged();
	}

	@Override
	public void onClick(View v) {
		
		setMulti(et.getText().toString());
		
		map = new HashMap<String, String>();
		map.put("1st", "1st");
		map.put("2nd", "2nd");
		map.put("3rd", /*multi = getMulti()*/ et.getText().toString());
		mylist.add(map);
		multiVerser.notifyDataSetChanged();
		
		et.setText("");
	}
}
