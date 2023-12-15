package com.example.roomapp;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WordRepository {

    private final WordDao mWordDao;
    //Cette liste de mots sera mise à jour automatiquement par Room chaque fois
    // que la base de données est modifiée.
    private final LiveData<List<Word>> mAllWords;

    public WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        // appel de la fct dans dao qui affcihees la listes des mots ascendant
        mAllWords = mWordDao.getAlphabetizedWords();
    }

// l'affichage des données mises à jour dans l'interface utilisateur.
    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public void insert(Word word) {
        WordRoomDatabase.databaseWriteExecutor.execute(() -> mWordDao.insert(word));
    }
}
//Un référentiel (Repository) gère les requêtes et vous permet d'utiliser plusieurs bases de données.
//WordRepository agit comme une interface entre la source de données (la base de données Room) et
// le reste de l'application. Elle expose des méthodes pour récupérer une liste observable de tous
// les mots et pour insérer un nouveau mot dans la base de données.
// La gestion des threads est également prise en compte pour éviter les blocages du thread principal.