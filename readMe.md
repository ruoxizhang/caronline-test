## Design

The application is mainly based on guava cache and in memory H2 database.  The post long url request would first create a short
url code, which will the partial path when user query it. The generated shout url code is first save in cache and using a 
executor service to save it into database. There is a counter for make sure generate short url code to be unique and use Base62
as the method to encode it.

### Cache: 
Using guava as has experience with it also it has a decent performance. Here are some benchmarking on it.
[1](https://cruftex.net/2016/03/16/Java-Caching-Benchmarks-2016-Part-1.html) 
[2](https://cruftex.net/2016/05/09/Java-Caching-Benchmarks-2016-Part-2.html) 
[3](https://cruftex.net/2017/09/01/Java-Caching-Benchmarks-Part-3.html) guava could maintain a decent performance even
the size of cache goes up to 1M. So we could considering increase the size a bit if needed.


## Run

### From code

Go into source folder and run
````
mvn spring-boot:run -f pom.xml
````

### From target
````
java -jar {target file}
````

### Way of using it
do a post request to the following address when it is running in local 
````
localhost:8080/shorturl
````
The data should be attached as a payload to http request in following format
````
{"longUrl":"www.bbc.com"}
````
this will return a Url that could be used, just hit the browser with the returned url
you will be redirected to the original website.