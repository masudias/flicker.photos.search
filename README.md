# Project Flickr (flickr.photos.search)
The purpose of this project is to use the API provided from Flickr by calling the `flickr.photos.search` method to get Photos. The photos are needed to be shown in a list. In my case I am showing them all in a `RecyclerView`. 

## Problem description
The image data we need to display should be fetched from the following endpoint.

https://api.flickr.com/services/rest/?api_key=949e98778755d1982f537d56236bbb42&method=flickr.photos.search

Each entry in the list structure should display: 
* Image
* Image size
* Image dimensions
* Title

More information regarding Flickr’s API can be accessed at  https://www.flickr.com/services/api/

The following should be implemented.
* A network layer that fetches image and applicable meta data from Flickr.
* A means of displaying this information in a list structure.

Scalability is paramount. The code you build should not end up being a bottleneck down the road (will this have to be rebuilt when it’s pulling from millions of images from many sources?).

## Solution
I have used the API specified above to get the images from Flickr using their search method with additional parameters. The additional parameters are briefly described as follows. 

https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=949e98778755d1982f537d56236bbb42&page=1&format=json&nojsoncallback=1&per_page=20&tags=public&extras=url_o

* page : To get paginated response from the server. The response of the API also includes total pages available in this search. 
* format : Defines the response format. There are other options available e.g. xml and Php Serial. 
* per_page : Defines the number of images to be provided in each API call. I have decided to get 20 images at a time. By default (when the per_page attribute is not set while calling the API), the method returns 100 images at a time. 
* tags : Defines the tags of the images which are to be fetched. I have used "public" tag to get all images which are tagged as public. 
* extras : This part is to decide which size of the image should be fetched. url_o indicates the original size of the image to be fetched. 

Calling the API above, reutrns a list of images which are then parsed and stored in an array to be shown in a `RecyclerView`. The API sometimes return invalid images (with no image url, height or width) which were filtered while showing the images in a list. Hence the overall UI representation of the application developed will look like [this](https://media.giphy.com/media/7SWzT6AjaiL8GEebLT/giphy.gif). 

Once the image list is fetched by calling the API, they are then parsed and being shown in the `RecyclerView`. I have enabled the offline capabilities, so that the latest images which are fetched are also saved locally to be viewed later when the application launches in offline mode. If the latest images are fetched, they are being cached also to make them available in offline mode. The images maintains the aspect ratio of their original size when they are being displayed in the application screen as well. I have used [Volley](https://developer.android.com/training/volley/) for network calls and [Glide](https://github.com/bumptech/glide) for loading images from url. I am going to describe the technical aspects of the overall solution in details in the later sections. 

## Reasoning behind technical choices
Mostly the reasoning behind the technical choices is the convenience. Volley is a library to make the http requests seamlessly without much hassle. I could use `HttpClient` for calling the APIs, however, in that case I had to take care a lot of things manually which is a lot easier to handle with Volley. Glide is widely used library for loading images specially in a list. It has several integrated function to make the image caching and loading a lot easier for developers to integrate. I used Glide to cache the images in offline mode if the images are loaded when the application was in online mode. I have used the native Sqlite for saving the information of the images to be stored locally to support the offline mode. 

I had another concern regarding the scalibility of the application. There was a requirement stated as, 

> The code you build should not end up being a bottleneck down the road (will this have to be rebuilt when it’s pulling from millions of images from many sources?).

I have designed the whole application keeping this concern in mind and adopted factory pattern in several cases to provide scalibility to the overall architecture. I will be discussing this in details in later sections. 

<!--## Improvement scope and trade-offs of technical choices-->


