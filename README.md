# Compose accessibility bugs

## Pager-related bugs

1. `HorizontalPager` ignores the scrollable state of the parent `Column`:
When we have a scrollable `Column` with a non-scrollable `Pager` inside, and we have the last on-screen `Pager` item focused, 
TalkBack prefers to scroll the `Pager` to the next page ignoring the fact that the current page still has some content below.
This behavior makes the rest of the content inaccessible.
[HorizontalPagerFragment](app/src/main/java/com/example/composea11ybugs/screen/HorizontalPagerFragment.kt)

https://user-images.githubusercontent.com/13486209/209683803-38aff89f-67fd-4413-9697-070df53866c4.mp4

2. Since custom focus order is [not supported](https://issuetracker.google.com/issues/186443263) by Compose,
it is impossible to quickly jump out of the `HorizontalPager` to the segment with tabs - we currently just go to the previous page.
This way, we have to necessarily scroll through all the pages back to the first one to reach tabs with categories.
This makes navigation clunky, and tabs do not serve their purpose to quickly bring the user to the desired page. It would be nice to have a way
to override this behavior with custom focus order.
Also, after selecting and clicking on a tab, we have to scroll through all the tabs till the end - maybe it could be better to allow
to automatically move the accessibility focus inside the `Pager` once a tab is selected. And then, when moving backwards, to jump out of the `Pager`
to the active tab.
[HorizontalPagerFragment](app/src/main/java/com/example/composea11ybugs/screen/HorizontalPagerFragment.kt)

https://user-images.githubusercontent.com/13486209/209683998-ae55be82-a621-4e31-a2ab-c7ca004b3061.mp4
