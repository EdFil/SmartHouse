package com.example.smarthouse.popupsalerts;

import java.util.HashMap;
import java.util.List;

import com.example.smarthouse.R;
import com.example.smarthouse.divisions.MovieActivity;
import com.example.smarthouse.mainactivity.ExpandableListAdapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.ExpandableListView;

@SuppressLint("ValidFragment")
public class moviePopup extends DialogFragment{
	
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {  	 
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.Opcoes)
               .setPositiveButton(R.string.Reproduzir, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
    				   Intent i = new Intent(getActivity().getBaseContext(), MovieActivity.class);
    				   startActivity(i);
                       dialog.dismiss();
                   }
               })
               .setNeutralButton(R.string.AdicionarFav, new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog, int id) {

		            	boolean remove = false;
	            		for (String temp : ((HashMap<String, List<String>>) getArguments().getSerializable("listDataChild")).get(getArguments().getString("Favoritos"))) {
	            			if(temp.equals(getArguments().getString("movie"))) {
	            				dialog.dismiss();
	            				return;
	            			}
	            		}
	            		
	            		for (String temp : ((HashMap<String, List<String>>) getArguments().getSerializable("listDataChild")).get(getArguments().getString("Filmes"))) {
	            			if(temp.equals(getArguments().getString("movie"))) {
	            				remove = true;
	            			}
	            		}
	            		
	            		if(remove) {
            				((HashMap<String, List<String>>) getArguments().getSerializable("listDataChild")).get("Filmes").remove(getArguments().getString("movie"));
            				((HashMap<String, List<String>>) getArguments().getSerializable("listDataChild")).get("Favoritos").add(getArguments().getString("movie"));
	            			            		}
	            		((ExpandableListAdapter)getArguments().getSerializable("listAdapter")).notifyDataSetChanged();
		                dialog.dismiss();
		            }     	
        	   })
        	   .setNegativeButton(R.string.Cancelar, new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog, int id) {
		                dialog.dismiss();
		            }     	
        	   });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
