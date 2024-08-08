package com.GradSkool.usermanagement.Exceptions;

public class NotFoundException extends RuntimeException{

   public NotFoundException(){
        super("Data You are Looking For Is Not Available or Removed !");
    }

}
