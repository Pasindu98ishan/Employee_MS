package com.example.Employee.MS.service;

import com.example.Employee.MS.dto.EmployeeDto;
import com.example.Employee.MS.entity.EmployeeEntity;
import com.example.Employee.MS.repo.EmployeeRepo;
import com.example.Employee.MS.util.Varlist;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private ModelMapper modelMapper;
    public String saveEmployee(EmployeeDto employeeDto){
        if(employeeRepo.existsById(employeeDto.getEmpID())){
            return Varlist.RSP_DUPLICATED;

        }else{
            employeeRepo.save(modelMapper.map(employeeDto, EmployeeEntity.class));
            return Varlist.RSP_success;
        }
    }
    public String updateEmployee(EmployeeDto employeeDto){
        if(employeeRepo.existsById(employeeDto.getEmpID())){
            employeeRepo.save(modelMapper.map(employeeDto,EmployeeEntity.class));
            return Varlist.RSP_success;
        }else{
            return Varlist.RSP_NO_DATA_FOUND;
        }
    }
    public List<EmployeeDto> getAllEmployee(){
        List<EmployeeEntity> employeelist=employeeRepo.findAll();
        return modelMapper.map(employeelist,new TypeToken<ArrayList<EmployeeDto>>(){

        }.getType());
    }
    public EmployeeDto searchEmployee(int empId){
        if(employeeRepo.existsById(empId)){
            EmployeeEntity employeeEntity=employeeRepo.findById(empId).orElse(null);
            return modelMapper.map(employeeEntity,EmployeeDto.class);
        }else{
            return null;
        }
    }
    public String deleteEmployee(int empID){
        if(employeeRepo.existsById(empID)){
            employeeRepo.deleteById(empID);
            return Varlist.RSP_success;

        }else{
            return Varlist.RSP_NO_DATA_FOUND;
        }
    }

}
