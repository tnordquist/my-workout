package edu.cnm.deepdive.myworkout.controller.ui.exercises;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.myworkout.databinding.FragmentExercisesBinding;
import edu.cnm.deepdive.myworkout.model.entity.Exercise;
import edu.cnm.deepdive.myworkout.model.entity.Muscle.Area;

public class ExercisesFragment extends Fragment implements OnItemSelectedListener {

  private FragmentExercisesBinding binding;
  private ExercisesViewModel exercisesViewModel;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    binding = FragmentExercisesBinding.inflate(inflater, container, false);
    binding.targetGroupSpinner.setOnItemSelectedListener(this);
    binding.exercisesSpinner.setOnItemSelectedListener(this);
    ArrayAdapter<Area> adapter =
        new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, Area.values());
    binding.targetGroupSpinner.setAdapter(adapter);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    exercisesViewModel = new ViewModelProvider(this).get(ExercisesViewModel.class);
    exercisesViewModel.getExercises().observe(getViewLifecycleOwner(), (exercises) -> {
      ArrayAdapter<Exercise> adapter =
          new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, exercises);
      binding.exercisesSpinner.setAdapter(adapter);
    });
  }

  @Override
  public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    if (parent == binding.exercisesSpinner) {
      Exercise exercise = (Exercise) parent.getItemAtPosition(position);
      binding.exerciseDescription.setText(exercise.getDescription());
    } else {
      exercisesViewModel.setArea((Area) parent.getItemAtPosition(position));
    }
  }

  @Override
  public void onNothingSelected(AdapterView<?> parent) {

  }
}