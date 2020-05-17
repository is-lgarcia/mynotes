package com.androidavanzado.prueba.view.fragment;

import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.androidavanzado.prueba.NewNotaDialogFragment;
import com.androidavanzado.prueba.NewNotaDialogViewModel;
import com.androidavanzado.prueba.R;
import com.androidavanzado.prueba.model.entity.NotaEntity;

import java.util.ArrayList;
import java.util.List;

public class NotaFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 2;
    private List<NotaEntity> notaList;
    private MyNotaRecyclerViewAdapter adapterNotas;
    private NewNotaDialogViewModel viewModel;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NotaFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static NotaFragment newInstance(int columnCount) {
        NotaFragment fragment = new NotaFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

        //Indicamos que el fragmento tiene un menu de opciones propio.
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nota_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if(view.getId() == R.id.listPortrait) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
                float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
                int numeroColumnas = (int) (dpWidth / 180);
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(numeroColumnas, StaggeredGridLayoutManager.VERTICAL));
            }

            notaList = new ArrayList<>();

            adapterNotas = new MyNotaRecyclerViewAdapter(notaList, getActivity());
            recyclerView.setAdapter(adapterNotas);

            lanzarViewModel();
        }
        return view;
    }

    private void lanzarViewModel() {
        viewModel = ViewModelProviders.of(getActivity()).get(NewNotaDialogViewModel.class);
        viewModel.getAllNotas().observe((LifecycleOwner) getActivity(), new Observer<List<NotaEntity>>() {
            @Override
            public void onChanged(List<NotaEntity> notaEntities) {
                adapterNotas.setNuevasNotas(notaEntities);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.option_menu_nota_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add_nota:
                mostrarDialogNuevaNota();
                return true;
            case R.id.action_delete_all:
                viewModel.deleteAll();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void mostrarDialogNuevaNota() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        NewNotaDialogFragment dialogFragment = new NewNotaDialogFragment();
        dialogFragment.show(fragmentManager, "NewNotaDialogFragment");
    }
}
