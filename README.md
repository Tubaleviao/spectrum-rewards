# Simple Rewards Application

To start: Import this project into Eclipse (Import > Maven > Existing Maven Projects) or into your favorite IDE.

Click on the Run icon to run the RewardsApplication.java

The application should automatically run on your port 8080 and you will be able to access through the following url:

``` http://localhost:8080/ ```

To test the application, you can call the following URLs:

```
http://localhost:8080/api/transactions
http://localhost:8080/api/users/Alvaro
http://localhost:8080/api/users/Ashley
```

## Data

The rewards response has the following structure: ``` {"username":"Alvaro","months":[0,52,1]} ```

Where each array element from the attribute "months" is the total rewards points for each month in order. So if this month is April, it would show results for February, March and April, respectively.

