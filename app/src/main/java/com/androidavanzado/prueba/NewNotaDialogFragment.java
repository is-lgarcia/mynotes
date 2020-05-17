package com.androidavanzado.prueba;

import android.app.AlertDialog;
import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;

public class NewNotaDialogFragment extends DialogFragment {

    private NewNotaDialogViewModel mViewModel;
    private View view;
    private EditText editTextTitulo, editTextContendo;
    private RadioGroup radioGroupColor;
    private Switch switchFavorito;

    public static NewNotaDialogFragment newInstance() {
        return new NewNotaDialogFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.new_nota_dialog_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(NewNotaDialogViewModel.class);
        // TODO: Use the ViewModel
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.new_nota_dialog_fragment,null);
        editTextTitulo = view.findViewById(R.id.editTitulo);
        editTextContendo = view.findViewById(R.id.editTextContenido);
        radioGroupColor = view.findViewById(R.id.radioGruopColor);
        switchFavorito = view.findViewById(R.id.switchNotaFavorita);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Introduzca los datos de la nueva nota");
        builder.setTitle("Nueva Nota");
        builder.setView(view);
        builder.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String titulo = editTextTitulo.getText().toString();
                String contenido = editTextContendo.getText().toString();
                String color = "azul";
                switch (radioGroupColor.getCheckedRadioButtonId()){
                    case R.id.radioButtonColorRojo:
                        color = "rojo";
                    break;
                    case R.id.radioButtonColorVerde:
                        color = "verde";
                    break;

                }
                boolean isFavorite = switchFavorito.isChecked();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });

        return builder.create();
    }
}
