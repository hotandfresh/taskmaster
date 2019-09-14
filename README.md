# Taskmaster

## Overview
This is a backend application that displays tasks, its id, title, description, and status.  The application utilizes a Dynamo db and is hosted on with AWS elastic beanstalk.

## How to Run The Application Locally

Clone the repo here and save it in a directory of your choice. Open the application with an editor of your choice and hit the green arrow to run it. Navigate to http://localhost:5000/api/v1/tasks
- If you don't have an editor, the application can be run from the root of the directory with the following terminal command: ```./gradlew run```

In terminal, go up a directory and go into a directory named ```loader```. Run the following commands:
- ```npm install```
- ```node load.js 10``` to generate 10 random tasks

Then, refresh ```http://localhost:5000/api/v1/tasks``` to see the tasks

## End Points

```/api/v1/tasks``` get request that displays a list of tasks, its id, title, description, and status
```/api/v1/tasks/{name}/tasks``` get request that displays tasks for an assignee
```/api/v1/tasks/{id}/state``` post request that will update the status of a status
```/api/v1/tasks/{id}/assign/{assignee}``` put request to update a task to an assignee
```/api/v1/tasks/{id}/images``` post request to upload an image for a task
```/api/v1/tasks/{id}``` get request to dispaly info for a task along with it's image url, if there is one

## Deployed
[demo](http://dev-env.ipm3pei5qu.us-west-2.elasticbeanstalk.com/api/v1/customers)
[taskmaster](http://taskmaster.6jwvzatvsi.us-west-2.elasticbeanstalk.com/api/v1/tasks)

## Acknowledgements
Thanks to [nhuuu](https://github.com/nhuuu), [rttgg](https://github.com/rttgg), [Bravelemming](https://github.com/Bravelemming)

## Resources
Steps were from this [documentation](https://github.com/codefellows/seattle-java-401d5/tree/master/class-26/lab)
