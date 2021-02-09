package com.example.demo;


        import static org.junit.Assert.assertEquals;
        import static org.junit.Assert.assertNotNull;

        import javafx.application.Application;
        //import org.junit.Test;
        import org.junit.jupiter.api.Test;
        import org.junit.runner.RunWith;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.context.SpringBootTest;
        import org.springframework.boot.test.web.client.TestRestTemplate;
        import org.springframework.boot.web.server.LocalServerPort;
        import org.springframework.http.HttpEntity;
        import org.springframework.http.HttpHeaders;
        import org.springframework.http.HttpMethod;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.test.context.junit4.SpringRunner;
        import org.springframework.web.client.HttpClientErrorException;

        import com.example.demo.model.Correction;

        import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CorrectionControllerIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void contextLoads() {

    }

    @Test
    public void testGetAllCorrections() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/correction",
                HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
    }

    @Test
    public void testGetCorrectionById() {
        Correction correction = restTemplate.getForObject(getRootUrl() + "/correction/1", Correction.class);
        System.out.println(correction.getCourbure());
        assertNotNull(correction);
    }

    @Test
    public void testCreateCorrection() {
        LocalDate tmp= LocalDate.now() ;
        Correction Correction = new Correction();
        Correction.setCourbure(2);

        Correction.setCreateDate(tmp);
        ResponseEntity<Correction> postResponse = restTemplate.postForEntity(getRootUrl() + "/Corrections", Correction, Correction.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void testUpdateCorrection() {
        int id = 1;
        LocalDate tmp=  LocalDate.now() ;
        Correction correction = restTemplate.getForObject(getRootUrl() + "/correction/" + id, Correction.class);
        correction.setCourbure(3);

        correction.setCreateDate(tmp);
        restTemplate.put(getRootUrl() + "/correction/" + id, correction);
        Correction updatedCorrection = restTemplate.getForObject(getRootUrl() + "/correction/" + id, Correction.class);
        assertNotNull(updatedCorrection);
    }

    @Test
    public void testDeleteCorrection() {
        int id = 2;
        Correction correction = restTemplate.getForObject(getRootUrl() + "/correction/" + id, Correction.class);
        assertNotNull(correction);
        restTemplate.delete(getRootUrl() + "/correction/" + id);
        try {
            correction = restTemplate.getForObject(getRootUrl() + "/correction/" + id, Correction.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}