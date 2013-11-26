package com.example.smarthouse;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class moviePopup extends DialogFragment{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.Opcoes)
               .setPositiveButton(R.string.Reproduzir, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       dialog.dismiss();
                   }
               })
               .setNeutralButton(R.string.AdicionarFav, new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog, int id) {
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
