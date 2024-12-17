package com.cd.dto;

import com.cd.entity.Expense;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOutputDto {
    private int id;
    private String userName;
    private String password;
    private String email;
    private String phoneNumber;
}
