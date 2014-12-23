./gradlew clean build
docker build -t andutta/tangy-mongo-1.0 .
docker push andutta/tangy-mongo-1.0
