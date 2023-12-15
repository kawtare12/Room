package com.example.roomapp;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
// ViewModel est de fournir des données à l'interface utilisateur a partir de repository
public class WordViewModel extends AndroidViewModel {

    private final WordRepository mRepository;
// On utilise Livedata pour les données modifiables de l interface tel la lsite des mots
    private final LiveData<List<Word>> mAllWords;

    public WordViewModel (Application application) {
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWords();
    }

    LiveData<List<Word>> getAllWords() { return mAllWords; }

    public void insert(Word word) { mRepository.insert(word); }
}
