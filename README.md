# vehicle-deviations-alert-system

## Enjoying fasttag service in india? 

###Imagine if government integrate fasttag to traffic department to collect fine for vehicle deviations like 
1) No parking 
2) overspeeding 
3) No seat belts etc 

## This is the demo app which reads the vehicle numbers standing in traffic signal and check if there are any penalties associated with it and deduct the fine from fasttag.

## Technologies used 
###Java 8, Spring Webflux, reactive mysql (r2dbc-mysql), AWS SQS and SNS. 

## How to use this app
1) VehicleController
   1) addVehicle: Add dummy vehicle to db with basic vehicle info
   2) getVehicle: Get vehicle from DB by vehicleNumber
2) ViolationController
   1) addViolations: You can add the violations to the vehicle
   2) getViolations: You can get the violations by vehicle number
   3) notifyViolations: This captures the vehicle number standing in signal _**asynchronously**_ and checks violations and then notifies traffic police about violations and associated fine with it.
3) GovtController
   1) getViolations: used to get violations by adhar number of owner.
   2) deductFastTagBalance: Deduct the fasttag balance.

# Flow chat
![Vehicle deviation alert system flowchat drawio](https://user-images.githubusercontent.com/37335021/148686556-91eeb4e6-f196-4ffa-a284-e4d4ac3f38b0.png)


# aws ci-cd used
![image](https://user-images.githubusercontent.com/37335021/119383974-a6fa0a00-bce1-11eb-8446-fb2273ed0f26.png)

