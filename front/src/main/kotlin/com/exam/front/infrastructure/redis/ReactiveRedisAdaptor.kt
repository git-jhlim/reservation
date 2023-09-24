package com.exam.front.infrastructure.redis

import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.data.redis.core.ReactiveStringRedisTemplate
import org.springframework.stereotype.Component
import java.time.Duration

@Component
class ReactiveRedisAdaptor (private val redisTemplate: ReactiveStringRedisTemplate){
	
	suspend fun getIncrementValue(key: String, value: String): Long {
		return redisTemplate.opsForValue().setIfAbsent(key, value, Duration.ofSeconds(30))
			.flatMap {
				redisTemplate.opsForValue().increment(key)
			}.awaitSingle()
	}
}