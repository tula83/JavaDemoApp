package com.example.demo.controller;

import com.example.demo.models.DemoModel;
import com.example.demo.repository.DemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DemoController {

    @Autowired
    private final DemoRepository demoRepository;

    public DemoController(DemoRepository demoRepository) {
        this.demoRepository = demoRepository;
    }

    public DemoRepository getDemoRepository() {
        return demoRepository;
    }




    @GetMapping("/getData")
    public ResponseEntity<Iterable<DemoModel>> find(){
        Iterable<DemoModel> data=demoRepository.findAll();
        return new ResponseEntity<>(data,HttpStatus.OK);
    }

    @GetMapping("/getData/{id}")
    public  ResponseEntity<Object> find(@PathVariable int id){
        Optional<DemoModel>data=demoRepository.findById(id);

        if(data.isPresent()){
           // DemoModel demo=data.get();
            return new ResponseEntity<>(data,HttpStatus.OK);
        }

        return new ResponseEntity<>("not found", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getData/name/{name}")
    public ResponseEntity<Object>findName(@PathVariable String name){
        Optional<DemoModel> data=demoRepository.findByName(name);
        if(data.isPresent()){
            return new ResponseEntity<>(data.get(),HttpStatus.OK);
        }

        return new ResponseEntity<>("not found ",HttpStatus.BAD_REQUEST);
    }



    @PostMapping("/insert")
    public ResponseEntity<String> insert(@RequestBody DemoModel data){

        try {
            System.out.println(data.getName());
            demoRepository.save(data);

        }
        catch(Exception  e){
            System.out.println("exception occurred"+e);
            return new ResponseEntity<String>("Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<String>("Data inserted successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<Object> delete(@PathVariable int id){
        Optional<DemoModel> data=demoRepository.findById(id);

        if(data.isPresent()){
            demoRepository.deleteById(id);
            return new ResponseEntity<>("deleted successfully",HttpStatus.OK);
        }

        return new ResponseEntity<>("id not found",HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/put")
    public ResponseEntity<Object> update(@RequestBody DemoModel demoModel){
        int  id  = demoModel.getId();

        if(demoRepository.findById(id).isPresent()){
            Optional<DemoModel> dataTemp=demoRepository.findById(id);
            DemoModel data=dataTemp.get();
            data.setName(demoModel.getName());
            demoRepository.save(data);
            return new ResponseEntity<>(data,HttpStatus.OK);
        }

        return new ResponseEntity<>("not found",HttpStatus.BAD_REQUEST);
    }



}
