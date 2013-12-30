package com.example.smarthouse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;

import com.example.smarthouse.mainactivity.ExpandableListAdapter;
import com.example.smarthouse.mainactivity.TextClock;
import com.example.smarthouse.popupsalerts.moviePopup;

public class MediaActivity extends FragmentActivity {

	private DataVariables _dataVariables;
	ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_media);
		
		_dataVariables = (DataVariables)getApplication();
		if(!_dataVariables.isWindowInited())
			_dataVariables.initWindowSize(this);
		// get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
 
        // preparing list data
        prepareListData();
 
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
 
        // setting list adapter
        expListView.setAdapter(listAdapter); 
        
        new Breadcumbs(this);
		new TextClock(this);
		
        // Listview Group click listener
        expListView.setOnGroupClickListener(new OnGroupClickListener() {
 
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                    int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });
 
        // Listview on child click listener
        expListView.setOnChildClickListener(new OnChildClickListener() {
 
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                    int groupPosition, int childPosition, long id) {

                moviePopup dialog = new moviePopup();

                // Supply num input as an argument.
                Bundle args = new Bundle();
                args.putString("movie", listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition));
                args.putSerializable("listDataChild", listDataChild);
                args.putString("Favoritos", listDataHeader.get(0));
                args.putString("Filmes", listDataHeader.get(1));
                args.putSerializable("listAdapter", listAdapter);
                dialog.setArguments(args);
                
                
                dialog.show(getSupportFragmentManager(), "notfDialog");
                /*Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                        listDataHeader.get(groupPosition)).get(
                                        childPosition), Toast.LENGTH_SHORT)
                        .show();*/
                return false;
            }

        });
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.media, menu);
		return true;
	}
	
    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
 
        // Adding child data
        listDataHeader.add("Favoritos");
        listDataHeader.add("Filmes");
 
        // Adding child data
        List<String> Favoritos = new ArrayList<String>();
        Favoritos.add("The Godfather");
        Favoritos.add("The Dark Knight");
 
        List<String> Filmes = new ArrayList<String>();
        Filmes.add("Balas e Bolinhos");
        Filmes.add("James Bond 007: From Russia with Love");
        Filmes.add("M�s de Agosto");
        Filmes.add("M�s de Setembro");
        Filmes.add("The Wolverine");

        listDataChild.put(listDataHeader.get(0), Favoritos); // Header, Child data
        listDataChild.put(listDataHeader.get(1), Filmes);
    }

}
