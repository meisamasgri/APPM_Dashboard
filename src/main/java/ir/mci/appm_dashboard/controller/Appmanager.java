package ir.mci.appm_dashboard.controller;

import ir.mci.appm_dashboard.entity.AppmApiKey;
import ir.mci.appm_dashboard.repository.AppmApiKeyRepository;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;

@RestController
public class Appmanager {

    @Autowired
    AppmApiKeyRepository appmApiKeyRepository;

    //add appmanager
    @RequestMapping(value = "/add-appm", method = RequestMethod.POST)
    public Boolean addAppm(@RequestBody String newAppmInfoString) throws IOException, ParseException, org.json.simple.parser.ParseException {

        System.out.println("INFO: NEW APPMANAGER INFO IS: "+newAppmInfoString);

        //parse newAppmInfo jason from request body
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(newAppmInfoString);

        String appmId = (String) json.get("appmId");
        String appmName = (String) json.get("appmName");
        String appmDesc = (String) json.get("appmDesc");
        String appmIp = (String) json.get("appmIp");
        String appmPort = (String) json.get("appmPort");
        String appmKey = (String) json.get("appmKey");
        String appmAction = (String) json.get("appmAction");

        //convert string to integer for appmId
        Integer appmIdInt = Integer.parseInt(appmId);

        //create new appm and save in database
        AppmApiKey appmApiKey = new AppmApiKey(appmIdInt, appmName, appmDesc, appmIp, appmPort, appmKey, appmAction);
        appmApiKeyRepository.save(appmApiKey);

        return true;
    }


}
