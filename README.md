# airLine

airLine is an application that uses real-time data from Philadelphia International Airport (PHL) and historical data from the Transportation
Security Administration (TSA) and Customs and Border Patrol to provide a traveller with a better idea of how long they'll need to budget
at the airport to make their flight in time.

![Alt text](https://github.com/cit-upenn/cit-591-projects-fall-2016-airport/blob/f6657f3ff76abe5845fc7fafd6a53bc66418b497/app_screenshot.png)

airLine is our solution to the problem of deciding when to arrive for a flight. Using the application, travellers can check live security
wait times, their expected flight delay times and parking availability at the airport, using this information to plan their days and their
arrivals at the airport. For passengers arriving internationally, there is also a feature that predicts the expected customs check wait
times at PHL, which is useful for scheduling pick-ups or catching public transportation. 

airLine's ultimate purpose is to reduce the stress associated with travelling; using our predictive algorithm and live updates, traveller's
have a better idea of how long they'll be standing in lines and sitting in terminals, eliminating the danger of arriving too late and missing
flights or connections farther along in a trip.

#Design

This application uses TSA security APIs, along with data migrated into an AWS ORCL database. The GUI is built on JavaFX. AWS credentials
have been removed from the source code for security purposes, so please contact us if you'd like a demo. Consult the User Manual for better
visuals on the program.
