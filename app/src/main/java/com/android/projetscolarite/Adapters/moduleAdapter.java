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

import com.android.projetscolarite.Models.Filiere;
import com.android.projetscolarite.Models.Module;
import com.android.projetscolarite.R;

import java.util.ArrayList;

public class moduleAdapter extends ArrayAdapter implements View.OnClickListener {
        ArrayList<Module> modules;
        ShowDialog showDialog;

        public moduleAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Module> objects){
        super(context,resource,objects);
        modules=objects;
        showDialog = (ShowDialog) context;
        }
        public interface ShowDialog{
            public void display(Module mod);
        }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){

            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_module,parent,false);
        }

        TextView nomMod;
        Button SupprimerMod;
        nomMod = convertView.findViewById(R.id.nomMod);
        SupprimerMod = convertView.findViewById(R.id.SupprimerMod);
        nomMod.setText(getItem(position).getNom());
        SupprimerMod.setOnClickListener(this);
        SupprimerMod.setTag(String.valueOf(position));
        return convertView;
    }


    @Nullable
    @Override
    public Module getItem(int position) {
        return modules.get(position);
    }
    @Override
    public void onClick(View v) {
        Module module = getItem(Integer.parseInt((String) v.getTag()));
        showDialog.display(module);
    }
}
