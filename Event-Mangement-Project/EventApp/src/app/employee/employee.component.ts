import { Component, OnInit } from '@angular/core';
import { FormBuilder,FormGroup } from '@angular/forms';
import { EmployeeModel } from './employee.model';
import { ApiService } from '../shared/api.service';


@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {
 
  formValue !: FormGroup;
  employeeModelobj: EmployeeModel=new EmployeeModel();
  employeeData !: any ;
  showAdd !:boolean;
  showUpdate !:boolean;
 


  constructor( private formbuilber:FormBuilder,
    private api: ApiService) { }

  ngOnInit(): void {
    this.getAllEmployee();
    this.formValue=this.formbuilber.group({
      fname:[''],
      lname:[''],
      email:['']

    })
  }
  clickAdd(){
    this.formValue.reset();
    this.showAdd=true;
    this.showUpdate=false;
  }
  postEmployeeDetails(){
    this.employeeModelobj.fname=this.formValue.value.fname;
    this.employeeModelobj.lname=this.formValue.value.lname;
    this.employeeModelobj.email=this.formValue.value.email;

    this.api.postEmployee(this.employeeModelobj)
    .subscribe(res=>{
  alert("Employee Added Succesfully")
  this.formValue.reset();
  this.getAllEmployee();

let refo=document.getElementById("cancel");
refo?.click();

 },
  err=>{
    alert("Something went wrong!!")

  })
     
  }
  
  getAllEmployee(){
    this.api.getEmployee()
    .subscribe(res=>{
   this.employeeData=res;
  })

  }
  deleteEmployee(v:any){
    this.api.deleteEmployee(v.id)
    .subscribe(res=>{
   alert("Employee Deleted !!")
   this.getAllEmployee();
  })

  }
  editButton(v:any){
    this.employeeModelobj.id=v.id;
    this.formValue.controls['fname'].setValue(v.fname);
    this.formValue.controls['lname'].setValue(v.lname);
    this.formValue.controls['email'].setValue(v.email);
    this.showAdd=false;
    this.showUpdate=true;

}
updateEmployeeDetails(){
  this.employeeModelobj.fname=this.formValue.value.fname;
    this.employeeModelobj.lname=this.formValue.value.lname;
    this.employeeModelobj.email=this.formValue.value.email;

    this.api.updateEmployee(this.employeeModelobj,this.employeeModelobj.id)
    .subscribe(res=>{
  alert("Updated Succesfully ");
  this.formValue.reset();
  this.getAllEmployee();

let refo=document.getElementById("cancel");
refo?.click();
    })
}}



