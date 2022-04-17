package ir.mci.appm_dashboard.controller;

import ir.mci.appm_dashboard.entity.AppmApiKey;
//import ir.mci.appm_dashboard.entity.sync.SyncRoot;
import ir.mci.appm_dashboard.entity.AppmMonitorList;
import ir.mci.appm_dashboard.repository.AppmApiKeyRepository;

import ir.mci.appm_dashboard.repository.AppmMonitorListRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.persistence.criteria.Root;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

@RestController
public class Appmanager {

    @Autowired
    AppmApiKeyRepository appmApiKeyRepository;

    //add appmanager
    @RequestMapping(value = "/add-appm", method = RequestMethod.POST)
    public void addAppm(@RequestBody String newAppmInfoString) throws IOException, ParseException, org.json.simple.parser.ParseException {

        System.out.println("INFO: NEW APPMANAGER INFO IS: "+newAppmInfoString);

        //parse newAppmInfo jason from request body
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(newAppmInfoString);

        String appmName = (String) json.get("appmName");
        String appmDesc = (String) json.get("appmDesc");
        String appmIp = (String) json.get("appmIp");
        String appmPort = (String) json.get("appmPort");
        String appmKey = (String) json.get("appmKey");
        String appmAction = (String) json.get("appmAction");


        //create new appm and save in database
        AppmApiKey appmApiKey = new AppmApiKey(appmName, appmDesc, appmIp, appmPort, appmKey, appmAction);
        appmApiKeyRepository.save(appmApiKey);

    }


    //delete appm
    @RequestMapping(value = "/del-appm/{id}", method = RequestMethod.GET)
    public void deleteAppm(@PathVariable Integer id) throws IOException, org.json.simple.parser.ParseException {

        appmApiKeyRepository.deleteById(id);

    }

    //execute action
    @RequestMapping(value = "/execute-action/{id}", method = RequestMethod.GET)
    public String appminfo(@PathVariable Integer id) throws IOException, org.json.simple.parser.ParseException {

        Optional<AppmApiKey> appmApiKey = appmApiKeyRepository.findById(id);


        String urlTestAction = "http://" + appmApiKey.get().getIpAddress() + ":" + appmApiKey.get().getPort() + "/AppManager/json/ExecuteAction?apikey=" + appmApiKey.get().getApiKey() + "&ActionId="+ appmApiKey.get().getTestActionId();
        System.out.println(urlTestAction);

        RestTemplate restTemplate = new RestTemplate();
//        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
//        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
//        messageConverters.add(converter);
//        restTemplate.setMessageConverters(messageConverters);
        String testActionResponse = restTemplate.getForObject(urlTestAction, String.class);

        return testActionResponse;
    }


    @Autowired
    AppmMonitorListRepository appmMonitorListRepository;

    //sync appmanager
    @RequestMapping(value = "/sync-appm/{id}", method = RequestMethod.GET)
    public String syncAppm(@PathVariable Integer id) throws org.json.simple.parser.ParseException {

        Optional<AppmApiKey> appmApiKey = appmApiKeyRepository.findById(id);
        String urlSyncAppm = "http://" + appmApiKey.get().getIpAddress() + ":" + appmApiKey.get().getPort() + "/AppManager/json/ListMonitor?apikey=" + appmApiKey.get().getApiKey() + "&type=all";
        String appmName = appmApiKey.get().getName();


        RestTemplate restTemplate = new RestTemplate();
        System.out.println(urlSyncAppm);

        String syncResponse = restTemplate.getForObject(urlSyncAppm, String.class);
        System.out.println(syncResponse);
        JSONParser parser = new JSONParser();

        JSONObject json = (JSONObject) parser.parse(syncResponse);
        JSONObject response = (JSONObject) json.get("response");
        JSONArray result = (JSONArray) response.get("result");

        String appmSyncResponse = (String) json.get("response-code");
        if(json.get("response-code").equals("4000")) {

            Date date1 = new Date();
            Optional<AppmApiKey> appmApiKey1 = appmApiKeyRepository.findById(id);
            appmApiKey1.get().setUpdateTime(date1);

            List<AppmMonitorList> x = appmMonitorListRepository.findAllByAppmId(id);
            x.forEach(appmMonitorList -> {
                appmMonitorListRepository.delete(appmMonitorList);
                    });
//            appmMonitorListRepository.deleteAllByAppmId(id);

            for (int i = 0; i < result.size(); i++) {
                JSONObject monitor = (JSONObject) result.get(i);

                String resource = (String) monitor.get("RESOURCEID");

                Date date = new Date();
                String monsGroup = (String) monitor.get("ASSOCIATEDGROUPS");


                if (monsGroup.contains(";")) {
                    String str = monsGroup;
                    String[] parts = str.split(";");
                    String strPart1 = parts[0];
                    String strPart2 = parts[1];
                    String[] group = strPart1.split(":");
                    String grpPart1 = group[0];
                    String grpPart2 = group[1];
                    if (monsGroup.equals("VirtualMachine")) {
                        monsGroup = "Infrastructure ESX";
                    } else {
                        monsGroup = grpPart2;
                    }
                } else {
                    monsGroup = "";
                }

                AppmMonitorList appmMonitorList = new AppmMonitorList(
                        (String) monitor.get("RESOURCEID"),
                        (String) monitor.get("RESOURCENAME"),
                        (String) monitor.get("DISPLAYNAME"),
                        monsGroup,
                        (String) monitor.get("DetailsPageURL"),
                        (String) monitor.get("TYPE"),
                        (String) monitor.get("TYPESHORTNAME"),
                        (String) monitor.get("HOSTNAME"),
                        (String) monitor.get("HOST"),
                        (String) monitor.get("HOSTIP"),
                        (String) monitor.get("HEALTHSTATUS"),
                        (String) monitor.get("HEALTHMESSAGE"),
                        (String) monitor.get("AVAILABILITYSTATUS"),
                        (String) monitor.get("AVAILABILITYMESSAGE"),
                        (String) monitor.get("POLL"),
                        id,
                        date,
                        (String) monitor.get("Managed")
                );

                appmMonitorListRepository.save(appmMonitorList);
//            System.out.println(resource);
            }

        }

//        json.g
//
//        JSONArray result = json.ge ("results");
//        JSONObject result1 =    .getJSONObject(0);
        System.out.println(json.get("response-code"));
        return appmSyncResponse;
//        System.out.println(json.get(""));
    }
//        JSONArray jsonArray = new JSONArray(json);
//        JSONObject jsons = new JSONObject();
//        json = loadJSONObject(syncResponse);
//
//        JSONArray values = jsonArray.getJSONArray("animals");
//
//        for (int i = 0; i < values.size(); i++) {
//
//            JSONObject animal = values.getJSONObject(i);
//
//            int id = animal.getInt("id");
//            String species = animal.getString("species");
//            String name = animal.getString("name");
//
//            println(id + ", " + species + ", " + name);
//        }
//    }



//        SyncRoot syncRoot = restTemplate.getForObject(urlSyncAppm, SyncRoot.class);
//        System.out.println(syncRoot.responseCode);
//        syncRootRepository.save(syncRoot);
//        syncResponseRepository.save(syncRoot.syncResponse);
//        syncResultRepository.saveAll(syncRoot.syncResponse.syncResult);

//    }

    //get appm sync time
    @RequestMapping(value = "/get-appm-sync-time/{id}", method = RequestMethod.GET)
    public String getAppmSyncTime(@PathVariable Integer id) throws IOException, org.json.simple.parser.ParseException {

        Optional<AppmApiKey> appmApiKey =appmApiKeyRepository.findById(id);
        String appmSyncTime = appmApiKey.get().getUpdateTime().toString();
        return appmSyncTime;
    }

}
