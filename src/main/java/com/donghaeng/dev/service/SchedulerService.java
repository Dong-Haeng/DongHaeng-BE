package com.donghaeng.dev.service;

import com.donghaeng.dev.domain.Scheduler;
import com.donghaeng.dev.repository.SchedulerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SchedulerService {

    private final SchedulerRepository schedulerRepository;

    private final String url = "https://api.everytime.kr/find/timetable/table/friend";

    public String fetchAndSaveTimetable(String identifier, boolean friendInfo) {
        String response = fetchTimetable(identifier, friendInfo);
        List<Scheduler> schedulers = parseSchedules(response);
        saveSchedules(schedulers);
        return response;
    }

    public String fetchTimetable(String identifier, boolean friendInfo) {
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("identifier", identifier);
        requestBody.add("friendInfo", String.valueOf(friendInfo));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("authority", "api.everytime.kr");
        headers.add("accept", "*/*");
        headers.add("accept-language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7");
        headers.add("origin", "https://everytime.kr");
        headers.add("referer", "https://everytime.kr/");
        headers.add("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"");
        headers.add("sec-ch-ua-mobile", "?0");
        headers.add("sec-ch-ua-platform", "\"macOS\"");
        headers.add("sec-fetch-dest", "empty");
        headers.add("sec-fetch-mode", "cors");
        headers.add("sec-fetch-site", "same-site");
        headers.add("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36");

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(url, requestEntity, String.class);

        return response;
    }

    private List<Scheduler> parseSchedules(String response) {
        List<Scheduler> schedules = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(response));
            Document doc = builder.parse(is);

            Element rootElement = doc.getDocumentElement();
            NodeList subjectNodes = rootElement.getElementsByTagName("subject");

            for (int i = 0; i < subjectNodes.getLength(); i++) {
                Element subjectElement = (Element) subjectNodes.item(i);
                String name = subjectElement.getAttribute("name");

                NodeList timeNodes = subjectElement.getElementsByTagName("time");
                for (int j = 0; j < timeNodes.getLength(); j++) {
                    Element timeElement = (Element) timeNodes.item(j);
                    String timeValue = timeElement.getAttribute("value");

                    NodeList dataNodes = timeElement.getElementsByTagName("data");
                    for (int k = 0; k < dataNodes.getLength(); k++) {
                        Element dataElement = (Element) dataNodes.item(k);
                        String day = dataElement.getAttribute("day");
                        String location = dataElement.getAttribute("location");

                        Scheduler scheduler = new Scheduler(name, day, timeValue, location);
                        schedules.add(scheduler);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return schedules;
    }

    private void saveSchedules(List<Scheduler> schedulers) {
        schedulerRepository.saveAll(schedulers);
    }
}