package ru.com.gid.profile;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ru.com.gid.api.GameModel;

public class ProfileViewModel extends ViewModel {

    MutableLiveData<List<GameModel>> wishedGames = new MutableLiveData<>();
    MutableLiveData<List<GameModel>> unrealeasedGames = new MutableLiveData<>();
    MutableLiveData<List<GameModel>> libraryGames = new MutableLiveData<>();

    public void postWishedGames(List<GameModel> games) {
        wishedGames.postValue(games);
    }

    public void postLibraryGames(List<GameModel> games) {
        libraryGames.postValue(games);
    }

    public void postUnreleasedGames(List<GameModel> games) {
        unrealeasedGames.postValue(games);
    }

    public MutableLiveData<List<GameModel>> getWishedGames() {
        return wishedGames;
    }
}