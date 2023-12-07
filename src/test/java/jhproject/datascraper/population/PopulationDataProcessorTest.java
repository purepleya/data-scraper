package jhproject.datascraper.population;

import jhproject.datascraper.TestContainerConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Import(TestContainerConfiguration.class)
@TestPropertySource(properties = "spring.jpa.hibernate.ddl-auto = none")
class PopulationDataProcessorTest {

    @Test
    @DisplayName("통합테스트")
    void run() {

//    TODO 통합테스트를 어떻게 할까...
        fail();
    }


}