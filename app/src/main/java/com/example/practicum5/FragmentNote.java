package com.example.practicum5;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.HashSet;

public class FragmentNote extends Fragment {
    public static final String ARG_NOTE = "NOTE";

    private static StringNote stringNote;
    int noteId;
    public static FragmentNote newInstance(String param1, String param2) {
        FragmentNote fragment = new FragmentNote();
        Bundle bundle= new Bundle();
        bundle.putParcelable(ARG_NOTE, stringNote);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText editText = view.findViewById(R.id.editText);
        Intent intent = new Intent(getApplicationContext(), FragmentNote.class);
        noteId = intent.getIntExtra("noteId", -1);
        if (noteId != -1){
            editText.setText(com.example.practicum5.MainActivity.notes.get(noteId));

        } else {
            com.example.practicum5.MainActivity.notes.add("");
            noteId = com.example.practicum5.MainActivity.notes.size() -1;
            com.example.practicum5.MainActivity.arrayAdapter.notifyDataSetChanged();

        }
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                com.example.practicum5.MainActivity.notes.set(noteId, String.valueOf(s));
                com.example.practicum5.MainActivity.arrayAdapter.notifyDataSetChanged();
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.easynote;", Context.MODE_PRIVATE);
                HashSet<String> set = new HashSet(com.example.practicum5.MainActivity.notes);
                sharedPreferences.edit().putStringSet("notes", set).apply();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


}