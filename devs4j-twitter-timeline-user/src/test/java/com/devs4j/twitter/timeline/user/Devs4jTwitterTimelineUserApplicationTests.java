package com.devs4j.twitter.timeline.user;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootTest
class Devs4jTwitterTimelineUserApplicationTests {

	private static final String AN_ID = "000000000000000000";

	/*
	@MockBean
	UserTimelineRepository mockRepository;
	
	@Autowired
	TimelineService timelineService;
	*/
	@Test
	void contextLoads() {
	}

	/*
	@Test
	void integrationTest() {
		UserTimelineEntity item = UserTimelineEntity.builder()
				.id(AN_ID)
				.build();

		
		try {
			UserTimelineEntity itemCacheMiss = timelineService.findById(AN_ID);
			UserTimelineEntity itemCacheHit = timelineService.findById(AN_ID);


			assertThat(itemCacheHit).isEqualTo(itemCacheMiss);

		} catch (AccountNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}*/
}
