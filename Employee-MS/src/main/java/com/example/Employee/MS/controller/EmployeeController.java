package com.example.Employee.MS.controller;

import com.example.Employee.MS.dto.EmployeeDto;
import com.example.Employee.MS.dto.ResponseDto;
import com.example.Employee.MS.service.EmployeeService;
import com.example.Employee.MS.util.Varlist;
import org.hibernate.jdbc.Expectation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ResponseDto responseDto;
    @PostMapping(value="/saveEmployee", consumes = "application/json")
    public ResponseEntity saveEmployee(@RequestBody EmployeeDto employeeDto){
        try {
            String res=employeeService.saveEmployee(employeeDto);
            if(res.equals("00")){
                responseDto.setContent(Varlist.RSP_success);
                responseDto.setMessage("Succesful");
                responseDto.setContent(employeeDto);
                return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
            }else if(res.equals("06")){
                responseDto.setContent(Varlist.RSP_DUPLICATED);
                responseDto.setMessage("Employee registered");
                responseDto.setContent(null);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);

            }else{
                responseDto.setContent(Varlist.RSP_FAIL);
                responseDto.setMessage("Error");
                responseDto.setContent(null);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception ex) {
            responseDto.setContent(Varlist.RSP_ERROR);
            responseDto.setMessage(ex.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);

        }


    }
    @PutMapping(value="/updateEmployee")
    public ResponseEntity updateEmployee(@RequestBody EmployeeDto employeeDto){
        try {
            String res=employeeService.updateEmployee(employeeDto);
            if(res.equals("00")){
                responseDto.setContent(Varlist.RSP_success);
                responseDto.setMessage("Succesful");
                responseDto.setContent(employeeDto);
                return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
            }else if(res.equals("01")){
                responseDto.setContent(Varlist.RSP_NO_DATA_FOUND);
                responseDto.setMessage("Not a registered employee");
                responseDto.setContent(null);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);

            }else{
                responseDto.setContent(Varlist.RSP_FAIL);
                responseDto.setMessage("Error");
                responseDto.setContent(null);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception ex) {
            responseDto.setContent(Varlist.RSP_ERROR);
            responseDto.setMessage(ex.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);

        }


    }
    @GetMapping(value="/getAllEmployee")
    public  ResponseEntity getAllEmployee(){
        try {
            List<EmployeeDto> employeeDtoList=employeeService.getAllEmployee();
            responseDto.setContent(Varlist.RSP_success);
            responseDto.setCode(Varlist.RSP_success);
            responseDto.setMessage("Succesful");
            responseDto.setContent(employeeDtoList);
            return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);

        }catch (Exception ex){
            responseDto.setContent(Varlist.RSP_ERROR);
            responseDto.setMessage(ex.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    @GetMapping(value="/searchEmployee/{empID}")
    public ResponseEntity searchEmployee(@PathVariable int empID){
        try {
            EmployeeDto employeeDto=employeeService.searchEmployee(empID);
            if(employeeDto!=null){
                responseDto.setContent(Varlist.RSP_success);
                responseDto.setMessage("Succesful");
                responseDto.setContent(employeeDto);
                return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);

            }else{
                responseDto.setContent(Varlist.RSP_FAIL);
                responseDto.setMessage("Error");
                responseDto.setContent(null);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception ex) {
            responseDto.setContent(Varlist.RSP_ERROR);
            responseDto.setMessage(ex.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }
    @DeleteMapping(value="/deleteEmployee/{empID}")
    public ResponseEntity deleteEmployee(@PathVariable int empID){
        try {
          String res=employeeService.deleteEmployee(empID);
            if(res.equals("00")){
                responseDto.setContent(Varlist.RSP_success);
                responseDto.setMessage("Succesful");
                responseDto.setContent(null);
                return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);

            }else{
                responseDto.setContent(Varlist.RSP_FAIL);
                responseDto.setMessage("No employee  available this employeeId");
                responseDto.setContent(null);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception ex) {
            responseDto.setContent(Varlist.RSP_ERROR);
            responseDto.setMessage(ex.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

}
