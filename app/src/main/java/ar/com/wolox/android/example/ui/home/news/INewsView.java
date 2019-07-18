package ar.com.wolox.android.example.ui.home.news;

import org.jetbrains.annotations.NotNull;
import java.util.List;
import ar.com.wolox.android.example.network.News;

/**
 *
 */

public interface INewsView {
    void goAddNews();

    void nothingNewToShow();

    void addRecentNews(@NotNull List<News> recentNews);

    void startLoading();

    void completeLoading();

    void addOlderNews(@NotNull List<News> olderNews);

    void onLoadOlderNewsError();

    void onLoadRecentNewsError();
}