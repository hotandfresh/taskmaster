# Taskmaster

## Overview
This is a backend application that displays tasks, its id, title, description, and status.  The application utilizes a noSQL db and is hosted on with AWS elastic beanstalk.  Then it was all moved to the cloud via lambda functions and hooked to AWS API Gateway

As of 9/26/19 an SnS system has been added to send text notifications.  The steps on creating that are described below.

## How to Run The Application Locally

Clone the repo here and save it in a directory of your choice. Open the application with an editor of your choice and hit the green arrow to run it. Navigate to http://localhost:5000/api/v1/tasks
- If you don't have an editor, the application can be run from the root of the directory with the following terminal command: ```./gradlew run```

The frontend application repo can be found [here](https://github.com/hotandfresh/taskmaster-frontend).

## How to Use This API

Open your API development tool, e.g. Postman, HTTPie, to make GET and POST requests use ```http://localhost:5000```, if running locally, or use ```taskmaster.6jwvzatvsi.us-west-2.elasticbeanstalk.com``` with the following endpoints:

## SNS Service
From the AWS Lambda console, create two tasks.  One is createSubscriber and the other is sendNotification.  Code used was found [here](https://github.com/codefellows/seattle-java-401d5/tree/master/class-38/demo/sns/lambda)

![lambda functions](/assets/lambdaDashboard.png)
![sns Dashboard](/assets/snsDashboard.png)
![sns Proof](/assets/snsProof.png)


From the AWS SNS console, create a topic called "TaskComplete".  All of the default options were used.  After that is completed, grab the ARN and replace the ARN found in the code linked above.

Head back to createSubscribers, create a test with the following JSON block:

```
{
    "phoneNumber":"+1areaCodeAndYourPhoneNumber"
}
```
Run that test.

Head to sendNotifications, and create the same test with the same information.  Run that test.  You should receive a message that says "Hey there!!!"

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
