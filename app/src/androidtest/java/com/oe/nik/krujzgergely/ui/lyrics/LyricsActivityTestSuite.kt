package com.oe.nik.krujzgergely.ui.lyrics

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    LyricsActivityAllRecyclerViewTest::class,
    LyricsActivityCountryRecyclerViewTest::class,
    LyricsActivityFavoriteRecyclerViewTest::class,
    LyricsActivityHipHopRecyclerViewTest::class,
    LyricsActivityJazzRecyclerViewTest::class,
    LyricsActivityMetalRecyclerViewTest::class,
    LyricsActivityOperaRecyclerViewTest::class,
    LyricsActivityPopRecyclerViewTest::class,
    LyricsActivityPunkRecyclerViewTest::class,
    LyricsActivityRockRecyclerViewTest::class,
    LyricsActivityTest::class,
    LyricsActivityRecyclerViewScrollTest::class
)
class LyricsActivityTestSuite