package zzyzzy.spring.massai_mara_park;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// 테스트는 메모리 DB를 사용하므로 실습 DB 파일이나 외부 DB가 없어도 동작합니다.
@SpringBootTest(properties = "spring.datasource.url=jdbc:h2:mem:test")
@AutoConfigureMockMvc
class AnimalControllerTests {

    @Autowired
    private MockMvc mvc;

    @Test
    void listReturnsAllTwelveAnimals() throws Exception {
        mvc.perform(get("/api/animals"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(11)));
    }

    @Test
    void viewCountIncreasesByOne() throws Exception {
        mvc.perform(post("/animals/1/view"))
                .andExpect(status().is3xxRedirection());

        mvc.perform(get("/api/animals/1"))
                .andExpect(jsonPath("$.viewCount").value(1));
    }
}
