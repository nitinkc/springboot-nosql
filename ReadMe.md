
# Start MongoDb
```bash
docker pull mongodb/mongodb-community-server:latest
docker run --name mongodb -p 27017:27017 -d mongodb/mongodb-community-server:latest
```
## Check MongoDb Compass
Connection string : uri
```bash
mongodb://localhost:27017/my-mongo-db
```

# Start Redis
```bash
docker run -d --name redis-stack -p 6379:6379 -p 8001:8001 redis/redis-stack:latest
```

### Check Redis
[http://localhost:8001/redis-stack/browser](http://localhost:8001/redis-stack/browser)


# API

```bash
curl --location 'localhost:8080/users/alice@example.com'
curl --location 'http://localhost:8080/users?name=Charlie'
```