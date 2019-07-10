package ar.com.wolox.android.example.ui.home.news;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 *
 */

public interface INewsView {
    void goAddNews();

    void nothingNewToShow();

    void addRecentNews(@NotNull ArrayList<String> recentNews);
}