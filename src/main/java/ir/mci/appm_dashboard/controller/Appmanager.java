package ir.mci.appm_dashboard.controller;

import ir.mci.appm_dashboard.entity.AppmApiKey;
import ir.mci.appm_dashboard.repository.AppmApiKeyRepository;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.Optional;

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

    //appm info
    @RequestMapping(value = "/appminfo/{id}", method = RequestMethod.GET)
    public Optional<AppmApiKey> appminfo(@PathVariable Integer id) throws IOException, org.json.simple.parser.ParseException {

        Optional<AppmApiKey> appmApiKey = appmApiKeyRepository.findById(id);
        return appmApiKey;
    }




}
