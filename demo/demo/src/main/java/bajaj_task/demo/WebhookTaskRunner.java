package bajaj_task.demo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WebhookTaskRunner implements CommandLineRunner {

    public void run(String... args) throws Exception {

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";

        String requestJson = "{ \"name\": \"John Doe\", \"regNo\": \"REG12347\", \"email\": \"john@example.com\" }";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());

        String webhookUrl = root.path("webhook").asText();
        String accessToken = root.path("accessToken").asText();

        // ----------- PLACE THE CODE BELOW HERE --------------

        String finalQuery = "SELECT e1.EMP_ID, e1.FIRST_NAME, e1.LAST_NAME, d.DEPARTMENT_NAME, "
                + "COUNT(e2.EMP_ID) AS YOUNGER_EMPLOYEES_COUNT "
                + "FROM EMPLOYEE e1 "
                + "JOIN DEPARTMENT d ON e1.DEPARTMENT = d.DEPARTMENT_ID "
                + "LEFT JOIN EMPLOYEE e2 ON e1.DEPARTMENT = e2.DEPARTMENT AND e2.DOB > e1.DOB "
                + "GROUP BY e1.EMP_ID, e1.FIRST_NAME, e1.LAST_NAME, d.DEPARTMENT_NAME "
                + "ORDER BY e1.EMP_ID DESC;";

        HttpHeaders solutionHeaders = new HttpHeaders();
        solutionHeaders.setContentType(MediaType.APPLICATION_JSON);
        solutionHeaders.setBearerAuth(accessToken);

        String solutionJson = "{ \"finalQuery\": \"" + finalQuery.replace("\"", "\\\"") + "\" }";
        HttpEntity<String> solutionEntity = new HttpEntity<>(solutionJson, solutionHeaders);

        ResponseEntity<String> solutionResponse = restTemplate.exchange(
                webhookUrl,
                HttpMethod.POST,
                solutionEntity,
                String.class
        );

        // Optional: Print response to console
        System.out.println(solutionResponse.getBody());
    }

}
