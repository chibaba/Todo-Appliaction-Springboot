# Todo-Appliaction-Springboot
working wth springboot security and actuators

Create a ToDo domain model that has the following fields and types:
id (String), description (String), completed (Boolean), created
(date with time), modified (date with time).
• Create a RESTful API that provides the basic CRUD (create, read,
update, delete) actions. Use the most common HTTP methods:
POST, PUT, PATCH, GET, and DELETE.

Create a repository that handles the state of multiple ToDo’s. For now,
an in-memory repository is enough.
• Add an error handler when there is a bad request or when submitting
a new ToDo doesn’t have the required fields. The only mandatory
field is the description.
• All the requests and responses should be in JSON format.
