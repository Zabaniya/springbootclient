package org.ypq.springboothelloworld;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableEurekaClient
@ActiveProfiles({"aliyun"})
public class SpringboothelloworldApplicationTests {

	@Autowired
	private Sender sender;

	@Autowired
	private LoadBalancerClient loadBalancerClient;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testSender() throws InterruptedException {
	    Random random = new Random();
		while(true) {
		    Thread.sleep(1000);
            if (random.nextInt(2) > 0 ) {
                sender.sendInfo();
            } else {
                sender.sendError();
            }
        }
	}

    @Test
    public void testEurekaClient() throws InterruptedException {
	    ServiceInstance si = loadBalancerClient.choose("eureka-provider");
	    StringBuffer sb = new StringBuffer("");
	    sb.append("http://").append(si.getHost()).append(":").append(si.getPort()).append("/products");
        RestTemplate rt = new RestTemplate();
        ParameterizedTypeReference<List<Product>> typeReference = new ParameterizedTypeReference<List<Product>>() {};
        ResponseEntity<List<Product>> responseEntity = rt.exchange(sb.toString(), HttpMethod.GET, null, typeReference);
        List<Product> plist = responseEntity.getBody();
        for (Product p : plist) {
            System.err.println(p.getId() + " " + p.getName());
        }
        Assert.assertEquals(1, plist.size());

    }

}
