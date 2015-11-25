package com.example.augusto.projeto2do.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.augusto.projeto2do.R;
import com.example.augusto.projeto2do.modelo.Categoria;

import java.util.List;

/**
 * Created by augusto on 23/11/15.
 */
public class CategoriasAdapter extends BaseAdapter {

    private List<Categoria> categorias;

    private Context context;

    public CategoriasAdapter(Context context, List<Categoria> categorias){
        super();
        this.categorias = categorias;
        this.context = context;
    }

    @Override
    public int getCount() {
        return categorias.size();
    }

    @Override
    public Object getItem(int position) {
        return categorias.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.list_view_categoria, parent, false);

        Categoria categoria = (Categoria) this.categorias.get(position);

        TextView textView = (TextView) view.findViewById(R.id.tvNomeCategoria);
        textView.setText(categoria.getNome());

        return view;
    }
}
