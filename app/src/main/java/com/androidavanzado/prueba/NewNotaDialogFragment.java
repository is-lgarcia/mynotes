package com.androidavanzado.prueba;

import android.app.AlertDialog;
import android.app.Dialog;
import androidx.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;

import com.androidavanzado.prueba.model.entity.NotaEntity;

public class NewNotaDialogFragment extends DialogFragment {

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

                NewNotaDialogViewModel mViewModel =
                        ViewModelProviders.of(getActivity()).get(NewNotaDialogViewModel.class);

                //Comunicamos el ViewModel con la NuevaNota
                mViewModel.insertarNota(new NotaEntity(titulo, contenido, isFavorite, color));
                dialog.dismiss();

            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        return builder.create();
    }
}
