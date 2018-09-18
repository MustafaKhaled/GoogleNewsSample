# Google News
>This project showing news(e.g. Google News) using API and displaying these news in such a great manner (Approach). 

# Prerequisites
- Android Studio - Latest Versions is recommeneded.
- Have a good background about RecyclerView.
- Concept of Pagination in RecyclerView. You can check this [Link](https://blog.iamsuleiman.com/android-pagination-tutorial-getting-started-recyclerview/) for more information.
- You previously worked with Okhttp Library - Retrofit.

# Getting Started
#### As we mentioed in project title section project title, we are going to use API in this Repo. So we are going to use https://newsapi.org/v2/everything. 
#### To use this API peerfectly, we should generate **API Key** by visiting this [News Api Website](https://newsapi.org/).
#### After registration we would get your API KEY (e.g. 5ece30fa8d2f4bf48b0daa54ee49c983).

# Features
- Infinite Scrolling in News using Pagination.
- Browse all News by clicking on single item(Article).
- Add Article to you favorite List.

#### RecyclerView for a list of news(article) with pagination 


# Implementation
#### `First`, as we perviously mentioed, we are going to use RecyclerView with Retrofit Library, so you should add its libraries.
    implementation 'com.squareup.retrofit2:retrofit:2.0.2'
    implementation 'com.squareup.retrofit2:converter-gson:2.0.2'
    implementation 'com.squareup.okhttp3:logging-interceptor:3.8.0'
    implementation 'com.android.support:recyclerview-v7:27.1.1'
#### `Second`, create you user interface, by creating XML file for the `activity_main.xml` which would contain the recyclerView. Also create your `article_list_item.xml` that shows how single item list would be like?.
#### I strongly recommend using ConstraintsLayouts :+1:

#### `Third` you are going to create your model classes that contains the logic of you application. We have 3 model classes 
1. `ArticalModel.java` Describes what single article includes.
2. `NewsResponseMode.java` Used for Retroifit response mapping.
3. `SourceModel.java` Used to store srouce of each article.

#### `Forth`Create your Retrofit Folder where you should configure your `RetrofitClient.java` and `HeaderInterceptor.java` where we would pass the Api Key generated before. 

#### `Fifth` Create your Adapters. We have 2 Activities with 2 RecyclerViews, one cor articles from API and the other is for the `favorite article` added by user.

#### `Sixth` working with `MainActivity.java`, where you request article list from the endpoint and mapping the result through retrofit created. 
#### The result from the network call would be so long, so it's not logical to show all data in just one time. So here is the reason we use `Pagination` concept, in order to load data by pages. 

#### We should also create `Favorite.java` which contains articles you like and you save it as Favorite :heart:. in this case we used SharedPerefernces to store user data.

# Excutable APK
[Here](https://goo.gl/1oJdhe) you can find the executable APK.

