# Compose accessibility bugs

## Pager-related bugs

1. `HorizontalPager` ignores the scrollable state of the parent `Column`:
When we have a scrollable column with a non-scrollable pager inside, and we have the last on-screen pager item focused, 
TalkBack prefers to scroll the `Pager` to the next page ignoring the fact that the current page still has some content below.
This behavior makes the rest of the content inaccessible.

2. Since custom focus order is [not supported](https://issuetracker.google.com/issues/186443263) by Compose,
it is impossible to quickly jump out of the `HorizontalPager` to the segment with tabs - we currently just go to the previous page.
This way, we have to necessarily scroll through all the pages back to the first one to reach tabs with categories.
This makes navigation clunky, and tabs do not serve their purpose to quickly bring the user to the desired page. It would be nice to have a way
to override this behavior with custom focus order.