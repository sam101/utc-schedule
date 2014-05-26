utc-schedule
============

This repository, initially developed for a IA04 UTC project, contains some java stuff to retrieve UTC's schedules as JSON format and get some nice data about the rooms / UVs . It uses Jackson for the JSON processing and the raw HTTP classes from the JDK to do the HTTP requests (which are a pain in the ass).

You will retrieve:

* The raw "'EDT" files
* The room schedules / the UVs that take place in these rooms
* Some data about the UVs (number of students enrolled, their semesters, ...)
* The list of semesters people are in 
* The user schedules

You'll need a valid UTC CAS account to retrieve this data obviously.

To use it, you can use the main mehod in com.slepetit.utc.schedule.Schedule

Enjoy and feel free to fork.