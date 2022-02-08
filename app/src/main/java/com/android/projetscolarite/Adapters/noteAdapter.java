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
import com.android.projetscolarite.Models.Module;
import com.android.projetscolarite.R;

import java.util.ArrayList;

public class noteAdapter extends ArrayAdapter implements View.OnClickListener {
        ArrayList<Evaluation> evaluations;
        ShowDialog showDialog;

        public noteAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Evaluation> objects){
        super(context,resource,objects);
        evaluations=objects;
        showDialog = (ShowDialog) context;
        }
        public interface ShowDialog{
            public void display(Evaluation eva);
        }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){

            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_bulltins,parent,false);
        }

        TextView nomMod,note;
        Button modifierNote;
        nomMod = convertView.findViewById(R.id.nomMod);
        note = convertView.findViewById(R.id.note);
        modifierNote = convertView.findViewById(R.id.modifierNote);
        nomMod.setText(getItem(position).getNomMod());
        note.setText(Double.toString(getItem(position).getNote()));
        modifierNote.setOnClickListener(this);
        modifierNote.setTag(String.valueOf(position));
        return convertView;
    }


    @Nullable
    @Override
    public Evaluation getItem(int position) {
        return evaluations.get(position);
    }
    @Override
    public void onClick(View v) {
        Evaluation evaluation = getItem(Integer.parseInt((String) v.getTag()));
        showDialog.display(evaluation);
    }
}
