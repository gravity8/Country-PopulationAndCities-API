package com.example.demo.model;

import com.example.demo.model.statesAndcities.SingleState;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class Question3Response {
    private HashMap<String, HashMap<String, List<String>>> States;

     public Question3Response(HashMap<String, HashMap<String, List<String>>> States){
         this.States = States;
     }
}
