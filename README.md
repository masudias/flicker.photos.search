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

## Improvement scope and trade-offs of technical choices
There are several improvement scope of the application that I have developed. I am listing them below. 
* The application is designed to support the portrait mode only. It can be done for landscape view as well which will provide a better experience to the user when using the application from a tablet. In that case, we need a separate layout for the landscape mode and might have to load different fragment to position the UI elements differently. 
* I could have improved the caching mechanism if I could have some more time. While caching a large number of images we need to deal with the syncing problem as well. Right now, I am deleting the exisiting data and repopulating the cache again each time I fetch new data. This can be improved through digging into the photo ids which are provided from Flickr APIs. The photo ids are unique but does not maintain a incremental or decremental sequence. However, I think there should be a way to sync the data more efficiently. 
* I am saving the API key in a constant file for convenience right now. However, I think the secret keys like this should be kept in environment which will be fetched during building the application through gradle. 
* The caching mechanism is very simple. However, I think if there were multiple image sources from where the data could be fetched from, I could learn and could have thought a better structure of the generalized format and better implementation of the overall caching mechanism. 

## Development documentation 
The entry point of the application is `FlickrDashboardActivity` which simply launches a fragment named `PhotoListFragment`. The `PhotoListFragment` handles querying the data from server and managing the overall caching mechanism. I choose to have a `Fragment` here to keep the overall implementation more scalable. 

The `PhotoListFragment` does the heavy work. It has a simple `RecyclerView` in it, which displays the data fetched from server. However, as I have implemented a caching mechanism I had to manage the overall data display mechanism a bit differently. I am providing a flowchart here to describe the overall process flow.

![alt text](https://github.com/masudias/flickr.photos.search/blob/master/Screen%20Shot%202018-05-20%20at%207.37.43%20PM.png "Flowchart")

For retriving information from cache I am using `LoaderManager` along with `LoaderCallback` functions which efficiently get the images stored from local sqlite database and serves the data to the calling `PhotoListFragment`. Once the data loading from cache is finished, it is immediately updated in the `RecyclerView` and if the application is online, I call the API to get photo list using Flickr API. Once the list is arrived from server, the images are smoothly replaced with the new images that we have got from calling the API. 

The `ImageView` which renders the images through Glide, is made to adjust its view bounds, so that the images gets the proper aspect ratio and can be seen in full version when the images are loaded completely. I could achieve some performance improvement by setting a fixed height and width for the `ImageView` along with setting `RecyclerView.hasFixedSize(true)`. However, I thought that, the main focus of this application is to view photos, and it will be better to see the photos in full compromising a bit with the performance from UX point of view. 

I have used `DatabaseOpenHelper` and `DataHelper` class along with some other utility provider classes to handle the database operations. The classes under `domain` package are needed to define the POJOs for database and the http requests. On the other hand, the `network` package includes the http request and response parser along with other uri builder, response listener interface and other necessary utilities.

## Keeping scalibility in mind
Scalibility was a major concern throughout the application development process and I tried to keep things scalable for fetching images from different servers. In this application, there are two places where I concentrated most in this regard. 
* Building a network layer so that its easier for me to adopt the changes of the other image sources. Definitely, we have to write a different parser along with building a differnt mechanism for API calling from each different server. However, I tried to follow the factory pattern here so that the network calling process can be generalized and can be integrated with the current application without much hassle. 
* Caching the images in a generalized format so that images from any source can be cached. However, the I took only the attributes which are mandatory for displaying in our application and most likely to have in the response body when I am pulling images for different soources. 

I am trying to describe them both in the following sub-sections. 

### Getting images from different sources
I have created a factory class named `ImageProviderFactory` which will be used to get the appropriate image provider based on the source type defined in `PhotoHttpResponse` class. The `Fragment` displaying the images will call the function defined in the `ImageProviderFactory` with the source specified and the `getImagesFromExternalSource` function will choose to call the API which is defined there based on that source type. I have preapred a graphical representation of the overall architecture of fetching data from different servers. 

![alt text](https://github.com/masudias/flickr.photos.search/blob/master/get-images-from-different-server.png "Get images from different servers")

I have prepared an interface as a listener for receiving response from the server API call. The listen takes a list of `Photo` as its parameter. That ensures, different list of images response from different sources should be converted to `Photo` type before passing it to the `PhotoListFragment`. 

### Caching images from different sources
As per the graphical representation above, I am saving the response got from each different servers in a generalized format to be served to the photo list when the application is offline. I have come up with a `Photo.Builder` to enforce building the `Photo` object with the parameters that require. There is an interface as well named `PhotoMaker` which is implemented by the `FlickrPhoto` class, keeping in mind that each source can have a differnt image url building mechanism. As a result we need to have some functions overriden by each photo domain class. For example, `FlickrPhoto` class has to overridde the `getPhotoUrl` and `getOwnerPhotoUrl` functions to provide the photo url that has to be saved in the database or to be provided to the `RecyclerView` to display the images.
