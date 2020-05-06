package redis;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * @author qiaoyihan
 * @date 2020/4/16
 */
public class BasicDataTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);

        jedis.zadd("bird-78", 9.0, "java");
        //jedis.zadd("bird-5", 8.0, "cpp");


        Set<String> s =  jedis.zrange("bird-78", 0, -1);

        System.out.println(s);
    }


}
