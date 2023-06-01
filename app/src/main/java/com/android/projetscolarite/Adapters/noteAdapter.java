package com.android.projetscolarite.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.projetscolarite.Models.Evaluation;
import com.android.projetscolarite.R;

import java.util.ArrayList;

public class noteAdapter extends ArrayAdapter implements View.OnClickListener {
    ArrayList<Evaluation> evaluations;
    ShowDialog showDialog;

    // Constructor
    public noteAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Evaluation> objects) {
        super(context, resource, objects);
        evaluations = objects;
        showDialog = (ShowDialog) context;
    }

    // Interface to display dialog
    public interface ShowDialog {
        public void display(Evaluation eva);
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            // Inflate the custom layout for the list item
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_bulltins, parent, false);
        }

        TextView nomMod, note;
        Button modifierNote;

        nomMod = convertView.findViewById(R.id.nomMod);
        note = convertView.findViewById(R.id.note);
        modifierNote = convertView.findViewById(R.id.modifierNote);

        // Set the data for each view in the list item
        nomMod.setText(getItem(position).getNomMod());
        note.setText(Double.toString(getItem(position).getNote()));

        // Set the click listener and tag for the "modifierNote" button
        modifierNote.setOnClickListener(this);
        modifierNote.setTag(String.valueOf(position));

        return convertView;
    }

    // Override to get the evaluation object at the specified position
    @Nullable
    @Override
    public Evaluation getItem(int position) {
        return evaluations.get(position);
    }

    // Handle button click event
    @Override
    public void onClick(View v) {
        // Retrieve the associated evaluation object based on the button's tag
        Evaluation evaluation = getItem(Integer.parseInt((String) v.getTag()));
        // Trigger the display method in the parent context
        showDialog.display(evaluation);
    }
}
