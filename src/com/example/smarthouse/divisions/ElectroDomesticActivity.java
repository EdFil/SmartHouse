package com.example.smarthouse.divisions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.TextView;

import com.example.smarthouse.R;
import com.example.smarthouse.mainactivity.ExpandableListAdapter;
import com.example.smarthouse.mainactivity.TextClock;
import com.example.smarthouse.popupsalerts.moviePopup;

public class ElectroDomesticActivity extends FragmentActivity {

	private TextClock _textClock;
	private String division;
	
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

 
		Intent i = getIntent();
        	setContentView(R.layout.activity_movie_list);        	
        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
 
        // preparing list data
        prepareListData();
 
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
 
        // setting list adapter
        expListView.setAdapter(listAdapter);
        
		_textClock = new TextClock(this);
		
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
                // TODO Auto-generated method stub
            	if(division.equals("Cozinha")) {
            		View frag = getSupportFragmentManager().findFragmentById(R.id.details).getView();

            		((TextView)frag.findViewById(R.id.AlertDescription)).setText("");
            		((TextView)frag.findViewById(R.id.Product)).setText( listDataHeader.get(groupPosition) +""+ listDataChild.get( listDataHeader.get(groupPosition)).get(childPosition));
            		Random rand = new Random();
            		int  n = rand.nextInt(30) + 5;
            		((TextView)frag.findViewById(R.id.Cons)).setText(n+"");

            	} else if(division.equals("Sala")) {

	                moviePopup dialog = new moviePopup();
	                dialog.show(getSupportFragmentManager(), "moviePopup");
	                /*Toast.makeText(
	                        getApplicationContext(),
	                        listDataHeader.get(groupPosition)
	                                + " : "
	                                + listDataChild.get(
	                                        listDataHeader.get(groupPosition)).get(
	                                        childPosition), Toast.LENGTH_SHORT)
	                        .show();*/            		
            	}
                return false;
            }

        });
    }
 
    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
 
        // Adding child data
 
        // Adding child data
        	
        listDataHeader.add("Favoritos");
        listDataHeader.add("Filmes");
        
        List<String> Favoritos = new ArrayList<String>();
        Favoritos.add("The Godfather");
        Favoritos.add("The Dark Knight");
 
        List<String> Filmes = new ArrayList<String>();
        Filmes.add("The Godfather");
        Filmes.add("The Dark Knight");
        Filmes.add("Balas e Bolinhos");
        Filmes.add("James Bond 007: From Russia with Love");
        Filmes.add("M�s de Agosto");
        Filmes.add("M�s de Setembro");
        Filmes.add("The Wolverine");

        listDataChild.put(listDataHeader.get(0), Favoritos); // Header, Child data
        listDataChild.put(listDataHeader.get(1), Filmes);
    }
}
