package ar.com.wolox.android.example.ui.home.news;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import ar.com.wolox.android.example.network.News;

/**
 *
 */

public interface INewsView {
    void goAddNews();

    void nothingNewToShow();

    void addRecentNews(@NotNull ArrayList<News> recentNews);

    void startLoading();

    void completeLoading();

    void addOlderNews(@NotNull ArrayList<News> olderNews);
}