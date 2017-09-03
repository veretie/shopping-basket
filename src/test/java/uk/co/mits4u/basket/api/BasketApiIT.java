package uk.co.mits4u.basket.api;

import com.google.common.io.Files;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.mits4u.basket.Application;

import java.math.BigDecimal;
import java.net.URL;
import java.nio.charset.Charset;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BasketApiIT {

    private URL base;
    private TestRestTemplate template;

    @Value("${local.server.port}")
    private int port;

    @Before
    public void setUp() throws Exception {
        template = new TestRestTemplate();
        base = new URL("http://localhost:" + port + "/basket/calculatePrice");
    }

    @Test
    public void priceBasket() throws Exception {

        Resource expectedResponse = new ClassPathResource("data/input/happyPathInput.json");
        String input = Files.toString(expectedResponse.getFile(), Charset.defaultCharset());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(input, headers);

        ResponseEntity<PricingResult> response = template.postForEntity(base.toString(), request, PricingResult.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody().getTotalCost()).isEqualByComparingTo(new BigDecimal("31.80"));

    }

    @Test
    public void priceBasketWithEmptyRequest() throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>("!-non-json-!", headers);

        ResponseEntity<ExceptionData> response = template.postForEntity(base.toString(), request, ExceptionData.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        ExceptionData responseBody = response.getBody();
        Assertions.assertThat(responseBody.getErrorMessage()).contains("Could not parse input. Allowable values for item names are:");

    }

}