package tfip.ssf.Workshop12.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import tfip.ssf.Workshop12.model.Generate;
import org.springframework.ui.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Iterator;


@Controller

//this tells the server wat to do...
public class GenerateController {
    private Logger logger = LoggerFactory.getLogger(GenerateController.class);

    //define the get mapping
    @GetMapping("/")
    //the model in this is a automatically generated spring boot model. When this ("/") GET is requested. A new class of generate will
    //be created. This will then 
    public String showGenerateForm(Model model){
        //calls the class generate
        Generate generate = new Generate();
        //this will then add the attribute generate into the model through the class generate 
        model.addAttribute("generate",generate);
        //this then activates the generates.html under resources/templates
        return "generate";
    }

    @PostMapping("/generate")
    //adding to the model.
    public String generateNumbers(@ModelAttribute Generate generate, Model model){
        logger.info("From the form " + generate.getNumberVal());
        int numberRandomNumbers = generate.getNumberVal();
        if (numberRandomNumbers > 10){
            //throw new RandomNumberException();
            model.addAttribute("errorMessage", "OMG exceed 10 already !");
            return "error";
        }
        String[] imgNumbers = {"1.jpg", "2.jpg","3.jpg","4.jpg","5.jpg","6.jpg","7.jpg","8.jpg","9.jpg","10.jpg"};
        List<String> selectedImg = new ArrayList();
        Random randNum = new Random();
        Set<Integer> uniqueGeneratedRandNumSet = new LinkedHashSet<Integer>();
        while(uniqueGeneratedRandNumSet.size() < numberRandomNumbers){
            Integer resultOfRandNumbers = randNum.nextInt(numberRandomNumbers+1);
            uniqueGeneratedRandNumSet.add(resultOfRandNumbers);
            logger.info("unique generated num set" + uniqueGeneratedRandNumSet);
        }

        Iterator<Integer> it = uniqueGeneratedRandNumSet.iterator();

        Integer currentElem = null;

        while(it.hasNext()){
            currentElem = it.next();
            logger.info("currentElem > " + currentElem);
            logger.info("currentElement int value to add"+currentElem.intValue());
            selectedImg.add(imgNumbers[currentElem.intValue()]);
            logger.info("image"+selectedImg);
        }
        model.addAttribute("randNumsResult", selectedImg.toArray());
        return "result";

    }
    


}
