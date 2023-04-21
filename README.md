# Compose accessibility bugs

## Pager-related bugs

### TalkBack accessibility focus scrolls to the next page too early

`HorizontalPager` ignores the scrollable state of the parent `Column`:
When we have a scrollable `Column` with a non-scrollable `Pager` inside, and we have the last on-screen `Pager` item focused, 
TalkBack prefers to scroll the `Pager` to the next page ignoring the fact that the current page still has some content below.
This behavior makes the rest of the content inaccessible.
[HorizontalPagerFragment](app/src/main/java/com/example/composea11ybugs/screen/HorizontalPagerFragment.kt)

https://user-images.githubusercontent.com/13486209/209683803-38aff89f-67fd-4413-9697-070df53866c4.mp4

### Clunky navigation due to absent accessibility focus override mechanisms

Since custom focus order is [not supported](https://issuetracker.google.com/issues/186443263) by Compose,
it is impossible to quickly jump out of the `HorizontalPager` to the segment with tabs - we currently just go to the previous page.
This way, we have to necessarily scroll through all the pages back to the first one to reach tabs with categories.
This makes navigation clunky, and tabs do not serve their purpose to quickly bring the user to the desired page. It would be nice to have a way
to override this behavior with custom focus order.
Also, after selecting and clicking on a tab, we have to scroll through all the tabs till the end - maybe it could be better to allow
to automatically move the accessibility focus inside the `Pager` once a tab is selected. And then, when moving backwards, to jump out of the `Pager`
to the active tab.
[HorizontalPagerFragment](app/src/main/java/com/example/composea11ybugs/screen/HorizontalPagerFragment.kt)

https://user-images.githubusercontent.com/13486209/209683998-ae55be82-a621-4e31-a2ab-c7ca004b3061.mp4

## Initial focus bug

When two `Fragment`s have the same `topBar` (same back button, same text), and we go from one such `Fragment` to the other one, the TalkBack accessibility focus jumps to the middle of the content. The expected behavior is to always go to the top left corner of the new screen (to the back button) when `Fragment`s are replaced just like it works in every other case when `topBar`s have different buttons and/or texts
[InitialFocusFragment](app/src/main/java/com/example/composea11ybugs/screen/InitialFocusFragment.kt)

https://user-images.githubusercontent.com/13486209/209964845-f5e2706c-d965-4f7a-b7a9-d88965bada03.mp4

## WebView scroll bug

When we have a `WebView` with a longer text, TalkBack does not properly scroll it as the focus goes through next paragraphs of text.

In addition, when the focus reaches the last element, TalkBack reports that there are no more focusable elements on the page. It is wrong - there is a button is the `Scaffold`. If we ignore its message about no more elements and continue swiping, `WebView` starts scrolling, and once it scrolls until the end, then TalkBack suddenly picks the previously-invisible button up.

https://user-images.githubusercontent.com/13486209/233626407-c51e6196-8813-4ef6-8dd8-fb75d667ae8d.mp4
