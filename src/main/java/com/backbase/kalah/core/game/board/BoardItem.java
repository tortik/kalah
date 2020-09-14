package com.backbase.kalah.core.game.board;

import com.backbase.kalah.core.game.Kalah;
import com.backbase.kalah.core.game.KalahType;
import com.backbase.kalah.core.game.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

class BoardItem {
    private final List<Kalah> kalahs;
    private final Kalah firstStore;
    private final Kalah secondStore;

    public BoardItem(Player one, Player two) {
        firstStore = new Kalah(KalahType.STORE, one, 7, 0);
        secondStore = new Kalah(KalahType.STORE, two, 0, 0);
        kalahs = initBoard(firstStore, secondStore);
    }

    public List<Kalah> getKalahs() {
        return kalahs;
    }

    public Kalah getFirstStore() {
        return firstStore;
    }

    public Kalah getSecondStore() {
        return secondStore;
    }

    private List<Kalah> initBoard(Kalah firstStore, Kalah secondStore) {
        List<Kalah> kalahs = new ArrayList<>();
        kalahs.add(secondStore);
        addKalahHome(kalahs, firstStore.getOwner(), kalahs.size());
        kalahs.add(firstStore);
        addKalahHome(kalahs, secondStore.getOwner(), kalahs.size());
        return kalahs;
    }

    private void addKalahHome(List<Kalah> kalahs, Player p, int startFrom) {
        IntStream.range(startFrom, startFrom + 6).forEach(i -> kalahs.add(new Kalah(KalahType.HOME, p, i)));
    }
}
