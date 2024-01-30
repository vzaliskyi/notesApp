## Overview
NotesApp is unfinished app in which I use such android components: activity, viewBinding, passing data between activities, ListAdapter, viewModel, toolBar, menu, dialog.
During developing app I was focused to implement functionality rather than make UI design.
Temporary I use list as a source of data but later I will connect database.

## Functionality
App contains 3 activities: MainActivity, NoteDetailActivity, NoteEditActivity
### MainActivity
+ Display list of all notes
+ Click on note redirect to NoteDetailActivity
+ Has floating button which redirect to NoteEditActivity

### NoteDetailActivity
+ Display text of note
+ Click on text of note redirect to NoteEditActivity
+ Has 3 items on toolbar: backItem, selectItem, deleteItem
+ Click on backItem finish activity and return to MainActivity
+ Click on selectItem mark note as selected
+ Click on deleteItem show dialog with prompt and 2 items: click on yes item delete note and return to MainActivity, click on no item hide dialog

### NoteEditActivity
+ Display text of note if it exist
+ Allow to edit text
+ Has 2 items in toolBar: cancelItem and saveitem
+ click on cancelItem cancel changes and return to NoteDetailActivity or MainActivity(depend on activity which started NoteEditActivity)
+ click on saveItem save change and create new note if it didn't exist. As well as cancelItem return to NoteDetailActivity or MainActivity


## Roadmap
1. Possibility to add tags to note
2. Add filter by tags and select value
3. Refactor code
4. Polish UI/UX
5. Disable animation in listAdapter

## Screenshots
<img src="https://github.com/vzaliskyi/notesApp/assets/78689702/d05512ec-a8d8-4d4e-a498-e0973af2bd0f" alt="MainActivity" width="250">
<img src="https://github.com/vzaliskyi/notesApp/assets/78689702/98139325-279c-4577-97e4-891c1219e473" alt="NoteDetailActivity" width="250">
<img src="https://github.com/vzaliskyi/notesApp/assets/78689702/2c97c083-d53d-41ba-9673-2c286335d0e5" alt="MainActivity" width="250">

