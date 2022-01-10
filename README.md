# vehicle-deviations-alert-system

## Enjoying FASTag service in india? 

###Imagine government integrating FASTag with traffic department to collect penalty for vehicle deviations as below
1) No parking 
2) overspeeding 
3) No seat belts etc.

## This is a demo app which reads the vehicle numbers standing in any traffic signal and check if there are any penalties associated with it and deduct it from FASTag.

## Technologies used 
###Java 8, Spring Webflux, reactive mysql (r2dbc-mysql), AWS SQS and SNS. 

## How to use this app
1) VehicleController
   1) addVehicle: Add dummy vehicle to the db with basic vehicle info
   2) getVehicle: Get vehicle from the DB by vehicleNumber
2) ViolationController
   1) addViolations: You can add violations to the vehicle
   2) getViolations: You can get violations by the vehicle number
   3) notifyViolations: This captures the vehicle number standing in signal _**asynchronously**_ and checks for violations and then notifies traffic police about violations and associated penalties with it.
3) GovtController
   1) getViolations: used to get violations by Aadhar number of the owner.
   2) deductFASTagBalance: Deduct the FASTag balance.

# Flow chart
![Vehicle deviation alert system flowchart diagram](https://user-images.githubusercontent.com/37335021/148686556-91eeb4e6-f196-4ffa-a284-e4d4ac3f38b0.png)


# aws ci-cd used
![image](https://user-images.githubusercontent.com/37335021/119383974-a6fa0a00-bce1-11eb-8446-fb2273ed0f26.png)

