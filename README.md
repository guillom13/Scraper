
# Scraper

This is a service designed to download and save (scrape) content
from the web.

See `instructions.md` for details about the coding exercise.

## Running the service in development

```
mvn compile exec:java
```

## Running the service in production

In production, the shaded jar file will be executed as a service by Tanuki.

Other than classpath oddities sometimes caused by Tanuki, this can be tested
by running `mvn package`, followed by:
```
java -jar target/plat-interview-1.0-SNAPSHOT-fat.jar
```