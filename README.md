# Simple Rewards Application

Code challenge made for Charter / Spectrum.

## To Start

Run the following docker commands:

```
docker build --tag rewards https://raw.githubusercontent.com/Tubaleviao/spectrum-rewards/main/Dockerfile
docker run --name rewards -p 8080:8080 -d --rm rewards
```

The application should automatically run on your port 8080 and you will be able to access through the following url:

``` http://localhost:8080/ ```

To test the application, you can call the following URLs:

```
http://localhost:8080/api/transactions
http://localhost:8080/api/rewards
http://localhost:8080/api/users/1
```

## Data

Each reward has the following structure: ``` {"username":"Alvaro","months":[0,52,1]} ```

Where each array element from the attribute "months" is the total rewards points for each month in order. So if this month is April, it would show results for February, March and April, respectively.

## Wrapping up

Make sure to stope the container and remove the image (might save you about 300MB of space!):

```
docker stop rewards
docker rmi rewards
```

Thank you for your time!
