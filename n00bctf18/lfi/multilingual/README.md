#Multilingual

Some good man made a service which provides a motivational article in different
language to the users. Users can also upload a language of their own. Some say good man hid the flag in a file named 'flag.txt'. Can you get the flag?

[Link to the index.php]

#Note to deployer
Dockerfile included

#SelfHelp
`docker build -t multilingual:latest .` //Inside the public directory
`docker run -d --name test1 -p 8080:80 multilingual:latest`