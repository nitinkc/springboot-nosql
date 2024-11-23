package learn.nosql.app;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CacheService {
  private final RedisTemplate<String, User> redisTemplate;

  @Cacheable(value = "users", key = "#email")
  public User getUserFromCache(String email) {
    return redisTemplate.opsForValue().get(email);
  }

  // Example methods using Redis
  public void saveUser(String key, User user) {
    redisTemplate.opsForValue().set(key, user);
  }

  public User getUser(String key) {
    return redisTemplate.opsForValue().get(key);
  }
}
