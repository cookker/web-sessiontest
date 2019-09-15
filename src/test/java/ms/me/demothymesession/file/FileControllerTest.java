package ms.me.demothymesession.file;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FileControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void fileUploadTest() throws Exception {
        MockMultipartFile mockFile = new MockMultipartFile("file",
                "파일명.txt",
                "text/plain",
                "파일내용".getBytes());

        mockMvc.perform(multipart("/files").file(mockFile))
                .andDo(print())
                .andReturn();
    }
}