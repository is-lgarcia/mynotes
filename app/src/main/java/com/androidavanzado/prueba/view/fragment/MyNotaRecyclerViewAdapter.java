package com.androidavanzado.prueba.view.fragment;

import android.app.AlertDialog;
import android.content.Context;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidavanzado.prueba.NewNotaDialogViewModel;
import com.androidavanzado.prueba.R;
import com.androidavanzado.prueba.model.entity.NotaEntity;

import java.util.List;

public class MyNotaRecyclerViewAdapter extends RecyclerView.Adapter<MyNotaRecyclerViewAdapter.ViewHolder> {

    private List<NotaEntity> mValues;
    private Context mcontext;
    private NewNotaDialogViewModel viewModel;

    public MyNotaRecyclerViewAdapter(List<NotaEntity> items, Context context) {
        mValues = items;
        mcontext = context;
        viewModel = ViewModelProviders.of((FragmentActivity) context).get(NewNotaDialogViewModel.class);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_nota, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.tvTitulo.setText(holder.mItem.getTitulo());
        holder.tvContenido.setText(holder.mItem.getContenido());

        if(holder.mItem.isFavorita()) {
            holder.ivFavorita.setImageResource(R.drawable.ic_star_black_24dp);
        }

        holder.ivFavorita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.mItem.isFavorita()){
                    holder.mItem.setFavorita(false);
                    holder.ivFavorita.setImageResource(R.drawable.ic_star_border_black_24dp);
                }else {
                    holder.mItem.setFavorita(true);
                    holder.ivFavorita.setImageResource(R.drawable.ic_star_black_24dp);
                }
                viewModel.updateNota(holder.mItem);
            }
        });
        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mcontext);
                builder.setMessage("Seguro que desea eliminar Nota")
                        .setPositiveButton("   Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                viewModel.deleteById(holder.mItem.getId());
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("Cancelar   ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create().show();

                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void setNuevasNotas(List<NotaEntity> nuevasNotas){
        this.mValues = nuevasNotas;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tvTitulo;
        public final TextView tvContenido;
        public final ImageView ivFavorita;
        public NotaEntity mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvTitulo = view.findViewById(R.id.textViewTitulo);
            tvContenido = view.findViewById(R.id.textViewContenido);
            ivFavorita = view.findViewById(R.id.imageViewFavorita);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvTitulo.getText() + "'";
        }
    }
}
