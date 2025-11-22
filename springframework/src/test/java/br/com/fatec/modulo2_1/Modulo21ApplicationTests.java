package br.com.fatec.modulo2_1;

import br.com.fatec.modulo2_1.repository.UsuarioRepository;
import br.com.fatec.modulo2_1.repository.client.UsuarioRepositoryWithMongodb;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
@ActiveProfiles("test")
class Modulo21ApplicationTests {

    @MockitoBean
    private MongoTemplate mongoTemplate;

    @MockitoBean
    private UsuarioRepository userRepositoryInterface;

    @MockitoBean
    private UsuarioRepositoryWithMongodb userRepositoryWithMongodb;

    @MockitoBean
    private GridFsTemplate gridFsTemplate;

    @Mock
    private MongoConverter mongoConverter;

    @MockitoBean
    private LettuceConnectionFactory redisConnectionFactory;

    @MockitoBean
    private RedisCacheManager redisCacheManager;

	@Test
	void contextLoads() {
	}

}
