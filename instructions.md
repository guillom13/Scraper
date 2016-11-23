# Platform take-home interview exercise

The Platform team deals a lot with services that take commands from SQS
(a queuing service) and process them. This 3 hour exercise is meant to
simulate a simple service that works this way.

Use any resources you would normally when working, e.g. Google or Stack Overflow.
Use any libraries you feel are appropriate. Try to limit yourself
to around 3 hours spent on this. It may not be possible to implement all
the requirements below in that time, so you'll need to prioritize.

Make note at the end of anything you wanted to do, or would do differently,
but didn't have time for. Also list any assumptions you made where things
were ambiguous or unclear in the instructions.

## Problem
Build a service that receives URLs of HTML pages to scrape from a queue,
and scrapes these pages to disk. Additionally, find style, script, and images
referenced by the HTML and save them to disk. Write a metadata file that links
to all these resources.

## Details

A skeleton project has been provided.  It includes a stub, started with
`ScraperApp` that generates sample messages and passes them to a handler
that you'll implement. The source code for the stub is not provided, as it is
intended to simulate delivering messages via SQS.  The stub will pass messages
to the handler using a very small number of threads (likely one), similar to
how polling SQS for messages might work.

### Input
The service should handle JSON messages formatted like the following:
```JSON
{
  "id": "5b8447f6-d1c4-11e5-98be-00e01b6ff393",
  "type": "ScrapePageV1.0",
  "url": "http://some-domain.com/some-path"
}
```
The id will be unique per-message (though the same message may occasionally be
delivered twice), but the url may not be.

### Output
The output it saves to disk should be of the following format:
```JSON
{
  "id": "5b8447f6-d1c4-11e5-98be-00e01b6ff393",
  "url": "http://some-domain.com/some-path",
  "file": "5b8447f6-d1c4-11e5-98be-00e01b6ff393.html",
  "resources": [
    "jquery.js",
    "reset.css",
    "background.png"
  ]
}
```

The paths within "resources" should point to wherever the files are stored locally.
Use whatever directory structure you think is appropriate.

### Environment
This service will run on an Ubuntu EC2 instance on AWS. It will also need to run on
various Linux distros and OS X for developers workstations.

This service will be IO bound. Optimize accordingly.

### Further Notes

For simplicity of implementation, maintenance, and debugging, we want to avoid
async solutions. At the level of load it's expected to service, there's no need
for complex async code where a ThreadPool would do.

If you're having trouble getting this to build in your IDE, make sure any jar
files in the 'lib' directory are in your classpath. They will be included
automatically by the command line maven build.

### Enhancements

When you're ready, there are some optional enhancements that can be made:

* Consider how 301/302 redirects are handled.
* Try using ScraperQueueSender.Mode.REALISTIC instead of HAPPY_PATH. (In ScraperApp)
* Consider how the threading model works with the IO bound operations.