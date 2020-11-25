# COVID-19 Data Assistant

## **Overview**:
The *COVID-19 Data Assistant* functions to help restaurants, stores, or any type of health organization keep track of
important information about their customers or visitors from that day. Businesses or organizations who use the
*COVID-19 Data Assistant* can store the individual's name, phone number, and the other places
they've been that day. If someone who visited the place or business tested positive for the virus, the business would
be able to contact everyone else who that person may have come in contact with that day. At the end of the day, the user
can then print out a daily log of visitors stored using the *COVID-19 Data Assistant*, and keep it for their own
records.

For my project, I wanted to create something relevant and useful for today's world. With the COVID-19 pandemic affecting
everyone globally, I felt that a data assistant would be incredibly useful for businesses and other organizations to 
effectively track their customers. In the case that someone who visited their business tests positive
for the virus sometime after visiting, they can contact the business owner. With the daily log of visitors, the owner
can then easily find all the people who visited their business that day, so that they can get tested. Overall, this
data manager assistant would help reduce the transmission of COVID-19 by encouraging people to get tested,
even if they are asymptomatic. I was inspired to create the *COVID-19 Data Assistant* after visiting a restaurant where
I was asked to leave my information for this very purpose, and I felt like it was simple yet excellent way to keep track
of all the business/organization's visitors.

## **User Stories**:
- As a user, I want to be able to add a visitor's information to the list
- As a user, I want to be able to view a list of names of visitors from the list
- As a user, I want to be able to delete a visitor and re-enter their information if I've made a mistake
- As a user, I want to be able to select a name on the visitor list and view their information in detail
- As a user, I want to be able to save my list of visitors' information to file
- As a user, I want to be able to load my list of visitors' information on file

## **Phase 4: Task 2**
For Task 2 of Phase 4, I chose to implement a robust class. Specifically, I made my ListOfPerson class in my model
package robust by having my outputNames() method throw an exception called NoViewableNamesException when there are no
names on the current list to be viewed by the user. This NoViewableNamesException is caught in the viewNamesPanel()
method (found in the GUI class of the ui package), where the user will be alerted with a warning window, telling them to
either load a list of names or add at least one name before trying to view the list again.

## **Phase 4: Task 3**
Given more time, I would definitely perform some refactoring to improve my design. In my GUI class, there is a lot of
code, and it is also somewhat repetitive. To clean this up, I would separate this code into smaller subclasses to
improve readability. For example, the code that is used to make new JFrames, JPanels, JLabels and JButtons is quite
repetitive. To fix this, JFrame, JPanel, JLabel, and JButton could all be made into separate subclasses. This design
choice would make for the GUI class as a whole to be much more readable, and cleaner looking. Additionally, in my
CovidDataAssistant class, the processCommand() method is quite long, specifically the lines of code that execute when
the user presses the "c" button. Instead, to improve readability, I could take those few lines and make them into a new
method, which is then called when the user presses "c". This would also make the overall code look much cleaner, and
therefore easier to understand.