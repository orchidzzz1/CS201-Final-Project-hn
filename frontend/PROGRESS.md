Current Functionality:\
    &nbsp;Log-In / Register Page as been added\
        &nbsp;You can switch between the forms, but the log-in/register button do nothing.\

Need To Do:\
    1) Upon Log-In, re-direct to the main page.\
    2) Upon Registration, re-direct to preferences page.\
    3) Create the CreatePost page.\
        &nbsp;a) Post details: Event Name, Date, Time, Location, Event-Type, Image\
            &nbsp;-> Upon post creation, take user to post's main page\
    4) Create the userPreferences page.\
        &nbsp;I) allow user to view and edit their preferenes\
        &nbsp;II) Include "save changes" button to verify changes -> if changes are not saved and user navigates off -> ask if want to save\
    5) Create mainPage\
        &nbsp;a) features: \
            &nbsp;I) user-specific feed (generated based on filters)\
            &nbsp;II) all events below user-specific feed\
            &nbsp;III) ability to search posts by title\
            &nbsp;IV) ability to filter posts by event attributes\
            &nbsp;V) logout button: should re-direct to log-in page\
            &nbsp;VI) createPost button: should re-direct to createPost page\
            &nbsp;VII) User-preferences button should re-direct to userPreferences page\
    6) Create Post structure:\
        &nbsp;a) Allows user to navigate to page that displays the event information\
        &nbsp;b) Post should have an RSVP button -> onClick => prompt user for name, email, and phone number\
              
