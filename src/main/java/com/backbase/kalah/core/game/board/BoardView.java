package com.backbase.kalah.core.game.board;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.backbase.kalah.core.GameConstant.ALL_KALAHS_NUMBER;

public class BoardView {

    private BoardItem boardItem;

    public BoardView(BoardItem boardItem) {
        this.boardItem = boardItem;
    }

    Map<Integer, String> firstPlayerView() {
        return IntStream.range(1, ALL_KALAHS_NUMBER + 1).boxed().
                collect(Collectors.toMap(Function.identity(),
                        i -> String.valueOf(boardItem.getKalahs().get(i % ALL_KALAHS_NUMBER).getStones()),
                        (v1, v2) -> v2, TreeMap::new));
    }

    Map<Integer, String> secondPlayerView() {
        List<String> stones = IntStream.concat(IntStream.range(8, 15), IntStream.range(1, 8))
                .map(i -> i % ALL_KALAHS_NUMBER).boxed()
                .map(i -> String.valueOf(boardItem.getKalahs().get(i).getStones()))
                .collect(Collectors.toList());
        return IntStream.range(0, stones.size())
                .boxed()
                .collect(Collectors.toMap(i -> i + 1, stones::get, (v1, v2) -> v2, TreeMap::new));
    }

}
