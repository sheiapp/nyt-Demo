# nyt Demo

In nyt Demo Project i have used MVVM Archtecture design pattern.

## I have used Nyt Times Most Popular API with API key as follows.
```bash
http://api.nytimes.com/svc/mostpopular/v2/mostviewed/all-sections/1.json?api-key=Zdie6yIOJDMopbnlAnmJ3Q45S2piAQkr
```
## Used Libraries
<li>navigation</li>
<li>viewmodel</li>
<li>livedata</li>
<li>new material design</li>
<li>koin DI and koin test</li>
<li>coroutines and coroutines test</li>
<li>Retrofit</li>
<li>gson converter</li>
<li>glide</li>
<li>android arch test</li>
<li>Junit 4</li>

## Work Flow of Project
when launch of app the koin DI will start and ListFragment will launch by navigation in MainActivity.
then ListFragment will call the method in viewmodel and observe the data from the view model, update the RecyclerView according to the data.
when user taps on a item in recyclerView will take to DetailFragment.the DetailFragment will observe the user clicked object


## How the code can be run?
run->Run'APP'.

## How the test can be run?
right click on package "com.demo.nytdemo(test)" ->  select Run 'Tests in'nytdemo".

## How the code coverage reports can be generated?
right click on package "com.demo.nytdemo(test)" -> select Run 'Tests in'nytdemo" with code coverage -> from there we can save the generated code coverage(i have already generated and placed inside project file directory)



