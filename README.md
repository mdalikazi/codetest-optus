# codetest-optus

### Summary ###

* __What was my apporach?__
    - Architecture  
    I have used Google's MVVM architecture with Jetpack. For background work I have used Kotlin Coroutines. For caching data I have used Room database.
    - UI  
    I have adhered to UI requirements as best as possible with my own interpretation. I have used fragments instead of activities as it made more sense to me.
    I have tested the UI on tablets and landscape modes and found no issues.

* __What 3rd party libraries did I use and why?__
    - Retrofit (https://github.com/square/retrofit)  
    I used Retrofit mainly for its integration of Coroutines. It is easy to setup and use coroutines out of the box, instead of setting up coroutines to work with Callback in other network libraries.
    
    - Stetho (https://github.com/facebook/stetho)  
    Stetho is useful for debugging Room database. You can inspect the database in Chrome browser via chrome://inspect.
    
    - Glide (https://github.com/bumptech/glide)
    One of the better performing image libraries. Supports lazy loading out of the box. Larger than Picasso, but faster.
    
* __Tests__
    - I have written one instrumented test and two unit tests.   
        - In case of instrumented test, it checks the opening of fragments and recycler view contents. Since this app is very simple I did want to complicate the UI test with more testing.
        - One unit test checks the database tables with mock data. This I believe is one of the most important things to test because everything relies on database working as expected.
        - The second unit test checks TypeConverters operation. This is also one of the most important things to write tests for.
        - I would have liked to add some tests for ViewModel and Repository however in this app I found that those tests would check what the Database unit test already checks.
    
* __Any known issues?__
    - I have not noticed any UI or functional issues. However, the image API sometimes fails and in that case I show a error icon. 
    - I have not come across any crashes. 

### Repo owner ###

Ali Kazi   
[LinkedIn](linkedin.com/in/mdalikazi)  
