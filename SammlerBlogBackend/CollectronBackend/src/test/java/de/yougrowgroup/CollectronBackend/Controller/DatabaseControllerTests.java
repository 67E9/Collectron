package de.yougrowgroup.CollectronBackend.Controller;

import de.yougrowgroup.CollectronBackend.Model.Collectible;
import de.yougrowgroup.CollectronBackend.Service.CollectibleService;
import de.yougrowgroup.CollectronBackend.Service.TypeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(DatabaseController.class)
class DatabaseControllerTests {

    @Autowired // Laut meinen Recherchen ist der Mvc ohne aw im Zusammenhang mit SpringBoot einsetzbar. Dazu habe ich den Constructor hinzugefügt.
    MockMvc mockMvc;
    //note: IntelliJ complains: "Could not autowire. No beans of 'MockMvc' type found", but the test seems to run fine. (Pascal)

    @MockBean
    private CollectibleService collectibleService;
    @MockBean
    TypeService typeService;



    /*note: the test below does not really make sense, because it tests jpa and not our custom logic
    * but illustrates how a mvc test works. We can use it a template for more sensible tests and delete it later on. */
    @Test
    public void findsCollectibleByIdTest() throws Exception{
        when(collectibleService.findCollectibleById(1)).thenReturn(new Collectible("test item"));

        this.mockMvc.perform(get("/api/collectible/findById/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("test item")));
    }

}

//TODO: Umschreiben zu einfachem JUNIT test ohne MockMVC? Methoden mit Custom Logic testen?
//TODO: additional test for filter requests with keyword!