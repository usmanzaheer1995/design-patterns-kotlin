package strategy

interface CacheBehaviour<K, V> {
    fun put(key: K, value: V)
    fun get(key: K): V?
}

interface EvictionStrategy<K> {
    fun evict(cache: CacheBehaviour<K, *>)
}

class LRUEvictionStrategy<K> : EvictionStrategy<K> {
    override fun evict(cache: CacheBehaviour<K, *>) {
        println("I am an LRU Eviction Strategy")
    }
}

class FIFOEvictionStrategy<K> : EvictionStrategy<K> {
    override fun evict(cache: CacheBehaviour<K, *>) {
        println("I am a FIFO Eviction Strategy")
    }
}

class CacheWithEvictionStrategy<K, V>(
    private val evictionStrategy: EvictionStrategy<K>,
    private val maxSize: Int,
) : CacheBehaviour<K, V> {
    private val cacheMap = LinkedHashMap<K, V>(maxSize, 0.75f, true)

    override fun put(key: K, value: V) {
        if (cacheMap.size >= maxSize) {
            evictionStrategy.evict(this)
        }
        cacheMap[key] = value
    }

    override fun get(key: K): V? {
        return cacheMap[key]
    }
}

object CacheStrategy {
    fun init() {
        val lruCache = CacheWithEvictionStrategy<String, Int>(LRUEvictionStrategy(), maxSize = 3)
        lruCache.put("A", 1)
        lruCache.put("B", 2)
        lruCache.put("C", 3)
        println(lruCache.get("A")) // Moves "A" to the most recently used position

        val fifoCache = CacheWithEvictionStrategy<String, Int>(FIFOEvictionStrategy(), maxSize = 3)
        fifoCache.put("X", 10)
        fifoCache.put("Y", 20)
        fifoCache.put("Z", 30)
        println(fifoCache.get("X")) // No change in order because of FIFO strategy
    }
}
