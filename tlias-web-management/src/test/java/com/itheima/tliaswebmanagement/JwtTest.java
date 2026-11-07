package com.itheima.tliaswebmanagement;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;

public class JwtTest {

    /**
     * 生成jwt令牌
     */
    @Test
    public void testGenerateJwt(){
        HashMap<String,Object> dataMap=new HashMap<>();
        dataMap.put("id","1");
        dataMap.put("username","admin");
        dataMap.put("password","123456");
        String Jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "aXRoZW1h")//秘钥是ithema经过Base64编码后的字符串
                .addClaims(dataMap)//添加自定义信息
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))//设置过期时间：当前时间+1个小时
                .compact();//构建令牌
        System.out.println(Jwt);
    }

    /**
     * 解析jwt令牌
     */
    @Test
    public void testParseJwt(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJwYXNzd29yZCI6IjEyMzQ1NiIsImlkIjoiMSIsInVzZXJuYW1lIjoiYWRtaW4iLCJleHAiOjE3OTQwMzkyMDF9.Grz4xcV7H1dW8vpcSLzGt_asQIc3ZTzLLFGuIngBd5U";
        Claims claims =Jwts.parser()
                .setSigningKey("aXRoZW1h")//指定秘钥
                .parseClaimsJws(token)//解析令牌
                .getBody();//过去自定义信息
        System.out.println(claims);
    }
}
