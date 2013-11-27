package com.example.smarthouse.popupsalerts;

import com.example.smarthouse.R;
import com.example.smarthouse.divisions.MovieActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class electroPopup extends DialogFragment{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.Opcoes)
               .setPositiveButton(R.string.Ligar, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       dialog.dismiss();
                   }
               })
               .setNeutralButton(R.string.Cancelar, new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog, int id) {
		                dialog.dismiss();
		            }     	
        	   })
        	   .setNegativeButton(R.string.Desligar, new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog, int id) {
		                dialog.dismiss();
		            }     	
        	   });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}

