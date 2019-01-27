# HP-Docker-Capstone

Start Docker Beanstalk with the following command
docker run -d -p 11300:11300 schickling/beanstalkd

Client Library for beanstalkd Java
https://github.com/jpeffer/JavaBeanstalkClient

Build with 
./gradlew build

Built file found in /build/libs
run the *-all* file with
java -jar <filename>

Make sure the beanstalk connection in .groovy is connected to the same port the Docker container is running on
